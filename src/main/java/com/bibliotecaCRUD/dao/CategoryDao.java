package com.bibliotecaCRUD.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecaCRUD.bean.Category;
import com.mysql.jdbc.PreparedStatement;

public class CategoryDao {

	
	
	public static List<Category> listOfCategorys(){
		List<Category> listOfCategorys = new ArrayList<>();
		
		try {
			String SQL = "SELECT * FROM Category";
			Connection con = BooksDao.getConnection();
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(SQL);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				Category category = new Category();
				
				category.setId(result.getInt("category_id"));
				category.setNameCategory(result.getString("category_name"));
				listOfCategorys.add(category);
				
			}
		
		}catch (Exception e) {
			System.out.println(e);
		}
		
		return listOfCategorys; 
	}
	
	public static int getCategoryIdByName(String categoryName) {
		int categoryId = 0;
		
		try {
			Connection con = BooksDao.getConnection();
			String SqlQuery = "SELECT category_id FROM Category WHERE category_name = ?";
			PreparedStatement ps = (PreparedStatement) con.prepareStatement(SqlQuery);
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				categoryId = rs.getInt("category_id");
			}
			rs.close();
		    ps.close();
		    con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return categoryId;
	}
	
	
}
