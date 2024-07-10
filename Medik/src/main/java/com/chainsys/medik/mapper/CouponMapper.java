package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.medik.model.Coupon;

public class CouponMapper implements RowMapper<Coupon>{

	@Override
	public Coupon mapRow(ResultSet rs, int rowNum) throws SQLException {
		 Coupon coupon = new Coupon();
		 
		 coupon.setCouponId(rs.getInt("coupon_id"));
		 coupon.setCouponCode(rs.getString("coupon_code"));
		 coupon.setDiscount(rs.getInt("discount"));
		 coupon.setValidity(rs.getDate("validity"));
		 return coupon;

	}

}
