<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de livro</title>
<link rel="stylesheet" href="viewBooksStyles.css">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
</head>
<body>
	<%@ page import="com.bibliotecaCRUD.dao.CategoryDao, com.bibliotecaCRUD.bean.Category, com.bibliotecaCRUD.dao.BooksDao, com.bibliotecaCRUD.bean.Books,com.bibliotecaCRUD.dao.AuthorDao, com.bibliotecaCRUD.bean.Author, java.util.*"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%
		List<Category> list = CategoryDao.listOfCategorys();
		request.setAttribute("list", list);
		List<Books> listBooks = BooksDao.getAllBooks();
		request.setAttribute("listBooks", listBooks);
		List<Author> listAuthors = AuthorDao.listOfAuthors();
		
		request.setAttribute("listAuth", listAuthors);
	%>
	<div class="add-form">
	<h1 class="titulo-addform"> Cadastrar novo livro</h1>
		<form action="createBookRequest.jsp" method="post" accept-charset="ISO-8859-1x" class="addbook-form">
			
			<label for="authorName">Autor</label>
			<select name="author" id="authorSelect" >
				<c:forEach items="${listAuthors}" var="author">
					<option value="${author.author_name()}">${author.author_name()}</option>
				</c:forEach>
				<% for (Author author : listAuthors) { %>
        			<option value="<%= author.getAuthor_id() %>"><%= author.getAuthor_name() %></option>
    			<% } %>
					<option value="Outro">Outro</option>
			</select>
			
			<label for="newAuthor" id="newAuthorText" style="display: none;">Adicionando um(a) novo(a) Autor(a)</label>
			<input type="text" id="newAuthorInput" name="newAuthor" value="" style="display: none;">
			
			<label for="bookTitle">Titulo do livro</label>
			<input type="text" id="bookTitle" name="title" value=""/>
			
			<label for="bookBlurb">Sinopse Livro</label>
			<textarea id="bookBlurb" name="blurb" rows="5" cols="40"></textarea>
			
			<label for="bookpages">Numero de páginas</label>
			<input type="number" id="bookpages" name="quantityPages" value=""/>
			
			<label for="bookgenre">Gênero</label>
			<select name="category">
				<c:forEach items="${list}" var="genero">
					<option value="${genero.getNameCategory()}">${genero.getNameCategory()}</option>
				</c:forEach>
			</select>
			
			<label for="publisherName">Editora</label>
			<select name="publisher" id="publisherSelect">
				<c:forEach items="${listBooks}" var="publisher">
					<option value="${publisher.getPublisherName()}">${publisher.getPublisherName()}</option>
				</c:forEach>
					<option value="Outro">Outro</option>
			</select>
			<label for="newPublisher" id='newPublisherLabel' style="display: none;">Adicionando uma nova Editora</label>
			<input type="text" id="newPublisherInput" name="newPublisher" value="" style="display: none;">
			
			<label for="publicationDate">Data de publicação</label>
			<input type="date" id="publicationDate" name="publicationDate">
			
			<input type="submit" value="Cadastrar livro">
	</form>
</div>
<c:out value="${author.getAuthor_name()}"/> 
<script>
	document.getElementById('publisherSelect').addEventListener('change', function(){
		var select = document.getElementById('publisherSelect');
		var newPublisherInput = document.getElementById('newPublisherInput');
		var newPublisherText = document.getElementById('newPublisherLabel');
		
		
		
		if(select.value === 'Outro'){
			newPublisherInput.style.display = 'block';
			newPublisherText.style.display = 'block';
		}else{
			newPublisherInput.style.display = 'none'; 
			newPublisherText.style.display = 'none'; 
		}
		
	});
	document.getElementById('authorSelect').addEventListener('change', function(){
		var selectAuthor = document.getElementById('authorSelect');
		var newAuthorInput = document.getElementById('newAuthorInput');
		var newAuthorText = document.getElementById('newAuthorText'); 
		
		if (selectAuthor.value === 'Outro') {
			newAuthorInput.style.display = 'block';
			newAuthorText.style.display = 'block';
		} else {
			newAuthorInput.style.display = 'none';
			newAuthorText.style.display = 'none'; 
		}
	});
</script>
</body>
</html>