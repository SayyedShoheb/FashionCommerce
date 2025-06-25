package com.FashionCommerce.controller;



import java.io.IOException;
import java.io.PrintWriter;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.spi.http.HttpContext;

import com.FashionCommerce.DAO.CustomerDAO;
import com.FashionCommerce.DAO.CustomerDAOImpl;
import com.FashionCommerce.exception.CustomerException;
import com.FashionCommerce.model.CustomerDetails;
import com.FashionCommerce.service.CustomerService;
import com.FashionCommerce.service.CustomerServiceImpl;
@WebServlet ("/customerRegistration")
public class CustomerRegistration extends HttpServlet{
	CustomerService customerService=new CustomerServiceImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//id, name, emailId, mobileNumber, gender, address, password
		PrintWriter writer=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String emailId=request.getParameter("emailId");
		long mobileNumber=Long.parseLong(request.getParameter("mobileNumber"));
		String gender=request.getParameter("gender");
		String address=request.getParameter("address");
		String password=request.getParameter("password");
		
		CustomerDetails customerDetails=new CustomerDetails();
		customerDetails.setName(name);
		customerDetails.setEmailId(emailId);
		customerDetails.setMobileNumber(mobileNumber);
		customerDetails.setGender(gender);
		customerDetails.setAddress(address);
		customerDetails.setPassword(password);
		
		
		CustomerService customerService=new CustomerServiceImpl();
		try {
		if(customerService.userRegistration(customerDetails)) {
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("CustomerLogin.html");
			requestDispatcher.forward(request, response);
		}
		else {
			RequestDispatcher dispatcher=request.getRequestDispatcher("CustomerRegistration.html");
			dispatcher.include(request, response);
			writer.println("Invalid Data");
		}
		}catch(CustomerException e) {
			RequestDispatcher dispatcher=request.getRequestDispatcher("CustomerRegistration.html");
			dispatcher.include(request, response);
			writer.println("<center><h5>"+e.getExceptionMsg()+"</h5></center>");
		}
	}
}
