package com.chainsys.medik.model;

import java.sql.Blob;
import java.sql.Date;

public class CartItem {
	private int productId;
    private String productName;
    private int productPrice;
    private byte[] productImage;
    private int quantity;
    private int cartId;
   private Date expDate;
public int getProductId() {
	return productId;
}
public void setProductId(int productId) {
	this.productId = productId;
}
public String getProductName() {
	return productName;
}
public void setProductName(String productName) {
	this.productName = productName;
}
public int getProductPrice() {
	return productPrice;
}
public void setProductPrice(int productPrice) {
	this.productPrice = productPrice;
}
public byte[] getProductImage() {
	return productImage;
}
public void setProductImage(byte[] productImage) {
	this.productImage = productImage;
}
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getCartId() {
	return cartId;
}
public void setCartId(int cartId) {
	this.cartId = cartId;
}
public Date getExpDate() {
	return expDate;
}
public void setExpDate(Date expDate) {
	this.expDate = expDate;
}
public CartItem(int productId, String productName, int productPrice, byte[] productImage, int quantity, int cartId,
		Date expDate) {
	super();
	this.productId = productId;
	this.productName = productName;
	this.productPrice = productPrice;
	this.productImage = productImage;
	this.quantity = quantity;
	this.cartId = cartId;
	this.expDate = expDate;
}
public CartItem() {
	
}

   
}
