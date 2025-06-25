<%@page import="com.FashionCommerce.DAO.OrderDAOImpl"%>
<%@page import="com.FashionCommerce.DAO.OrderDAO"%>
<%@page import="com.FashionCommerce.model.OrderDetails"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="com.FashionCommerce.DAO.PaymentDAOImpl"%>
<%@page import="com.FashionCommerce.DAO.PaymentDAO"%>
<%@page import="com.FashionCommerce.model.PaymentDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String paymentType=request.getParameter("paymentType");
PaymentDetails paymentDetails=(PaymentDetails)session.getAttribute("PaymentDetails1");
paymentDetails.setPaymentType(paymentType);
System.out.println("In paymentStatus"+paymentDetails);


PaymentDAO paymentDAO=new PaymentDAOImpl();
int result=paymentDAO.insertPaymentDetails(paymentDetails);

OrderDetails orderDetails=new OrderDetails();

orderDetails.setCustomerId(paymentDetails.getCustomerId());
orderDetails.setProductId(paymentDetails.getProductId());
orderDetails.setPuchaseQuantity(paymentDetails.getQuantity());
orderDetails.setPurchaseDate(paymentDetails.getPaymentDate());
orderDetails.setTotoalPurchasePrice(paymentDetails.getAmount());

OrderDAO orderDAO=new OrderDAOImpl();
int result1 =orderDAO.insertOrderDetails(orderDetails);
PrintWriter writer=response.getWriter();
if(result>0 && result1>0){
	RequestDispatcher requestDispatcher=request.getRequestDispatcher("RedirectToHomePage.html");
	requestDispatcher.include(request, response);
	writer.println("<center><h3>Payment Successfull</h3></center>");
}
else{
	RequestDispatcher dispatcher=request.getRequestDispatcher("PaymentType.jsp");
	dispatcher.forward(request, response);
	writer.println("<center><h3>Payment failed</h3></center>");
}
%>
</body>
</html>