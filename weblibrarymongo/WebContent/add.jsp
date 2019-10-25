<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Books</title>
</head>
<body>
	<form action="data" method=post>
		Book Title: <input type="text" name="title" placeholder="Book Title"><br>
		Book Author: <input type="text" name="auna" placeholder="Author Name"><br>
		Book Price: <input type="text" name="price" placeholder="Book Price"><br>
		Book Description: <textarea name="desc" placeholder="Description"></textarea><br>
		<input type="submit" value="Add">
	</form>
	<% if (request.getAttribute("counter")!=null)
		out.print("Data Added Successfuly.");
		%>
	<a href="index.jsp">Home</a>
</body>
</html>