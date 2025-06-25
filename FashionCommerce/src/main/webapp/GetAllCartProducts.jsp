<%@page import="com.FashionCommerce.model.CustomerDetails"%>
<%@page import="com.FashionCommerce.DAO.CartDAOImpl"%>
<%@page import="com.FashionCommerce.DAO.CartDAO"%>
<%@page import="com.FashionCommerce.model.ProductDetails"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
/* Cart CSS*/
*{
padding: 0px;
margin: 0px;
}
#cartHeader{
	height: 80px;
	width: 100%;
	background-color:black;
}
#mycarttext{
	font-family: sans-serif;
	font-size: x-large;
	padding-left: 150px;
	padding-top: 30px;
	color: white;
	
}
#mainBodyCSS{
width:100%;
height:700px;
background: linear-gradient(skyblue,rgb(250, 250, 251));
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
}</style>
</head>
<body>
<header id="cartHeader">
<h1 id="mycarttext">My Cart</h1>
</header>
<main id="mainBodyCSS">
<% CustomerDetails loginCustomerDetails=(CustomerDetails)session.getAttribute("LoginCustomerDetails");
int customerId=loginCustomerDetails.getId();
CartDAO cartDAO=new CartDAOImpl();
List<ProductDetails> cartproductsList=cartDAO.getAllCartProducts(customerId);%>

<div id="allProductsDivision">		
			<%
for(ProductDetails productDetails:cartproductsList)
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
			<form action="CustomerBuy.jsp">
				<input type="text" value="<%=productDetails.getName() %>" name="pName" id="name" hidden>
				<input type="text" value="<%=productDetails.getBrand() %>" name="brand" id="brand" hidden>
				<input type="text" value="<%=productDetails.getPrice() %>" name="price" id="price" hidden>
				<input type="text" value="<%=productDetails.getDiscount() %>" name="discount" id="discount" hidden>
				<input type="text" value="<%=productDetails.getQuantity() %>" name="quantity" id="quantity" hidden>			
				<input value="<%=productDetails.getId() %>" name="productId"  id="productId" hidden>
				<input value="<%=loginCustomerDetails.getId() %>" name="customerId" id="customerId" hidden>				
				<input type="submit" value="Buy" id="buyButtonCss">
			</form>	
		</buttons>
		</div>
		
		<%} %>		
		
</div>
</main>
</body>
</html>