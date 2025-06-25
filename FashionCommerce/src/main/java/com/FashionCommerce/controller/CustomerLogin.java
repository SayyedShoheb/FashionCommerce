package com.FashionCommerce.controller;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FashionCommerce.DAO.CustomerDAO;
import com.FashionCommerce.DAO.CustomerDAOImpl;
import com.FashionCommerce.DAO.ProductDAO;
import com.FashionCommerce.DAO.ProductDAOImpl;
import com.FashionCommerce.model.CustomerDetails;
import com.FashionCommerce.service.CustomerService;
import com.FashionCommerce.service.CustomerServiceImpl;

@WebServlet ("/customerLogin")
public class CustomerLogin extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//here we are validating the credentials if the credentials are are present in out data base then we are forwarding
//		to the home page including the credensials of the user.
		PrintWriter writer=response.getWriter();
		String emailId=request.getParameter("emailId");
		String password=request.getParameter("password");
		CustomerDAO customerDAO=new CustomerDAOImpl();
		CustomerDetails customerDetails=customerDAO.getLoginCustomerDetails(emailId, password);
		ProductDAO productDAO = new ProductDAOImpl();
		 if(customerDetails!=null) {
			 HttpSession session=request.getSession();
			 session.setAttribute("LoginCustomerDetails", customerDetails);
			 RequestDispatcher requestDispatcher=request.getRequestDispatcher("DisplayAllProducts.jsp");		 
			 requestDispatcher.forward(request, response);
		 }
		 else {
			 RequestDispatcher dispatcher=request.getRequestDispatcher("CustomerLogin.html");
			 dispatcher.include(request, response);
			 writer.println("<center><h5> Invalid Credentials</h5></center>");
		 }
	}
}

