package com.FashionCommerce.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.FashionCommerce.DAO.CartDAO;
import com.FashionCommerce.DAO.CartDAOImpl;
import com.FashionCommerce.model.ProductDetails;

@WebServlet ("/getAllCartProducts")
public class GetAllCartProducts extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession(false);
		int customerId=(int)session.getAttribute("customerId");
		CartDAO cartDAO=new CartDAOImpl();
		List<ProductDetails> cartList=cartDAO.getAllCartProducts(customerId);
		session.setAttribute("CartList", cartList);
		
			RequestDispatcher dispatcher=request.getRequestDispatcher("DisplayAllProducts.jsp");
			dispatcher.forward(request, response);
		
	}
}
