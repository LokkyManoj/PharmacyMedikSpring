package com.chainsys.medik.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chainsys.medik.model.User;

public class MedikDetailsMapper implements RowMapper<User> {
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		User user= new User();
		 user.setId(rs.getInt("id"));
		user.setUserName(rs.getString("name"));
		user.setMobileNo(rs.getString("mobile_no"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		return user;
	}

	
}

