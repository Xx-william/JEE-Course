package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/controller/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("application/json");
		// Create path components to save the file
		final String path = getServletContext().getInitParameter("file-upload");
		final Part filePart = request.getPart("fileUpload");
		final String fileName = getFileName(filePart);

		OutputStream out = null;
		InputStream filecontent = null;
		final PrintWriter out1 = response.getWriter();

		try {
			out = new FileOutputStream(new File(path + File.separator + fileName));
			filecontent = filePart.getInputStream();

			int read = 0;
			final byte[] bytes = new byte[1024];

			while ((read = filecontent.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}
			System.out.println("New file " + fileName + " created at " + path);
			
		
			String outString = "{ \"isSuccess\" : \"true\"}";
			out1.println(outString);
			
		} catch (FileNotFoundException fne) {
			System.out.println("You either did not specify a file to upload or are "
					+ "trying to upload a file to a protected or nonexistent " + "location.");
			System.out.println("<br/> ERROR: " + fne.getMessage());
			
			 
			String outString = "{ \"isSuccess\" : \"false\"}";
			out1.println(outString);
		} finally {
			if (out != null) {
				out.close();
			}
			if (filecontent != null) {
				filecontent.close();
			}
			if (out1 != null) {
				out1.close();
			}
		}
	}

	private String getFileName(final Part part) {
		final String partHeader = part.getHeader("content-disposition");
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}
}
