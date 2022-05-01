import org.junit.Before;
import org.junit.Test;
import server.data.domain.Book;
import server.data.domain.User;
import server.sql.DB;
import server.sql.DBException;
import org.junit.Assert;

import javax.ws.rs.client.ClientBuilder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTest {

    private Connection con;
    private DB db = new DB();

    @Before
    public void setUp() throws DBException, SQLException {
        con = DB.initBD();
        DB.createTables(con);
        DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
        DB.addUser(con, "Ruben", "r@mail", "4321",new Date(2022, 1, 10));
        DB.addBook(con, "El Camino de lo reyes", "Brandon Sanderson",  new Date(2006, 3, 15), true);
        DB.addBook(con, "El Imperio Final", "Sandon Branderson",  new Date(2006, 3, 15), true);
    }

    @Test
    public void testGetUsers() throws SQLException {
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.get(0).getName(), "Alex");
        Assert.assertEquals(usersList.get(0).getEmail(), "a@mail");
        Assert.assertEquals(usersList.get(0).getPassword(), "1234");

        Assert.assertEquals(usersList.get(1).getName(), "Ruben");
        Assert.assertEquals(usersList.get(1).getEmail(), "r@mail");
        Assert.assertEquals(usersList.get(1).getPassword(), "4321");
    }

    @Test
    public void testGetBooks() throws SQLException {
        List<Book> bookList = DB.getBooksList(con);
        Assert.assertEquals(bookList.get(0).getName(), "El Camino de lo reyes");
        Assert.assertEquals(bookList.get(0).getAuthor(), "Brandon Sanderson");
        Assert.assertEquals(bookList.get(0).getAvailable(), true);

        Assert.assertEquals(bookList.get(1).getName(), "El Imperio Final");
        Assert.assertEquals(bookList.get(1).getAuthor(), "Sandon Branderson");
        Assert.assertEquals(bookList.get(1).getAvailable(), true);
    }

    @Test
    public void testAddUser() throws DBException, SQLException {
        DB.addUser(con, "Eneko", "e@mail", "12345", new Date(2006, 3, 15));
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.size(), 3);
        Assert.assertEquals(usersList.get(2).getName(), "Eneko");
        Assert.assertEquals(usersList.get(2).getEmail(), "e@mail");
        Assert.assertEquals(usersList.get(2).getPassword(), "12345");
    }

    @Test
    public void testAddBook() throws DBException, SQLException {
        DB.addBook(con, "Juramentada", "Paco",  new Date(2006, 3, 15), false);
        List<Book> booksList = DB.getBooksList(con);
        Assert.assertEquals(booksList.size(), 3);
        Assert.assertEquals(booksList.get(2).getName(), "Juramentada");
        Assert.assertEquals(booksList.get(2).getAuthor(), "Paco");
        Assert.assertEquals(booksList.get(2).getAvailable(), false);
    }

    @Test
    public void testDeleteBook() throws SQLException {
        DB.deleteBook(con, "Juramentada");
        List<Book> booksList = DB.getBooksList(con);
        Assert.assertEquals(booksList.size(), 2);
    }

    @Test
    public void testDeleteUser() throws SQLException{
        DB.deleteUser(con, "Eneko");
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.size(), 2);
    }

    @Test
    public void testUpdateBook() throws SQLException {
        DB.updateBookAuthor(con, "El imperio final", "BRANDON SANDERSON");
        DB.updateBookPublishDate(con, "El imperio final", new Date(2016, 7, 15));
        DB.updateBookAvailability(con, "El imperio final", false);
        DB.updateBookName(con, "El imperio final", "El Pozo de la Ascension");
        List<Book> booksList = DB.getBooksList(con);
        Assert.assertEquals(booksList.size(), 2);
        Assert.assertEquals(booksList.get(1).getName(), "El Pozo de la Ascension");
        Assert.assertEquals(booksList.get(1).getAuthor(), "BRANDON SANDERSON");
        Assert.assertEquals(booksList.get(1).getAvailable(), false);

    }

    @Test
    public void testUpdateUser() throws SQLException {
        DB.updateUserEmail(con, "Ruben", "uben@mail");
        DB.updateUserPassword(con, "Ruben", "5678");
        DB.updateUserBirthdate(con, "Ruben", new Date(2008, 9, 25));
        DB.updateUserName(con, "Ruben", "Aida");
        List<User> usersList = DB.getUsersList(con);
        Assert.assertEquals(usersList.size(), 2);
        Assert.assertEquals(usersList.get(1).getName(), "Aida");
        Assert.assertEquals(usersList.get(1).getEmail(), "uben@mail");
        Assert.assertEquals(usersList.get(1).getPassword(), "5678");
    }
}