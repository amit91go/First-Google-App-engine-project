package de.uniba.wiai.lspi.demo.votingSystem.Servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.uniba.wiai.lspi.demo.votingSystem.Dao.CandidateDao;
import de.uniba.wiai.lspi.demo.votingSystem.Objects.Candidate;

/**
 * Servlet implementation class AddCandidateServlet
 */
@WebServlet("/AddCandidateServlet")
public class AddCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCandidateServlet() {
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
		Candidate candidateObj = new Candidate();
		candidateObj.setFirstName(request.getParameter("div_firstName"));
		candidateObj.setLastName(request.getParameter("div_lastName"));
		candidateObj.setFaculty(request.getParameter("div_faculty"));
		
		CandidateDao candidateDao = new CandidateDao();
		candidateDao.addCandidate(candidateObj);
		RequestDispatcher rd = getServletContext().getRequestDispatcher("/index.jsp");
		out.println("<font color=green>Candidate added successfully.</font>");
		rd.include(request, response);
		
		
	}

}
