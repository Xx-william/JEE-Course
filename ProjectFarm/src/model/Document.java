package model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import model.exception.InvalidDataException;

public class Document implements Serializable {

	private static final long serialVersionUID = 3404763898326246494L;
	private int documentId;
	private int projectId;
	private String documentPath;
	private Date added;
	
	public Document(String documentPath,int projectId) throws InvalidDataException {
		setDocumentPath(documentPath);
		setAdded(new Date());
		setProjectId(projectId);
	}

	public void setDocumentId(int id){
		this.documentId = id;
	}
	public int getDocumentId(){
		return this.documentId;
	}
	public void setProjectId(int projectId){
		this.projectId = projectId;
	}
	public int getProjectId(){
		return this.projectId;
	}
	public String getDocumentPath() {
		return documentPath;
	}

	public String getDocumentName(){
		String[] filenames = documentPath.split("/");
		String filename = "";
		for(String filenametempt : filenames){
			filename = filenametempt;
		}
	
		return filename;
	}
	public void setDocumentPath(String documentPath) throws InvalidDataException {
		File file = new File(documentPath);
		
		if(!file.exists()) {
			throw new InvalidDataException("File " + documentPath + " does not exists");
		}
		
		if(!file.isFile()) {
			throw new InvalidDataException("Path " + documentPath + " does not point to a file");
		}
		
		this.documentPath = documentPath;
	}

	public Date getAdded() {
		return added;
	}

	public void setAdded(Date added) {
		this.added = added;
	}

}
