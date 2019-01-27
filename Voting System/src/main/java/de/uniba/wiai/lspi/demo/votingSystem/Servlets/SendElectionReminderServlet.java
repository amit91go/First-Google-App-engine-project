package de.uniba.wiai.lspi.demo.votingSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Key;

import de.uniba.wiai.lspi.demo.votingSystem.Dao.ElectionDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.VoterDao;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Election;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;

/**
 * Servlet implementation class SendElectionReminderServlet
 */
@WebServlet("/SendElectionReminderServlet")
public class SendElectionReminderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendElectionReminderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Election electionObj = new Election();
		ElectionDao electionDao = new ElectionDao();
		electionObj = electionDao.getElectionDetails();
		if(Integer.parseInt(electionObj.getElectionDate().substring(0,4)) == LocalDateTime.now().getYear())
			if(Integer.parseInt(electionObj.getElectionDate().substring(5,7)) == LocalDateTime.now().getMonthValue())
				if((Integer.parseInt(electionObj.getElectionDate().substring(8)) - 1) == LocalDateTime.now().getDayOfMonth())
				{
					PrintWriter out = response.getWriter();
					out.print("hello");
					VoterDao voterDao = new VoterDao();
					List<Voter> voterList = voterDao.getVoters();
					for(Voter voterObj: voterList)
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
							msg.addRecipient(Message.RecipientType.TO,new InternetAddress(voterObj.getEmailId(), voterObj.getFirstName()+" "+voterObj.getLastName()));
							msg.setSubject("Sample University Elections Details");
							msg.setText("Dear Mr. "+voterObj.getLastName()+",\nYour university election deatils are as follows:\n	Election Date: "+
									electionObj.getElectionDate()+"\n	Start Time: "+
									electionObj.getStartTime()+" Hrs\n	End Time: "+electionObj.getEndTime()+" Hrs\n"+
									"Your one time token for voting: "+ token);
							Transport.send(msg);
						} catch (AddressException e) {
							e.printStackTrace();
						} catch (MessagingException e) {
							e.printStackTrace();
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
						voterObj.setToken(token);
						voterObj.setVotingStatus(1);
						Key voterKey = voterDao.retrieveKey(voterObj.getEmailId());
						voterDao.updateToken(voterKey, voterObj);

					}
				}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
