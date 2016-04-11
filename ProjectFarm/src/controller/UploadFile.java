package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import model.Document;
import model.db.DocumentDB;





@WebServlet("/controller/UploadFile")
@MultipartConfig
public class UploadFile extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		OutputStream out = null;
		final PrintWriter out1 = response.getWriter();
		final String path = getServletContext().getInitParameter("file-upload");
		int projectId = -1;
		
		 String ajaxUpdateResult = "";

	        try {

	            List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);            

	            for (FileItem item : items) {

	                if (item.isFormField()) {

	                	String projectIdStr = item.getString();
	                	projectId = Integer.parseInt(projectIdStr);

	                } else {

	                    String fileName = item.getName();

	                    InputStream filecontent = item.getInputStream();
	                    String folderPath = path + File.separator + projectId;
	                    String filePath = folderPath + File.separator + fileName;
	            		try {
	            			File filetempt = new File(folderPath);
	        				if(!filetempt.exists()){
	        					try{
	        						filetempt.mkdir();
	        					}catch (Exception e){
	        						e.printStackTrace();
	        					}	        					
	        				}
	        				
	        			out = new FileOutputStream(new File(filePath));
	        			
	        
	        			int read = 0;
	        			final byte[] bytes = new byte[1024];
	        
	        			while ((read = filecontent.read(bytes)) != -1) {
	        				out.write(bytes, 0, read);
	        			}
	        			
	        			System.out.println("New file " + fileName + " created at " + path);
	        			try{	        				
	        				Document document = new Document(filePath,projectId);
		        			 DocumentDB.saveDocument(document);
	        			}catch (Exception e){
	        				System.out.println(e.getMessage());
	        			}
	        			 
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
	                    
	                    

	                   // System.out.println(Streams.asString(content));

	                    ajaxUpdateResult += "File " + fileName + " is successfully uploaded\n\r";
	                    
	                }

	            }
	            
	            System.out.println(ajaxUpdateResult);
	        } catch (FileUploadException e) {

	            throw new ServletException("Parsing file upload failed.", e);

	        }


	}


}
