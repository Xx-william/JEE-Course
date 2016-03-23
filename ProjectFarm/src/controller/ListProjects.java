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

import model.ListProjectsPage;
import model.db.ProjectDB;

@WebServlet("/controller/ListProjects")
public class ListProjects extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		ArrayList<ListProjectsPage> listProjectsPages = new ArrayList<ListProjectsPage>();
		listProjectsPages = ProjectDB.getListProjectPage();
		
		Gson gson = new Gson();
		String json = gson.toJson(listProjectsPages);
		
		req.setAttribute("projects", json);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/listProjects.jsp");
		dispatcher.forward(req, resp);
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
}
