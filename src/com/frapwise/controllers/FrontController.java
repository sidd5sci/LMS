package com.frapwise.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.frapwise.entities.User;
import com.frapwise.exceptions.UserException;
import com.frapwise.models.UserModel;
import com.frapwise.utils.RandomGenerator;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.htm")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		
		this.process(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.process(request,response);
	}

	
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String requestUrl = request.getRequestURI();
		
		ServletContext ctx = request.getServletContext();
		String path = ctx.getInitParameter("viewPath");
		String url = ctx.getInitParameter("url");
		System.out.println(path);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		
		if(requestUrl.endsWith("home.htm")) {
			RequestDispatcher rd = request.getRequestDispatcher(path+"web/index.jsp");
			rd.forward(request, response);
		}else if(requestUrl.endsWith("login.htm")) {
			RequestDispatcher rd = request.getRequestDispatcher(path+"auth/login.jsp");
			rd.forward(request, response);
		}else if(requestUrl.endsWith("login-db.htm")) {
			UserModel um = new UserModel();
			User u = new User();
			try {
				u = um.Authenticate(request.getParameter("email"), 
									request.getParameter("password"));
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
				Cookie cookie = new Cookie("ssid",ssid);
				cookie.setMaxAge(5*60*60);
				response.addCookie(cookie);
				response.sendRedirect(url+"dashboard.htm");
				
			}else {
				out.println("fuck....");
			}
			
			
		}else if(requestUrl.endsWith("dashboard.htm")) {
			RequestDispatcher rd = request.getRequestDispatcher(path+"admin/index.jsp");
			rd.forward(request, response);
		}
		
		
	}
}
