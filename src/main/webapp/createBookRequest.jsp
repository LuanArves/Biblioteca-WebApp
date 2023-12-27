<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bibliotecaCRUD.bean.Books, com.bibliotecaCRUD.bean.Author, com.bibliotecaCRUD.bean.Category"%>
<%@ page import="com.bibliotecaCRUD.dao.BooksDao, com.bibliotecaCRUD.dao.AuthorDao, com.bibliotecaCRUD.dao.CategoryDao, com.bibliotecaCRUD.dao.CreateDao" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>

<head>
<meta charset="UTF-8">
</head>

<%

	Books book = new Books();
	Author author = new Author();
	Category category = new Category();
	request.setCharacterEncoding("UTF-8");
//Obtendo as informações do formulario 
//	livro
String title = request.getParameter("title");
String blurb = request.getParameter("blurb");
int quantityPages = Integer.parseInt(request.getParameter("quantityPages"));
String publisher;
if(request.getParameter("publisher").equals("Outro")){
	publisher = request.getParameter("newPublisher");
}else publisher = request.getParameter("publisher");

String publicationDateStr = request.getParameter("publicationDate");
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
Date publicationDate = sdf.parse(publicationDateStr);

book.setTittle(title);
book.setBlurb(blurb);
book.setQuantityPages(quantityPages);
book.setPublisherName(publisher);
book.setDatePublication(publicationDate);

//	Autor
String authorData;
if(request.getParameter("author").equals("Outro")){
	authorData = request.getParameter("newAuthor");
}else{
	authorData = request.getParameter("author");
}
author.setAuthor_name(authorData);

//	Gênero
String categoryData = request.getParameter("category");
category.setNameCategory(categoryData);

int[] createStatus = CreateDao.createBook(book, author, category);
response.sendRedirect("viewBooks.jsp");

%>
