package com.bibliotecaCRUD.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.bibliotecaCRUD.bean.Author;
import com.bibliotecaCRUD.bean.Books;
import com.bibliotecaCRUD.bean.Category;
import com.mysql.jdbc.PreparedStatement;



public class BooksDao {
	
	
	public static Connection getConnection() {
		Connection conection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conection=DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca?useUnicode=true&characterEncoding=UTF-8","root",""); 
		}catch (Exception e) {
			System.out.println(e);
		}
		return conection; 
	}

	
	public static int[] createBook(Books book) {
		int[] status = new int[3];
		
		try {
			Connection connection = getConnection();
			connection.setAutoCommit(false);
			
			//Criando o livro no banco de dados
			String SqlQuery = "INSERT INTO Books (book_title, book_blurb, quantity_pages, date_publication) VALUES (?,?,?,?)";
			PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(SqlQuery);
			ps1.setString(1, book.getTittle());
			ps1.setString(2, book.getBlurb());
			ps1.setInt(3, book.getQuantityPages());
			if (book.getDatePublication() != null) {
	            ps1.setDate(4, new java.sql.Date(book.getDatePublication().getTime()));
	        } else {
	            ps1.setNull(4, java.sql.Types.DATE);
	        }
			status[0] = ps1.executeUpdate();
			
			//Criando a editora no banco de dados
			String SqlQueryPublisher = "INSERT INTO Publisher (publisher_name) VALUES (?)";
			PreparedStatement ps2 = (PreparedStatement) connection.prepareStatement(SqlQueryPublisher);
			ps2.setString(1, book.getPublisherName());
			status[1] = ps2.executeUpdate();
			
			//Associando o livro a editora
			String SqlQueryBookByPublisher = "INSERT INTO books_by_publisher (book_id, publisher_id) VALUES (?,?)";
			PreparedStatement ps3 = (PreparedStatement) connection.prepareStatement(SqlQueryBookByPublisher);
			ps3.setInt(1, book.getId());
			ps3.setInt(2, book.getPublisherID());
			status[2] = ps3.executeUpdate();
			
			connection.commit();
	        ps1.close();
	        ps2.close();
	        ps3.close();
	        connection.close();
	        
		}catch(Exception e) {
			System.out.println(e);
		}
		return status;
	}
	

	public static int[] updateBook(Books book) {
		int[] status = new int[2];
		
		try {
			Connection connection = getConnection();
			connection.setAutoCommit(false);
			String SqlQuery = "UPDATE Books SET book_title=?, book_blurb=?, quantity_pages=?, date_publication=? WHERE book_id=?";
			  PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(SqlQuery);
		        ps1.setString(1, book.getTittle());
		        ps1.setString(2, book.getBlurb());
		        ps1.setInt(3, book.getQuantityPages());
		        if (book.getDatePublication() != null) {
		            ps1.setDate(4, new java.sql.Date(book.getDatePublication().getTime()));
		        } else {
		            ps1.setNull(4, java.sql.Types.DATE);
		        }
		        ps1.setInt(5, book.getId());
		        status[0] = ps1.executeUpdate();
		        
		       PreparedStatement ps2 = (PreparedStatement) connection.prepareStatement("UPDATE Publisher SET publisher_name=? WHERE publisher_id=?");
		        ps2.setString(1, book.getPublisherName());
		        ps2.setInt(2, book.getPublisherID());
		        status[1] = ps2.executeUpdate();
		        
		        connection.commit();
		        ps1.close();
		        ps2.close();
		        connection.close();
		       //Verificando se o metodo está recebendo corretamente os valores do objeto atraves da URL
		        System.out.println("ID : " + book.getId());
		        System.out.println("Title : " + book.getTittle());
		        System.out.println("Editora : " +book.getPublisherName());
		        System.out.println("Sinopse : " + book.getBlurb());
		        System.out.println("Quantidade : " + book.getQuantityPages());
		        System.out.println("Data : " + book.getDatePublication());
		        System.out.println("Status Q1 : " + status[0]);
		        System.out.println("Status Q2 : " + status[1]);
		}catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	
	
	public static Books getRecordById(int id) {
		Books book = null;
		
		try {
			Connection connection = getConnection();
			
			
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement("SELECT b.*, p.publisher_name FROM Books b JOIN Publisher p ON b.publisher_id = p.publisher_id WHERE b.book_id=?");
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			
			while(result.next()) {
				book = new Books();
				book.setId(result.getInt("book_id"));
				book.setTittle(result.getString("book_title"));
				book.setPublisherName(result.getString("publisher_name"));
				book.setPublisherID(result.getInt("publisher_id"));
				book.setBlurb(result.getString("book_blurb"));
				book.setQuantityPages(result.getInt("quantity_pages"));
				book.setDatePublication(result.getDate("date_publication"));
			}
			ps.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return book;
	}
	
	
	
	public static List<Books> getAllBooks(){
		List<Books> listOfBooks = new ArrayList<>();
		
		try {
			Connection conection = getConnection();
			PreparedStatement ps = (PreparedStatement) conection.prepareStatement("SELECT b.*, p.publisher_name, a.author_id, a.author_name, c.category_id, c.category_name " +
	                "FROM Books b " +
	                "INNER JOIN Publisher p ON b.publisher_id = p.publisher_id " +
	                "INNER JOIN book_by_author ba ON b.book_id = ba.book_id " +
	                "INNER JOIN Authors a ON ba.author_id = a.author_id " +
	                "INNER JOIN book_by_category bc ON b.book_id = bc.book_id " +
	                "INNER JOIN Category c ON bc.category_id = c.category_id");
			ResultSet result = ps.executeQuery();

			while(result.next()) {
				Books book = new Books();
				book.setId(result.getInt("book_id"));
				book.setTittle(result.getString("book_title"));
				book.setPublisherID(result.getInt("publisher_id"));
				book.setPublisherName(result.getString("publisher_name"));
				book.setQuantityPages(result.getInt("quantity_pages"));
				book.setBlurb(result.getString("book_blurb"));
				book.setDatePublication(result.getDate("date_publication"));
				
				Author author = new Author();
				author.setAuthor_name(result.getString("author_name"));
			
				Category category = new Category();
				category.setNameCategory(result.getString("category_name"));
				
				book.setAuthor(author);
				book.setCategory(category);
				listOfBooks.add(book);
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		return listOfBooks; 
	}
	
	public static int getPublisherIdByName(String publisherName) {
		int publisherId = 0; 
		
		try {
			Connection connection = getConnection();
			String SqlQuery = "SELECT publisher_id FROM Publisher WHERE publisher_name = ?";
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(SqlQuery);
			ps.setString(1, publisherName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) { // Verifica se há alguma linha retornada pela consulta
		         publisherId = rs.getInt("publisher_id"); // Obtém o ID da editora
		   }
		        
		    rs.close();
		    ps.close();
		    connection.close();
		}catch(Exception e) {
			System.out.println(e);
		}
		return publisherId;
	}
	
	public static boolean checkPublisherExistence(String publisherName) {
	    boolean exists = false;

	    try {
	        Connection connection = getConnection();
	        String query = "SELECT * FROM Publisher WHERE publisher_name = ?";
	        PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
	        ps.setString(1, publisherName);

	        ResultSet rs = ps.executeQuery();
	        exists = rs.next(); // Se houver um resultado, a editora existe

	        rs.close();
	        ps.close();
	        connection.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return exists;
	}
	
	
}
