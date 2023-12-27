package com.bibliotecaCRUD.bean;

public class Publisher {
	
	private static int AUTOINCREMENT = 1;
	private int publisher_id; 
	private String name_publisher;
	
	public Publisher () {
		
	}
	public Publisher (String name) {
		this.setPublisher_id(AUTOINCREMENT++);
		this.name_publisher = name;
	}
	
	public String getName_publisher() {
		return name_publisher;
	}
	public void setName_publisher(String name_publisher) {
		this.name_publisher = name_publisher;
	}
	public int getPublisher_id() {
		return publisher_id;
	}
	public void setPublisher_id(int publisher_id) {
		this.publisher_id = publisher_id;
	} 
	
	
}
