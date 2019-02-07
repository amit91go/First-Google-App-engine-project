package de.uniba.wiai.lspi.demo.votingSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;

import de.uniba.wiai.lspi.demo.votingSystem.Dao.ElectionDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.TokenDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.VoterDao;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Election;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Token;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;

/**
 * Servlet implementation class SendVotingDetailsServlet
 */
@WebServlet("/SendVotingDetailsServlet")
public class SendVotingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendVotingDetailsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		// TODO Auto-generated method stub
		
		String mailIds = request.getParameter("div_mailIds").toString();
		String[] mailArray = mailIds.split(";");
		ArrayList<String> mailList = new ArrayList<String>();
		for(int i=0;i<mailArray.length;i++)
		{
			mailList.add(mailArray[i]);
		}
		
		Election electionObj = new Election();
		ElectionDao electionDao = new ElectionDao();
		electionObj = electionDao.getElectionDetails();
		PrintWriter out = response.getWriter();
		VoterDao voterDao = new VoterDao();
		TokenDao tokenDao = new TokenDao();
		Voter voterObj = new Voter();
		Token tokenObj = new Token();
		Key voterKey;
		
		for(String mailId: mailList)
		{
		
			Random random = new Random();
			StringBuilder builder = new StringBuilder(6);
			for (int i = 0; i < 6; i++) {
				builder.append(ALPHABET.charAt(random.nextInt(ALPHABET.length())));
			}
			String token =  builder.toString();
		
			Properties props = new Properties();
			Session session = Session.getDefaultInstance(props, null);

		
			try {
				Message msg = new MimeMessage(session);
				msg.setFrom(new InternetAddress("amit91go@gmail.com", "Amit Admin"));
				msg.addRecipient(Message.RecipientType.TO,new InternetAddress(mailId, "Mr. Student"));
				msg.setSubject("Sample University Elections Details");
				msg.setText("Dear Student"+",\nYour university election details are as follows:\n	Election Date: "+
						electionObj.getElectionDate()+"\n	Start Time: "+
						electionObj.getStartTime()+" Hrs\n	End Time: "+electionObj.getEndTime()+" Hrs\n"+
						"Your one time token for voting: "+ token+"\n\n"+
						"Best Regards,\nUniversity Election Authority");
				Transport.send(msg);
				} catch (AddressException e) {
					e.printStackTrace();
				} catch (MessagingException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			
			voterObj.setEmailId(mailId);
			voterDao.addVoter(voterObj);
			tokenObj.setTokenid(token);
			tokenObj.setVotingStatus(1);
			voterKey = voterDao.retrieveKey(mailId);
			tokenDao.addToken(tokenObj ,voterKey);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
			out.println("<font color=green>Voting Details sent successfully.</font>");
			rd.include(request, response);
		}
		

		
	}

}
