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

import model.Project;
import model.db.ProjectDB;

@WebServlet("/controller/ListProjects")
public class ListProjects extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6939583898072280605L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ArrayList<Project> projects = new ArrayList<Project>();
		projects = ProjectDB.getAllProjects();

		Gson gson = new Gson();
		String json = gson.toJson(projects);

		req.setAttribute("projects", json);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/listProjects.jsp");
		dispatcher.forward(req, resp);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
