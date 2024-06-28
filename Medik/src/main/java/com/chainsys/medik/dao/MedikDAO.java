package com.chainsys.medik.dao;


import com.chainsys.medik.model.User;

public interface MedikDAO {

	public void insertUser(User user);
	public User findUserByEmailAndPassword(String email, String password);
}
