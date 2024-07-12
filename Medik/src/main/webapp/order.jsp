<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.chainsys.medik.model.Orders"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Medik Pharmacy</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #b2f9ff;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .order-status {
            background-color: #97a1a2;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 600px;
            width: 100%;
        }
        .order-status h2 {
            margin-bottom: 20px;
            color: #333;
        }
        .order-details {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .order-item {
            display: flex;
            justify-content: space-between;
            padding: 10px;
            background-color: #f9f9f9;
            border-radius: 5px;
        }
        .order-item label {
            font-weight: bold;
            color: #555;
        }
        .order-item span {
            color: #777;
        }
        .form-group {
            margin-top: 20px;
            display: flex;
            flex-direction: column;
        }
        .form-group label {
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 100%;
        }
        .form-group button {
            margin-top: 20px;
            padding: 10px;
            background-color: #3d7676;
            border: none;
            border-radius: 5px;
            color: #fff;
            font-size: 16px;
            cursor: pointer;
        }
        .form-group button:hover {
            background-color: black;
        }
    </style>
</head>
<body>
    <% Orders order = new Orders(); %>
    <div class="order-status">
        <div class="order-details">
            <div class="order-item">
                <label>Order Date:</label>
                <span><%= session.getAttribute("orderdate") %></span>
            </div>
            <div class="order-item">
                <label>Expected Delivery Date:</label>
                <span><%=session.getAttribute("expectedDeliveryDate") %></span>
            </div>
            <div class="order-item">
                <label>Order Status:</label>
                <span>Processing</span>
            </div>
            <div class="order-item">
                <label>Total Quantity:</label>
                <span><%=  session.getAttribute("totalQuantity") %></span>
            </div>
            <div class="order-item">
<%--             <input type="hidden" name="couponCode" value="<%=session.getAttribute("couponCode")%>">
 --%>            <%String newAmount=(String) session.getAttribute("newTotal1");%>
            <%if(newAmount!=null){ %>
              <label>Total Price:</label>
                <span><%= newAmount%></span>
            
               <%}else{%> 
            	       <% session.removeAttribute("newTotal1"); %>
            	   
                <label>Total Price:</label>
                <span><%=session.getAttribute("total") %></span>
                   <%} %>
            </div>
        </div>
        <form action="order" method="post">
           <input type="hidden" name="total" value="<%= session.getAttribute("total") %>" />
    <input type="hidden" name="productId" value="<%= session.getAttribute("product_id") %>" />
    <input type="hidden" name="userId" value="<%= session.getAttribute("id") %>" />
    <input type="hidden" name="quantity" value="<%= session.getAttribute("totalQuantity") %>" />
    <input type="hidden" name="status" value="Processing" />
    <input type="hidden" name="orderDate" value="<%= session.getAttribute("orderdate") %>" />
    <input type="hidden" name="expectedDeliveryDate" value="<%= session.getAttribute("expectedDeliveryDate") %>" />
           

            <div class="form-group">
                <label>Add Delivery Address:</label>
                <input type="text" name="address"  value="" min="10" required />
            </div>
            <div class="form-group">
                <button type="submit" name="action" value="placeOrder">Confirm</button>
            </div>
        </form>
    </div>
</body>
</html>
