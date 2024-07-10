<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Base64"%>
<%@ page import="java.util.List"%>
<%@ page import="com.chainsys.medik.model.CartItem"%>
<%@ page import="com.chainsys.medik.model.Products"%>
<%@ page import="com.chainsys.medik.model.Coupon"%>


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

       .sidebar {
    position: fixed;
    top: 0;
    right: -400px; /* Start hidden off-screen */
    width: 400px;
    height: 100%;
    background-color: #fff;
    box-shadow: -2px 0 5px rgba(0, 0, 0, 0.3);
    transition: right 0.3s ease;
    z-index: 1000;
    overflow-y: auto; /* Enable scrolling if content exceeds height */
}

.sidebar.open {
    right: 0; /* Slide in when open */
}

.sidebar-content {
    padding: 20px;
}

.sidebar-close {
    position: absolute;
    top: 10px;
    right: 10px;
    cursor: pointer;
    font-size: 20px;
}

/* Coupon CSS */
.coupon {
    width: 100%; /* Adjust width to fit sidebar */
    height: auto; /* Adjust height accordingly */
    border-radius: 10px;
    overflow: hidden;
    margin: auto;
    filter: drop-shadow(0 3px 5px rgba(0, 0, 0, 0.5));
    position: relative; /* Adjust positioning as needed */
    text-transform: uppercase;
    background: #fff;
    padding: 20px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    margin-bottom: 20px; /* Optional: Add margin to separate from other content */
}

.coupon::before,
.coupon::after {
    content: "";
    position: absolute;
    top: 0;
    width: 50%;
    height: 100%;
    z-index: -1;
}

.coupon::before {
    left: 0;
    background-image: radial-gradient(
        circle at 0 50%,
        transparent 25px,
        gold 26px
    );
}

.coupon::after {
    right: 0;
    background-image: radial-gradient(
        circle at 100% 50%,
        transparent 25px,
        gold 26px
    );
}

 .container {
        display: flex;
        align-items: center;
    }
    .input-field {
        padding: 8px;
        font-size: 16px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-right: 8px;
    }
     .apply-button {
       background-color: #3d7676;
            color: #fff;
            border: none;
            padding: 8px 15px;
            cursor: pointer;
            border-radius: 15px;
            font-size: 15px;
            transition: background-color 0.3s ease;
    }
    </style>

    <script>
    // Function to toggle sidebar visibility
    function toggleSidebar() {
        var sidebar = document.getElementById('sidebar');
        sidebar.classList.toggle('open'); // Toggle the 'open' class to show/hide sidebar
    }

    // Function to close sidebar
    function closeSidebar() {
        var sidebar = document.getElementById('sidebar');
        sidebar.classList.remove('open'); // Remove the 'open' class to hide sidebar
    }
</script>

    <% int total = 0; %>
    <% int totalQuantity = 0; %>
    <% HttpSession session1 = request.getSession(); %>
    <% Products product = new Products(); %>
</head>

<body>
    <a href="searchMedicine" class="back-icon"><i class='bx bx-arrow-back'></i></a>

    <h2>Your Cart</h2>
    <%
    String expiryMessage = (String) session1.getAttribute("expiryMessage");
    if (expiryMessage != null) {
    %>
    <div class="expiry-message" style="color: red; text-align: center;">
        <p><%= expiryMessage %></p>
    </div>
    <%
    }
    %>

    <div class="container">
        <%
        List<CartItem> cartItems = (List<CartItem>) request.getAttribute("cartItems");
        if (cartItems != null && !cartItems.isEmpty()) {
            for (CartItem item : cartItems) {
        %>
        <div class="product-card">
            <h3><%= item.getProductName() %></h3>

            <% if (item.getProductImage() != null) { %>
            <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(item.getProductImage()) %>"
                alt="<%= item.getProductName() %>">
            <% } %>

            <div class="product-info">
                <p><strong>Price:</strong> Rs.<%= item.getProductPrice() %></p>
                <p><strong>Quantity:</strong></p>
                <div class="update-quantity">
                    <form action="updateCartQuantity" method="post">
                        <input type="hidden" name="cartId" value="<%= item.getCartId() %>" />
                        <input type="number" name="quantity" value="<%= item.getQuantity() %>" min="1" />
                        <input type="hidden" name="quantity" value="<%=totalQuantity+=item.getQuantity() %>" min="1" />
                        <button type="submit">&#10004;</button>
                    </form>
                </div>
                <input type="hidden" value= <%= total += (item.getProductPrice() * item.getQuantity()) %> >
                <p><strong>Total Price:</strong> Rs.<%= item.getProductPrice() * item.getQuantity() %></p>

                <% // Check for expiry message and display if present
                    String itemExpiryMessage = (String) session1.getAttribute("expiryMessage");
                    if (itemExpiryMessage != null && !itemExpiryMessage.isEmpty()) {
                %>
                <p class="expiry-message" style="color: red; text-align: center;"><%= itemExpiryMessage %></p>
                <% } %>
            </div>
            <div class="product-actions">
                <form action="placeOrder" method="post">
                    <input type="hidden" name="action" value="buy">
                    <input type="hidden" name="total"
                        value=<%= item.getProductPrice() * item.getQuantity() %>>
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
        session.setAttribute("totalQuantity", totalQuantity);
        %>
    </div>

   
  <%String newAmount=(String) request.getAttribute("newTotal");%>
      
    <%if(newAmount!=null){ %>
    <center>
        <p>Total Amount: Rs.<%= newAmount %></p>
    </center>
    
    <%}else{ %> 
     <center>
        <p>Total Amount: Rs.<%= total %></p>
    </center>
   <%} %>

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
        <form action="click1" method="post">
    <button type="button" onclick="toggleSidebar()">Apply Coupon</button>
    </form>
    </div>

    <!-- Apply Coupon Button -->
    

    <!-- Coupon HTML (hidden by default) -->
    <div id="sidebar" class="sidebar">
    <div class="sidebar-content">
        <!-- Coupon HTML -->
        <%
    List<Coupon> coupons = (List<Coupon>) request.getAttribute("coupons");
    if (coupons != null && !coupons.isEmpty()) {
        for (Coupon coupon : coupons) {
    %>
        <div class="coupon">
            <div class="left">
                <div>Enjoy Your Coupon</div>
            </div>
            <div class="center">
                <div>
                    <h2><%= coupon.getDiscount() %>% OFF</h2>
                    <h3>Coupon Code :<%= coupon.getCouponCode() %></h3>
                    <small>Valid until <%= coupon.getValidity() %></small>
                </div>
            </div>
            
        </div>
        <%
            }
        } else {
    %>
            <h4>No coupons available</h4>
    <%
        }
    %>
        <!-- End Coupon HTML -->
        
        <div class="container">
        <form action="applyCoupon" method="post">
    <input type="text" class="input-field"  name="couponCode" placeholder="Enter Coupon Code" required>
    <button class="apply-button">Apply</button>
    </form>
</div>
        <div class="sidebar-close" onclick="closeSidebar()">&#10005;</div>
    </div>
</div>
</body>

</html>
