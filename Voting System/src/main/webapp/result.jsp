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
		<h1>Voting Result</h1>		
		<p>&nbsp;</p>	
		<form role="form" action="" method="post">
		<h3>Total Voters: <%=request.getAttribute("voterList") %></h3>
		<h3>Total votes: <%=  request.getAttribute("tokenList")%></h3>
		<h3>Percentage of votes: <%=  request.getAttribute("castedVotes")%></h3>
		
	    <table class="table table-striped">
		    <tbody>
		    <tr>
		    <th>Candidate Name</th>
		    <th>Faculty</th>
		    <th>Number of Votes</th>
		    </tr>
			<%  
			List<CandidateVotes> candidates = (List<CandidateVotes>) request.getAttribute("resultSet");
			
			for(CandidateVotes candidate: candidates) { 
			%>
		      <tr>
			      	<td>
			      		<label><%= candidate.getCandidateObj().getFirstName() %> <%= candidate.getCandidateObj().getLastName() %> </label>
			      	</td>
			      	<td> 
			      	<label>
			      	<%=candidate.getCandidateObj().getFaculty() %>
			      	</label>
			      	</td>
			      	<td><label><%=candidate.getVoteCount() %></label></td>
		      </tr>
		     <% } %>
		    </tbody>
		  </table>

		</form>				
	</div>	
</body>
</html>