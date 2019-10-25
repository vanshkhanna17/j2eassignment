<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Book Details</title>
</head>
<body>
		<div>
		<form action="data">
			<input type="text" name="srch" placeholder="Enter Book Title"><br>
			<button type="submit">Search</button> <button type="submit">Reset</button>
		
		</form><br>
	</div>
	<table border="1">
		<tr>
			<td>Book ID</td><td>Book Title</td><td>Book Author</td><td>Price</td><td>Book Description</td>
		</tr>
		<c:forEach var="zip" items="${list}">
			<tr>
				<td>${zip.id}</td>
				<td>${zip.name}</td>
				<td>${zip.auna}</td>
				<td>${zip.price}</td>
				<td>${zip.desc}</td>
				<td><a class="btn btn-primary" href="review?id=${zip.id }">Add review</a>&nbsp;<a class="btn btn-primary" href="review?id1=${zip.id }">View review</a></td>
				</tr>
			
		</c:forEach>
	</table>
</body>
</html>