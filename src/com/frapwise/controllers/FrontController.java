package com.frapwise.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println(path);
		
		if(requestUrl.endsWith("home.htm")) {
			RequestDispatcher rd = request.getRequestDispatcher(path+"web/index.jsp");
			rd.forward(request, response);
		}
		
		
	}
}
