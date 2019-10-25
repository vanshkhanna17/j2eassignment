<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import = "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="review" method="post">
		<input type="hidden" value="${id}" name="id"/>
		<textarea name="txt"></textarea>
		<input type="submit" value="Add">
	</form>	

</body>
</html>