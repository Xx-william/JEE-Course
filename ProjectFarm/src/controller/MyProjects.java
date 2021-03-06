package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.Owner;
import model.Project;
import model.db.ProjectDB;

@WebServlet("/controller/MyProjects")
public class MyProjects extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 697084369146478332L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		String name = (String) session.getAttribute("name");
		String password = (String) session.getAttribute("password");

		Owner owner = new Owner(email, name, password);

		ArrayList<Project> projects = new ArrayList<Project>();

		projects = ProjectDB.getProjectsOfOwner(owner);

		Gson gson = new Gson();
		String json = gson.toJson(projects);

		req.setAttribute("projects", json);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/myProjects.jsp");
		dispatcher.forward(req, resp);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
