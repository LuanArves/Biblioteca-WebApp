<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.bibliotecaCRUD.bean.Books, com.bibliotecaCRUD.dao.BooksDao"%>
<%@ page import="java.util.*" %>
<head>
<meta charset="UTF-8">
</head>




<%
    String id = request.getParameter("id");
    Books livro = BooksDao.getRecordById(Integer.parseInt(id)); 
    request.setCharacterEncoding("UTF-8");
    // Obtendo os valores do formulário
    String title = request.getParameter("title");
    String publisherName = request.getParameter("publisherName");
    String blurb = request.getParameter("blurb");
    int quantityPages = Integer.parseInt(request.getParameter("quantityPages"));
   
    
    // Setando os valores no objeto livro
    livro.setTittle(title);
    livro.setPublisherName(publisherName);
    livro.setBlurb(blurb);
    livro.setQuantityPages(quantityPages);
    
    	
    // Atualizando os dados
    int[] updateStatus = BooksDao.updateBook(livro);
    int statusBook = updateStatus[0];
    int statusPublisher = updateStatus[1];
	if(statusBook == statusPublisher){
		response.sendRedirect("viewBooks.jsp");
	}else response.sendRedirect("erro.jsp");
%>