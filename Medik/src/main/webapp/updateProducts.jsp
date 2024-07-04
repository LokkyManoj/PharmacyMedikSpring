<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.Blob"%>
<%@ page import="java.util.Base64"%>
<%@ page import="com.chainsys.medik.model.Products"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<title>Medik Pharmacy</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
}

form {
	background-color: #fff;
	padding: 20px;
	border-radius: 5px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	width: 400px;
	margin: 20px auto;
}

label {
	display: block;
	margin-bottom: 5px;
}

input[type="text"], input[type="number"], textarea {
	width: 100%;
	padding: 8px;
	margin-bottom: 10px;
	border: 1px solid #ccc;
	border-radius: 4px;
	box-sizing: border-box;
}

button[type="submit"] {
	background-color: #3d7676;
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

button[type="submit"]:hover {
	background-color: black;
}
</style>
<meta charset="UTF-8">
<title>Update Product</title>
</head>
<body>
	<%
	Products product = (Products) request.getAttribute("product");
	if (product != null) {
	%>
	<form action="updateProduct" method="post">
		<input type="hidden" name="product_id"
			value="<%=product.getProductId()%>"> <label>Product
			Name:</label> <input type="text" name="product_name"
			value="<%=product.getProductName()%>" required> <br> <label>Product
			Price:</label> <input type="number" name="product_price"
			value="<%=product.getProductPrice()%>" required> <br>
		Quantity:</label> <input type="number" name="product_quantity"
			value="<%=product.getProductQuantity()%>" required> <br>

		<label>Description:</label>
		<textarea name="description" required><%=product.getDescription()%></textarea>
		<br> <label>Uses:</label>
		<textarea name="uses" required><%=product.getUses()%></textarea>
		<br> <label>Contains:</label>
		<textarea name="contains" required><%=product.getContains()%></textarea>
		<br> <label>Category:</label> <input type="text" name="product_category"
			value="<%=product.getCategory()%>" required>
			
			<br> <label>MFD Date:</label> <input type="date" name="mfd_date"
			value="<%=product.getMfdDate()%>" required>
			
			<br> <label>EXP Date:</label> <input type="date" name="exp_date"
			value="<%=product.getExpDate()%>" required>
			 <br>
			 <br>
		<button type="submit">Update</button>
	</form>
	<%
	} else {
	%>
	<p>Product not found.</p>
	<%
	}
	%>
</body>
</html>
