package com.frapwise.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frapwise.entities.Leave;
import com.frapwise.entities.User;
import com.frapwise.entities.UserLeaveMapper;
import com.frapwise.exceptions.LeaveException;
import com.frapwise.exceptions.UserException;
import com.frapwise.models.DepartmentModel;
import com.frapwise.models.LeaveModel;
import com.frapwise.models.UserLeaveMapperModel;
import com.frapwise.models.UserModel;
import com.frapwise.routes.ApiRoutes;
import com.frapwise.utils.Util;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import java.util.*;

/**
 * Servlet implementation class ApiController
 */
@WebServlet("*.api")
public class ApiController extends HttpServlet implements ApiRoutes{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ApiController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			this.process(request, response);
		} catch (ParseException e) {
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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException {

		String requestUrl = request.getRequestURI();
		System.out.println(requestUrl);
		PrintWriter out = response.getWriter();

		if (requestUrl.endsWith(GET_AVAILIBLE_LEAVE_BY_USER_LEAVETYPE)) {

			UserLeaveMapperModel ulmModel = new UserLeaveMapperModel();
			List<UserLeaveMapper> ulms = ulmModel.getLeaveByUser(Integer.parseInt(request.getParameter("uid"))

			);
			Integer lid = Integer.parseInt(request.getParameter("leavetype"));
			for (UserLeaveMapper u : ulms) {
				if (u.getLeaveTypeId() == lid) {
					out.println(u.getLeaveAvailible());
				}

			}

		} else if (requestUrl.endsWith(GET_LEAVE_STATUS_BY_USER_DATE)) {

			LeaveModel lModel = new LeaveModel();
			List<Leave> leaves = null;
			try {
				leaves = lModel.getLeavesByUser(Integer.parseInt(request.getParameter("uid")));
			} catch (NumberFormatException | LeaveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String from = request.getParameter("from");
			String to = request.getParameter("to");
			int flag = 1;

			for (Leave l : leaves) {
				
				if(!l.getStatus().equals("rejected")) {
					flag = Util.getDatesCollision(l, from, to);
					
//					if (l.getLeaveFrom().equals(from)) {
//						flag = 0;
//					}
//					if (l.getLeaveTo().equals(to)) {
//						flag = 2;
//					}
				}
							
				
				
			}
			
			out.println(flag);
			

		}          
		else if (requestUrl.endsWith(GET_USERS_BY_DEPARTMENT)) {
			UserModel userModel = new UserModel();
			List<User> users = null;
			try {
				users = userModel.getUserByDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			} catch (NumberFormatException | UserException e) {
				// TODO Auto-generated catch block
				out.println("Error occured");
			}
			StringBuilder JSON = new StringBuilder("{\"result\":[ ");
			for(User u:users) {
				
				JSON.append(Util.encodeJson(u));
				JSON.append(",");
			}
			
			JSON.setCharAt(JSON.length()-1, ' ');
			JSON.append("]}");
			out.println(JSON.toString());
			
		}
		else if(requestUrl.endsWith(CHECK_DEPARTMENT_EXISIT)) {
			DepartmentModel dptModel = new DepartmentModel();
			
			//dptModel.checkDepartmentExisit();
		}
		else if(requestUrl.endsWith(UPLOAD_FILE)) {
			this.upload(request, response);
		}

	}// end process

	private void upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String file_name = null;
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return;
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return;
			}
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if (file_name == null) {
						if (fileItem.getFieldName().equals("file_name")) {
							file_name = fileItem.getString();
						}
					}
				} else {
					if (fileItem.getSize() > 0) {
						fileItem.write(new File("D:\\siddhartha\\Siddhartha\\My_works\\java_web\\LeaveManagmentSystem\\WebContent\\resources\\uploads" + fileItem.getName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.sendRedirect("admin-upload-data.htm?file="+file_name);
		}
	}
}
