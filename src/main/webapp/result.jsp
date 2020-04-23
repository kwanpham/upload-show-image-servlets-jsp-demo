<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html"%>
<!DOCTYPE html>
<html>
<head>
    <title>JSP-Servlet Upload file</title>
</head>
<body>
<h2>${message}</h2>

<br/>
<c:if test="${not empty imageName}">
    <img src="/image/${imageName}" alt = "Error">
</c:if>

<br/>

<a href="index.jsp">Back to Upload page</a>
</body>
</html>