package server.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import server.data.dto.BookDTO;
//import server.data.dto.UserDTO;

import server.data.domain.Book;
import server.data.domain.User;

public class DBTest {

    private static final long serialVersionUID = 1L;
    // private static ArrayList<UserDTO> usersList;
    private static ArrayList<User> usersList;
    //	private static ArrayList<BookDTO> booksList;
    private static ArrayList<Book> booksList;

    public static Connection initBD() throws DBException, SQLException {
        Connection con = null;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root"; //root
        String password = "root"; //"root"
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection success.");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return con;
    }


    public static void createTables(Connection con) throws DBException {
        dropTables(con);
        String bookq = "CREATE TABLE IF NOT EXISTS Book (name VARCHAR(255) , author VARCHAR(255) , publishDate Date, available Boolean)";
        String userq = "CREATE TABLE IF NOT EXISTS User (id int, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255), birthDate Date)";
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

    public static void dropTables(Connection con) throws DBException {
        String bookq = "DROP TABLE IF EXISTS Book";
        String userq = "DROP TABLE IF EXISTS Book";
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

    public static ArrayList<User> getUsersList(Connection con) throws SQLException {
        String sent = "SELECT * FROM User";
        Statement st = null;
        st = con.createStatement();
        usersList = new ArrayList<User>();
        User user = new User();
        ResultSet rs = st.executeQuery(sent);
        while (rs.next()) {
            user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDate(4),
                    (List<Book>) rs.getArray(5));
            usersList.add(user);
        }
        rs.close();
        return usersList;
    }

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
