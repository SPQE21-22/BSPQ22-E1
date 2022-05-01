package server.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import server.data.dto.BookDTO;
//import server.data.dto.UserDTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import server.Main;
import server.data.domain.Book;
import server.data.domain.User;

public class DB {

    private static final long serialVersionUID = 1L;
    // private static ArrayList<UserDTO> usersList;
    private static ArrayList<User> usersList;
    //	private static ArrayList<BookDTO> booksList;
    private static ArrayList<Book> booksList;

    private static final Logger logger = LogManager.getLogger(DB.class);

    public static Connection initBD() throws DBException, SQLException {
        Connection con = null;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root"; //root
        String password = "root"; //"root"
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            logger.info("Connection with librarydb succeeded");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return con;
    }


    public static void createTables(Connection con) throws DBException {
        dropTables(con);
        String bookq = "CREATE TABLE IF NOT EXISTS Book (id int auto_increment PRIMARY KEY, name VARCHAR(255) , author VARCHAR(255) , publishDate Date, available Boolean)";
        String userq = "CREATE TABLE IF NOT EXISTS User (id int auto_increment PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255), birthDate Date)";
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
        String userq = "DROP TABLE IF EXISTS User";
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
        birthDate.setYear(birthDate.getYear()-1900);
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
        } /*finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/
    }
    public static void addBook(Connection con, String name, String author, Date publishDate, Boolean available)
            throws DBException {
        publishDate.setYear(publishDate.getYear()-1900);
        try (PreparedStatement stmt = con
                .prepareStatement("INSERT INTO Book (name, author, publishDate , available) VALUES (?,?,?,?)");
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
        } /*finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }*/
    }


    public static ArrayList<User> getUsersList(Connection con) throws SQLException {
        String sent = "SELECT * FROM User";
        Statement st = null;
        st = con.createStatement();
        usersList = new ArrayList<User>();
        User user = new User();
        ResultSet rs = st.executeQuery(sent);
        while (rs.next()) {
            user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), null);
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
            book = new Book(rs.getString(2), rs.getString(3), rs.getDate(4), rs.getBoolean(5));
            booksList.add(book);
        }
        rs.close();
        return booksList;
    }

    public static void deleteBook(Connection con, String name) {
        String sent = "DELETE FROM Book WHERE name = '" + name + "'";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(Connection con, String name) {
        String sent = "DELETE FROM User WHERE name = '" + name + "'";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateBookAuthor(Connection con, String name, String author) {
        String sql = "UPDATE Book SET author = '" + author + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateBookName(Connection con, String name, String newName) {
        String sql = "UPDATE Book SET name = '" + newName + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateBookPublishDate(Connection con, String name, Date date) {
        date.setYear(date.getYear()-1900);
        String sql = "UPDATE Book SET publishDate = '" + date + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateBookAvailability(Connection con, String name, Boolean available) {
        String sql = "UPDATE Book SET available = " + available + " WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateUserName(Connection con, String name, String newName) {
        String sql = "UPDATE User SET name = '" + newName + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateUserEmail(Connection con, String name, String email) {
        String sql = "UPDATE User SET email = '" + email + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateUserPassword(Connection con, String name, String password) {
        String sql = "UPDATE User SET password= '" + password + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void updateUserBirthdate(Connection con, String name, Date birthdate) {
        birthdate.setYear(birthdate.getYear()-1900);
        String sql = "UPDATE User SET birthDate = '" + birthdate + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

/* DB TESTING
    public static void main(String args[]) {
        Connection con = null;
        try {
            con = initBD();
            createTables(con);
            addUser(con, "Alex", "a@mail", "1234", new Date(2022, 1, 10));
            addUser(con, "Aida", "a@mail", "1234", new Date(2001, 2, 10));
            addBook(con, "El nombre del Viento", "Path", new Date(2006, 3, 15), true);
            usersList = getUsersList(con);
            booksList = getBooksList(con);
            for (User u :
                    usersList) {
                logger.info(u);
            }

            for (Book u :
                    booksList) {
                logger.info(u);
            }
            updateBookAuthor(con, "El nombre del Viento", "yo");
            updateBookName(con, "El nombre del Viento", "hola");
            updateBookAvailability(con, "hola", false);
            updateBookPublishDate(con, "hola", new Date(2000, 0, 1));

            updateUserName(con, "Aida", "Aida2");
            updateUserEmail(con, "Aida2", "email@g.com");
            updateUserBirthdate(con, "Aida2", new Date(1900, 0, 3));
            updateUserPassword(con, "Aida2", "hola");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DBException e) {
            e.printStackTrace();
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

 */
}
