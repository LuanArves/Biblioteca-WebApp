package com.bibliotecaCRUD.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecaCRUD.bean.Author;
import com.mysql.jdbc.PreparedStatement;

public class AuthorDao {

	
	public static List<Author> listOfAuthors() {
		List<Author> listOfAuthors = new ArrayList<>();
		
		try {
			String SQL = "SELECT * FROM Authors";
			Connection con = BooksDao.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(SQL);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Author author = new Author();
				
				author.setAuthor_id(result.getInt("author_id"));
				author.setAuthor_name(result.getString("author_name"));
				author.setAbout_author(result.getString("about"));
				listOfAuthors.add(author);				
			}
		}catch (SQLException e) {
			e.printStackTrace();
			//throw e;
		}
		return listOfAuthors; 
	}
	public static boolean checkAuthorExistence(String authorName) {
		boolean exists = false;
		
		try {
			Connection connection = BooksDao.getConnection();
			String Query = "SELECT * FROM Authors WHERE author_name = ?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(Query);
			ps.setString(1, authorName);
			
			ResultSet rs = ps.executeQuery();
			exists = rs.next();
			
			rs.close();
			ps.close();
			connection.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return exists;
	}
	
		public static int getAuthorByName(String authorName) {
			int authorId = 0;
			try {
				Connection connection = BooksDao.getConnection();
				String Query = "SELECT author_id FROM Authors WHERE author_name = ?";
				PreparedStatement ps = (PreparedStatement) connection.prepareStatement(Query);
				ps.setString(1, authorName);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					authorId = rs.getInt("author_id");
				}
				rs.close();
				ps.close();
				connection.close();
			}catch (Exception e) {
				System.out.println(e);
			}
			return authorId;
		}
}
