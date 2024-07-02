package com.chainsys.medik.dao;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chainsys.medik.model.Products;
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
        String query = "select * from user where email = ? and password = ?";
        Object[] params = {email, password};
        try {
            return jdbcTemplate.queryForObject(query, new MedikDetailsMapper(), params);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
	
	public boolean isUserExists(String email) {
        String query = "select count(*) from user where email = ?";
        Integer count = jdbcTemplate.queryForObject(query,Integer.class, new Object[]{email});
        return count != null && count > 0;
    }
	
	public boolean addProduct(Products product){
        String sql = "INSERT INTO pharmacy_admin (product_id, product_name, product_image, product_quantity, product_price, description, uses, contains, product_category, mfd_date, exp_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] params = { 
            product.getProductId(), product.getProductName(), product.getProductImage(),
            product.getProductQuantity(), product.getProductPrice(), product.getDescription(),
            product.getUses(), product.getContains(), product.getCategory(), product.getMfdDate(),
            product.getExpDate()
        };
        int row = jdbcTemplate.update(sql, params);
        return row > 0;
    }

	   
}
