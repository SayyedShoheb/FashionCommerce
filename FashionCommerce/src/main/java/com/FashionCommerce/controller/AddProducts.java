package com.FashionCommerce.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.FashionCommerce.DAO.ProductDAO;
import com.FashionCommerce.DAO.ProductDAOImpl;
import com.FashionCommerce.model.ProductDetails;
import com.FashionCommerce.service.AdminService;
import com.FashionCommerce.service.AdminServiceImpl;

@WebServlet ("/addProduct")
public class AddProducts extends HttpServlet{
	
	
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	ProductDAO productDAO =new ProductDAOImpl();
	PrintWriter writer=response.getWriter();
	//id, name, brand, price, discount, category, quantity
	String name=request.getParameter("productName");
	String brand=request.getParameter("brand");
	String price1=request.getParameter("price");
	double price =0.0;
	if(price1!=null) {
	price =Double.parseDouble(price1);
	}
	double discount=Double.parseDouble(request.getParameter("discount"));
	String category=request.getParameter("category");
	int quantity=Integer.parseInt(request.getParameter("quantity"));
	String imageaddress=request.getParameter("imageaddress");
	ProductDetails productDetails=new ProductDetails();
	productDetails.setName(name);
	productDetails.setBrand(brand);
	productDetails.setPrice(price);
	productDetails.setDiscount(discount);
	productDetails.setCategory(category);
	productDetails.setQuantity(quantity);
	productDetails.setImageaddress(imageaddress);
	System.out.println(productDetails);
	
if(productDAO.addProduct(productDetails)!=0) {
	RequestDispatcher requestDispatcher =request.getRequestDispatcher("AllProducts.jsp");
	requestDispatcher.forward(request, response);
	
}
else {
	RequestDispatcher dispatcher =request.getRequestDispatcher("AddProduct.html");
	dispatcher.include(request, response);
	writer.println("Server Error");
}
}
}
