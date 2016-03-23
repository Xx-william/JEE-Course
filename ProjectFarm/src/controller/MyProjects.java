package controller;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.MyProjectPage;
import model.Owner;
import model.Project;
import model.db.ProjectDB;

@WebServlet("/controller/MyProjects")
public class MyProjects extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		HttpSession session = req.getSession();
		String email = (String) session.getAttribute("email");
		String name = (String)session.getAttribute("name");
		String password = (String)session.getAttribute("password");
		
		Owner owner = new Owner(email,name,password);
				
		ArrayList<Project> projects = new ArrayList<Project>();
		
		
		projects = (ArrayList<Project>) ProjectDB.getProjectsOfOwner(owner).clone();
		ArrayList<MyProjectPage> myProjectPages = new ArrayList<MyProjectPage>();
		for(Project project : projects){
			myProjectPages.add(new MyProjectPage(project));
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(myProjectPages);

		req.setAttribute("projects", json);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/myProjects.jsp");
		dispatcher.forward(req, resp);
		

	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
}
