<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Medik Pharmacy</title>
<style>
/* Existing styles */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background: linear-gradient(to bottom,#ebf6f7,#cfeefd);
    height:1000px;
}

header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 10px 20px;
    box-shadow: 0 5px 3px -2px rgba(0, 0, 0, 0.3);
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
    margin-left:-30px;
    margin-bottom:20px;
}

h1 {
    font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, sans-serif;
    font-size: 24px;
    margin: 0;
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

.cart {
    position: relative;
}

.cart img {
    height: 25px;
}

main {
    padding: 20px;
}

.order-medicines {
    background-color: #008080;
    color: white;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    margin: 20px 0;
}

.order-medicines input[type="text"] {
    padding: 10px;
    border-radius: 5px;
    border: none;
    width: 250px;
}

.order-medicines button {
    padding: 10px 20px;
    border-radius: 5px;
    border: none;
    background-color: #006666;
    color: white;
    cursor: pointer;
}

.order-medicines button:hover {
    background-color: #004c4c;
}

.category-cards {
    display: flex;
    justify-content: center;
    gap: 50px;
    flex-wrap: wrap;
    margin-top: 20px;
}

/* Updated card styles */
.card {
    width: 250px;
    height: 250px;
    border-radius: 15px;
    overflow: hidden;
    position: relative;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    transition: transform 0.3s, box-shadow 0.3s;
    background-color: #ffffff;
}

.card img {
    width: 100%;
    height: 70%;
    object-fit: cover;
    border-bottom: 1px solid #eee;
    transition: transform 0.3s;
}

.card a {
    display: block;
    height: 100%;
    color: inherit;
    text-decoration: none;
}
.card h3 {
    position: absolute;
    bottom: 15px;
    left: 50%;
    transform: translateX(-50%);
    color: black;
    font-size: 15px;
    margin: 0;
    text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.1);
}

.card:hover {
    transform: translateY(-10px);
    box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
}

.card:hover img {
    transform: scale(1.05);
}

footer {
    background-color: #e9ecef;
    padding: 20px 0;
    text-align: center;
    border-top: 1px solid #e9ecef;
    width: 100%;
}

.cart-count {
    position: absolute;
    top: -4px;
    right: 29px;
    background-color: red;
    color: white;
    border-radius: 50%;
    padding: 2px 6px;
    font-size: 12px;
    font-weight: bold;
    line-height: 1;
    text-align: center;
}

a {
    text-decoration: none;
    color: white;
}

.order-medicines {
    background: url('images1/tablets3.jpg') no-repeat center center;
    background-size: cover;
    padding: 20px;
    border-radius: 10px;
    text-align: center;
    max-width: 1100px;
    margin: 20px auto;
    position: relative;
}

.order-medicines::before {
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    background: rgba(224, 247, 250, 0.15);
    border-radius: 10px;
    z-index: 1;
}

.order-medicines h2 {
    color: black;
    position: relative;
    z-index: 2;
}

.order-medicines h4 {
    color: black;
    position: relative;
    z-index: 2;
}

.msg {
    color: black;
}

.order-medicines div {
    margin: 10px 0;
    font-size: 18px;
    position: relative;
    z-index: 2;
}

.order-medicines button {
    background-color: #00838f;
    color: white;
    border: none;
    padding: 10px 20px;
    font-size: 16px;
    border-radius: 5px;
    cursor: pointer;
    position: relative;
    z-index: 2;
}

.order-medicines button:hover {
    background-color: #005662;
}

.browse h3 {
    padding-left: 50px;
    color: black;
}
</style>
</head>
<body>
<%
if(session == null){
response.sendRedirect("pharmacyLogin.jsp");	
}
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
response.setHeader("Pragma", "no-cache"); 
response.setHeader("Expires", "0");
HttpSession session1= request.getSession(); %>
    <form action="home">
        <header>
            <div class="logo-container">
                <img src="images1/pharmlogo2.png" alt="Pharmacy Logo" class="logo">
                <img src="images1/MedikLogo.png" alt="Medik Logo" class="logo1">
            </div>
            <nav class="top-nav">
                <ul>
                    <% if (session.getAttribute("email") != null) { %>
                    <li><a href="#"><img src="images1/hello.png"
                            alt="HiIn Icon">Hello <%=session.getAttribute("name")%></a></li>
                    <li><a href="logout" class="logout-btn"><img
                            src="images1/logout.png" alt="Logout Icon">Logout</a></li>
                    <% } else { %>
                    <li><a href="pharmacyReg.jsp"><img
                            src="images1/register.png" alt="SignIn Icon">SignUp</a></li>
                    <li><a href="pharmacyLogin.jsp"><img
                            src="images1/login.png" alt="LogIn Icon">LogIn</a></li>
                    <% } %>
                    <li><a href="ViewCartServlet" style="text-decoration: none;" class="cart"><img
                            src="images1/carticon.png" alt="Cart Icon">Cart
                            <span class="cart-count"><%= session1.getAttribute("cartItemCount") != null ? session1.getAttribute("cartItemCount") : 0 %></span>
                             </a></li>
                </ul>
            </nav>
        </header>
        <main>
            <div class="order-medicines">
                <h2>Order Medicines Online</h2>
                <div class="msg">
                    <span>1 Lakh+ Products</span> | 
                    <span>Easy Returns</span>
                </div>

                <div>
                   <h4> Click here to search for medicines</h4>
                    <button type="button" onclick="location.href='PharmacyMainServlet'">Search</button>
                 </div> 
            </div>
            <div class="browse">
                <h3>Browse other categories........</h3>
            </div>
            <section class="category-cards">
                <div class="card">
                    <a href="PharmacyMainServlet1">
                        <img src="images1/skingirl2.jpg" alt="SkinCare">
                        <h3>Skin care</h3>
                    </a>
                </div>
                <div class="card">
                    <a href="PharmacyMainServlet2">
                        <img src="images1/healthyfit4.jpg" alt="HealthCare Devices">
                        <h3>Fitness Supplements</h3>
                    </a>
                </div>
                <div class="card">
                    <a href="#">
                        <img src="images1/healthfood.jpg" alt="Health Food and Drinks">
                        <h3>Healthy Food and Drinks</h3>
                    </a>
                </div>
            </section>
        </main>
    </form>
</body>
</html>
