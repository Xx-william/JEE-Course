package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.Document;
import model.Evaluation;
import model.Project;
import model.db.DocumentDB;
import model.db.EvaluationDB;
import model.db.ProjectDB;

@WebServlet("/controller/Evaluate")
public class Evaluate extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1119057336789879153L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/evaluate.jsp");
		String projectIdStr = (String) req.getParameter("projectId");

		int projectId = Integer.parseInt(projectIdStr);
		Project project = ProjectDB.getProject(projectId);
		ArrayList<Document> documents = DocumentDB.getDocumentByProjectId(projectId);
		project.setDocuments(documents);
		ArrayList<Evaluation> evaluations = EvaluationDB.getEvalByProjID(projectId);
		project.setEvaluations(evaluations);

		Gson gson = new Gson();
		String json = gson.toJson(project);
		req.setAttribute("project", json);
		dispatcher.forward(req, resp);
	}
}
