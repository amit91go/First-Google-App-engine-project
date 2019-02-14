<%@ page import="java.util.*" %>
<%@ page import="de.uniba.wiai.lspi.demo.votingSystem.Objects.*" %>
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
</head>
<body>
	<div class="container">
		<h1>Voting Ballot</h1>		
		<p>&nbsp;</p>	
		<h3>Candidates List</h3>	
		<form role="form" action="/vote" method="post">
	    <table class="table table-striped">
		    <tbody>
			<%  
			List<Candidate> candidates = (List<Candidate>) request.getAttribute("candidateList");
			
			for(Candidate candidate: candidates) { 
			%>
		      <tr>
			      <div class="radio">
			      	<td>
			      		<label><input type="radio" name="voteRadio" value="<%=candidate.getFirstName()%>"> <%= candidate.getFirstName() %> <%= candidate.getLastName() %></label>
			      	</td>
			      </div>
		      </tr>
		     <% } %>
		    </tbody>
		  </table>
		  <div class="form-group">
			  <label >Voting Token:</label>
			  <input type="text" class="form-control" name="div_token">
		  </div>
		  <div class="btn-group" role="group">
			<button type="submit" class="btn btn-success">VOTE</button>
		 </div>
		 <div>
		     <input type="hidden" name="emailId" value="<%= request.getAttribute("emailId")%>">
		 </div>
		</form>				
	</div>	
</body>
</html>