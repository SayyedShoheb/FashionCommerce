package com.FashionCommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FashionCommerce.DAO.ProductDAO;
import com.FashionCommerce.DAO.ProductDAOImpl;
import com.FashionCommerce.model.ProductDetails;
import com.mysql.cj.Session;

@WebServlet ("/filterProducts")
public class FilterProducts extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String searchBy=request.getParameter("filter");
	PrintWriter writer=response.getWriter();
	ProductDAO productDAO=new ProductDAOImpl();
	List<ProductDetails> filteredProductDetails=productDAO.filterAllProductDetails(searchBy);
	System.out.println(filteredProductDetails);
	HttpSession session=request.getSession();
	
	session.setAttribute("filteredProductDetails", filteredProductDetails);
	if(filteredProductDetails==null) {
		List<ProductDetails> allProductDetails=productDAO.getAllProductsDetails();
		session.setAttribute("filteredProductsResult", null);	
	}
	if(filteredProductDetails!=null) {
		session.setAttribute("ProductsFound", "true");
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("DisplayAllProducts.jsp");
		requestDispatcher.forward(request, response);
	}
	else {
		writer.println("<center><h5>No Result Found</h5></center>");
		RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayAllProducts.jsp");
		dispatcher.include(request, response);
	}
}
}
