package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/Evaluate")
public class Evaluate extends HttpServlet{
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		RequestDispatcher dispatcher = req.getRequestDispatcher("/pages/evaluate.jsp");
		dispatcher.forward(req, resp);
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
}
