<%@page import="java.sql.Time"%>
<%@page import="java.sql.Date"%>
<%@page import="java.time.LocalTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="com.FashionCommerce.model.PaymentDetails"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details</title>
<style type="text/css">
/*CustomerBuy.jsp css
*/
  #paymentDetailsFormCss{
	width: 60vh ;
    height: 80vh;
    display: flex;
    justify-content: space-evenly;
    justify-content: center;
    align-items: center;
    flex-direction: column;
   border-radius: 10px;
   background: radial-gradient(rgb(250, 250, 251) 50%,skyblue);
}
</style>
</head>
<body id="customerbuybodycss">
    
<%
int customerId=Integer.parseInt(request.getParameter("customerId"));
int productId=Integer.parseInt(request.getParameter("productId"));
String name=request.getParameter("pName");
String brand=request.getParameter("brand");
double amount=Double.parseDouble(request.getParameter("price"));
double discount=Double.parseDouble(request.getParameter("discount"));

double price=Double.parseDouble(request.getParameter("price"));
int quantity=Integer.parseInt(request.getParameter("quantity"));

LocalDate tdate=LocalDate.now();
LocalTime ttime=LocalTime.now();
Date date=Date.valueOf(tdate);
Time time=Time.valueOf(ttime);

PaymentDetails paymentDetails=new PaymentDetails();

System.out.println("BBuy"+paymentDetails);

paymentDetails.setCustomerId(customerId);
paymentDetails.setProductId(productId);
paymentDetails.setPaymentDate(date);
paymentDetails.setPaymentTime(time);
paymentDetails.setQuantity(quantity);
paymentDetails.setAmount(price);

System.out.println("ABuy"+paymentDetails);
session.setAttribute("PaymentDetails", paymentDetails);

%>
    <center>
    <div id="paymentDetailsFormCss">
        <form action="PaymentType.jsp" >
            <h1>Product Details</h1>
            <label for="name">Product Name:</label>
            <input type="text" id="name" readonly> <br><br>
            <label for="brand">Brand:</label>
            <input type="text" id="brand" readonly> <br><br>

            <label for="price">Price (₹):</label>
            <input type="text" id="price" readonly> <br><br>
            <label for="quantity">Quantity:</label>   
            <input type="number" placeholder="Enter Quantity" id="quantity" name="quantity" min="1"> <br><br>
            
            <label for="totalAmount">Total Amount (₹):</label>
            <input type="text" id="totalAmount" name="totalAmount" readonly> <br><br>

            <input type="submit" Value="Confirm">
            <input type="button" value="Cancel" onclick="DisplayAllProducts.jsp">
        </form>
        </div>
    </center>

    <script>
        function getQueryParam(param) {
            const params = new URLSearchParams(window.location.search);
            return params.get(param);
        }

        // Retrieve parameters from URL
        const pName = getQueryParam("pName");
        const brand = getQueryParam("brand");
        const price = parseFloat(getQueryParam("price")) || 0; 
        const discount = parseFloat(getQueryParam("discount")) || 0; 

        // Populate input fields with retrieved data
        if (pName) {
            document.getElementById("name").value = pName;
        }
        if (brand) {
            document.getElementById("brand").value = brand;
        }
        if (price) {
            document.getElementById("price").value = price.toFixed(2);
        }

        // Calculate total amount dynamically
        const quantityInput = document.getElementById("quantity");
        const totalAmountInput = document.getElementById("totalAmount");

        quantityInput.addEventListener("input", () => {
            const quantity = parseInt(quantityInput.value) || 0;
            const discountedPrice = price - (price * discount / 100);
            const totalAmount = discountedPrice * quantity;

            // Display calculated total amount
            totalAmountInput.value = totalAmount.toFixed(2);
        });
    </script>
</body>
</html>