<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.List"%>
<%@ page import ="com.chainsys.medik.model.CartItem"%>
<%-- <%@ page import ="com.chainsys.medik.model.Order"%>
 --%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Medik Pharmacy</title>
<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #b2f9ff;
        margin: 0;
        padding: 20px;
    }

    h2 {
        color: #333;
        text-align: center;
        margin-bottom: 20px;
    }

    .container {
        display: flex;
        flex-wrap: wrap;
        justify-content: space-around;
    }

    .product-card {
        border: 1px solid #ccc;
        padding: 16px;
        margin: 16px;
        border-radius: 8px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 25%;
        box-sizing: border-box;
        background-color: #fff;
        text-align: center;
    }

    .product-card img {
        max-width: 100%;
        height: auto;
        border-radius: 8px;
    }

    .product-info {
        margin-top: 12px;
    }

    .product-info p {
        margin: 8px 0;
    }

    .product-info strong {
        display: block;
        margin-bottom: 4px;
        color: #555;
    }

    .product-actions {
        margin-top: 12px;
        display: flex;
        justify-content: center;
        gap: 10px;
    }

    .product-actions button {
        background-color: #3d7676;
        color: #fff;
        border: none;
        padding: 8px 15px;
        cursor: pointer;
        border-radius: 15px;
        font-size: 15px;
        transition: background-color 0.3s ease;
    }

    .product-actions button.delete {
        background-color: #dc3545;
    }

    .product-actions button:hover {
        background-color: #060707;
    }

    .product-actions button.delete:hover {
        background-color: #060707;
    }
    
    .no-items {
        text-align: center;
        font-size: 18px;
        color: #777;
        margin-top: 20px;
    }
    
    .back-icon {
        position: fixed;
        top: 20px;
        left: 20px;
        font-size: 24px;
        cursor: pointer;
        z-index: 9999;
    }
    .delete-btn {
        background-color: transparent;
        color: #dc3545;
        border: none;
        padding: 0;
        cursor: pointer;
        transition: color 0.3s ease;
    }
    
    .delete-btn:hover {
        color: #060707;
    }
    .delete-btn img {
        height: 15px;
    }
    .update-quantity {
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .update-quantity input[type="number"] {
        width: 60px;
        padding: 5px;
        margin-right: 5px;
    }
    .update-quantity button {
        background-color: #3d7676;
        color: #fff;
        border: none;
        padding: 5px 10px;
        cursor: pointer;
        border-radius: 5px;
        font-size: 14px;
    }
    .update-quantity button:hover {
        background-color: black;
    }
</style>
  <% int total = 0; %>
  <%int totalQuantity=0; %>
  <%HttpSession session1= request.getSession(); %>
</head>
<body>
    <a href="searchMedicine" class="back-icon"><i class='bx bx-arrow-back'></i></a>

    <h2>Your Cart</h2>
    <div class="container">
        <%   
/*         Order order = new Order();
 */        List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
        if (cartItems != null && !cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
        %>
        <div class="product-card">
            <h3><%= item.getProductName() %></h3>
            <% if (item.getProductImage() != null) { %>
                <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(item.getProductImage()) %>" alt="<%= item.getProductName() %>">
            <% } %>
          
            <div class="product-info">
                <p><strong>Price:</strong> Rs.<%= item.getProductPrice() %></p>
                <p><strong>Quantity:</strong></p>
                <div class="update-quantity">
                
                
                    <form action="updateCartQuantity" method="post">
                    
                    
<%--                     <input type="hidden" name="user_id" value="<%= userId %>">
 --%>                        <input type="hidden" name="cartId" value="<%= item.getCartId() %>" />
                        <input type="number" name="quantity" value="<%=item.getQuantity() %>" min="1" />
                        <input type="hidden" name="quantity" value="<%=totalQuantity+=item.getQuantity() %>" min="1" />
                        
                        <button type="submit">&#10004;</button>
                    </form>
                </div>
                <input type="hidden" value= <%= total += (item.getProductPrice() * item.getQuantity()) %> >
<%--                 <%=session.setAttribute("total", total)%>
 --%>                <p><strong>Total Price:</strong> Rs.<%= item.getProductPrice() * item.getQuantity() %></p>
            </div> 
            <div class="product-actions">
                <form action="placeOrder" method="post">
                    <input type="hidden" name="action" value="buy">
                    <input type="hidden" name="total" value=<%= item.getProductPrice() * item.getQuantity() %>>
                    <button type="submit">Buy</button>
                </form>
                <form action="deleteCartItems" method="post">
                 <%
        HttpSession session2 = request.getSession(false);
        Integer userId = (Integer) session2.getAttribute("id");
    %>
                <input type="hidden" name="user_id" value="<%= userId %>">
                <input type="hidden" name="cart_id" value="<%= session1.getAttribute("cartId") %>">
                    <input type="hidden" name="productId" value="<%= item.getProductId() %>">
                    <button type="submit" class="delete-btn">
                        <img src="images1/dustbin.png" alt="Delete" width="20" height="20">
                    </button>
                </form>
            </div>
        </div>
        <%   
            }
        } else {
        %>
        <p class="no-items">No items added to the cart.</p>
        <%  
        }
        session.setAttribute("total", total);
        session.setAttribute("totalQuantity",totalQuantity);
        %>
    </div>
    <center><p>Total Amount: Rs.<%= total %></p></center> 
    <div class="product-actions" style="text-align: center;">
        <form action="placeOrder" method="get">
            <input type="hidden" name="action" value="buy">
            <input type="hidden" name="total" value=<%= total %>>
             <input type="hidden" name="productId" value="<%= session.getAttribute("product_id") %>">
        <input type="hidden" name="userId" value="<%= session.getAttribute("id") %>">
        <input type="hidden" name="quantity" value="<%= session.getAttribute("quantity") %>">
        <input type="hidden" name="total" value="<%= session.getAttribute("total") %>">
            <button type="submit">Buy Now</button>
        </form>
    </div>
</body>
</html>