<%@page import="com.FashionCommerce.model.PaymentDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="Style.css">
</head>
<body id="paymenttypebodycss">
<% 
String price=request.getParameter("totalAmount");
String quantity1=request.getParameter("quantity");
System.out.println("In Paymenttype S"+price);
System.out.println("In Paymenttype S"+quantity1);

double totalPrice=0;
int quantity=0;
if(price!=null){
totalPrice=Double.parseDouble(price);
quantity=Integer.parseInt(quantity1);
}
System.out.println("In Paymenttype"+totalPrice);
System.out.println("In Paymenttype"+quantity);


PaymentDetails paymentDetails=(PaymentDetails)session.getAttribute("PaymentDetails");
paymentDetails.setAmount(totalPrice);
paymentDetails.setQuantity(quantity);

System.out.println("In Paymenttype"+paymentDetails);
session.setAttribute("PaymentDetails1", paymentDetails);
%>
<center>
<div id="paymenttypeDetailsFormCss">
<form action="PaymentStatus.jsp">
<h2>Cloose Your payment Type</h2>
<input type="radio" value="Cash On Delevery" name="paymentType">Cash On Delivery <br>
<input type="radio" value="Card pay" name="paymentType">Card pay <br>
<input type="radio" value="UPI" name="paymentType">UPI <br>
<input type="radio" value="EMI" name="paymentType" >EMI <br>
<input type="submit" value="Pay">
</form></div>
</center>
</body>
</html>