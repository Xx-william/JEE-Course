package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EvaluatePage {
	private String acronym;
	private String created;
	private String description;
	private String category;
	private int incubation;
	private double budget;
	private ArrayList<String> documents = new ArrayList<String>();
	
	public EvaluatePage(String acronym,Date created,String description,
			Category category, int incubation, double budget, ArrayList<Document> documents){
		setAcronym(acronym);
		setCreated(created);
		setDescription(description);
		setCategory(category);
		setIncubation(incubation);
		setBudget(budget);
		setDocuments(documents);
	}
	
	
	public void setAcronym(String  acronym){
		this.acronym = acronym;
	}
	public String getAcronym(){
		return this.acronym;
	}
	public void setCreated(Date created){		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
		this.created = sdf.format(created);
	}
	public String getCreated(){
		return this.created;
	}
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription(){
		return this.description;
	}
	public void setCategory(Category category){
		this.category = category.getDescription();
	}
	public String getCategory(){
		return this.category;
	}
	public void setIncubation(int incubation){
		this.incubation = incubation;
	}
	public int getIncubation(){
		return this.incubation;
	}
	public void setBudget(double budget){
		this.budget = budget;
	}
	public double getBudget(){
		return this.budget;
	}
	public void setDocuments(ArrayList<Document> documents){
		for(Document document : documents){
			this.documents.add(document.getDocumentPath());
		}		
	}
	public void addDocument(String path){
		this.documents.add(path);
	}
	public ArrayList<String> getDocuments(){
		return this.documents;
	}
}
