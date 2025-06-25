<%@page import="com.FashionCommerce.model.ProductDetails"%>
<%@page import="com.mysql.cj.Session"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.List"%>
<%@page import="com.FashionCommerce.*"%>
<%@page import="com.FashionCommerce.DAO.ProductDAOImpl"%>
<%@page import="com.FashionCommerce.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Products</title>
<link rel="stylesheet" href="Style1.css">
</head>
<body id="allproductsbodycss">
	<%! ProductDAO productDAO=new ProductDAOImpl();
List<ProductDetails> allProductsDetails=productDAO.getAllProductsDetails();   %>

	<center>
		<form action="filterProducts">
			<label>filter :</label><input
				placeholder="Search by Name,Brand and Category" name="filter">
			<input type="submit" value="Search">
		</form>
		<h3>All Product details</h3>
		<br> 
		<a href="AddProduct.html">AddProduct</a>
		<table border="1">
			<% 
/* allProductsDetails=(List<ProductDetails>)session.getAttribute("filteredProducts");
if(allProductsDetails==null){
	allProductsDetails=productDAO.getAllProductsDetails();
}*/
%>
			<tr>
				<th>Product Name</th>
				<th>Product Price</th>
				<th>Product Brand</th>
				<th>Product Discount</th>
				<th>Product Category</th>
				<th>Product Quantity</th>
				<th>Action</th>
			</tr>
			<%
for(ProductDetails productDetails:allProductsDetails)
{%>
	
			<tr>
				<td><%=productDetails.getName() %></td>
				<td><%=productDetails.getPrice() %></td>
				<td><%=productDetails.getBrand() %></td>
				<td><%=productDetails.getDiscount() %>%</td>
				<td><%=productDetails.getCategory() %></td>
				<td><%=productDetails.getQuantity() %></td>
				<td> 	<%  session.setAttribute("productdetails", productDetails); %>
				<form action=""><input type="submit" value="Update"></input></form>
					 <form action="deleteproductdetails"><input type="submit" value="Delete"></input></form></td>
			</tr>
			<%} %>
		</table>
	</center>
</body>
</html>