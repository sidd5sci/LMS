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
import com.frapwise.exceptions.LeaveTypeException;
import com.frapwise.exceptions.UserException;
import com.frapwise.models.DepartmentModel;
import com.frapwise.models.LeaveModel;
import com.frapwise.models.LeaveTypeModel;
import com.frapwise.models.SessionModel;
import com.frapwise.models.UserLeaveMapperModel;
import com.frapwise.models.UserModel;
import com.frapwise.utils.AES_Cipher;
import com.frapwise.utils.RandomGenerator;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.htm")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	private final static String HOME 					= "home.htm";
	
	// auth routes
	private final static String LOGIN 					= "login.htm";
	private final static String AUHENTICATE 			= "login-db.htm";
	private final static String LOGOUT 					= "logout.htm";
	
	// Employee routes
	private final static String EMPLOYEE_DASHBOARD 		= "employee-dashboard.htm";
		// leaves
		private final static String EMPLOYEE_ADD_LEAVE		 = "employee-add-leave.htm";
		private final static String EMPLOYEE_ADD_LEAVE_DB	 = "employee-add-leave-db.htm";
		private final static String EMPLOYEE_ALL_LEAVES		 = "employee-all-leaves.htm";
		private final static String EMPLOYEE_STATUS_LEAVE	 = "employee-status-leave.htm";
		private final static String EMPLOYEE_PENDING_LEAVE	 = "employee-pending-leave.htm";
		private final static String EMPLOYEE_APROVED_LEAVE	 = "employee-aproved-leave.htm";
	
	// Admin routes
	private final static String ADMIN_DASHBOARD 		= "admin-dashboard.htm";
		// department
		private final static String ADMIN_ADD_DEPARTMENT	 = "admin-add-department.htm";
	    private final static String ADMIN_ADD_DEPARTMENT_DB	 = "admin-add-department-db.htm";
		private final static String ADMIN_ALL_DEPARTMENT	 = "admin-all-department.htm";
		private final static String ADMIN_EDIT_DEPARTMENT	 = "admin-edit-department.htm";
		private final static String ADMIN_EDIT_DEPARTMENT_DB = "admin-edit-department-db.htm";
		private final static String ADMIN_REMOVE_DEPARTMENT	 = "admin-remove-department.htm";
		// leave types
		private final static String ADMIN_ADD_LEAVETYPE		 = "admin-add-leavetype.htm";
		private final static String ADMIN_ADD_LEAVETYPE_DB	 = "admin-add-leavetype-db.htm";
		private final static String ADMIN_ALL_LEAVETYPE		 = "admin-all-leavetype.htm";
		private final static String ADMIN_EDIT_LEAVETYPE	 = "admin-edit-leavetype.htm";
		private final static String ADMIN_EDIT_LEAVETYPE_DB	 = "admin-edit-leavetype-db.htm";
		private final static String ADMIN_REMOVE_LEAVETYPE   = "admin-remove-leavetype.htm";
		// employees
		private final static String ADMIN_ADD_EMPLOYEE	     = "admin-add-employee.htm";
		private final static String ADMIN_ADD_EMPLOYEE_DB	 = "admin-add-employee-db.htm";
		private final static String ADMIN_ALL_EMPLOYEE		 = "admin-all-employee.htm";
		private final static String ADMIN_EDIT_EMPLOYEE		 = "admin-edit-employee.htm";
		private final static String ADMIN_EDIT_EMPLOYEE_DB	 = "admin-edit-employee-db.htm";
		private final static String ADMIN_REMOVE_EMPLOYEE	 = "admin-remove-employee.htm";
		private final static String ADMIN_EMPLOYEE_FILTER	 = "";
		private final static String ADMIN_ADD_EL_CL			 = "";
		// leave
		private final static String ADMIN_ADD_LEAVE 		 = "admin-add-leave.htm";
		private final static String ADMIN_ADD_LEAVE_DB 		 = "admin-add-leave-db.htm";
		private final static String ADMIN_ALL_LEAVE			 = "admin-all-leaves.htm";
		private final static String ADMIN_TODAYS_LEAVES		 = "admin-today-leaves.htm";
		private final static String ADMIN_LEAVE_APPROVAL 	 = "admin-approve-leave.htm";
		private final static String ADMIN_ALL_LEAVE_FILTER   = "";
		private final static String ADMIN_ASSIGN_LEAVES		 = "admin-assign-leaves.htm";
		private final static String ADMIN_ASSIGN_LEAVES_DB	 = "admin-assign-leave-db.htm";
		
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			this.process(request,response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			this.process(request,response);
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
		System.out.println(path+" | "+requestUrl);
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		if(requestUrl.endsWith("/")) {
			response.sendRedirect(HOME);
		}else if(requestUrl.endsWith(HOME)) {
			RequestDispatcher rd = request.getRequestDispatcher(path+"web/index.jsp");
			rd.forward(request, response);
		}else if(requestUrl.endsWith(LOGOUT)) {
			
			String ssid = (String)session.getAttribute("ssid");
			if(ssid != null) {
				session.invalidate(); // removes all the session attributes bound
				// removing the cookie
				Cookie cookie = new Cookie("ssid","");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				
				request.setAttribute("message", "You have logged out successfully");
				
				
			}else{
				/* response.sendRedirect("/Frapwise/login"); */
			}
			response.sendRedirect(HOME);
			
		}else if(requestUrl.endsWith(LOGIN)) {
			
			RequestDispatcher rd = request.getRequestDispatcher(path+"auth/login.jsp");
			rd.forward(request, response);
		}else if(requestUrl.endsWith(AUHENTICATE)) {
			UserModel um = new UserModel();
			User u = new User();
			u = null;
			try {
				u = um.Authenticate(request.getParameter("email"), 
									AES_Cipher.getEncrypted(request.getParameter("password")) );
			} catch (UserException e) {
				// TODO Auto-generated catch block
				request.setAttribute("message", "Email or password incorrect");
			}
			if(u != null) {
				
				String  ssid = RandomGenerator.getAlphaNumericString(16);
				session.setAttribute("ssid", ssid);
				session.setAttribute("uid", u.getId());
				session.setAttribute("email", u.getEmail());
				session.setAttribute("username", u.getUsername());
				session.setAttribute("name", u.getFname()+" "+u.getLname());
				
				System.out.println(ssid);
				// setting session id to cookie
				Cookie cookie = new Cookie("ssid",ssid);
				cookie.setMaxAge(5*60*60);
				response.addCookie(cookie);
				// setting session DB
				Session sess = new Session();
				sess.setSsid(ssid);
				sess.setUid(u.getId());
				sess.setPayload(session.toString());
				System.out.println(u.getId());
				SessionModel sessModel = new SessionModel(); 
				sessModel.setSession(sess);
				
				if(u.getRole().equals("Admin"))
					response.sendRedirect(url+ADMIN_DASHBOARD);
				else
					response.sendRedirect(url+EMPLOYEE_DASHBOARD );
				
			}else {
				request.setAttribute("message", "Email or password incorrect");
				RequestDispatcher rd = request.getRequestDispatcher(path+"auth/login.jsp");
				rd.forward(request, response);
			}
			
			
		}else if(requestUrl.endsWith(ADMIN_DASHBOARD)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				request.setAttribute("pageName", "home");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
			
			
			
		}else if(requestUrl.endsWith(ADMIN_ADD_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				request.setAttribute("pageName", "department-add");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
			
			
		}else if(requestUrl.endsWith(ADMIN_ADD_DEPARTMENT_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				DepartmentModel dptModel = new DepartmentModel();
				Department dpt = new Department();
				dpt.setName(request.getParameter("departmentName"));
				dpt.setDescription(request.getParameter("departmentDesc"));
				
				try {
					dptModel.add(dpt);
				} catch (DepartmentException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Department name is empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(ADMIN_DASHBOARD);
					
				
			}
		}else if(requestUrl.endsWith(ADMIN_ALL_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				
				DepartmentModel dptModel = new DepartmentModel(); 
				try {
					request.setAttribute("departments", dptModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message","Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "department-all");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(requestUrl.endsWith(ADMIN_EDIT_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				
				DepartmentModel dptModel = new DepartmentModel();
				Department dpt = dptModel.getDepartmentById(Integer.parseInt(request.getParameter("id")) );
				request.setAttribute("department", dpt);
				request.setAttribute("pageName", "department-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
		}else if(requestUrl.endsWith(ADMIN_EDIT_DEPARTMENT_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();
				Department dpt = new Department();
				dpt.setId(Integer.parseInt(request.getParameter("id")) );
				dpt.setName(request.getParameter("departmentName"));
				dpt.setDescription(request.getParameter("departmentDesc"));
				int flag = 0;
				try {
					flag = (int) dptModel.update(dpt);
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}
				
				if(flag == 1) {
					request.setAttribute("message", "Department updated successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
				
				
			}
		}else if(requestUrl.endsWith(ADMIN_REMOVE_DEPARTMENT)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();							
				int flag = 0;
				try {
					flag = (int) dptModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}
				
				if(flag == 1) {
					request.setAttribute("message", "Department removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
				
				
			}
		}else if(requestUrl.endsWith(ADMIN_ADD_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				request.setAttribute("pageName", "leavetype-add");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
			
		}else if(requestUrl.endsWith(ADMIN_ADD_LEAVETYPE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				LeaveTypeModel ltModel = new LeaveTypeModel();
				LeaveType lt = new LeaveType();
				
				lt.setName(request.getParameter("leaveTypeName"));
				lt.setDescription(request.getParameter("leaveTypeDesc"));
				
				try {
					ltModel.add(lt);
				} catch (LeaveTypeException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "LeaveType name is empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(ADMIN_DASHBOARD);
					
				
			}
		}else if(requestUrl.endsWith(ADMIN_ALL_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				
				LeaveTypeModel ltModel = new LeaveTypeModel(); 
				try {
					request.setAttribute("leavetypes", ltModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message","Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leavetype-all");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(requestUrl.endsWith(ADMIN_EDIT_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				
				LeaveTypeModel ltModel = new LeaveTypeModel();
				LeaveType lt = ltModel.getLeaveTypeById(Integer.parseInt(request.getParameter("id")) );
				request.setAttribute("leavetype", lt);
				request.setAttribute("pageName", "leavetype-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
		}else if(requestUrl.endsWith(ADMIN_EDIT_LEAVETYPE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				LeaveTypeModel ltModel = new LeaveTypeModel();
				LeaveType lt = new LeaveType();
				lt.setId(Integer.parseInt(request.getParameter("id")) );
				lt.setName(request.getParameter("leaveTypeName"));
				lt.setDescription(request.getParameter("leaveTypeDesc"));
				int flag = 0;
				try {
					flag = (int) ltModel.update(lt);
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}
				
				if(flag == 1) {
					request.setAttribute("message", "LeaveType updated successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
				
				
			}
		}else if(requestUrl.endsWith(ADMIN_REMOVE_LEAVETYPE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				LeaveTypeModel ltModel = new LeaveTypeModel();							
				int flag = 0;
				try {
					flag = (int) ltModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}
				
				if(flag == 1) {
					request.setAttribute("message", "LeaveType removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
				
				
			}
		}else if(requestUrl.endsWith(ADMIN_ADD_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll()); 
				request.setAttribute("pageName", "employee-add");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
			
		}else if(requestUrl.endsWith(ADMIN_ADD_EMPLOYEE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				UserModel userModel = new UserModel();
				User u = new User();
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel(); 
				LeaveTypeModel ltModel = new LeaveTypeModel();
				List<Object> leaveTypes =  ltModel.getAll();
				
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setUsername(request.getParameter("username"));
				u.setHomeOffice(request.getParameter("homeOffice"));
				u.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				u.setEmail(request.getParameter("email"));
				u.setPassword(AES_Cipher.getEncrypted(request.getParameter("password")) );
				u.setRole(request.getParameter("role"));
				u.setStatus(request.getParameter("status"));
				
				
				try {
					int id = (int) userModel.add(u);
					
					for(Object o:leaveTypes) {
						LeaveType lt = (LeaveType) o;
						UserLeaveMapper ulm = new UserLeaveMapper();
						ulm.setUid(id);
						ulm.setLeaveTypeId(lt.getId());
						ulm.setLeaveMax(0);
						ulm.setLeaveTaken(0);
						ulm.setLeaveAvailible(0);
						ulm.setTimeDuration(0);
						ulm.setLeaveFrom(new SimpleDateFormat("dd-M-yy").format(new java.util.Date()) );
						ulm.setLeaveTo(new SimpleDateFormat("dd-M-yy").format(new java.util.Date()));
						ulmModel.add(ulm);
					}
					
				} catch (UserException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Employee data is empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(ADMIN_DASHBOARD);
					
				
			}
		}else if(requestUrl.endsWith(ADMIN_ALL_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				
				UserModel userModel = new UserModel(); 
				try {
					request.setAttribute("users", userModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message","Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "employee-all");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(requestUrl.endsWith(ADMIN_EDIT_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				
				UserModel userModel = new UserModel();
				User u = new User();
				try {
					u = userModel.getUserById(Integer.parseInt(request.getParameter("id")) );
				} catch (NumberFormatException | UserException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll());
				request.setAttribute("user", u);
				request.setAttribute("pageName", "employee-edit");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
		}else if(requestUrl.endsWith(ADMIN_EDIT_EMPLOYEE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				UserModel userModel = new UserModel();
				User u = new User();
				u.setId(Integer.parseInt(request.getParameter("id")) );
				u.setFname(request.getParameter("fname"));
				u.setLname(request.getParameter("lname"));
				u.setUsername(request.getParameter("username"));
				u.setHomeOffice(request.getParameter("homeOffice"));
				u.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				u.setEmail(request.getParameter("email"));
				u.setPassword(AES_Cipher.getEncrypted(request.getParameter("password")) );
				u.setRole(request.getParameter("role"));
				u.setStatus(request.getParameter("status"));
				
				int flag = 0;
				try {
					flag = (int) userModel.update(u);
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}
				
				if(flag == 1) {
					request.setAttribute("message", "User updated successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
				
				
			}
		}else if(requestUrl.endsWith(ADMIN_REMOVE_EMPLOYEE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				UserModel userModel = new UserModel();							
				int flag = 0;
				try {
					flag = (int) userModel.remove(Integer.parseInt(request.getParameter("id")));
				} catch (Exception e) {
					request.setAttribute("message", "Some error occured !!");
					e.printStackTrace();
				}
				
				if(flag == 1) {
					request.setAttribute("message", "User removed successfully ");
					response.sendRedirect(ADMIN_DASHBOARD);
				}
				
			}
		}else if(requestUrl.endsWith(ADMIN_ADD_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll()); 
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll()); 
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll()); 
				request.setAttribute("pageName", "leave-create");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
			
		}else if(requestUrl.endsWith(ADMIN_ADD_LEAVE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				LeaveModel leaveModel = new LeaveModel();
				Leave l = new Leave();
				
				l.setUserId(Integer.parseInt(request.getParameter("user")) );
				l.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				l.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				l.setLeaveFrom(request.getParameter("from"));
				l.setLeaveTo(request.getParameter("to"));
				l.setTimeOffType(Integer.parseInt(request.getParameter("timeOffType")) );				
				
				System.out.println(l.toString());
				try {
					leaveModel.add(l);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Leave data is empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(ADMIN_DASHBOARD);
					
				
			}
		}else if(requestUrl.endsWith(ADMIN_ALL_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
								
				LeaveModel leaveModel = new LeaveModel(); 
				try {
					request.setAttribute("leaves", leaveModel.getAll());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message","Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-all");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
			
		}else if(requestUrl.endsWith(ADMIN_TODAYS_LEAVES)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
								
				java.util.Date today= new java.util.Date();
				String todayDate = new SimpleDateFormat("yy-M-dd").format(today);
				LeaveModel leaveModel = new LeaveModel(); 
				
				try {
					request.setAttribute("leaves", leaveModel.getLeavesToday(todayDate) );
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message","Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-list");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
				
			}
		}else if(requestUrl.endsWith(ADMIN_ASSIGN_LEAVES)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll()); 
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll()); 
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll()); 
				request.setAttribute("pageName", "leave-assign");
				RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
				rd.forward(request, response);
			}
		}else if(requestUrl.endsWith(ADMIN_ASSIGN_LEAVES_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll()); 
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll()); 
				UserModel userModel = new UserModel();
				request.setAttribute("users", userModel.getAll()); 
				
				UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
				UserLeaveMapper ulm = new UserLeaveMapper();
				
				ulm.setUid(Integer.parseInt(request.getParameter("user")));
				ulm.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				ulm.setLeaveMax(Integer.parseInt(request.getParameter("max")));
				ulm.setLeaveFrom(request.getParameter("from"));
				ulm.setLeaveTo(request.getParameter("to"));
				ulm.setLeaveTaken(0);
				ulm.setLeaveAvailible(Integer.parseInt(request.getParameter("max")));
				ulm.setTimeDuration(0);
				
				ulmModel.add(ulm);
				
				response.sendRedirect(ADMIN_DASHBOARD);
			}
		}
		
//		EMPOYEE
		
		
		else if(requestUrl.endsWith(EMPLOYEE_DASHBOARD)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			// get the session data from database
			Session sess = new Session();
			SessionModel sessModel = new SessionModel(); 
			sess = sessModel.getSession(ssid);
			request.setAttribute("pageName", "home");
			RequestDispatcher rd = request.getRequestDispatcher(path+"employee/index.jsp");
			rd.forward(request, response);
			
		}
		else  if(requestUrl.endsWith(EMPLOYEE_ADD_LEAVE)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}else {
				DepartmentModel dptModel = new DepartmentModel();
				request.setAttribute("departments", dptModel.getAll()); 
				LeaveTypeModel ltModel = new LeaveTypeModel();
				request.setAttribute("leavetypes", ltModel.getAll()); 
				UserModel userModel = new UserModel();
				request.setAttribute("user", userModel.getUserById((Integer)session.getAttribute("uid"))); 
				request.setAttribute("pageName", "leave-create");
				RequestDispatcher rd = request.getRequestDispatcher(path+"employee/index.jsp");
				rd.forward(request, response);
			}
		}else if(requestUrl.endsWith(EMPLOYEE_ADD_LEAVE_DB)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
				
				LeaveModel leaveModel = new LeaveModel();
				Leave l = new Leave();
				
				l.setUserId(Integer.parseInt(request.getParameter("user")) );
				l.setLeaveTypeId(Integer.parseInt(request.getParameter("leaveType")));
				l.setDepartmentId(Integer.parseInt(request.getParameter("department")));
				l.setLeaveFrom(request.getParameter("from"));
				l.setLeaveTo(request.getParameter("to"));
				l.setTimeOffType(Integer.parseInt(request.getParameter("timeOffType")) );				
				
				System.out.println(l.toString());
				try {
					leaveModel.add(l);
				} catch (UserException e) {
					// TODO Auto-generated catch block
					request.setAttribute("message", "Leave data is empty");
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				response.sendRedirect(EMPLOYEE_DASHBOARD);
					
				
			}
		}
		else if(requestUrl.endsWith(EMPLOYEE_ALL_LEAVES)) {
			Cookie[] cookies = request.getCookies();
			String ssid = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("ssid")) {
					ssid = c.getValue();
				}
			}
			if(ssid.equals("")) {
				out.println("419 Session Expired ... ");
			}
			else {
				// get the session data from database
				Session sess = new Session();
				SessionModel sessModel = new SessionModel(); 
				sess = sessModel.getSession(ssid);
								
				LeaveModel leaveModel = new LeaveModel(); 
				try {
					request.setAttribute("leaves", leaveModel.getLeavesByUser((Integer)session.getAttribute("uid")));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					request.setAttribute("message","Some error occured");
					e.printStackTrace();
				}
				request.setAttribute("pageName", "leave-list");
				RequestDispatcher rd = request.getRequestDispatcher(path+"employee/index.jsp");
				rd.forward(request, response);
				
			}
		}
		
		
		
		
		
		
		
		
		
	}// end process
	
	
	
	
	
	
	
}
