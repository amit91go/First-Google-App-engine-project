<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Voting System</title>
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
 .logoutLblPos{
   position:fixed;
   right:100px;
   top:35px;
 }
</style>
</head>
<body>
<% 
if(session.getAttribute("user") == null)
{
	response.sendRedirect("login.jsp");
}
%>
	<div class="container">
		<p>&nbsp;</p>
		<h2>Add New Candidate</h2>
		
		<p>&nbsp;</p>
		<form role="form" action="/AddCandidateServlet" method="post">
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
					<span class="input-group-addon">Faculty</span>
					<input name="div_faculty" type="text" class="form-control" required>
				</div>
			</div>
			<button type="submit" class="btn btn-success" style="width:15%">Add Candidate</button>
		</form>	
		<form align="right" name="form1" method="post" action="/LogoutServlet">
  			<label class="logoutLblPos">
  			<input name="submit2" type="submit" id="submit2" value="Log Out">
  			</label>
		</form>		
	</div>	
</body>
</html>