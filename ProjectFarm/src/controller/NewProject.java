package controller;

import java.io.IOException;
import java.io.PrintWriter;

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
import model.db.UserDB;

@WebServlet("/NewProject")
public class NewProject extends HttpServlet{
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String title = req.getParameter("title");
		String description = req.getParameter("description");
		String categoryStr = req.getParameter("category");
		String incubationStr = req.getParameter("incubation");
		String budgetStr = req.getParameter("budget");
		HttpSession session = req.getSession();
		
		System.out.println(title);
		System.out.println(description);
		System.out.println(categoryStr);
		System.out.println(incubationStr);
		System.out.println(budgetStr);
		


		try{
			int incubation = Integer.parseInt(incubationStr);
			double budget = Double.parseDouble(budgetStr);
			Owner owner = (Owner) UserDB.getUser((String)session.getAttribute("email"));
			Category category = CategoryDB.getCategory(categoryStr);
			Project project = new Project(title,description,incubation,budget,owner,category);
		}catch(Exception e){
			
		}
		
		resp.setContentType("application/json");
		PrintWriter out = resp.getWriter();
		String outString = "{ \"isSuccess\" : \"true\"}";
		out.println(outString);
	}
	

}
