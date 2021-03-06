package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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

@WebServlet("/controller/LogIn")
public class LogIn extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 987216777849460946L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		try {
			User user = UserDB.getUserWithPassword(email, password);
			if (user instanceof Owner) {
				setSession(req, email, user.getName(), "Owner", "true", password);
				addCookies(resp, email);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/ownerFrontPage.jsp");
				dispatcher.forward(req, resp);

			} else if (user instanceof Evaluator) {
				setSession(req, email, user.getName(), "Evaluator", "true", password);
				addCookies(resp, email);
				RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/evaluatorFrontPage.jsp");
				dispatcher.forward(req, resp);
			}
		} catch (DatabaseAccessError e) {
			e.printStackTrace();
		} catch (InvalidDataException e) {// user name or password not correct
			RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
			req.setAttribute("error", "User name or password not correct");
			dispatcher.forward(req, resp);
			System.out.println(e.getMessage());
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void setSession(HttpServletRequest req, String email, String name, String type, String isLogIn,
			String password) {
		HttpSession session = req.getSession();
		session.setAttribute("email", email);
		session.setAttribute("name", name);
		session.setAttribute("type", type);
		session.setAttribute("isLogIn", isLogIn);
		session.setAttribute("password", password);
	}

	public void addCookies(HttpServletResponse resp, String email) {
		Cookie cookie = new Cookie("Email", email);
		cookie.setMaxAge(60 * 60 * 24 * 360); // 1 year
		cookie.setPath("/ProjectFarm");
		resp.addCookie(cookie);
	}
}
