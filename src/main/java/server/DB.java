package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import server.data.dto.BookDTO;
//import server.data.dto.UserDTO;

import server.data.domain.Book;
import server.data.domain.User;

public class DB extends Exception {

	private static final long serialVersionUID = 1L;
	// private static ArrayList<UserDTO> usersList;
	private static ArrayList<User> usersList;
//	private static ArrayList<BookDTO> booksList;
	private static ArrayList<Book> booksList;

	public static Connection initBD() throws DBException, SQLException {
		Connection con = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/libraryDB";
			String username = "mysql"; //root
			String password = "mysql"; //""
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			System.out.println("Connection success.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
//		Connection con = null;
//		try {
//			Class.forName("org.sqlite.JDBC");
//			con = DriverManager.getConnection("jdbc:sqlite:libraryDB");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//			throw new DBException("Cannot load database driver", e);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return con;
	}

	public static void closeBD(Connection con) throws DBException {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DBException("Failure to disconnect from the database", e);

			}
		}
	}

	public static void createTables(Connection con) throws DBException {
		String bookq = "CREATE TABLE IF NOT EXISTS Book (name String, author String, publishDate Date, available Boolean)";
		String userq = "CREATE TABLE IF NOT EXISTS User (id int auto_increment, name String, email String, password String, birthDate Date)";
		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(bookq);
			st.executeUpdate(userq);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Failure to create database tables", e);
		} finally {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void addUser(Connection con, String name, String email, String password, Date birthDate)
			throws DBException {
		try (PreparedStatement stmt = con
				.prepareStatement("INSERT INTO User (name, email, password , birthDate) VALUES (?,?,?,?)");
				Statement stmtForId = con.createStatement()) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.setDate(4, birthDate);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("User cannot be added");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static void addBook(Connection con, String name, String author, Date publishDate, boolean available)
			throws DBException {
		try (PreparedStatement stmt = con
				.prepareStatement("INSERT INTO User (name, author, publishDate , available) VALUES (?,?,?,?)");
				Statement stmtForId = con.createStatement()) {
			stmt.setString(1, name);
			stmt.setString(2, author);
			stmt.setDate(3, publishDate);
			stmt.setBoolean(4, available);
			stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DBException("Book cannot be added");
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

//		public static UserDTO userByEmail(Connection con, String email) throws SQLException {
//		
//			String sent = "SELECT * FROM User WHERE email='"+ email +"'";
//			Statement st = null;
//			st = con.createStatement();
//		
//			UserDTO user = new UserDTO();
//			ResultSet rs = st.executeQuery(sent);
//		
//			while(rs.next()) {
//			user = new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), rs.getArray(6));
//			}
//		
//			rs.close();
//			return user;
//		}

	@SuppressWarnings("unchecked")
	public static User userByEmail(Connection con, String email) throws SQLException {
		String sent = "SELECT * FROM User WHERE email='" + email + "'";
		Statement st = null;
		st = con.createStatement();
		User user = new User();
		ResultSet rs = st.executeQuery(sent);
		while (rs.next()) {
			user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
					(List<Book>) rs.getArray(5));
		}
		rs.close();
		return user;
	}

//	public static ArrayList<UserDTO> getUsersList(Connection con) throws SQLException {
//
//		String sent = "SELECT * FROM User";
//		Statement st = null;
//		st = con.createStatement();
//
//		usersList = new ArrayList<UserDTO>();
//		UserDTO user = new UserDTO();
//
//		ResultSet rs = st.executeQuery(sent);
//
//		while (rs.next()) {
//			user = new UserDTO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5),
//					rs.getArray(6));
//			usersList.add(user);
//		}
//		rs.close();
//		return usersList;
//	}

	@SuppressWarnings("unchecked")
	public static ArrayList<User> getUsersList(Connection con) throws SQLException {
		System.out.println("PERRO");
		String sent = "SELECT * FROM User";
		Statement st = null;
		st = con.createStatement();
		usersList = new ArrayList<User>();
		User user = new User();
		ResultSet rs = st.executeQuery(sent);
		while (rs.next()) {
			user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
					(List<Book>)rs.getArray(5));
			usersList.add(user);
		}
		rs.close();
		return usersList;
	}

//	public static ArrayList<BookDTO> getBooksList(Connection con) throws SQLException {
//
//		String sent = "SELECT * FROM Book";
//		Statement st = null;
//		st = con.createStatement();
//
//		booksList = new ArrayList<BookDTO>();
//		BookDTO book = new BookDTO();
//
//		ResultSet rs = st.executeQuery(sent);
//
//		while (rs.next()) {
//			book = new BookDTO(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4));
//			booksList.add(book);
//		}
//		rs.close();
//		return booksList;
//	}

	public static ArrayList<Book> getBooksList(Connection con) throws SQLException {
		String sent = "SELECT * FROM Book";
		Statement st = null;
		st = con.createStatement();
		booksList = new ArrayList<Book>();
		Book book = new Book();
		ResultSet rs = st.executeQuery(sent);
		while (rs.next()) {
			book = new Book(rs.getString(1), rs.getString(2), rs.getDate(3), rs.getBoolean(4));
			booksList.add(book);
		}
		rs.close();
		return booksList;
	}

	public static void deleteBook(Connection con, String name) {
		String sent = "DELETE FROM usuario WHERE name = '" + name + "'";
		Statement st = null;
		try {
			st = con.createStatement();
			st.executeUpdate(sent);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
