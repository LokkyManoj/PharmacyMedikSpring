<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List" %>
<%@ page import="com.chainsys.medik.model.Coupon" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Coupon Details</title>
</head>
<body>
<h2>Coupon Details</h2>

<table border="1">
    <tr>
        <th>Coupon Code</th>
        <th>Discount</th>
        <th>Validity</th>
    </tr>
    <%
        List<Coupon> coupons = (List<Coupon>) request.getAttribute("coupons");
        if (coupons != null) {
            for (Coupon coupon : coupons) {
    %>
        <tr>
            <td><%= coupon.getCouponCode() %></td>
            <td><%= coupon.getDiscount() %></td>
            <td><%= coupon.getValidity() %></td>
            <td>
                <form action="deleteUser" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="<%= coupon.getCouponId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
    <%
            }
        } else {
    %>
        <tr>
            <td colspan="4">No coupons available</td>
        </tr>
    <%
        }
    %>
</table>
</body>
</html>
