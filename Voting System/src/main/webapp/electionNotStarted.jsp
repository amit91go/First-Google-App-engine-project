<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h3>Election not yet started.</h3>
<h3>Please login to vote on <%= request.getAttribute("startDate") %> at <%= request.getAttribute("startTime")%></h3>
</body>
</html>