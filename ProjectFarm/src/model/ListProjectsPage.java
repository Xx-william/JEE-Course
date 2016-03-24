package model;

public class ListProjectsPage {
	private int projectId;
	private String acronym;
	private String category;
	private int incubation;
	private double budget;
	private int numEvaluations;
	
	public ListProjectsPage(int projectId,String acronym,String category, int incubation, double budget, int numEvaluations){
		setProjectId(projectId);
		setAcronym(acronym);
		setCategory(category);
		setIncubation(incubation);
		setBudget(budget);
		setNumEvaluations(numEvaluations);
	}
	
	public ListProjectsPage(Project project){
		setProjectId(projectId);
		setAcronym(project.getAcronym());
		setCategory(project.getCategory().getDescription());
		setIncubation(project.getFundingDuration());
		setBudget(project.getBudget());
			
		if(!project.getEvaluations().isEmpty()){
			setNumEvaluations(project.getEvaluations().size());
		}else{
			setNumEvaluations(0);
		}
	}
	
	public void setProjectId(int projectId){
		this.projectId = projectId;
	}
	public int getProjectId(){
		return this.projectId;
	}
	
	public void setAcronym(String acronym){
		this.acronym = acronym;
	}
	public String getAcronym(){
		return this.acronym;
	}
	public void setCategory(String category){
		this.category = category;
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
	public void setNumEvaluations(int numEvaluations){
		this.numEvaluations = numEvaluations;
	}
	public int getNumEvaluations(){
		return this.numEvaluations;
	}
}
