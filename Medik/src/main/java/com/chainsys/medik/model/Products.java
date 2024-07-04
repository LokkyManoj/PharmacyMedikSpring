package com.chainsys.medik.model;

import java.sql.Date;

public class Products {

	 private int productId;
	    private String productName;
	    private byte[] productImage;
	    private int productQuantity;
	    private int productPrice;
	    private String description;
	    private String uses;
	    private String contains;
	    private String category;
	    private Date mfdDate;
	    private Date expDate;
	    private int isDeleted;
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
		public byte[] getProductImage() {
			return productImage;
		}
		
		public void setProductImage(byte[] productImage ) {
			this.productImage = productImage;
		}
		public int getProductQuantity() {
			return productQuantity;
		}
		public void setProductQuantity(int productQuantity) {
			this.productQuantity = productQuantity;
		}
		public int getProductPrice() {
			return productPrice;
		}
		public void setProductPrice(int productPrice) {
			this.productPrice = productPrice;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getUses() {
			return uses;
		}
		public void setUses(String uses) {
			this.uses = uses;
		}
		public String getContains() {
			return contains;
		}
		public void setContains(String contains) {
			this.contains = contains;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public Date getMfdDate() {
			return mfdDate;
		}
		public void setMfdDate(Date mfdDate) {
			this.mfdDate = mfdDate;
		}
		public Date getExpDate() {
			return expDate;
		}
		public void setExpDate(Date expDate) {
			this.expDate = expDate;
		}
		public int getIsDeleted() {
			return isDeleted;
		}
		public void setIsDeleted(int isDeleted) {
			this.isDeleted = isDeleted;
		}
		public Products(int productId, String productName, byte[] productImage, int productQuantity, int productPrice,
				String description, String uses, String contains, String category, Date mfdDate, Date expDate,
				int isDeleted) {
			super();
			this.productId = productId;
			this.productName = productName;
			this.productImage = productImage;
			this.productQuantity = productQuantity;
			this.productPrice = productPrice;
			this.description = description;
			this.uses = uses;
			this.contains = contains;
			this.category = category;
			this.mfdDate = mfdDate;
			this.expDate = expDate;
			this.isDeleted = isDeleted;
		}
		public Products() {
			
		}
		@Override
		public String toString() {
			return "Products [productId=" + productId + ", productName=" + productName + ", productImage="
					+ productImage + ", productQuantity=" + productQuantity + ", productPrice=" + productPrice
					+ ", description=" + description + ", uses=" + uses + ", contains=" + contains + ", category="
					+ category + ", mfdDate=" + mfdDate + ", expDate=" + expDate + ", isDeleted=" + isDeleted + "]";
		}
	    
}