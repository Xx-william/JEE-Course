package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Document;
import model.db.DocumentDB;

@WebServlet("/controller/GetDocument")
public class getDocument extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1988793764826351989L;

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);

	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String documentIdStr = (String) req.getParameter("documentId");
		int documentId = Integer.parseInt(documentIdStr);

		Document document = DocumentDB.getDocumentById(documentId);

		String documentName = document.getDocumentName();
		File file = new File(document.getDocumentPath());

		FileInputStream inStream = new FileInputStream(file);
		ServletContext context = getServletContext();
		String mimeType = context.getMimeType(document.getDocumentPath());
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		resp.setContentType(mimeType);
		resp.setContentLength((int) file.length());
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"%s\"", file.getName());
		resp.setHeader(headerKey, headerValue);

		// obtains response's output stream
		OutputStream outStream = resp.getOutputStream();

		byte[] buffer = new byte[4096];
		int bytesRead = -1;

		while ((bytesRead = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, bytesRead);
		}

		inStream.close();
		outStream.close();

	}

}
