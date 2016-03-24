package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Evaluation;
import model.Evaluator;
import model.db.exception.DatabaseAccessError;

public class EvaluationDB {
	private static String GET_EVALUATION_OF_PROJECT_ID = "SELECT * FROM evaluation WHERE project_id = ?";
	private static String ADD_EVALUATION = "INSERT INTO evaluation(project_id,evaluator_mail,attractivness,risk) VALUES(?,?,?,?)";
	
	public static void addEvaluation(int projectID, String evaluatorMail,int attractivness, int risk) throws DatabaseAccessError {
		Connection conn = null;
		try{
			 
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(ADD_EVALUATION);
			stmt.setInt(1, projectID);
			stmt.setString(2, evaluatorMail);
			stmt.setInt(3, attractivness);
			stmt.setInt(4, risk);
			
			int result = stmt.executeUpdate();
			if (result <= 0){
				throw new DatabaseAccessError("new evaluation can not be added");
			}else{
				System.out.println("new evaluation has been added to the database");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				DBUtil.dropConnection(conn);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}
	public static ArrayList<Evaluation> getEvalByProjID(int projectId){
		ArrayList<Evaluation> evaluations = new ArrayList<Evaluation>();
		Connection conn = null;
		try{
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_EVALUATION_OF_PROJECT_ID);
			stmt.setInt(1, projectId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
			//	int pid = rs.getInt("project_id");
				String evaluatorMail = rs.getString("evaluator_mail");
				int attractiveness = rs.getInt("attractiveness");
				int risk = rs.getInt("risk");
				
			//	Project project = ProjectDB.getProject(pid);
				Evaluator evaluator = (Evaluator)UserDB.getUser(evaluatorMail);
				
				Evaluation evaluation = new Evaluation(evaluator,attractiveness,risk);
				evaluations.add(evaluation);
			}
		}catch (Exception e){
			e.printStackTrace();
		}finally{
			try{
				DBUtil.dropConnection(conn);
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		return evaluations;
	}
}
