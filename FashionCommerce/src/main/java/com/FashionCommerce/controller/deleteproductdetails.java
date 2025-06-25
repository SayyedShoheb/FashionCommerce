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

import com.FashionCommerce.DAO.ProductDAO;
import com.FashionCommerce.DAO.ProductDAOImpl;
import com.FashionCommerce.model.ProductDetails;

@WebServlet("/deleteproductdetails")
public class deleteproductdetails extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	HttpSession session=request.getSession(false);
	ProductDetails productDetails=(ProductDetails)session.getAttribute("productdetails");
	System.out.println(productDetails);
	PrintWriter writer=response.getWriter();
	ProductDAO productDAO=new ProductDAOImpl();
	int result=productDAO.deleteProduct(productDetails.getId());
	if (result>0) {
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("AllProducts.jsp");
		requestDispatcher.include(request, response);
		writer.println("<center><h5>product deleted successfully</h5></center>");
	} else {
		RequestDispatcher dispatcher=request.getRequestDispatcher("AllProducts.jsp");
		dispatcher.include(request, response);
		writer.println("<center><h5>server error 404</h5></center>");
	}
}

}
