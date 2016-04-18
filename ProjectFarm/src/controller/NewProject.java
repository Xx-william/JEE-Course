package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Category;
import model.Owner;
import model.Project;
import model.db.CategoryDB;
import model.db.ProjectDB;
import model.db.UserDB;

@WebServlet("/controller/NewProject")
public class NewProject extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6660822334125504767L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String categoryStr = req.getParameter("category");
		String incubationStr = req.getParameter("incubation");
		String budgetStr = req.getParameter("budget");
		HttpSession session = req.getSession();

		try {
			int incubation = Integer.parseInt(incubationStr);
			double budget = Double.parseDouble(budgetStr);
			Owner owner = (Owner) UserDB.getUser((String) session.getAttribute("email"));
			Category category = CategoryDB.getCategory(categoryStr);
			//Date date = new Date();
			//Project project = new Project(title, description, incubation, budget, owner, category, date);
			Project project = new Project(title,description, incubation, budget, owner, category);
			
			ProjectDB.saveProject(project);

		} catch (Exception e) {
			e.printStackTrace();
		}

		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String outString = "{ \"isSuccess\" : \"true\"}";
		out.println(outString);
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
