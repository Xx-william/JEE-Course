package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.net.URL;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Category;
import model.Document;
import model.Evaluation;
import model.Evaluator;
import model.Owner;
import model.Project;
import model.User;
import model.db.CategoryDB;
import model.db.ProjectDB;
import model.db.UserDB;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

public class GeneralTest {
	
//	@Before
//	public void before(){
//		Owner o = new Owner("owner@hotmail.com","Owner","123");
//		Evaluator e = new Evaluator("evaluator@hotmail.com","Evaluator","123");
//		
//		try {
//			UserDB.addUser(o);
//			UserDB.addUser(e);
//		} catch (Exception ex){
//			ex.printStackTrace();
//			fail("error on init the test data");
//		}
//	}
	
	@Test
	public void testCategory() {
		Category c = new Category("Test");
		assertEquals("Test", c.getDescription());
	}

	@Test
	public void testProject() {
		Category c = new Category("Test");
		Owner o = new Owner("a@a", "ma", "12");
		try {
			new Project("AA", "BB", 10, 100, o, c);
			new Project("AA", "BB", 1, 1, o, c);
		} catch (InvalidDataException e) {
			fail("error in project");
		}

		try {
			new Project(null, "BB", 1, 1, o, c);
			fail("project allowed null acronym");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", null, 1, 1, o, c);
			fail("project allowed null description");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", -1, 1, o, c);
			fail("project allowed negative funding period");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", 1, -1, o, c);
			fail("project allowed negative budget");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", 1, 1, null, c);
			fail("project allowed null owner ");
		} catch (InvalidDataException e) {
		}

		try {
			new Project("AA", "DD", 1, 1, o, null);
			fail("project allowed null category ");
		} catch (InvalidDataException e) {
		}

	}

	@Test
	public void testEvaluation() {

		Evaluator ev = new Evaluator("x@x", "x", "y");
		try {
			new Evaluation(1,null, 1, 1);
			fail("accepted null evaluator");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(1,ev, 0, 1);
			fail("accepted invalid attractiveness range");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(1,ev, 6, 1);
			fail("accepted invalid attractiveness range");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(1,ev, 1, 0);
			fail("accepted invalid risk range");
		} catch (InvalidDataException e) {
		}

		try {
			new Evaluation(1,ev, 5, 6);
			fail("accepted invalid risk range");
		} catch (InvalidDataException e) {
		}

		Category ca = new Category("Test");
		Owner ow = new Owner("a@a", "ma", "12");
		try {
			Project po = new Project("AA", "BB", 10, 100, ow, ca);
			Evaluation e = new Evaluation(1,ev, 5, 5);
			po.addEvaluation(e);
			assertEquals(po.getEvaluations().size(), 1);
			assertEquals(po.getEvaluations().get(0), e);
		} catch (InvalidDataException e) {
			fail("error creating project");
		}

	}

	@Test
	public void testDocument() {

		URL location = this.getClass().getProtectionDomain().getCodeSource()
				.getLocation();

		try {
			new Document(location.getFile() + "/model/Project.class",1);
		} catch (model.exception.InvalidDataException e) {
			fail("error creating document");
		}

		try {
			new Document(location.getFile() + "/model",1);
			fail("invalid document path");
		} catch (model.exception.InvalidDataException e) {

		}

		try {
			new Document(location.getFile() + "/helo",1);
			fail("invalid document path");
		} catch (model.exception.InvalidDataException e) {

		}

		Category ca = new Category("Test");
		Owner ow = new Owner("a@a", "ma", "12");
		try {
			Project po = new Project("AA", "BB", 10, 100, ow, ca);
			Document doc = new Document(location.getFile()
					+ "/model/Project.class",1);
			po.addDocument(doc);
			assertEquals(po.getDocuments().size(), 1);
			assertEquals(po.getDocuments().get(0), doc);
		} catch (InvalidDataException e) {
			fail("error creating project");
		}
	}

	@Test
	public void testCategoriesDB() {
		try {
			List<Category> cats = CategoryDB.getCategories();
			if (cats.size() == 0) {
				fail("empty category list");
			}
		} catch (DatabaseAccessError e) {
			fail("Error accessing db");
		} catch (InvalidDataException e) {
			fail("Error input data");
		}
	}

	@Test
	public void testUserDB() {
		try {
			UserDB.getUserWithPassword("owner@hotmail.com", "123");				
		} catch (DatabaseAccessError e) {
			fail("database error");
		}catch (InvalidDataException e) {
			fail("error checking password");
		}
		try {
			User u = UserDB.getUser("owner@hotmail.com");
			if (u == null) {
				fail("error retrieving owner");
			}else if(!(u instanceof Owner)){
				fail("error retrieving user type");
			}
		} catch (DatabaseAccessError e) {
			fail("database error");

		}

		try {
			User u = UserDB.getUser("evaluator@hotmail.com");
			if (u == null) {
				fail("error retrieving evaluator");
			}else if(!(u instanceof Evaluator)){
				fail("error retrieving user type");
			}
			
		} catch (DatabaseAccessError e) {
			fail("database error");

		}

		try {
			User u = UserDB.getUser("sarah@geek.com");
			if (u != null) {
				fail("error retrieving owner");
			}
		} catch (DatabaseAccessError e) {
			fail("database error");

		}

	}

	@Test
	public void testProjectDB() {

		Owner u;
		try {
			u = (Owner) UserDB.getUser("owner@hotmail.com");
			Category c = CategoryDB.getCategories().get(0);
			Project p = new Project("A", "B", 10, 1, u, c);
			
			
			
			ProjectDB.saveProject(p);
//			Project x = ProjectDB.getProject("A");
//			assertEquals(x,p);
			
			List<Project> ps = ProjectDB.getProjectsOfOwner(u);
			assertEquals(ps.get(0),p);
			
			Project x = ProjectDB.getProject(ps.get(0).getProjectId());
			assertEquals(x,p);
			
			ps = ProjectDB.getProjectsOfOwner((Owner)UserDB.getUser("owner@hotmial.com"));
			if(ps.size() != 0) {
				fail("error getting projects");
			}
			
			ps = ProjectDB.getAllProjects();
			assertEquals(ps.get(0),p);
			

			
			
		} catch (DatabaseAccessError | InvalidDataException e) {
			fail("eror creating project");
		}

	}

}
