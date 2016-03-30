package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Document;
import model.db.exception.DatabaseAccessError;

public class DocumentDB {
 private static String GET_DOCUMENT_BYPROJECTID = "SELECT * FROM document WHERE document_project_id = ?";
 private static String INSERT_DOCUMENT = "INSERT INTO document(document_path,document_project_id,document_added) VALUES (?,?,?)";
 
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
			 Document document = new Document(path,projectId);
			 documents.add(document);
			 
		 }
	 }catch(Exception e){
		 e.printStackTrace();
	 }finally{
		 try{
			 DBUtil.dropConnection(conn);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	 }
	 return documents;
 }
 
 public static void saveDocument(Document document){
	 Connection conn = null;
			 
	try{
		conn = DBUtil.getConnection();
		PreparedStatement stmt = conn.prepareStatement(INSERT_DOCUMENT);
		stmt.setString(1, document.getDocumentPath());
		stmt.setInt(2,document.getProjectId());
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		String dateStr = sdf.format(document.getAdded());		
		stmt.setString(3, dateStr);
		
		int result = stmt.executeUpdate();
		if(result<=0){
			throw new DatabaseAccessError("Your file can not be saved");
		}
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			 DBUtil.dropConnection(conn);
		 }catch(Exception e){
			 e.printStackTrace();
		 }
	}
 }
}
