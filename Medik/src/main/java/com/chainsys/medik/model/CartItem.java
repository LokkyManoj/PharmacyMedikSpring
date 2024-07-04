package com.chainsys.medik.model;

import java.sql.Blob;

public class CartItem {
	private int productId;
    private String productName;
    private int productPrice;
    private byte[] productImage;
    private int quantity;
    private int cartId;
   

    public CartItem(int productId, String productName, int productPrice, byte[] productImage, int quantity,int cartId) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
        this.quantity = quantity;
        this.cartId=cartId;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public byte[] getProductImage() {
        return productImage;
    }

    public int getQuantity() {
        return quantity;
    }
    
    public int getCartId() {
    	return cartId;
    }
}
