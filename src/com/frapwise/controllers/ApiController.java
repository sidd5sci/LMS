package com.frapwise.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frapwise.entities.Leave;

import com.frapwise.entities.User;
import com.frapwise.entities.UserLeaveMapper;
import com.frapwise.exceptions.DepartmentException;
import com.frapwise.exceptions.LeaveException;
import com.frapwise.exceptions.LeaveTypeException;
import com.frapwise.exceptions.UserException;
import com.frapwise.models.DepartmentModel;
import com.frapwise.models.LeaveModel;

import com.frapwise.models.UserLeaveMapperModel;
import com.frapwise.models.UserModel;
import com.frapwise.routes.ApiRoutes;
import com.frapwise.utils.Util;
import com.frapwise.vo.ReportVO;

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
			try {
				this.process(request, response);
			} catch (LeaveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			try {
				this.process(request, response);
			} catch (LeaveException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException, ParseException, LeaveException {

		String requestUrl = request.getRequestURI();
		System.out.println(requestUrl);
		PrintWriter out = response.getWriter();
		ServletContext ctx = request.getServletContext();
		String path = ctx.getInitParameter("viewPath");
		String url = ctx.getInitParameter("url");
		String uploadPath = ctx.getInitParameter("uploadPath");

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
				if(flag != 1) {
					break;
				}			
				System.out.println(flag);
				
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
		else if(requestUrl.endsWith(CHECK_DEPARTMENT_EXIST)) {
			DepartmentModel dptModel = new DepartmentModel();
			
			//dptModel.checkDepartmentExisit();
		}
		/**
		 * upload file
		 */
		else if(requestUrl.endsWith(UPLOAD_FILE)) {
			
			String fileName = upload(request,response);
			if(fileName.length() > 0 ) {
				List<ReportVO> report = Util.readerExcelFile(fileName);
				
				request.setAttribute("report", report);
			}
			request.setAttribute("filename", fileName);
			request.setAttribute("pageName", "leave-upload-check");
			RequestDispatcher rd = request.getRequestDispatcher(path + "admin/index.jsp");
			rd.forward(request, response);
			
		}
		/**
		 * 
		 */
		else if(requestUrl.endsWith(APPLY_UPLOADED_FILE)) {
			String filename = request.getParameter("filename");
			LeaveModel lModel = new LeaveModel();
			Cookie cookie = new Cookie("message","File_uploaded_successfully");
			cookie.setMaxAge(10);
			if(filename.length() > 0 ) {
				List<ReportVO> report = Util.readerExcelFile(filename);
				List<Leave> leaves = null;
				int count = 0,total = 0;
				try {
					leaves = Util.convertReportVOtoLeave(report);
					//lModel.addBulkLeaves(leaves);
					for(Leave l :leaves) {
						int i = lModel.isExist(l);
						System.out.println(l.toString());
						System.out.println("APPLY :\t"+i);// 1->filled 0-> empty
						
						try {
							if(i == 0) {
								
								// checking for date collision
								//Util.getDatesCollision(l, date1, date2)
								lModel.add(l);
								count++;
							}
								
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} catch (LeaveTypeException | DepartmentException e) {
					// TODO Auto-generated catch block
					cookie.setValue("File_was_corrpted");
					e.printStackTrace();
				}
				
				
			}
			
			response.addCookie(cookie);
			response.sendRedirect(url+"admin-dashboard.htm");
		}
		else if(requestUrl.endsWith(CHECK_USERNAME_EXIST)) {
			
			
		}
		

	}// end process

	private String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String file_name = null;
		String tempName = "";
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return "";
		}
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				return "";
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
						tempName = fileItem.getName();
						
						fileItem.write(new File("D:\\siddhartha\\Siddhartha\\My_works\\java_web\\LeaveManagmentSystem\\WebContent\\resources\\uploads\\excel\\" + fileItem.getName()));
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//response.sendRedirect("admin-upload-data.htm?file="+file_name+"&tmp="+tempName);
		}
		
		return tempName;
	}// end upload
}
