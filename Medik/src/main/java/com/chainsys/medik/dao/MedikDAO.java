package com.chainsys.medik.dao;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.chainsys.medik.model.CartItem;
import com.chainsys.medik.model.Orders;
import com.chainsys.medik.model.Products;
import com.chainsys.medik.model.User;

import jakarta.servlet.http.HttpServletRequest;
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
	 public List<CartItem> getCartItemsByUserId(int userId, HttpServletRequest request);
	 public int getCartItemCount(int userId);
	 public boolean deleteCartItemsByUserId(int cartId);
	 public boolean updateCartQuantity(int cartId,int quantity);
	 public boolean placeOrder(Orders order);
}
