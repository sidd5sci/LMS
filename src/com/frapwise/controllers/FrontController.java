package com.frapwise.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frapwise.entities.Department;
import com.frapwise.entities.Leave;
import com.frapwise.entities.LeaveType;
import com.frapwise.entities.Session;
import com.frapwise.entities.User;
import com.frapwise.entities.UserLeaveMapper;
import com.frapwise.exceptions.DepartmentException;
import com.frapwise.exceptions.LeaveException;
import com.frapwise.exceptions.LeaveTypeException;
import com.frapwise.exceptions.UserException;
import com.frapwise.exceptions.UserLeaveMapperException;
import com.frapwise.models.DepartmentModel;
import com.frapwise.models.LeaveModel;
import com.frapwise.models.LeaveTypeModel;
import com.frapwise.models.SessionModel;
import com.frapwise.models.UserLeaveMapperModel;
import com.frapwise.models.UserModel;
import com.frapwise.routes.WebRoutes;
import com.frapwise.utils.AES_Cipher;
import com.frapwise.utils.RandomGenerator;
import com.frapwise.utils.Util;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.htm")
public class FrontController extends HttpServlet implements WebRoutes {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());

		try {
			this.process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.process(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void process(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String requestUrl = request.getRequestURI();

		ServletContext ctx = request.getServletContext();
		String path = ctx.getInitParameter("viewPath");
		String url = ctx.getInitParameter("url");
		System.out.println(path + " | " + requestUrl);

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		/**
		 * Redirect to home page
		 */
		if (requestUrl.endsWith("/")) {
			response.sendRedirect(HOME);
		}
		/**
		 *  Open the home page
		 */
		else if (requestUrl.endsWith(HOME)) {
			RequestDispatcher rd = request.getRequestDispatcher(path + "web/index.jsp");
			rd.forward(request, response);
		}
		/**
		 * Logout the user 
		 */
		else if (requestUrl.endsWith(LOGOUT)) {

			String ssid = (String) session.getAttribute("ssid");
			if (ssid != null) {
				session.invalidate(); // removes all the session attributes bound
				// removing the cookie
				Cookie cookie = new Cookie("ssid", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);

				request.setAttribute("message", "You have logged out successfully");

			} else {
				/* response.sendRedirect("/Frapwise/login"); */
			}
			response.sendRedirect(HOME);

		}
		/**
		 * Open the login page
		 */
		else if (requestUrl.endsWith(LOGIN)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				RequestDispatcher rd = request.getRequestDispatcher(path + "auth/login.jsp");
				rd.forward(request, response);
			} else {
				String role = (String) session.getAttribute("role");
				if (role.equals("Admin"))
					response.sendRedirect(url + ADMIN_DASHBOARD);
				else
					response.sendRedirect(url + EMPLOYEE_DASHBOARD);
			}
		} 
		/**
		 * Authenticate the user 
		 */
		else if (requestUrl.endsWith(AUHENTICATE)) {
			UserModel um = new UserModel();
			Cookie mcookie = new Cookie("message", "");
			mcookie.setMaxAge(10);
			User u = new User();
			u = null;
			try {
				u = um.Authenticate(request.getParameter("email"),
						AES_Cipher.getEncrypted(request.getParameter("password")));
			} catch (UserException e) {
				mcookie.setValue("Email_or_password_incorret");
			}
			if (u != null) {

				String ssid = RandomGenerator.getAlphaNumericString(16);
				session.setAttribute("ssid", ssid);
				session.setAttribute("uid", u.getId());
				session.setAttribute("email", u.getEmail());
				session.setAttribute("username", u.getUsername());
				session.setAttribute("name", u.getFname() + " " + u.getLname());
				session.setAttribute("role", u.getRole());
				System.out.println(ssid);
				// setting session id to cookie
				Cookie cookie = new Cookie("ssid", ssid);
				cookie.setMaxAge(5 * 60 * 60);
				response.addCookie(cookie);
				// setting session DB
				Session sess = new Session();
				sess.setSsid(ssid);
				sess.setUid(u.getId());
				sess.setPayload(session.toString());
				System.out.println(u.getId());
				SessionModel sessModel = new SessionModel();
				sessModel.setSession(sess);

				if (u.getRole().equals("Admin"))
					response.sendRedirect(url + ADMIN_DASHBOARD);
				else
					response.sendRedirect(url + EMPLOYEE_DASHBOARD);

			} else {

				response.addCookie(mcookie);

				RequestDispatcher rd = request.getRequestDispatcher(path + "auth/login.jsp");
				rd.forward(request, response);
			}

		} 
		/**
		 * Open the Admin Dashboard
		 */
		else if (requestUrl.endsWith(ADMIN_DASHBOARD)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);
				request.setAttribute("pageName", "home");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Open the admin Department add page 
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);
				request.setAttribute("pageName", "department-add");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Store the department in the database
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_DEPARTMENT_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				DepartmentModel dptModel = new DepartmentModel();
				Department dpt = new Department();
				dpt.setName(request.getParameter("departmentName"));
				dpt.setDescription(request.getParameter("departmentDesc"));

				Cookie cookie = new Cookie("message", "Department_Added_Successfully");
				cookie.setMaxAge(10);
				try {
					dptModel.add(dpt);
				} catch (DepartmentException e) {
					cookie.setValue("Department_already_exist");

				} catch (Exception e) {
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_ALL_DEPARTMENT);

			}
		} 
		/**
		 * Open all the list of the department
		 */
		else if (requestUrl.endsWith(ADMIN_ALL_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				DepartmentModel dptModel = new DepartmentModel();
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				request.setAttribute("pageName", "department-all");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Open the department edit form
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				DepartmentModel dptModel = new DepartmentModel();
				Department dpt = dptModel.getDepartmentById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("department", dpt);
				request.setAttribute("pageName", "department-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}
		} 
		/**
		 * Store the department updates in the databse
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_DEPARTMENT_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				Department dpt = new Department();
				dpt.setId(Integer.parseInt(request.getParameter("id")));
				dpt.setName(request.getParameter("departmentName"));
				dpt.setDescription(request.getParameter("departmentDesc"));

				Cookie cookie = new Cookie("message", "Department_Updated_Successfully");
				cookie.setMaxAge(10);
				int flag = 0;
				try {
					flag = (int) dptModel.update(dpt);
				} catch (DepartmentException e) {
					cookie.setValue("Some_error_occured");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);

			}
		} 
		/**
		 * Remove the department by department id
		 */
		else if (requestUrl.endsWith(ADMIN_REMOVE_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				Cookie cookie = new Cookie("message", "Department_Removed_Successfully");
				cookie.setMaxAge(10);
				int flag = 0;
				try {
					flag = (int) dptModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (DepartmentException e) {
					cookie.setValue("Some_error_occured");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_ALL_DEPARTMENT);

			}
		} 
		/**
		 * Opens the add leaveType form 
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				request.setAttribute("pageName", "leavetype-add");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}

		} 
		/**
		 * Store the leaveType in the databse 
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_LEAVETYPE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				LeaveTypeModel ltModel = new LeaveTypeModel();
				LeaveType lt = new LeaveType();

				lt.setName(request.getParameter("leaveTypeName"));
				lt.setDescription(request.getParameter("leaveTypeDesc"));

				Cookie cookie = new Cookie("message", "LeaveType_Added_Successfully");
				cookie.setMaxAge(10);

				try {
					ltModel.add(lt);
				} catch (LeaveTypeException e) {
					// TODO Auto-generated catch block
					cookie.setValue("LeaveType_already_exist");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured ");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_ALL_LEAVETYPE);

			}
		} 
		/**
		 * Opens the all the leaveType page
		 */
		else if (requestUrl.endsWith(ADMIN_ALL_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				LeaveTypeModel ltModel = new LeaveTypeModel();
				try {
					request.setAttribute("leavetypes", ltModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leavetype-all");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Opens the leavetype edit form
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				LeaveTypeModel ltModel = new LeaveTypeModel();
				LeaveType lt = ltModel.getLeaveTypeById(Integer.parseInt(request.getParameter("id")));
				request.setAttribute("leavetype", lt);
				request.setAttribute("pageName", "leavetype-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}
		} 
		/**
		 * Store the updated leaveType to databse
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_LEAVETYPE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				LeaveTypeModel ltModel = new LeaveTypeModel();
				LeaveType lt = new LeaveType();
				lt.setId(Integer.parseInt(request.getParameter("id")));
				lt.setName(request.getParameter("leaveTypeName"));
				lt.setDescription(request.getParameter("leaveTypeDesc"));

				Cookie cookie = new Cookie("message", "LeaveType_Updated_Successfully");
				cookie.setMaxAge(10);
				int flag = 0;
				try {
					flag = (int) ltModel.update(lt);
				} catch (LeaveTypeException e) {
					cookie.setValue("Some_error_occured");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_ALL_LEAVETYPE);

			}
		} 
		/**
		 * Remove the leaveTypeby id
		 */
		else if (requestUrl.endsWith(ADMIN_REMOVE_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				LeaveTypeModel ltModel = new LeaveTypeModel();
				Cookie cookie = new Cookie("message", "LeaveType_Removed_Successfully");
				cookie.setMaxAge(10);
				int flag = 0;
				try {
					flag = (int) ltModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (LeaveTypeException e) {
					cookie.setValue("Some_error_occured ");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured ");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);

			}
		} 
		/**
		 * Opens the employee add form
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				request.setAttribute("pageName", "employee-add");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}

		} 
		/**
		 * Store the employee to the database
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_EMPLOYEE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				UserModel userModel = new UserModel();
				User u = new User();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				LeaveTypeModel ltModel = new LeaveTypeModel();
				List<Object> leaveTypes = ltModel.getAll();
				int flag = 0;
				Cookie cookie = new Cookie("message", "Employee_added_successfully");
				cookie.setMaxAge(10);

				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setUsername(request.getParameter("username"));
				u.setHomeOffice(request.getParameter("homeOffice"));
				u.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				u.setEmail(request.getParameter("email"));
				u.setPassword(AES_Cipher.getEncrypted(request.getParameter("password")));
				u.setRole(request.getParameter("role"));
				u.setStatus(request.getParameter("status"));

				try {
					int id = (int) userModel.add(u);

					for (Object o : leaveTypes) {
						LeaveType lt = (LeaveType) o;
						UserLeaveMapper ulm = new UserLeaveMapper();
						ulm.setUid(id);
						ulm.setLeaveTypeId(lt.getId());
						ulm.setLeaveMax(Integer.parseInt(lt.getDescription()));
						ulm.setLeaveTaken(0);
						ulm.setLeaveAvailible(Integer.parseInt(lt.getDescription()));
						ulm.setTimeDuration(0);
						ulm.setLeaveFrom(new SimpleDateFormat("dd-M-yy").format(new java.util.Date()));
						ulm.setLeaveTo(new SimpleDateFormat("dd-M-yy").format(new java.util.Date()));
						ulmModel.add(ulm);
					}

				} catch (UserException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Employee_username_or_email_already_exist");
					flag = 1;
				} catch (Exception e) {
					e.printStackTrace();
				}

				response.addCookie(cookie);
				if (flag == 0)
					response.sendRedirect(ADMIN_ALL_EMPLOYEE);
				else
					response.sendRedirect(ADMIN_ADD_EMPLOYEE);

			}
		} 
		/**
		 * Opens the all employee list page
		 */
		else if (requestUrl.endsWith(ADMIN_ALL_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				UserModel userModel = new UserModel();
				try {
					request.setAttribute("users", userModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "employee-all");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Opens the edit employee form
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				UserModel userModel = new UserModel();
				User u = new User();
				try {
					u = userModel.getUserById(Integer.parseInt(request.getParameter("id")));
				} catch (NumberFormatException | UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				request.setAttribute("user", u);
				request.setAttribute("pageName", "employee-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}
		}
		/**
		 * Store the updated employee to the databse
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_EMPLOYEE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				UserModel userModel = new UserModel();
				User u = new User();
				Cookie cookie = new Cookie("message", "Employee_updated_successfully");
				cookie.setMaxAge(10);

				u.setId(Integer.parseInt(request.getParameter("id")));
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setUsername(request.getParameter("username"));
				u.setHomeOffice(request.getParameter("homeOffice"));
				u.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				u.setEmail(request.getParameter("email"));
				u.setPassword(AES_Cipher.getEncrypted(request.getParameter("password")));
				u.setRole(request.getParameter("role"));
				u.setStatus(request.getParameter("status"));

				int flag = 0;
				try {
					flag = (int) userModel.update(u);
				} catch (UserException e) {
					cookie.setValue("Some_error_occured");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);

			}
		} 
		/**
		 * Remove the employee from the database by id
		 */
		else if (requestUrl.endsWith(ADMIN_REMOVE_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				UserModel userModel = new UserModel();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				Cookie cookie = new Cookie("message", "Employee_removed_successfully");
				cookie.setMaxAge(10);
				int flag = 0;

				try {
					flag = (int) userModel.remove(Integer.parseInt(request.getParameter("id")));
					ulmModel.removeByUid(Integer.parseInt(request.getParameter("id")));

				} catch (UserException e) {
					cookie.setValue("Some_error_occured");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);

			}
		} 
		/**
		 * Opens the add leave form  
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());
				request.setAttribute("pageName", "leave-create");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}

		} 
		/**
		 * Store the leave to the dataabse 
		 */
		else if (requestUrl.endsWith(ADMIN_ADD_LEAVE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				LeaveModel leaveModel = new LeaveModel();
				Leave l = new Leave();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				Cookie cookie = new Cookie("message", "Employee_leave_applied_successfully");
				cookie.setMaxAge(10);

				l.setUserId(Integer.parseInt(request.getParameter("user")));
				l.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				l.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				l.setLeaveFrom(request.getParameter("from"));
				l.setLeaveTo(request.getParameter("to"));
				l.setTimeOffType(Integer.parseInt(request.getParameter("timeOffType")));

				int duration = (int) Util.getDaysNoWeekendsJquery(l.getLeaveFrom(), l.getLeaveTo());
				System.out.println(l.toString());

				try {
					Integer id = (Integer) leaveModel.add(l);
					if (id != null) {
						ulmModel.updateLeaveByUserAndLeaveType(Integer.parseInt(request.getParameter("user")),
								l.getLeaveTypeId(), duration, "+");
					}
				} catch (LeaveException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Some_error_occured");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}
				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);
			}
		}
		/**
		 * Oepns the all the leave list page
		 */
		else if (requestUrl.endsWith(ADMIN_ALL_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				LeaveModel leaveModel = new LeaveModel();
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());
				try {
					request.setAttribute("leaves", leaveModel.getAll());
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-all");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Opens the list of todays leave list page
		 */
		else if (requestUrl.endsWith(ADMIN_TODAYS_LEAVES)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				java.util.Date today = new java.util.Date();
				String todayDate = new SimpleDateFormat("yy-M-dd").format(today);
				LeaveModel leaveModel = new LeaveModel();
				Cookie cookie = new Cookie("message", "");
				cookie.setMaxAge(0);
				try {
					request.setAttribute("leaves", leaveModel.getLeavesToday(todayDate));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					cookie.setValue("Some error occured");
					cookie.setMaxAge(10);
					e.printStackTrace();
				}
				response.addCookie(cookie);
				request.setAttribute("pageName", "leave-list");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}
		} 
		/**
		 * Opens the assign max leaves page 
		 */
		else if (requestUrl.endsWith(ADMIN_ASSIGN_LEAVES)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());
				request.setAttribute("pageName", "leave-assign");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}
		} 
		/**
		 * Store the assigned leave to the database
		 */
		else if (requestUrl.endsWith(ADMIN_ASSIGN_LEAVES_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();
				Cookie cookie = new Cookie("message", "Leaves_assigned_successfully");
				cookie.setMaxAge(10);

				ulm.setUid(Integer.parseInt(request.getParameter("user")));
				ulm.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				ulm.setLeaveMax(Integer.parseInt(request.getParameter("max")));
				ulm.setLeaveFrom(request.getParameter("from"));
				ulm.setLeaveTo(request.getParameter("to"));
				ulm.setLeaveTaken(0);
				ulm.setLeaveAvailible(Integer.parseInt(request.getParameter("max")));
				ulm.setTimeDuration((int) Util.getDays(ulm.getLeaveFrom(), ulm.getLeaveTo()));

				try {
					ulmModel.add(ulm);
				} catch (UserLeaveMapperException e) {
					cookie.setValue("Some_error_occured");
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_DASHBOARD);
			}
		} 
		/**
		 * Opens the assigned max leave edit form
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_MAX_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				LeaveTypeModel ltModel = new LeaveTypeModel();

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();

				try {
					ulm = ulmModel.getLeavemapperByLeaveTypeAndUser(Integer.parseInt(request.getParameter("uid")),
							Integer.parseInt(request.getParameter("leaveType")));
					request.setAttribute("leavetypes", ltModel.getAll());
				} catch (Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("leavemap", ulm);
				request.setAttribute("pageName", "leave-map-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}
		} 
		/**
		 * Store the assigned updated leave max value to the database
		 */
		else if (requestUrl.endsWith(ADMIN_EDIT_MAX_LEAVE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();
				Cookie cookie = new Cookie("message", "Leave_map_updated_sucessfully");
				cookie.setMaxAge(10);

				ulm.setId(Integer.parseInt(request.getParameter("id")));
				ulm.setLeaveMax(Integer.parseInt(request.getParameter("max")));
				ulm.setLeaveFrom(request.getParameter("from"));
				ulm.setLeaveTo(request.getParameter("to"));
				System.out.println(ulm.toString());
				try {
					int flag = (int) ulmModel.setLeaveMaxById(ulm.getId(), ulm);
				} catch (Exception e) {
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_STATUS_LEAVE);

			}

		} 
		/**
		 * Approve the leave form admin
		 */
		else if (requestUrl.endsWith(ADMIN_LEAVE_APPROVAL)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				LeaveModel leaveModel = new LeaveModel();
				Cookie cookie = new Cookie("message", "Leave_aproved_successfully");
				cookie.setMaxAge(10);

				int flag = leaveModel.approve(Integer.parseInt(request.getParameter("id")));

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_ALL_LEAVE);
			}

		} 
		/**
		 * Rejects the leave form admin
		 */
		else if (requestUrl.endsWith(ADMIN_LEAVE_REJECT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				LeaveModel leaveModel = new LeaveModel();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				Leave leave = leaveModel.getLeaveById(Integer.parseInt(request.getParameter("id")));
				Cookie cookie = new Cookie("message", "Leaves_rejected_successfully");
				cookie.setMaxAge(10);

				int flag = leaveModel.reject(Integer.parseInt(request.getParameter("id")));
				if (flag == 1) {
					ulmModel.updateLeaveByUserAndLeaveType(leave.getUserId(), leave.getLeaveTypeId(),
							(int) Util.getDaysNoWeekends(leave.getLeaveFrom(), leave.getLeaveTo()), "-");
				
				
				}

				response.addCookie(cookie);
				response.sendRedirect(ADMIN_ALL_LEAVE);
			}

		} 
		/**
		 * Opens all the max leave status page
		 */
		else if (requestUrl.endsWith(ADMIN_STATUS_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				request.setAttribute("leaveMapper", ulmModel.getAll());
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());

				request.setAttribute("pageName", "leave-status");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
			}
		}
		/**
		 * Apply the filers
		 */
		else if (requestUrl.endsWith(ADMIN_APPLY_FILTERS)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll());

				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String status = request.getParameter("status");
				Integer uid = Integer.parseInt(request.getParameter("uid"));

				LeaveModel leaveModel = new LeaveModel();
				try {
					request.setAttribute("leaves", leaveModel.getLeaveByFilter(from, to, status, uid));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-all");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Opens the page for upload the excel file
		 */
		else if (requestUrl.endsWith(ADMIN_UPLOAD_FILE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				request.setAttribute("pageName", "leave-upload");
				RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
				rd.forward(request, response);
				
				Util.readerExcelHeader("report.xlsx");

			}
		}

//		EMPOYEE

		/**
		 * Open the aEmployee dashboard
		 */
		else if (requestUrl.endsWith(EMPLOYEE_DASHBOARD)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			// get the session data from database
			Session sess = new Session();
			SessionModel sessModel = new SessionModel();
			sess = sessModel.getSession(ssid);
			request.setAttribute("pageName", "home");
			RequestDispatcher rd = request.getRequestDispatcher(path + "employee/index.jsp");
			rd.forward(request, response);

		} 
		/**
		 * Opens the employee add leave page
		 */
		else if (requestUrl.endsWith(EMPLOYEE_ADD_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("user", userModel.getUserById((Integer) session.getAttribute("uid")));
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();

				request.setAttribute("pageName", "leave-create");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/index.jsp");
				rd.forward(request, response);
			}
		} 
		/**
		 * Store the leave to the database 
		 */
		else if (requestUrl.endsWith(EMPLOYEE_ADD_LEAVE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);
				int uid = (int) session.getAttribute("uid");

				LeaveModel leaveModel = new LeaveModel();
				Leave l = new Leave();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();
				Cookie cookie = new Cookie("message", "Leaves_applied_successfully");
				cookie.setMaxAge(10);

				l.setUserId(Integer.parseInt(request.getParameter("user")));
				l.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				l.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				l.setLeaveFrom(request.getParameter("from"));
				l.setLeaveTo(request.getParameter("to"));
				l.setTimeOffType(Integer.parseInt(request.getParameter("timeOffType")));

				/* int duration = Integer.parseInt(request.getParameter("duration")); */
				int duration = (int) Util.getDaysNoWeekendsJquery(l.getLeaveFrom(), l.getLeaveTo());

				System.out.println(l.toString());
				Integer id = 0;
				try {
					id = (Integer) leaveModel.add(l);
					if (id != null) {
						ulmModel.updateLeaveByUserAndLeaveType(uid, l.getLeaveTypeId(), duration, "+");
					}

				} catch (LeaveException e) {
					// TODO Auto-generated catch block
					cookie.setValue("Leave_data_is_incorrect");
				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				// response.sendRedirect(EMPLOYEE_DASHBOARD);
				out.println(id);

			}
		} 
		/**
		 * Opens all the leave leist page of the employeee
		 */
		else if (requestUrl.endsWith(EMPLOYEE_ALL_LEAVES)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				LeaveModel leaveModel = new LeaveModel();
				try {
					request.setAttribute("leaves", leaveModel.getLeavesByUser((Integer) session.getAttribute("uid")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-list");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Opens the edit leave page
		 */
		else if (requestUrl.endsWith(EMPLOYEE_EDIT_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				LeaveModel lModel = new LeaveModel();
				request.setAttribute("leave", lModel.getLeaveById(Integer.parseInt(request.getParameter("id"))));
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());
				UserModel userModel = new UserModel();
				request.setAttribute("user", userModel.getUserById((Integer) session.getAttribute("uid")));

				request.setAttribute("pageName", "leave-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/index.jsp");
				rd.forward(request, response);

			}

		} 
		/**
		 * Store the updated leave to the database
		 */
		else if (requestUrl.endsWith(EMPLOYEE_EDIT_LEAVE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);
				int uid = (int) session.getAttribute("uid");

				LeaveModel leaveModel = new LeaveModel();
				Leave pre = new Leave();
				Leave l = new Leave();

				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();

				l.setId(Integer.parseInt(request.getParameter("id")));
				l.setUserId(Integer.parseInt(request.getParameter("user")));
				l.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				l.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				l.setLeaveFrom(request.getParameter("from"));
				l.setLeaveTo(request.getParameter("to"));
				l.setTimeOffType(Integer.parseInt(request.getParameter("timeOffType")));
				/* int duration = Integer.parseInt(request.getParameter("duration")); */
				int duration = (int) Util.getDaysNoWeekends(l.getLeaveFrom(), l.getLeaveTo());

				pre = leaveModel.getLeaveById(l.getId());

				System.out.println(l.toString());

				Integer id = 0;
				try {
					id = (Integer) leaveModel.update(l);
					if (id != null && id != 0) {
						if (Util.getDaysNoWeekends(pre.getLeaveFrom(), pre.getLeaveTo()) < duration) {
							ulmModel.updateLeaveByUserAndLeaveType(uid, l.getLeaveTypeId(), duration, "+");
						} else if (Util.getDaysNoWeekends(pre.getLeaveFrom(), pre.getLeaveTo()) > duration) {
							ulmModel.updateLeaveByUserAndLeaveType(uid, l.getLeaveTypeId(), duration, "-");
						}
					}
				} catch (LeaveException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Leave data is empty");
				} catch (Exception e) {
					e.printStackTrace();
				}

				// response.sendRedirect(EMPLOYEE_DASHBOARD);
				out.println(id);

			}

		} 
		/**
		 * Remove the leave form the database
		 */
		else if (requestUrl.endsWith(EMPLOYEE_REMOVE_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				LeaveModel leaveModel = new LeaveModel();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				Leave leave = leaveModel.getLeaveById(Integer.parseInt(request.getParameter("id")));
				Cookie cookie = new Cookie("message", "Leaves_canceled_successfully");
				cookie.setMaxAge(10);

				int flag = 0;
				try {
					flag = (int) leaveModel.remove(Integer.parseInt(request.getParameter("id")));
					if (flag == 1) {
						ulmModel.updateLeaveByUserAndLeaveType(leave.getUserId(), leave.getLeaveTypeId(),
								(int) Util.getDaysNoWeekends(leave.getLeaveFrom(), leave.getLeaveTo()), "-");
					}

				} catch (Exception e) {
					cookie.setValue("Some_error_occured");
					e.printStackTrace();
				}

				response.addCookie(cookie);
				response.sendRedirect(EMPLOYEE_ALL_LEAVES);

			}

		}
		/**
		 * Opens the list of all the leave status page
		 */
		else if (requestUrl.endsWith(EMPLOYEE_STATUS_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {

				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				request.setAttribute("leaveMapper", ulmModel.getLeaveByUser((Integer) session.getAttribute("uid")));
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll());

				request.setAttribute("pageName", "leave-status");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/index.jsp");
				rd.forward(request, response);
			}
		} 
		/**
		 * Apply filters 
		 */
		else if (requestUrl.endsWith(EMPLOYEE_APPLY_FILTERS)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for (Cookie c : cookies) {
				if (c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if (ssid.equals("")) {
				out.println("419 Session Expired ... ");
			} else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel();
				sess = sessModel.getSession(ssid);

				String from = request.getParameter("from");
				String to = request.getParameter("to");
				String status = request.getParameter("status");
				Integer uid = (Integer) session.getAttribute("uid");

				LeaveModel leaveModel = new LeaveModel();
				try {
					request.setAttribute("leaves", leaveModel.getLeaveByFilter(from, to, status, uid));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-list");
				RequestDispatcher rd = request.getRequestDispatcher(path + "employee/index.jsp");
				rd.forward(request, response);

			}

		}

	}// end process

}
