<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Edição</title>
<link rel="stylesheet" href="styles.css">
<link rel="stylesheet" href="viewBooksStyles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" >
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
</head>
<body>
	<%@page import="com.bibliotecaCRUD.bean.Books, com.bibliotecaCRUD.dao.BooksDao"%>
	
	<%
		String id = request.getParameter("id");
		Books livro = BooksDao.getRecordById(Integer.parseInt(id)); 
	%>
	
	<div class="form-container">
	<h1 class="titulo-formulario">Atualizando cadastro do livro </h1>
	<form action="editBookRequest.jsp" method="post" accept-charset="ISO-8859-1">
		<input type="hidden" name="id" value="<%= livro.getId()%>"/>
		
        <label for="bookTitle">Titulo do livro: </label>
        <input type="text" id="bookTitle" name="title" value="<%= livro.getTittle()%>"/>
        
        <label for="publisherId">Editora : </label>
        <input type="text" id="publisherId" name="publisherName" value="<%= livro.getPublisherName()%>"/>  
    
        <label for="bookBlurb">Descrição: </label>
        <textarea id="bookBlurb" name="blurb" rows="5" cols="40"><%= livro.getBlurb()%></textarea>

        <label for="bookPages">Número de páginas</label>
        <input type="number" id="bookPages" name="quantityPages" value="<%= livro.getQuantityPages()%>"/>        
        
        <label for="publicationDate">Data de publicação</label>
        <input type="date" id="publicationDate" name="datePublication" value="<%= livro.getDatePublication()%>"/>
        
         <input type="submit" value="Atualizar Livro">
    
	</form>
	</div>
</body>
</html>