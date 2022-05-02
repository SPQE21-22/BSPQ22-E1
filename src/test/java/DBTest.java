import junit.framework.JUnit4TestAdapter;
import org.apache.log4j.Logger;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;
import server.data.domain.Book;
import server.data.domain.Room;
import server.data.domain.User;
import server.sql.DB;
import server.sql.DBException;

import javax.ws.rs.client.ClientBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import org.databene.contiperf.*;

public class DBTest {
    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    private Connection con;
    private static final Logger logger = Logger.getLogger(DBTest.class.getName());

    @Before
    public void setUp() throws DBException, SQLException {
        logger.info("Started SetUp");
        con = DB.initBD();
        DB.createTables(con);
        DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
        DB.addUser(con, "Ruben", "r@mail", "4321",new Date(2022, 1, 10));
        DB.addBook(con, "El Camino de lo reyes", "Brandon Sanderson",  new Date(2006, 3, 15), true);
        DB.addBook(con, "El Imperio Final", "Sandon Branderson",  new Date(2006, 3, 15), true);
        DB.addRoom(con, "Sala de ordenadores", 14, "Mayo", 17, 19, false);
        DB.addRoom(con, "Sala de trabajo", 3, "Julio", 10, 13, false);
        
        logger.info("Finished SetUp");
    }

    @Test
    public void testGetUsers() throws SQLException, InterruptedException {
        logger.info("Started GetUsers");
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.get(0).getName(), "Alex");
        Assert.assertEquals(usersList.get(0).getEmail(), "a@mail");
        Assert.assertEquals(usersList.get(0).getPassword(), "1234");

        Assert.assertEquals(usersList.get(1).getName(), "Ruben");
        Assert.assertEquals(usersList.get(1).getEmail(), "r@mail");
        Assert.assertEquals(usersList.get(1).getPassword(), "4321");
        logger.info("Finished GetUsers");

    }

    @Test
    public void testGetBooks() throws SQLException {
        logger.info("Started GetBooks");
        List<Book> bookList = DB.getBooksList(con);
        Assert.assertEquals(bookList.get(0).getName(), "El Camino de lo reyes");
        Assert.assertEquals(bookList.get(0).getAuthor(), "Brandon Sanderson");
        Assert.assertEquals(bookList.get(0).getAvailable(), true);

        Assert.assertEquals(bookList.get(1).getName(), "El Imperio Final");
        Assert.assertEquals(bookList.get(1).getAuthor(), "Sandon Branderson");
        Assert.assertEquals(bookList.get(1).getAvailable(), true);
        logger.info("Finished GetBooks");
    }

    @Test
    public void testGetRooms() throws SQLException {
        logger.info("Started GetRooms");
        List<Room> roomsList = DB.getRoomsList(con);
        Assert.assertEquals(roomsList.get(0).getName(), "Sala de ordenadores");
        Assert.assertEquals(roomsList.get(0).getDay(), 14);
        Assert.assertEquals(roomsList.get(0).getMonth(), "Mayo");
        Assert.assertEquals(roomsList.get(0).getBooked(), false);

        Assert.assertEquals(roomsList.get(1).getName(), "Sala de trabajo");
        Assert.assertEquals(roomsList.get(1).getDay(), 3);
        Assert.assertEquals(roomsList.get(1).getMonth(), "Julio");
        Assert.assertEquals(roomsList.get(1).getBooked(), false);
        logger.info("Finished GetRooms");
    }
    
    @Test
    public void testAddUser() throws DBException, SQLException {
        logger.info("Started AddUser");
        DB.addUser(con, "Eneko", "e@mail", "12345", new Date(2006, 3, 15));
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.size(), 3);
        Assert.assertEquals(usersList.get(2).getName(), "Eneko");
        Assert.assertEquals(usersList.get(2).getEmail(), "e@mail");
        Assert.assertEquals(usersList.get(2).getPassword(), "12345");
        
        logger.info("Finished AddUser");
    }

    @Test
    public void testAddBook() throws DBException, SQLException {
        logger.info("Started AddBook");
        DB.addBook(con, "Juramentada", "Paco",  new Date(2006, 3, 15), false);
        List<Book> booksList = DB.getBooksList(con);
        Assert.assertEquals(booksList.size(), 3);
        Assert.assertEquals(booksList.get(2).getName(), "Juramentada");
        Assert.assertEquals(booksList.get(2).getAuthor(), "Paco");
        Assert.assertEquals(booksList.get(2).getAvailable(), false);
        logger.info("Finished AddBook");
    }
    
    @Test
    public void testAddRoom() throws DBException, SQLException {
        logger.info("Started AddRoom");
        DB.addRoom(con, "Sala de lectura", 2,  "Febrero",9,10, false);
        List<Room> roomList = DB.getRoomsList(con);
        Assert.assertEquals(roomList.size(), 3);
        Assert.assertEquals(roomList.get(2).getName(), "Sala de lectura");
        Assert.assertEquals(roomList.get(2).getDay(), 2);
        Assert.assertEquals(roomList.get(2).getMonth(), "Febrero");
        Assert.assertEquals(roomList.get(2).getBooked(), false);
        logger.info("Finished AddRoom");
    }

    @Test
    public void testDeleteBook() throws SQLException {
        logger.info("Started DeleteBook");
        DB.deleteBook(con, "Juramentada");
        List<Book> booksList = DB.getBooksList(con);
        Assert.assertEquals(booksList.size(), 2);
        logger.info("Finished DeleteBook");
    }

    @Test
    public void testDeleteUser() throws SQLException{
        logger.info("Started DeleteUser");
        DB.deleteUser(con, "Eneko");
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.size(), 2);
        logger.info("Finished DeleteUser");
    }
    
    @Test
    public void testDeleteRoom() throws SQLException{
        logger.info("Started DeleteRoom");
        DB.deleteRoom(con, "Eneko");
        List<Room> roomsList = DB.getRoomsList(con);
        Assert.assertEquals(roomsList.size(), 2);
        logger.info("Finished DeleteRoom");
    }

    @Test
    public void testUpdateBook() throws SQLException {
        logger.info("Started UpdateBook");
        DB.updateBookAuthor(con, "El imperio final", "BRANDON SANDERSON");
        DB.updateBookPublishDate(con, "El imperio final", new Date(2016, 7, 15));
        DB.updateBookAvailability(con, "El imperio final", false);
        DB.updateBookName(con, "El imperio final", "El Pozo de la Ascension");
        List<Book> booksList = DB.getBooksList(con);
        Assert.assertEquals(booksList.size(), 2);
        Assert.assertEquals(booksList.get(1).getName(), "El Pozo de la Ascension");
        Assert.assertEquals(booksList.get(1).getAuthor(), "BRANDON SANDERSON");
        Assert.assertEquals(booksList.get(1).getAvailable(), false);
        logger.info("Finished UpdateBook");

    }

    @Test
    public void testUpdateUser() throws SQLException {
        logger.info("Started UpdateUser");
        DB.updateUserEmail(con, "Ruben", "uben@mail");
        DB.updateUserPassword(con, "Ruben", "5678");
        DB.updateUserBirthdate(con, "Ruben", new Date(2008, 9, 25));
        DB.updateUserName(con, "Ruben", "Aida");
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.size(), 2);
        Assert.assertEquals(usersList.get(1).getName(), "Aida");
        Assert.assertEquals(usersList.get(1).getEmail(), "uben@mail");
        Assert.assertEquals(usersList.get(1).getPassword(), "5678");
        logger.info("Finished UpdateUser");
    }
    
    @Test
    public void testUpdateRoom() throws SQLException {
        logger.info("Started UpdateRoom");
        DB.updateRoomDay(con,2, "Sala de ordenadores");
        DB.updateRoomMonth(con, "Diciembre", "Sala de ordenadores");
        DB.updateRoomHourEnd(con, 21, "Sala de ordenadores");
        DB.updateRoomHourBeg(con, 21, "Sala de ordenadores");
        DB.updateRoomBooked(con, true, "Sala de ordenadores");
        DB.updateRoomName(con, "Sala de ordenadores", "Sala de portatiles");
        List<Room> roomsList = DB.getRoomsList(con);
        Assert.assertEquals(roomsList.size(), 2);
        Assert.assertEquals(roomsList.get(0).getName(), "Sala de portatiles");
        Assert.assertEquals(roomsList.get(0).getDay(), 2);
        Assert.assertEquals(roomsList.get(0).getMonth(), "Diciembre");
        Assert.assertEquals(roomsList.get(0).getHourEnd(), 21);
        Assert.assertEquals(roomsList.get(0).getHourBeg(), 21);
        Assert.assertEquals(roomsList.get(0).getBooked(), true);
        logger.info("Finished UpdateRoom");
    }
}