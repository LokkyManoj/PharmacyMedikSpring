package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.medik.model.Coupon;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class CouponMapper implements RowMapper<Coupon>{
//	private final HttpServletRequest request;
//
//    public CouponMapper(HttpServletRequest request) {
//        this.request = request;
//    }


	@Override
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Coupon coupon = new Coupon();
		 
		 coupon.setCouponId(rs.getInt("coupon_id"));
		 coupon.setCouponCode(rs.getString("coupon_code"));
		 coupon.setDiscount(rs.getInt("discount"));
		 coupon.setValidity(rs.getDate("validity"));
		 coupon.setMinAmount(rs.getInt("min_amount"));
		 return coupon;

	}


}
