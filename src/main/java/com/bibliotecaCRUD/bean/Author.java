package com.bibliotecaCRUD.bean;

public class Author {
	
	private static int AUTOINCREMENT = 1;
	private int author_id;
	private String author_name;
	private String about_author; 
	
	public Author() {
	}
	public Author (String name, String about) {
		this.author_id = AUTOINCREMENT++;
		this.author_name = name;
		this.about_author = about;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAbout_author() {
		return about_author;
	}
	public void setAbout_author(String about_author) {
		this.about_author = about_author;
	}
	
	
	
	
}
