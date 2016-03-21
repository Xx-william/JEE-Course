package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

@WebServlet("/LogIn")
public class LogIn extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		try {
			User user = UserDB.getUserWithPassword(email, password);
			if(user instanceof Owner){
				System.out.println("Owner loged in");
				HttpSession session = req.getSession();
				session.setAttribute("email", email);
				session.setAttribute("name",user.getName());
				session.setAttribute("type", "Owner");	
				session.setAttribute("isLogIn", "true");
				
				RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/ownerFrontPage.jsp");
				dispatcher.forward(req, resp);
				
			}else if(user instanceof Evaluator){
				System.out.println("Evaluator loged in");
				HttpSession session = req.getSession();
				session.setAttribute("email", email);
				session.setAttribute("name",user.getName());
				session.setAttribute("type", "Evaluator");
			}
		} catch (DatabaseAccessError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch ( InvalidDataException e){
			System.out.println(e.getMessage());
		}
	}
	
}
