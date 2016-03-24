package model.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import model.Category;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

public class CategoryDB {

	private static String SEARCH_CATEGORYS = "SELECT category_description FROM category";
	private static String GET_CATEGORY = "SELECT * FROM category WHERE category_description = ?";
	private static Map<String, Category> categories;

	// static {
	// categories = new LinkedHashMap<>();
	//
	// initializeCategoryList();
	// }

	public static List<Category> getCategories() throws DatabaseAccessError, InvalidDataException {
		categories = new LinkedHashMap<>();
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			PreparedStatement stmt = conn.prepareStatement(SEARCH_CATEGORYS);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String description = rs.getString("category_description");
				categories.put(description, new Category(description));
				while (rs.next()) {
					description = rs.getString("category_description");
					categories.put(description, new Category(description));
				}
			} else {
				throw new InvalidDataException("There is no category in database");
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			throw new DatabaseAccessError("General error", e);
		} finally {
			try {
				DBUtil.dropConnection(conn);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}

		return new LinkedList<Category>(categories.values());
	}

	public static Category getCategory(String name) {
		Category category = null;
		Connection conn = null;
				try{
					conn = DBUtil.getConnection();
					PreparedStatement stmt = conn.prepareStatement(GET_CATEGORY);
					stmt.setString(1, name);
					ResultSet rs = stmt.executeQuery();
					while(rs.next()){
						category = new Category(rs.getString("category_description"));
					}
					
					
				}catch(Exception e){
					e.printStackTrace();
				}finally {
					try {
						DBUtil.dropConnection(conn);
					} catch (SQLException e) { /* ignored */
						e.printStackTrace();
					}
				}
				return category;
	}

	// private static void initializeCategoryList() throws DatabaseAccessError,
	// InvalidDataException{
	//
	//
	// categories.put("Apps",new Category("Apps"));
	// categories.put("Robotics",new Category("Robotics"));
	// categories.put("Information Systems",new Category("Information
	// Systems"));
	// categories.put("Hardware",new Category("Hardware"));
	// }

}
