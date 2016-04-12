package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.db.EvaluationDB;

@WebServlet("/controller/NewEvaluation")
public class NewEvaluation extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2835791222600336843L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String attractivenessStr = (String) req.getParameter("attractiveness");
		String riskStr = (String) req.getParameter("risk");
		String projectIdStr = (String) req.getParameter("projectId");

		try {
			int attractiveness = Integer.parseInt(attractivenessStr);
			int risk = Integer.parseInt(riskStr);
			int projectId = Integer.parseInt(projectIdStr);

			EvaluationDB.addEvaluation(projectId, (String) session.getAttribute("email"), attractiveness, risk);
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			String outString = "{ \"isSuccess\" : \"true\"}";
			out.println(outString);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
			resp.setContentType("application/json");
			PrintWriter out = resp.getWriter();
			String outString = "{ \"isSuccess\" : \"false\",\"errorMessage\" : \"" + e.getMessage() + "\"}";
			out.println(outString);
			out.close();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
