package com.FashionCommerce.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FashionCommerce.DAO.AdminDAO;
import com.FashionCommerce.DAO.AdminDAOImpl;
import com.FashionCommerce.model.AdminDetails;
import com.FashionCommerce.service.AdminService;
import com.FashionCommerce.service.AdminServiceImpl;
@WebServlet ("/adminLogin")
public class AdminLogin extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter writer=response.getWriter();
		String emailId=request.getParameter("emailId");
		String password=request.getParameter("password");
		AdminDAO adminDAO=new AdminDAOImpl();
		AdminDetails  adminDetails=new AdminDetails();
		adminDetails=adminDAO.verifyAdminLoginCredentials(emailId, password);
		System.out.println(adminDetails);
		if(adminDetails.getEmailId().equals(emailId) && adminDetails.getPassword().equals(password)) {
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("AdminOperations.html");
			requestDispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("AdminLogin.html");
			dispatcher.include(request, response);
			writer.println("<center><h5>Invalid Data<h5><center>");
		}
	}

}
