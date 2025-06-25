<%@page import="com.FashionCommerce.DAO.CartDAOImpl"%>
<%@page import="com.FashionCommerce.DAO.CartDAO"%>
<%@page import="com.FashionCommerce.model.CustomerDetails"%>
<%@page import="com.mysql.cj.Session"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="java.util.List"%>
<%@page import="com.FashionCommerce.model.*"%>
<%@page import="com.FashionCommerce.DAO.ProductDAOImpl"%>
<%@page import="com.FashionCommerce.DAO.ProductDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FashionCommerce</title>
<link rel="stylesheet" href="Style.css">

<style type="text/css">
/* table css for division format */
#filterProductsDivision{
	width: 100%;
	height: 45px;
	background-color: lightgrey;	
}
.productDivision{
   width: 270px;
    height: 450px;
    background-color:skyblue;
    border-radius: 5px;
    float: left;
    margin-left: 20px;
    margin-bottom: 60px;
}
.productDivision:hover{ 
    background-color: aquamarine;
    
}
.productImageCss{
    height: 200px;
    width: 100%;
    border-radius: 5px;
}
.productImageCss:hover{
   transform:scale(1.3);
   transition:3s all;
}
#productDetailsDiv{
    display: grid;
   row-gap: 6px;
   color: black;
   margin-left: 15px
}
#cartButtonCss{
    width: 90px;
    height: 30px;
    border: none;
    border-radius: 3px;
    background-color: darkblue;
    color: red;
    margin-top: 25px;
    margin-left: 15px;
}
#buyButtonCss{
    width: 40px;
    height: 30px;
    border: none;
    border-radius: 3px;
    background-color: darkblue;
    color: red;
    margin-top: 25px;
    margin-left:100px;
}
#allProductsDivision{
     padding-top:20px;
	background-color:darkblue;	 
}
#searchInputBox{
	width: 200px;
	height: 30px;
	background: none;
	margin-top: 6px;
}
#searchBox{
width: 60px;
height: 30px;
background-color: darkblue;
color: red;
border: none;
border-radius: 4px;
}
    
/* header css     */
#header{
	background: black;
	width: 100%;
    height: 20vh;
    display: flex;
    justify-content: space-between;
}
#titleCss{
	color:white;
	padding-top: 25px;
	padding-left: 5%;
}
#shopping-cartImageCss{
    width:40px;
    padding-top: 25px;
}
#profileIconImageCss{   
    width: 40px;
    padding-top: 25px;  
}
#footer{
	background: black;
	width: 100%;
    height: 25vh;
    float: left;
    display: flex;
    justify-content: space-evenly;
    justify-content: center;
    align-items: center;
}
#madewithcss{
color: pink;
}
</style>

</head>
<body>

<%CustomerDetails  customerDetails=(CustomerDetails)session.getAttribute("LoginCustomerDetails"); 
CartDAO cartDAO=new CartDAOImpl();
int productsCount=cartDAO.productsCountInCart(customerDetails.getId());
%>

<header id="header"> 
 
 <h1 id="titleCss">FashionCommerse</h1>

<form action="GetAllCartProducts.jsp"> 
<input type="image" src="https://w7.pngwing.com/pngs/798/196/png-transparent-computer-icons-shopping-cart-e-commerce-add-to-cart-button-purple-angle-text-thumbnail.png" id="shopping-cartImageCss" >
<sup id="productsCountCss"><%=productsCount %></sup>
</form>

<a href=""><img src="https://w7.pngwing.com/pngs/522/396/png-transparent-computer-icons-profile-miscellaneous-logo-profile-thumbnail.png" alt="" id="profileIconImageCss"></a>

<h4 style="color: white; padding-top:35px ;">Welcome! <%=customerDetails.getName() %></h4>
</header>

<% HttpSession session2=request.getSession();
session2.setAttribute("customerId", customerDetails.getId());
%>
<%!
ProductDAO productDAO=new ProductDAOImpl();
List<ProductDetails> allProductsDetails;

%>
<% 
if(session.getAttribute("filteredProductsResult")==null){
	allProductsDetails=productDAO.getAllProductsDetails();
}
if(session.getAttribute("ProductsFound")=="true"){
allProductsDetails=(List<ProductDetails>)session.getAttribute("filteredProductDetails");
}
if(allProductsDetails==null){
	allProductsDetails=productDAO.getAllProductsDetails();
}
%>
<div id="filterProductsDivision">
	<center>
		<form action="filterProducts">
			<label>filter :</label> <input
				placeholder="Search by Name,Brand and Category" name="filter" id="searchInputBox">
			<input type="submit" value="Search" id="searchBox">
		</form>
</center></div>

<div id="allProductsDivision">		
<%
for(ProductDetails productDetails:allProductsDetails)
{

%>
			<div class="productDivision">
			<img src="<%= productDetails.getImageaddress() %>" 
			alt="" class="productImageCss" > 			
			<div id="productDetailsDiv">
			<h2>Product Details</h2> 
			<label for="">Product Name : <%=productDetails.getName() %></label> 
			<label for="">Product Price : <%=productDetails.getPrice() %></label>
			<label for="">Product Brand : <%=productDetails.getBrand() %></label>
			<label for="">Product Discount : <%=productDetails.getDiscount() %></label>
			<label for="">Product Category : <%=productDetails.getCategory() %></label>
			<label for="">Product Quantity : <%=productDetails.getQuantity() %></label>
			</div>	
			<buttons style="display: flex;">
			<form action="addToCart" >
				<input value="<%=productDetails.getId() %>" name="productId" hidden >
				<input value="<%=customerDetails.getId() %>" name="customerId" hidden>
				<input type="submit" value="Add To Cart" id="cartButtonCss">
			</form>	
			<form action="CustomerBuy.jsp">
				<input type="text" value="<%=productDetails.getName() %>" name="pName" id="name" hidden>
				<input type="text" value="<%=productDetails.getBrand() %>" name="brand" id="brand" hidden>
				<input type="text" value="<%=productDetails.getPrice() %>" name="price" id="price" hidden>
				<input type="text" value="<%=productDetails.getDiscount() %>" name="discount" id="discount" hidden>
				<input type="text" value="<%=productDetails.getQuantity() %>" name="quantity" id="quantity" hidden>	
				<input type="text" value="<%= productDetails.getPrice()%>" name="price" id="price" hidden>		
				<input value="<%=productDetails.getId() %>" name="productId"  id="productId" hidden>
				<input value="<%=customerDetails.getId() %>" name="customerId" id="customerId" hidden>				
				<input type="submit" value="Buy" id="buyButtonCss">
			</form>	
		</buttons>
		</div>
		
		<%} %>		
		
</div>
<footer>
<div id="footer">
	 <h1 id="titleCss">FashionCommerse</h1>
	 <h4 id="madewithcss">Made with Love</h4>
	</div></footer>
</body>

</html>