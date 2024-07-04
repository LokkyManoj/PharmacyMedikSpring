<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.chainsys.medik.model.Products"%>
<%@ page import="java.util.Base64"%>
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

.back-icon-container {
    position: absolute;
    top: 20px;
    left: 20px;
}

.back-icon-container i {
    color: black;
    font-size: 24px;
    cursor: pointer;
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
    width: 45%;
    box-sizing: border-box;
    background-color: #ffffff;
}

.product-card img {
    max-width: 50%;
    height: 30%;
}

.product-info {
    margin-top: 12px;
}

table {
    width: 100%;
    border-collapse: collapse;
    margin-top: 12px;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 8px;
    text-align: left;
}

.search-container {
    display: flex;
    align-items: center;
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    margin-bottom: 20px;
    width: 500px;
    margin-left: 50px;
}

.search-container input[type="text"] {
    flex: 1;
    border: none;
    padding: 10px;
    font-size: 16px;
}

.search-container button {
    background-color: #007bff;
    color: #fff;
    border: none;
    padding: 10px 15px;
    cursor: pointer;
}

.search-container button:hover {
    background-color: #0056b3;
}

.product-actions {
    margin-top: 12px;
    display: flex;
    justify-content: space-between;
}

.product-actions button {
    background-color: #3d7676;
    color: #fff;
    border: none;
    padding: 8px 12px;
    cursor: pointer;
    border-radius: 4px;
}

.product-actions button.delete {
    background-color: #dc3545;
}


</style>
</head>
<body>
<div class="back-icon-container">
    <a href="adminPage.jsp"><i class='bx bx-arrow-back'></i></a>
</div>
<div class="search-container">
    <form action="viewProducts" method="get">
        <input type="text" name="searchQuery" id="searchInput" placeholder="Search...">
        <button type="submit" name="searchButton">
            <i class='bx bx-search-alt'></i>
        </button>
    </form>
</div>
<h2>Products List</h2>
<div class="container">
    <%
    List<Products> products = (List<Products>) request.getAttribute("products");
    if (products != null && !products.isEmpty()) {
        for (Products product : products) {
    %>
    <div class="product-card">
        <h3><%= product.getProductName() %></h3>
        <%
        if (product.getProductImage() != null) {
        %>
        <img src="data:image/jpeg;base64,<%= Base64.getEncoder().encodeToString(product.getProductImage()) %>" alt="<%= product.getProductName() %>">
        <%
        }
        %>
        <div class="product-info">
            <p><strong>Price:</strong> Rs.<%= product.getProductPrice() %></p>
            <p><strong>Description:</strong> <%= product.getDescription() %></p>
            <p><strong>Product Category:</strong> <%= product.getCategory() %></p>
            <p><strong>Product Quantity:</strong> <%= product.getProductQuantity() %></p>
            <table>
                <tr>
                    <th>Uses</th>
                    <td><%= product.getUses() %></td>
                </tr>
                <tr>
                    <th>Contains</th>
                    <td><%= product.getContains() %></td>
                </tr>
                <tr>
                    <th>Mfd Date</th>
                    <td><%= product.getMfdDate() %></td>
                </tr>
                <tr>
                    <th>Exp Date</th>
                    <td><%= product.getExpDate() %></td>
                </tr>
            </table>
            <div class="product-actions">
                <form action="showUpadteForm" method="get" style="display:inline;">
                    <input type="hidden" name="product_id" value="<%= product.getProductId() %>">
                    <button type="submit">Update</button>
                </form>
                <form action="deleteProduct" method="post" style="display:inline;">
                    <input type="hidden" name="product_id" value="<%= product.getProductId() %>">
                    <button type="submit" class="delete">Delete</button>
                </form>
            </div>
        </div>
    </div>
    <%
        }
    } else {
    %>
    <p>No products available.</p>
    <%
    }
    %>
</div>
</body>
</html>
