<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.chainsys.medik.model.Orders"%>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Success</title>
</head>
<body>
    <h1>Order Placed Successfully!</h1>
    <div>
        <p>Order Date: <%= session.getAttribute("orderdate") %></p>
         <p>Quantity: <%=  session.getAttribute("totalQuantity") %></p>
        <p>Total Price: <%=session.getAttribute("total") %></p>
         <p>Expected Delivery Date: <%=session.getAttribute("expectedDeliveryDate") %></p>
    </div> 
    <a href="index.jsp">Go back to home</a>
</body>
</html>
