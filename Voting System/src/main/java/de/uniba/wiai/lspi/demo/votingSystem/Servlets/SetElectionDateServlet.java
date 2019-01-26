package de.uniba.wiai.lspi.demo.votingSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.wiai.lspi.demo.votingSystem.Dao.CandidateDao;
import de.uniba.wiai.lspi.demo.votingSystem.Dao.ElectionDao;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Election;

/**
 * Servlet implementation class SetElectionDateServlet
 */
@WebServlet("/SetElectionDateServlet")
public class SetElectionDateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SetElectionDateServlet() {
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
		Election electionObj = new Election();
		electionObj.setElectionDate(request.getParameter("div_electionDate"));
		electionObj.setStartTime(request.getParameter("div_startTime"));
		electionObj.setEndTime(request.getParameter("div_endTime"));
		
		ElectionDao electionDao = new ElectionDao();
		out.println(electionDao.setElectionDetails(electionObj));
	}

}
