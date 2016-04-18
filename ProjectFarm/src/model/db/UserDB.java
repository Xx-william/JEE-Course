package model.db;

/**
 * @author WANG Xi
 * @version 1.0
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import model.Evaluator;
import model.Owner;
import model.User;
import model.db.exception.DatabaseAccessError;
import model.exception.InvalidDataException;

public class UserDB {
	private static String SEARCH_USER = "SELECT * FROM user WHERE user_Email = ?";
	private static String ADDUSER = "INSERT INTO user(user_Name,user_Email,user_Password,user_Type) VALUES(?,?,?,?) ";
	private static String GET_USERWITHPASS = "SELECT * FROM user WHERE user_Email = ? AND user_Password = ?";

	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws DatabaseAccessError
	 * @throws InvalidDataException
	 */
	public static User getUserWithPassword(String email, String password)
			throws DatabaseAccessError, InvalidDataException {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			PreparedStatement stmt = conn.prepareStatement(GET_USERWITHPASS);
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String name = rs.getString("user_Name");
				String type = rs.getString("user_Type");
				if (type.equals("Owner")) {
					Owner owner = new Owner(email, name, password);
					return (Owner) owner;
				} else {
					Evaluator evaluator = new Evaluator(email, name, password);
					return (Evaluator) evaluator;
				}
			} else {
				throw new InvalidDataException("User name or password not correct");
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

	}

	public static void addUser(User u) throws DatabaseAccessError, InvalidDataException {
		User user = getUser(u.getEmail());
		if (user != null) {
			throw new InvalidDataException("This Email has already been used!");
		} else {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();

				PreparedStatement stmt = conn.prepareStatement(ADDUSER);
				stmt.setString(1, u.getName());
				stmt.setString(2, u.getEmail());
				stmt.setString(3, u.getPassword());
				if (u instanceof Owner) {
					stmt.setString(4, "Owner");
				} else if (u instanceof Evaluator) {
					stmt.setString(4, "Evaluator");
				}
				stmt.executeUpdate();

			} catch (ClassNotFoundException | SQLException | NamingException e) {
				throw new DatabaseAccessError("erro when add owner", e);
			} finally {
				try {
					DBUtil.dropConnection(conn);
				} catch (SQLException e) { /* ignored */
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @param login
	 *            : user's email
	 * @return User : if user doesn't exist return null, otherwise return Owner
	 *         or Evaluator
	 */
	public static User getUser(String login) throws DatabaseAccessError {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();

			PreparedStatement stmt = conn.prepareStatement(SEARCH_USER);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String email = login;
				String name = rs.getString("user_Name");
				String password = rs.getString("user_Password");
				String type = rs.getString("user_Type");

				if (type.equals("Owner")) {
					Owner owner = new Owner(email, name, password);
					return (Owner) owner;
				} else {
					Evaluator evaluator = new Evaluator(email, name, password);
					return (Evaluator) evaluator;
				}
			} else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException | NamingException e) {
			e.printStackTrace();
			throw new DatabaseAccessError("General error", e);
			
		} finally {
			try {
				DBUtil.dropConnection(conn);
			} catch (SQLException e) { /* ignored */
				e.printStackTrace();
			}
		}

	}

}
