package com.chainsys.medik.model;


public class User {
	private int id;
	private String userName;
	private String mobileNo;
	private String email;
	private String password;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public User(int id, String userName, String mobileNo, String email, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.mobileNo = mobileNo;
		this.email = email;
		this.password = password;
	}
	public User() {
			}
	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", mobileNo=" + mobileNo + ", email=" + email
				+ ", password=" + password + "]";
	}
	
}
