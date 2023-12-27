package com.bibliotecaCRUD.bean;

public class User {
	
	private static int AUTOINCREMENT = 1;	
	private int userId;
	private String userName;
	private String userLastName;
	private String userEmail;
	private String userPhone;
	//private String addressID;
	
	public User() {
		
	}
	
	public User(String userName, String userLastName, String userEmail, String userPhone) {
		this.userId = AUTOINCREMENT++;
		this.userName = userName;
		this.userLastName = userLastName;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	} 
	
	
}
