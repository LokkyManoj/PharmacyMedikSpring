package com.chainsys.medik.dao;


import org.springframework.stereotype.Repository;

import com.chainsys.medik.model.Products;
import com.chainsys.medik.model.User;
@Repository
public interface MedikDAO {

	public void insertUser(User user);
	public User findUserByEmailAndPassword(String email, String password);
	public boolean isUserExists(String email);
	public boolean addProduct(Products product);
	
	
}
