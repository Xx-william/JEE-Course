package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Document;
import model.Evaluation;
import model.Owner;
import model.Project;
import model.db.exception.DatabaseAccessError;

public class ProjectDB {
	private static String SAVE_PROJECT = "INSERT INTO project(project_description,project_fundingDuration,project_budget,project_created,project_owner,project_category,project_acronym) values(?,?,?,?,?,?,?)";
	private static String GET_PROJECT_BY_ID = "SELECT * FROM project WHERE project_id = ?";
	private static String GET_PROJECTSOFOWNER = "SELECT * FROM project WHERE project_owner = ?";
	private static String GET_EVALUATIONS_IDS_OF_PROJECT = "SELECT evaluation_id FROM evaluation WHERE project_id = ?";
	private static String GET_ALL_PROJECTS = "SELECT * FROM project";
	// private static Map<String, Project> projects;

	// static {
	// projects = new LinkedHashMap<String, Project>();
	// }

	public static void saveProject(Project project) throws DatabaseAccessError {

		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SAVE_PROJECT);

			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String dateStr = sdf.format(project.getCreated());
			stmt.setString(1, project.getDescription());
			stmt.setInt(2, project.getFundingDuration());
			stmt.setDouble(3, project.getBudget());
			stmt.setString(4, dateStr);
			stmt.setString(5, project.getOwner().getEmail());
			stmt.setString(6, project.getCategory().getDescription());
			stmt.setString(7, project.getAcronym());
			int rt = stmt.executeUpdate();
			if (rt <= 0) {
				throw new DatabaseAccessError("Data base error at #ProjectDB");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.dropConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// projects.put(project.getAcronym(), project);
	}

	public static Project getProject(int id) {
		Project project = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_PROJECT_BY_ID);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

			rs.next();
			int projectId = rs.getInt("project_id");
			String acronym = rs.getString("project_acronym");
			String description = rs.getString("project_description");
			int fundingDuration = rs.getInt("project_fundingDuration");
			double budget = rs.getDouble("project_budget");
			String createdStr = rs.getString("project_created");
			String ownerStr = rs.getString("project_owner");
			String categoryName = rs.getString("project_category");

			ArrayList<Document> documents = DocumentDB.getDocumentByProjectId(projectId);
			ArrayList<Evaluation> evaluations = EvaluationDB.getEvalByProjID(projectId);

			// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			// Date created = sdf.parse(createdStr);
			// Owner owner = (Owner)UserDB.getUser(OwnerStr);
			// Category category = CategoryDB.getCategory(categoryName);

			project = new Project(projectId, acronym, description, fundingDuration, budget, ownerStr, categoryName,
					createdStr);
			project.setEvaluations(evaluations);
			project.setDocuments(documents);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.dropConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return project;
	}
	// public static Project getProject(String acronym) throws
	// DatabaseAccessError,InvalidDataException {
	// Connection conn = null;
	// try {
	// conn = DBUtil.getConnection();
	// PreparedStatement stmt = conn.prepareStatement(GET_PROJECT);
	// stmt.setString(1, acronym);
	// ResultSet rs = stmt.executeQuery();
	// if(rs.next()){
	// String title = rs.getString("project_acronym");
	// String description = rs.getString("project_description");
	// double budget = rs.getDouble("project_budget");
	// String created = rs.getString("project_created");
	// String ownerEmail = rs.getString("project_owner");
	// String categoryStr = rs.getString("project_category");
	// int fundingDuration = rs.getInt("project_fundingDuration");
	//
	// Owner owner = (Owner)UserDB.getUser(ownerEmail);
	// Category category = CategoryDB.getCategory(categoryStr);
	//
	//
	// SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
	// Date date = sdf.parse(created);
	//
	// Project project = new Project(title,description,fundingDuration,
	// budget,owner,category,date);
	//
	// return project;
	// }else{
	// throw new InvalidDataException("There is no project called" + acronym);
	// }
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	//
	// return projects.get(acronym);
	// }

	public static ArrayList<Project> getProjectsOfOwner(Owner owner) {
		ArrayList<Project> projects = new ArrayList<Project>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_PROJECTSOFOWNER);
			stmt.setString(1, owner.getEmail());

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int projectId = rs.getInt("project_id");
				String title = rs.getString("project_acronym");
				String description = rs.getString("project_description");
				double budget = rs.getDouble("project_budget");
				String created = rs.getString("project_created");
				String category = rs.getString("project_category");
				int fundingDuration = rs.getInt("project_fundingDuration");
				String ownerEmail = owner.getEmail();
				Project project = new Project(projectId, title, description, fundingDuration, budget, ownerEmail,
						category, created);
				project.setProjectId(rs.getInt("project_id"));

				ArrayList<Evaluation> evaluations = EvaluationDB.getEvalByProjID(projectId);
				ArrayList<Document> documents = DocumentDB.getDocumentByProjectId(projectId);
				project.setEvaluations(evaluations);
				project.setDocuments(documents);
				projects.add(project);
			}
		} catch (Exception e) {

		} finally {
			try {
				DBUtil.dropConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return projects;
	}

	public static ArrayList<Project> getAllProjects() {
		ArrayList<Project> projects = new ArrayList<Project>();
		Connection conn = null;

		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_ALL_PROJECTS);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int projectId = rs.getInt("project_id");
				String title = rs.getString("project_acronym");
				String description = rs.getString("project_description");
				double budget = rs.getDouble("project_budget");
				String created = rs.getString("project_created");
				String category = rs.getString("project_category");
				int fundingDuration = rs.getInt("project_fundingDuration");
				String ownerEmail = rs.getString("project_owner");
				Project project = new Project(projectId, title, description, fundingDuration, budget, ownerEmail,
						category, created);
				project.setProjectId(rs.getInt("project_id"));
				ArrayList<Evaluation> evaluations = EvaluationDB.getEvalByProjID(projectId);
				ArrayList<Document> documents = DocumentDB.getDocumentByProjectId(projectId);
				project.setEvaluations(evaluations);
				project.setDocuments(documents);
				projects.add(project);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				DBUtil.dropConnection(conn);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return projects;
	}

}
