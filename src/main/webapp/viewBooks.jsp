<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros </title>
<link rel="stylesheet" href="viewBooksStyles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">

</head>
<body>
	<header>
		<nav class="top-menu">
			<ul>
				<li><a href="addbookform.jsp">Cadastrar Livros</a></li>
				<li><a href="#">Cadastrar Autores</a></li>
				<li><a href="#">Cadastrar Editora</a></li>
			</ul>
		</nav>
	</header>
	<%@ page import="com.bibliotecaCRUD.dao.BooksDao, com.bibliotecaCRUD.bean.Books, java.util.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<h1>Lista de livros</h1>
	<%
	List<Books> list = BooksDao.getAllBooks();
	request.setAttribute("list", list);
	%>
	
	<table >
		<tr><th>ID</th>
		<th>Titulo</th>
		<th>Autor(a)</th>
		<th>Genêro</th>
		<th>Editora</th>
		<th>Sinopse</th>
		<th>Número de paginas</th>
		<th>Data de Publicação</th>
		<th>Opções</th>
		
		<c:forEach items="${list}" var="livro">
			<tr>
				<td>${livro.getId()}</td>
				<td>${livro.getTittle()}</td>
				<td>${livro.getAuthor().getAuthor_name()}</td>
				<td>${livro.getCategory().getNameCategory()}</td>
				<td>${livro.getPublisherName()}</td>
				<td>${livro.getBlurb()}</td>
				<td>${livro.getQuantityPages()}</td>
				<td>${livro.getDatePublication()}</td>
				<td><a href="formeditBook.jsp?id=${livro.getId()}">Editar</a>
				&nbsp;|&nbsp;
				<a href="#">Excluir</a></td>	
				
			</tr>
		</c:forEach>
	</table>
	
	
</body>
</html>