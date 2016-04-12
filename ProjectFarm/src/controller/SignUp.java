package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Evaluator;
import model.Owner;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

@WebServlet("/controller/SignUp")
public class SignUp extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4427946673312373623L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String name = req.getParameter("userName");
		String password = req.getParameter("password");
		String passwordV  = req.getParameter("passwordVerify");
		String type = req.getParameter("optradio");
		
		if (type.equals("Owner")) {
			Owner owner = new Owner(email, name, password);
			try {
				verifyPassword(password,passwordV);
				UserDB.addUser(owner);
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				String outString = "{ \"isSuccess\" : \"true\"}";
				out.println(outString);

			} catch (DatabaseAccessError e) {
				e.printStackTrace();
			} catch (InvalidDataException e) {
				System.out.println(e.getMessage());
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				String outString = "{ \"isSuccess\" : \"false\",\"errorMessage\" : \"" + e.getMessage() + "\"}";
				out.println(outString);
				out.close();
			}
		} else {
			Evaluator evaluator = new Evaluator(email, name, password);
			try {
				verifyPassword(password,passwordV);
				UserDB.addUser(evaluator);
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				String outString = "{ \"isSuccess\" : \"true\"}";
				out.println(outString);
			} catch (DatabaseAccessError e) {
				e.printStackTrace();
			} catch (InvalidDataException e) {
				System.out.println(e.getMessage());
				resp.setContentType("application/json");
				PrintWriter out = resp.getWriter();
				String outString = "{ \"isSuccess\" : \"false\",\"errorMessage\" : \"" + e.getMessage() + "\"}";
				out.println(outString);
				out.close();
			}
		}

	}
	
	private void verifyPassword(String pass1,String pass2) throws InvalidDataException{
		if(!pass1.equals(pass2)){
			throw new InvalidDataException("Two passwords are not the same!");
		}
		
	}
}
