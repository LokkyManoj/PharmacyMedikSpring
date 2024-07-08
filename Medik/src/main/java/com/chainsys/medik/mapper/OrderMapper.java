package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.medik.model.Orders;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class OrderMapper implements RowMapper<Orders> {
	 


	@Override
	public Orders mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Orders order = new Orders();

	        order.setOrderId(rs.getInt("order_id"));
	        order.setProductId(rs.getInt("product_id"));
	        order.setOrderDate(rs.getDate("order_date"));
	        order.setQuantity(rs.getInt("quantity"));
	        order.setStatus(rs.getString("status"));
	        order.setExpectedDeliveryDate(rs.getDate("expected_delivery_date"));
	        order.setUserId(rs.getInt("user_id"));
	        order.setAddress(rs.getString("address"));
	        return order;
	
	}

}
