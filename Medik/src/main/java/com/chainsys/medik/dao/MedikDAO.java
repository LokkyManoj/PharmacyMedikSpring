package com.chainsys.medik.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.chainsys.medik.model.Products;
import com.chainsys.medik.model.User;
@Repository
public interface MedikDAO {

	public void insertUser(User user);
	public User findUserByEmailAndPassword(String email, String password);
	public boolean isUserExists(String email);
	public boolean addProduct(Products product);
	public List<Products> getAllProducts();
	public void updateProducts(Products product);
	public Products findProductById(int productId);
	public void deleteProduct(int productId);
	public List<Products> searchProductsByName(String name);
	 public List<Products>getProductsByCategory(String category, int isDeleted);
	 public boolean addToCart(int userId, int productId, int quantity);
}
