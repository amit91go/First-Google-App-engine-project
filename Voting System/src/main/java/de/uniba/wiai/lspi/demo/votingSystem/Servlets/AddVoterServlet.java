package de.uniba.wiai.lspi.demo.votingSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.wiai.lspi.demo.votingSystem.Dao.CandidateDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.VoterDao;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Voter;

/**
 * Servlet implementation class AddVoterServlet
 */
@WebServlet("/AddVoterServlet")
public class AddVoterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVoterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		PrintWriter out = response.getWriter();
		Voter voterObj = new Voter();
		/*voterObj.setFirstName(request.getParameter("div_firstName"));
		voterObj.setLastName(request.getParameter("div_lastName"));*/
		voterObj.setEmailId(request.getParameter("div_emailId"));
		
		VoterDao voterDao = new VoterDao();
		out.println(voterDao.addVoter(voterObj));
	}

}
