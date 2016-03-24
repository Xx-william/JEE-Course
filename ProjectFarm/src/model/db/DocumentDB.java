package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.RE;

import model.Document;

public class DocumentDB {
 private static String GET_DOCUMENT_BYPROJECTID = "SELECT * FROM document WHERE document_project_id = ?";
 
 public static ArrayList<Document>getDocumentByProjectId(int projectId){
	 ArrayList<Document> documents = new ArrayList<Document>();
	 Connection conn = null;
	 try{
		 conn = DBUtil.getConnection();
		 PreparedStatement stmt = conn.prepareStatement(GET_DOCUMENT_BYPROJECTID);
		 stmt.setInt(1, projectId);
		 ResultSet rs = stmt.executeQuery();
		 
		 while(rs.next()){
			 String path = rs.getString("document_path");
			 Document document = new Document(path);
			 documents.add(document);
			 
		 }
	 }catch(Exception e){
		 e.printStackTrace();
	 }
	 return documents;
 }
}
