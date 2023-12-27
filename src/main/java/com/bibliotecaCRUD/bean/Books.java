package com.bibliotecaCRUD.bean;

import java.util.Date;

public class Books {
		
		private int id;
		private String title;
		private String genre; 
		private String blurb;
		private int quantityPages;
		private int publisherID;
		private String publisherName;
		private Date datePublication;
		private Category category;
		private Author author;
		
		public Books() {
		}
		
		public Books(int id, String title, String genre, String description) {
			this.id = id;
			this.title = title;
			this.genre = genre;
			this.blurb = description;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getTittle() {
			return title;
		}
		public void setTittle(String tittle) {
			this.title = tittle;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getBlurb() {
			return blurb;
		}
		public void setBlurb(String description) {
			this.blurb = description;
		}

		public int getPublisherID() {
			return publisherID;
		}

		public void setPublisherID(int publisherID) {
			this.publisherID = publisherID;
		}

		public int getQuantityPages() {
			return quantityPages;
		}

		public void setQuantityPages(int quantityPages) {
			this.quantityPages = quantityPages;
		}

		public Date getDatePublication() {
			return datePublication;
		}

		public void setDatePublication(Date datePublication) {
			this.datePublication = datePublication;
		}

		public String getPublisherName() {
			return publisherName;
		}

		public void setPublisherName(String publisherName) {
			this.publisherName = publisherName;
		}
		public Category getCategory() {
			return category;
		}

		public void setCategory(Category category) {
			this.category = category;
		}

		public Author getAuthor() {
			return author;
		}

		public void setAuthor(Author author) {
			this.author = author;
		}

		
}
