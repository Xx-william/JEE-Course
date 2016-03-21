package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Category;
import model.db.CategoryDB;
import model.db.exception.DatabaseAccessError;

@WebServlet("/controller/OwnerFrontPage")
public class OwnerFrontPage extends HttpServlet{

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		try {
			List<Category> categorys = CategoryDB.getCategories();
//			StringBuilder sb = new StringBuilder();
			
//			sb.append("{\"names\":");
//			for(Category category : categorys){
//				sb.append("[\""+category.getDescription()+"\"],");
//			}
//			sb.deleteCharAt(sb.length()-1);
//			sb.append("}");
			
//			resp.setContentType("application/json");
//			PrintWriter out = resp.getWriter();
//			out.println(sb);
			ArrayList<String> categoryNames = new ArrayList();
			for(Category category: categorys){
				String description = category.getDescription();
				categoryNames.add(category.getDescription());
			}
			req.setAttribute("category", categoryNames);
			
			RequestDispatcher dispatcher = req.getRequestDispatcher("/newProject.jsp");
			dispatcher.forward(req, resp);
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
}
