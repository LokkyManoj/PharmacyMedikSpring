package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.medik.model.CartItem;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CartItemRowMapper implements RowMapper<CartItem> {
	 private final HttpServletRequest request;

     public CartItemRowMapper(HttpServletRequest request) {
         this.request = request;
     }

	@Override
	public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		
        HttpSession session = request.getSession();
        session.setAttribute("quantity", rs.getInt("quantity"));
        session.setAttribute("cartId", rs.getInt("cart_id"));
        session.setAttribute("product_id", rs.getInt("product_id"));

        int cartId = rs.getInt("cart_id");
        int productId = rs.getInt("product_id");
        String productName = rs.getString("product_name");
        int productPrice = rs.getInt("product_price");
        byte[] productImage = rs.getBytes("product_image");
        int quantity = rs.getInt("quantity");

        return new CartItem(productId, productName, productPrice, productImage, quantity, cartId);

	}

	
}
