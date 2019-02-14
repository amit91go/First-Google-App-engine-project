package de.uniba.wiai.lspi.demo.votingSystem.Servlets;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;


import de.uniba.wiai.lspi.demo.votingSystem.Dao.CandidateDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.ElectionDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.TokenDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.VoteDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.VoterDao;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.CandidateVotes;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Election;


@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(VoteServlet.class.getName());
	
    public VoteServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	try {
    		ElectionDao electionDao = new ElectionDao();
    		Election electionObject = new Election();
    		electionObject = electionDao.getElectionDetails();
    		Date today = new Date();
    		DateFormat sdf = new SimpleDateFormat("hh:mm",Locale.GERMANY); 
    		Date startDateTime = sdf.parse(electionObject.getStartTime());
    		Date endDateTime = sdf.parse(electionObject.getEndTime());
    		java.sql.Timestamp startTime = new java.sql.Timestamp(startDateTime.getTime());
    		java.sql.Timestamp endTime = new java.sql.Timestamp(endDateTime.getTime());
    		java.sql.Timestamp timeNow = new java.sql.Timestamp(today.getTime());
    		log.warning("TimeNow ="+timeNow +" Start = " +startTime + " End =" + endTime);
    		int startTimemins = (startTime.getHours()* 60) + (startTime.getMinutes());
    		int endTimemins = (endTime.getHours()* 60) + (endTime.getMinutes());
    		int timeNowmins = (timeNow.getHours()* 60) + (timeNow.getMinutes());	

    		if(Integer.parseInt(electionObject.getElectionDate().substring(0,4)) == LocalDateTime.now().getYear())
    		{
    			if(Integer.parseInt(electionObject.getElectionDate().substring(5,7)) == LocalDateTime.now().getMonthValue())
    			{
    				if(((Integer.parseInt(electionObject.getElectionDate().substring(8))) > LocalDateTime.now().getDayOfMonth())
    						|| (((Integer.parseInt(electionObject.getElectionDate().substring(8))) == LocalDateTime.now().getDayOfMonth()) && (startTimemins > timeNowmins)))
    				{

    					request.setAttribute("startDate", electionObject.getElectionDate());
    					request.setAttribute("startTime", electionObject.getStartTime());
    					request.getRequestDispatcher("/electionNotStarted.jsp").forward(request, response);
    				}
    				else if((Integer.parseInt(electionObject.getElectionDate().substring(8))) ==  LocalDateTime.now().getDayOfMonth()
    						&& ((startTimemins <= timeNowmins)) && (endTimemins >= timeNowmins))
    				{
    					CandidateDao candidateDao = new CandidateDao();
    					List<Candidate> candidateList = candidateDao.getCandiadates();
    					request.setAttribute("candidateList", candidateList);
    					request.setAttribute("emailId", request.getParameter("emailId"));
    					request.getRequestDispatcher("/vote.jsp").forward(request, response);
    				}
    				
    				else if(((Integer.parseInt(electionObject.getElectionDate().substring(8))) < LocalDateTime.now().getDayOfMonth())
    						|| (((Integer.parseInt(electionObject.getElectionDate().substring(8))) == LocalDateTime.now().getDayOfMonth()) && (endTimemins < timeNowmins))) 
    					{

    					VoterDao voterDao = new VoterDao();
    					int voterList = voterDao.GetVoterListCount();
    					TokenDao tokenDao = new TokenDao();
    					int tokenList = tokenDao.getExpiredTokens();
    					float castedVotes = ((tokenList/voterList)*100);
    					request.setAttribute("voterList", voterList);
    					request.setAttribute("tokenList", tokenList);
    					request.setAttribute("castedVotes", castedVotes);
    					request.setAttribute("resultSet", getCandidateVotes());
    					//response.getWriter().append("results").append(request.getContextPath());
    					request.getRequestDispatcher("/result.jsp").forward(request, response);
    				}
    				else
    				{
        					request.setAttribute("startDate", electionObject.getElectionDate());
        					request.setAttribute("startTime", electionObject.getStartTime());
        					request.getRequestDispatcher("/electionNotStarted.jsp").forward(request, response);
    				}
    			}
    			else
    			{
					request.setAttribute("startDate", electionObject.getElectionDate());
					request.setAttribute("startTime", electionObject.getStartTime());
					request.getRequestDispatcher("/electionNotStarted.jsp").forward(request, response);
    				
    			}
    		}
    		else
    		{
				request.setAttribute("startDate", electionObject.getElectionDate());
				request.setAttribute("startTime", electionObject.getStartTime());
				request.getRequestDispatcher("/electionNotStarted.jsp").forward(request, response);
    		}
    	}catch(Exception ex)
    	{
    	}

    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			log.warning("EmailId Post = " + request.getParameter("emailId"));
			PrintWriter out = response.getWriter();
			String candidateName = request.getParameter("voteRadio").toString();
			CandidateDao candidateDao = new CandidateDao();
			String emailId = request.getParameter("emailId");
			String token = request.getParameter("div_token");
			VoterDao voterDao = new VoterDao();
			Key voterKey = voterDao.retrieveKey(emailId);
			TokenDao tokenDao = new TokenDao();
			if(tokenDao.checkTokenStatus(voterKey, token))
			{
			Key candidateKey = candidateDao.getCandiateKey(candidateName);
			VoteDao voteDao = new VoteDao();
			voteDao.addVote(candidateKey);			 
			tokenDao.updateTokenStatus(voterKey);
			out.println("Vote submitted successfully.");
			}
			else
			{
				out.println("Token already used.");
			}			
		}
		catch(Exception ex)
		{
			log.severe(ex.getMessage());
		}
	}
	
	private List<CandidateVotes> getCandidateVotes()
	{
		VoteDao voteDao = new VoteDao();
		List<Entity> voteList = voteDao.getVoteCount();
		List<CandidateVotes> candidateVotesList = new ArrayList<CandidateVotes>();
		CandidateDao candidateDao = new CandidateDao();
		for(Entity entity : voteList)
		{
			CandidateVotes candidateVotesObj = new CandidateVotes();
			Candidate candidateObj = new Candidate();
			Key parentKey = entity.getParent();
			candidateObj = candidateDao.getCandiadate(parentKey);
			candidateVotesObj.setCandidateObj(candidateObj);
			candidateVotesObj.setVoteCount(Integer.parseInt(entity.getProperty("voteCount").toString()));
			candidateVotesList.add(candidateVotesObj);
		}
		return candidateVotesList;
	}
}