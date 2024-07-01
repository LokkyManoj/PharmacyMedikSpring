package com.chainsys.medik.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.medik.model.User;
import com.chainsys.medik.mapper.MedikDetailsMapper;


@Repository
public class MedikDAOImpl implements MedikDAO{
	@Autowired
	JdbcTemplate jdbcTemplate;
	public void insertUser(User user)  {
		String save="insert into user(name,mobile_no,email,password) values(?,?,?,?)";
		Object[] params= {user.getUserName(),user.getMobileNo(),user.getEmail(),user.getPassword()};
		int rows=jdbcTemplate.update(save, params);
		System.out.println("Rows Affected:"+rows);
	}
	@Override
	public User findUserByEmailAndPassword(String email, String password) {
		String query = "SELECT * FROM user WHERE email = ? AND password = ?";
        Object[] params = {email, password};
        return jdbcTemplate.queryForObject(query,new MedikDetailsMapper(),params);
	}
	
	 
	   
}
