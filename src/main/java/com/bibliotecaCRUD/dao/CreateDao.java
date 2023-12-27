package com.bibliotecaCRUD.dao;

import java.sql.Connection;
import java.sql.ResultSet;

import com.bibliotecaCRUD.bean.Author;
import com.bibliotecaCRUD.bean.Books;
import com.bibliotecaCRUD.bean.Category;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class CreateDao {
	
	
	public static int[] createBook(Books book, Author author, Category category) {
		int[] status = new int[6];
		
		try {
			Connection connection = BooksDao.getConnection();
			connection.setAutoCommit(false);
			
			// Verificando as informações da editora
			int publisherID = 0;

			if (BooksDao.checkPublisherExistence(book.getPublisherName())) { //caso exista o id será gravado no publisherID
			    publisherID = BooksDao.getPublisherIdByName(book.getPublisherName());
			} else { // Caso nao exista será criado na tabela e depois retorna o id para a associação na tabela livros
			    String SqlQueryPublisher = "INSERT INTO Publisher (publisher_name) VALUES (?)";
			    PreparedStatement ps2 = (PreparedStatement) connection.prepareStatement(SqlQueryPublisher, Statement.RETURN_GENERATED_KEYS);
			    ps2.setString(1, book.getPublisherName());
			    status[1] = ps2.executeUpdate();

			    ResultSet generatedKeysPublisher = ps2.getGeneratedKeys();
			    if (generatedKeysPublisher.next()) {
			        publisherID = generatedKeysPublisher.getInt(1);
			    }
			    generatedKeysPublisher.close();
			    ps2.close();
			}

			// Criando o livro no banco de dados
			String SqlQuery = "INSERT INTO Books (book_title, book_blurb, quantity_pages, date_publication, publisher_id) VALUES (?,?,?,?,?)";
			PreparedStatement ps1 = (PreparedStatement) connection.prepareStatement(SqlQuery, Statement.RETURN_GENERATED_KEYS);
			ps1.setString(1, book.getTittle());
			ps1.setString(2, book.getBlurb());
			ps1.setInt(3, book.getQuantityPages());
			if (book.getDatePublication() != null) {
			    ps1.setDate(4, new java.sql.Date(book.getDatePublication().getTime()));
			} else {
			    ps1.setNull(4, java.sql.Types.DATE);
			}
			ps1.setInt(5, publisherID); // Use o ID da editora obtido ou verificado
			status[0] = ps1.executeUpdate();
			ResultSet generatedKeys = ps1.getGeneratedKeys(); // Capturando o id do livro recém gravado no banco
			if (generatedKeys.next()) {
			    int generatedId = generatedKeys.getInt(1);
			    book.setId(generatedId); // Definindo o ID gerado no objeto Book
			}
			
			
			// Associando o livro a categoria
			int categoryId = CategoryDao.getCategoryIdByName(category.getNameCategory()); //Pegando o ID do genero pelo nome do genero
			String SqlQueryBookByCategory = "INSERT INTO book_by_category (book_id, category_id) VALUES (?,?)";
			PreparedStatement ps4 = (PreparedStatement) connection.prepareStatement(SqlQueryBookByCategory);
			ps4.setInt(1, book.getId());
			ps4.setInt(2, categoryId);
			status[3] = ps4.executeUpdate();
			
			// Verificando as informaçẽs de autores 
			if(AuthorDao.checkAuthorExistence(author.getAuthor_name())) {
				author.setAuthor_id(AuthorDao.getAuthorByName(author.getAuthor_name()));
			}else {
				//Criando o Autor na tabela Authors
				String SqlQueryCreateAuthor = "INSERT INTO Authors (author_name, about) VALUES (?,?)";
				PreparedStatement ps5 = (PreparedStatement) connection.prepareStatement(SqlQueryCreateAuthor,  Statement.RETURN_GENERATED_KEYS);
				ps5.setString(1, author.getAuthor_name());
				ps5.setString(2, author.getAbout_author());
				status[4] = ps5.executeUpdate();
			
				ResultSet generatedKeysAuthor = ps5.getGeneratedKeys(); //capturando o id de um autor recém gravada no banco
				if (generatedKeysAuthor.next()) {
				    int generatedAuthorId = generatedKeysAuthor.getInt(1);
				    	// Defina o ID gerado no objeto Book
				    	author.setAuthor_id(generatedAuthorId);
				    
				}
				generatedKeysAuthor.close();
				ps5.close();
				
			}
			
			// Associando o livro ao autor na tabela books_by_author
			String SqlQueryBooksByAuthor = "INSERT INTO book_by_author (book_id, author_id) VALUES (?,?)";
			PreparedStatement ps6 = (PreparedStatement) connection.prepareStatement(SqlQueryBooksByAuthor);
			ps6.setInt(1, book.getId());
			ps6.setInt(2, author.getAuthor_id());
			status[5] = ps6.executeUpdate();
			
			
			
			
			  // Verificação dos dados recebidos do objeto book
	        System.out.println("Verificando os dados do livro:");
	        System.out.println("Título do livro: " + book.getTittle());
	        System.out.println("Sinopse: " + book.getBlurb());
	        System.out.println("Número de páginas: " + book.getQuantityPages());
	        System.out.println("Nome do editor: " + book.getPublisherName());
	        System.out.println("Data de publicação: " + book.getDatePublication());

	        // Verificação dos dados do objeto author
	        System.out.println("\nVerificando os dados do autor:");
	        System.out.println("Nome do autor: " + author.getAuthor_name());
	        System.out.println("Sobre o autor: " + author.getAbout_author());

	        // Verificação dos dados do objeto category
	        System.out.println("\nVerificando os dados da categoria:");
	        System.out.println("Nome da categoria: " + category.getNameCategory());

			
			
			
			
			
			
			connection.commit();
			ps1.close();
			ps4.close();
			ps6.close();
			generatedKeys.close();
			connection.close();
		
		}catch (Exception e) {
			System.out.println(e);
		}
		return status;
	}
	 
	
}
