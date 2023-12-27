package com.bibliotecaCRUD.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bibliotecaCRUD.bean.Author;
import com.bibliotecaCRUD.bean.Books;
import com.bibliotecaCRUD.bean.Category;

public class TestBooksDao {

	 public static void main(String[] args) {
	       // testUpdateBook();
	        testeCreate();
		 //testeBuscaDeAutores();
	 }

	 
	    public static void testUpdateBook() {
	        // Criando um objeto Books com valores fixos
	        Books book = new Books();
	        book.setId(5);  // Substitua pelo ID do livro que deseja atualizar
	        book.setPublisherID(3);
	        book.setTittle("Novo Título");
	        book.setPublisherName("Nova editora");
	        book.setBlurb("Nova descrição do livro");
	        book.setQuantityPages(200);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	        try {
	            Date publicationDate = sdf.parse("2023-11-30"); // Substitua pela data desejada
	            book.setDatePublication(publicationDate);
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        // Chama o método updateBook
	        int[] status = BooksDao.updateBook(book);
	        int status1 = status[0];
	        int status2 = status[1];
	        
	        if (status1 == status2) {
	            System.out.println("Atualização bem-sucedida!");
	        } else {
	            System.out.println("A atualização falhou!");
	        }
	        
	   
	    }
	        public static void testeCreate() {
	            // Criando instâncias de Books, Author e Category com dados de exemplo
	            Books book = new Books();
	            book.setTittle("Livro de Teste");
	            book.setBlurb("Descrição do livro de teste");
	            book.setQuantityPages(200);
	            book.setDatePublication(new Date(System.currentTimeMillis())); // Data atual
	            book.setPublisherName("HarperCollins");

	            Author author = new Author();
	            author.setAuthor_name("Stephen King");
	            author.setAbout_author("Sobre o autor de teste");

	            Category category = new Category();
	            category.setNameCategory("Ficção");
	            
	            

	            try {
	                // Chamando o método createBook e verificando o retorno
	                int[] status = CreateDao.createBook(book, author, category);

	                // Verificando o status de retorno do método
	                if (status != null && status.length > 0) {
	                    System.out.println("Status da operação:");
	                    for (int i = 0; i < status.length; i++) {
	                        System.out.println("Status " + (i + 1) + ": " + status[i]);
	                    }
	                } else {
	                    System.out.println("Erro ao executar a operação.");
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    
	        public static void testeBuscaDeAutores() {
	        	   List<Author> lista = AuthorDao.listOfAuthors();
	   	        for (Author author : lista) {
	   	            System.out.println("ID: " + author.getAuthor_id() + ", Name: " + author.getAuthor_name());
	   	        }
	        }

	
}
