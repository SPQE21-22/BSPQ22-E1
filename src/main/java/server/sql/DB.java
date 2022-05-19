package server.sql;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
import server.Main;
import server.data.domain.Book;
import server.data.domain.Fine;
import server.data.domain.Room;
import server.data.domain.Supply;
import server.data.domain.User;

/**
 * DB class implemented
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */


public class DB {

    private static final long serialVersionUID = 1L;
    // private static ArrayList<UserDTO> usersList;
    private static ArrayList<User> usersList;
    //	private static ArrayList<BookDTO> booksList;
    private static ArrayList<Book> booksList;
    private static ArrayList<Room> roomsList;
    private static ArrayList<Supply> suppliesList;
    private static ArrayList<Fine> finesList;

    //private static final Logger logger = LogManager.getLogger(DB.class);

    /**
     *
     * @return connection to the DB needed to be able to work or apply changes to the database
     * @throws DBException
     * @throws SQLException
     */
    public static Connection initBD() throws DBException, SQLException {
        Connection con = null;

        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/librarydb";
        String username = "root"; //root
        String password = "root"; //"root"
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, username, password);
            //logger.info("Connection with librarydb succeeded");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return con;
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @throws DBException
     */
    public static void createTables(Connection con) throws DBException {
        dropTables(con);
        String bookq = "CREATE TABLE IF NOT EXISTS Book (b_id int auto_increment PRIMARY KEY, name VARCHAR(255) , author VARCHAR(255) , publishDate Date, available Boolean)";
        String userq = "CREATE TABLE IF NOT EXISTS User (u_id int auto_increment PRIMARY KEY, name VARCHAR(255), email VARCHAR(255), password VARCHAR(255), birthDate Date)";
        String roomq = "CREATE TABLE IF NOT EXISTS Room (r_id int auto_increment PRIMARY KEY, name VARCHAR(255), day INTEGER, month VARCHAR(255), hourBeg INTEGER, hourEnd INTEGER, booked Boolean, u_id VARCHAR(255))";
        String supplyq = "CREATE TABLE IF NOT EXISTS Supply (s_id int auto_increment PRIMARY KEY, name VARCHAR(255), price DOUBLE, type VARCHAR(255))";
        String fineq = "CREATE TABLE IF NOT EXISTS Fine (f_id int auto_increment PRIMARY KEY, quantity DOUBLE, email VARCHAR(255))";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(bookq);
            st.executeUpdate(userq);
            st.executeUpdate(roomq);
            st.executeUpdate(supplyq);
            st.executeUpdate(fineq);
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @throws DBException
     */
    public static void dropTables(Connection con) throws DBException {
        String bookq = "DROP TABLE IF EXISTS Book";
        String userq = "DROP TABLE IF EXISTS User";
        String roomq = "DROP TABLE IF EXISTS Room";
        String supplyq = "DROP TABLE IF EXISTS Supply";
        String fineq = "DROP TABLE IF EXISTS Fine";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(supplyq);
            st.executeUpdate(fineq);
            st.executeUpdate(roomq);
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - parameter needed to build the User object in the DB
     * @param email - parameter needed to build the User object in the DB
     * @param password - parameter needed to build the User object in the DB
     * @param birthDate - parameter needed to build the User object in the DB
     * @throws DBException
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - parameter needed to build the Book object in the DB
     * @param author - parameter needed to build the Book object in the DB
     * @param publishDate - parameter needed to build the Book object in the DB
     * @param available - parameter needed to build the Book object in the DB
     * @throws DBException
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - parameter needed to build the Room object in the DB
     * @param day - parameter needed to build the Room object in the DB
     * @param month - parameter needed to build the Room object in the DB
     * @param hourBeg - parameter needed to build the Room object in the DB
     * @param hourEnd - parameter needed to build the Room object in the DB
     * @param booked - parameter needed to build the Room object in the DB
     * @throws DBException
     */
    public static void addRoom(Connection con, String name, int day, String month, int hourBeg, int hourEnd, Boolean booked, String user_name)
            throws DBException {
        try (PreparedStatement stmt = con
                .prepareStatement("INSERT INTO Room (name, day, month, hourBeg, hourEnd, booked, u_id) VALUES (?,?,?,?,?,?,?)");
             Statement stmtForId = con.createStatement()) {
            stmt.setString(1, name);
            stmt.setInt(2, day);
            stmt.setString(3, month);
            stmt.setInt(4, hourBeg);
            stmt.setInt(5, hourEnd);
            stmt.setBoolean(6, booked);
            stmt.setString(7, user_name);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Room cannot be added");
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - parameter needed to build the Supply object in the DB
     * @param price - parameter needed to build the Supply object in the DB
     * @throws DBException
     */
    public static void addSupply(Connection con, String name, double price, String type)
            throws DBException {
        try (PreparedStatement stmt = con
                .prepareStatement("INSERT INTO Supply (name, price, type) VALUES (?,?,?)");
             Statement stmtForId = con.createStatement()) {
            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setString(3, type);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Supply cannot be added");
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

    //Menu tabla - 4 strings TODO

    public static void addFine(Connection con, double quantity, String id_user)
            throws DBException {
        try (PreparedStatement stmt = con
                .prepareStatement("INSERT INTO Fine (quantity, email) VALUES (?, ?)");
             Statement stmtForId = con.createStatement()) {
            stmt.setDouble(1, quantity);
            stmt.setString(2, id_user);
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Supply cannot be added");
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @return ArrayList containing all users stored in the DB
     * @throws SQLException
     */
    public static ArrayList<User> getUsersList(Connection con) throws SQLException {
        String sent = "SELECT * FROM User";
        Statement st = null;
        st = con.createStatement();
        usersList = new ArrayList<User>();
        User user = new User();
        ResultSet rs = st.executeQuery(sent);
        while (rs.next()) {
            user = new User(rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5), new ArrayList<Book>(), new ArrayList<Fine>());
            usersList.add(user);
        }
        rs.close();
        return usersList;
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @return ArrayList containing all books stored in the DB
     * @throws SQLException
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @return ArrayList containing all rooms stored in the DB
     * @throws SQLException
     */
    public static ArrayList<Room> getRoomsList(Connection con) throws SQLException {
        String sent = "SELECT * FROM Room";
        Statement st = null;
        st = con.createStatement();
        roomsList = new ArrayList<Room>();
        Room room = new Room();
        ResultSet rs = st.executeQuery(sent);
        while (rs.next()) {
            User u = new User();
            sent = "SELECT * FROM User WHERE email = '" + rs.getString(8)+"'";
            st = null;
            st = con.createStatement();
            ResultSet rs2 = st.executeQuery(sent);
            while(rs2.next()) {
                u = new User(rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getDate(5), new ArrayList<Book>(), new ArrayList<Fine>());
            }
            room = new Room(rs.getString(2), u, rs.getInt(3), rs.getString(4), rs.getInt(5), rs.getInt(6), rs.getBoolean(7));
            roomsList.add(room);
        }
        rs.close();
        return roomsList;
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @return ArrayList containing all supplies stored in the DB
     * @throws SQLException
     */
    public static ArrayList<Supply> getSuppliesList(Connection con) throws SQLException {
        String sent = "SELECT * FROM Supply";
        Statement st = null;
        st = con.createStatement();
        suppliesList = new ArrayList<Supply>();
        Supply supply = new Supply();
        ResultSet rs = st.executeQuery(sent);
        while (rs.next()) {
            supply = new Supply(rs.getString(2), rs.getDouble(3), rs.getString(4));
            suppliesList.add(supply);
        }
        rs.close();
        return suppliesList;
    }

    public static ArrayList<Fine> getFinesList(Connection con) throws SQLException {
        String sent = "SELECT * FROM Fine";
        Statement st = null;

        st = con.createStatement();

        finesList = new ArrayList<Fine>();
        Fine fine = new Fine();
        ResultSet rs = st.executeQuery(sent);
        while (rs.next()) {
            User u = new User();
            sent = "SELECT * FROM User WHERE email = '" + rs.getString(3)+"'";
            st = null;
            st = con.createStatement();
            ResultSet rs2 = st.executeQuery(sent);
            while(rs2.next()) {
                u = new User(rs2.getString(2), rs2.getString(3), rs2.getString(4), rs2.getDate(5), new ArrayList<Book>(), new ArrayList<Fine>());
            }
            fine = new Fine(u, rs.getDouble(2));
            finesList.add(fine);
        }
        rs.close();
        return finesList;
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the book we are looking for delete
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the user we are looking for delete
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the room we are looking for delete
     */
    public static void deleteRoom(Connection con, String name) {
        String sent = "DELETE FROM Room WHERE name = '" + name + "'";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the supply we are looking for delete
     */
    public static void deleteSupply(Connection con, String name) {
        String sent = "DELETE FROM Supply WHERE name = '" + name + "'";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void deleteFine(Connection con, int f_id) {
        String sent = "DELETE FROM Fine WHERE f_id = " + f_id;
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(sent);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the book we are looking for update
     * @param author - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the book we are looking for update
     * @param newName - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the book we are looking for update
     * @param date - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the book we are looking for update
     * @param available - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the user we are looking for update
     * @param newName - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the user we are looking for update
     * @param email - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the user we are looking for update
     * @param password - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the user we are looking for update
     * @param birthdate - parameter we want to update
     */
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

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param newname - parameter we want to update
     * @param name - way to find the room we are looking for update
     */
    public static void updateRoomName(Connection con, String name, String newname) {
        String sql = "UPDATE Room SET name = '" + newname + "' WHERE name = " + name;

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param day - parameter we want to update
     * @param name - way to find the room we are looking for update
     */
    public static void updateRoomDay(Connection con, int day, String name) {
        String sql = "UPDATE Room SET day = " + day + " WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param month - parameter we want to update
     * @param name - way to find the room we are looking for update
     */
    public static void updateRoomMonth(Connection con, String month, String name) {
        String sql = "UPDATE Room SET month = '" + month + "' WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param hourBeg - parameter we want to update
     * @param name - way to find the room we are looking for update
     */
    public static void updateRoomHourBeg(Connection con, int hourBeg, String name) {
        String sql = "UPDATE Room SET hourBeg = " + hourBeg + " WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param hourEnd - parameter we want to update
     * @param name - way to find the room we are looking for update
     */
    public static void updateRoomHourEnd(Connection con, int hourEnd, String name) {
        String sql = "UPDATE Room SET hourEnd = " + hourEnd + " WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param booked - parameter we want to update
     * @param name - way to find the room we are looking for update
     */
    public static void updateRoomBooked(Connection con, Boolean booked, String name) {
        String sql = "UPDATE Room SET booked = " + booked + " WHERE name = '" + name + "'";

        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param id - way to find the supply we are looking for update
     * @param name - parameter we want to update
     */
    public static void updateSupplyName(Connection con, int id, String name) {
        String sql = "UPDATE Supply SET name = '" + name + "' WHERE id = " + id;
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the supply we are looking for update
     * @param price - parameter we want to update
     */
    public static void updateSupplyPrice(Connection con, String name, double price) {
        String sql = "UPDATE Supply SET price = " + price + " WHERE name = '" + name + "'";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param name - way to find the supply we are looking for update
     * @param arrivingDate - parameter we want to update
     */
    public static void updateSupplyDate(Connection con, String name, Date arrivingDate) {
        String sql = "UPDATE Supply SET arrivingDate = " + arrivingDate + " WHERE name = '" + name + "'";
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param f_id - way to find the fine we are looking for update
     * @param newQuantity - parameter we want to update
     */
    public static void updateFineQuantity(Connection con, int f_id, double newQuantity) {
        String sql = "UPDATE Fine SET quantity = " + newQuantity + " WHERE f_id = " + f_id;
        try {
            Statement st = con.createStatement();
            st.executeUpdate(sql);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *
     * @param con - connection to the DB needed to be able to work or apply changes to the database
     * @param f_id - way to find the fine we are looking for update
     * @param u_id - parameter we want to update
     */
    public static void updateFineUser(Connection con, int f_id, int u_id) {
        String sql = "UPDATE Fine SET u_id = " + u_id + " WHERE f_id = " + f_id;
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