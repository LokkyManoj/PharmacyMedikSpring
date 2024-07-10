<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Medik Pharmacy</title>
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css'
	rel='stylesheet'>
    <style>
    .home-icon{
font-size: 23px;
	cursor: pointer;
	display: flex;
	margin-left:1235px;
	
}

.home-icon {
	position: relative;
}
    
    
    body {
    font-family: Arial, sans-serif;
    background-color: #f8f8f8;
    margin: 0;
    padding: 20px;
    background:url('images1/medbg1.jpg');
	 background-repeat: no-repeat;
  background-size: cover;
   background-position: center;
  min-height: 100vh;
	
}

h2 {
    color: #333;
    text-align: center;
}

form {
    background-color: #fff;
    padding: 20px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    width: 400px;
    margin: 0 auto;
}

label {
    display: block;
    margin-bottom: 5px;
    font-weight: bold;
}

input[type="text"],
input[type="number"],
textarea {
    width: calc(100% - 20px);
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type="file"] {
    width: calc(100% - 20px);
    padding: 8px;
    margin-bottom: 10px;
    box-sizing: border-box;
}

input[type="submit"] {
    background-color:#3d7676;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color:black;
}

a {
    display: block;
    text-align: center;
    margin-top: 10px;
    text-decoration: none;
    color: #333;
}

a:hover {
    text-decoration: underline;
}


.top-nav ul {
	list-style: none;
	margin: 0;
	padding: 0;
	display: flex;
	gap: 20px;
}

.top-nav a {
	text-decoration: none;
	color: black;
	font-size: 16px;
	display: flex;
	align-items: center;
}

.top-nav img {
	margin-right: 5px;
	height: 20px;
}

.top-nav a:hover {
	text-decoration: underline;
}

    body {
	font-family: 'Gill Sans', sans-serif;
	margin: 0;
	padding: 0;
}
   header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
     background-color:#83d4eb;
	border-bottom: 1px solid black;
}

.logo-container {
    display: flex;
    align-items: center;
}

.logo {
    height: 50px;
    margin-right: 10px;
}

.logo1 {
    height: 50px;
    margin-left: -30px;
    margin-bottom: 20px;
} 
.error {
    color: red;
    text-align: center;
    margin-top: 10px;
    font-weight: bold;
    background-color: #ffe6e6;
    padding: 10px;
    border: 1px solid red;
    border-radius: 5px;
}
    </style>
</head>

<body>
<header>

            <div class="logo-container">
                <img src="images1/pharmlogo2.png" alt="Pharmacy Logo" class="logo">
                <img src="images1/MedikLogo.png" alt="Medik Logo" class="logo1">
            </div>
            </header>
        <form action="addCoupon" method="post">
    
        <label for="coupon_code">CouponCode:</label>
        <input type="text"  name="coupon_code" required  placeholder="CouponCode"><br><br>
        
         <label for="discount">Discount:</label>
        <input type="number" id="discount" name="discount" placeholder="Discount"  required><br><br>

        <label for="validity">Validity:</label>
        <input type="date" id="validity" name="validity" required><br><br>
       
       <center> <input type="submit" value="Add Coupon"></center>
        <a href="ViewProductServlet">View the Coupons</a>
    </form>
    
    
</body>
</html>