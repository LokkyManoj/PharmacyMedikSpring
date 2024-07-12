package com.chainsys.medik.model;

import java.sql.Date;

public class Coupon {
	private int couponId;
	private String couponCode;
	private int discount;
	private Date validity;
	private int daysLeft;
	private int minAmount;
	public int getMinAmount() {
		return minAmount;
	}
	public void setMinAmount(int minAmount) {
		this.minAmount = minAmount;
	}
	public int getDaysLeft() {
		return daysLeft;
	}
	public void setDaysLeft(int daysLeft) {
		this.daysLeft = daysLeft;
	}
	
	public Coupon(int couponId, String couponCode, int discount, Date validity, int daysLeft, int minAmount) {
		super();
		this.couponId = couponId;
		this.couponCode = couponCode;
		this.discount = discount;
		this.validity = validity;
		this.daysLeft = daysLeft;
		this.minAmount = minAmount;
	}
	public int getCouponId() {
		return couponId;
	}
	public void setCouponId(int couponId) {
		this.couponId = couponId;
	}
	public String getCouponCode() {
		return couponCode;
	}
	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public Date getValidity() {
		return validity;
	}
	public void setValidity(Date validity) {
		this.validity = validity;
	}
	public Coupon() {
		
	}

}
