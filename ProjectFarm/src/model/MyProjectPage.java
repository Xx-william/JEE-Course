package model;

import java.util.List;

public class MyProjectPage {
	private String acronym;
	private String category;
	private int incubation;
	private double budget;
	private double risk;
	private double attractiveness;
	private int numEvaluators;
	
	public MyProjectPage(Project project){
		setAcronym(project.getAcronym());
		setCategory(project.getCategory().getDescription());
		setIncubation(project.getFundingDuration());
		setBudget(project.getBudget());

		List<Evaluation> evaluations = project.getEvaluations();
		setNumEvaluators(evaluations.size());
		double allRisk = 0;
		double allAttractiveness = 0;
		if(!evaluations.isEmpty()){
			for(Evaluation evaluation : evaluations){
				allRisk += evaluation.getRiskLevel();
				allAttractiveness += evaluation.getAttractiveness();
			}
			setRisk(allRisk / numEvaluators);
			setAttractiveness(allAttractiveness / numEvaluators);
		}else{
			setRisk(0);
			setAttractiveness(0);
		}
		
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
	public void setRisk(double risk){
		this.risk = risk;
	}
	public double getRist(){
		return this.risk;
	}
	public void setAttractiveness(double attractiveness){
		this.attractiveness = attractiveness;
	}
	public double getAttractiveness(){
		return this.attractiveness;
	}
	public void setNumEvaluators(int numEvaluators){
		this.numEvaluators = numEvaluators;
	}
	public int getNumEvaluators(){
		return this.numEvaluators;
	}
}
