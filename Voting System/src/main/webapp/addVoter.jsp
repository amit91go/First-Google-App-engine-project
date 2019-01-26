<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Voter</title>
<!-- Bootstrap -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/js/bootstrap.min.js"></script>
<style>
 .input-group{
 width: 25% !important;
 }
 .input-group-addon{
 width: 15% !important;
 }
</style>
</head>
<body>
	<div class="container">
		<h1>New Voter</h1>
		
		<p>&nbsp;</p>
		
		<form role="form" action="/AddVoterServlet" method="post">
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">First Name</span>
					<input name="div_firstName" type="text" class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Last Name</span>
					<input name="div_lastName" type="text" class="form-control" required>
				</div>
			</div>
			<div class="form-group">
				<div class="input-group">
					<span class="input-group-addon">Email Id</span>
					<input name="div_emailId" type="text" class="form-control" required>
				</div>
			</div>
			<button type="submit" class="btn btn-success" style="width:15%">Add Candidate</button>
		</form>				
	</div>	
</body>
</html>