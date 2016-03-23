package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller/Disconnect")
public class Disconnect extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath()+"/index.jsp");
	}
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
		doPost(req,resp);
	}
}
