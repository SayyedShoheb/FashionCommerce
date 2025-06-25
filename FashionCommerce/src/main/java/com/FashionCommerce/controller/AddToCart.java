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

import com.FashionCommerce.DAO.CartDAO;
import com.FashionCommerce.DAO.CartDAOImpl;
import com.FashionCommerce.model.Cart;
@WebServlet ("/addToCart")
public class AddToCart extends HttpServlet{
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	int productId=Integer.parseInt(request.getParameter("productId"));
	int customerId=Integer.parseInt(request.getParameter("customerId"));
	Cart cartProduct=new Cart();
	cartProduct.setCustomerId(customerId);
	cartProduct.setProductId(productId);
	CartDAO  cartDAO=new CartDAOImpl();
	boolean result=cartDAO.getDetailsOfTheProduct(customerId, productId);
	PrintWriter writer=response.getWriter();
	if(result) {
		RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayAllProducts.jsp");
		dispatcher.include(request, response);
		writer.println("<center><h3>Product Already Exist</h3><center>");
	}else {
		cartDAO.insertProductInCart(cartProduct);
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("DisplayAllProducts.jsp");
		requestDispatcher.forward(request, response);
	}
}
}

