
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.data.domain.Book;
import server.data.domain.User;
import server.sql.DB;
import server.sql.DBException;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertNotNull;

public class ServerTest {
    private Client client;
    private WebTarget webTarget;
    private Connection con;
    private DB db = new DB();

    @Before
    public void setUp() throws DBException, SQLException {
    	client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", "127.0.0.1", "8080"));
        con = DB.initBD();
        DB.createTables(con);
        DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
        DB.addUser(con, "Ruben", "r@mail", "4321",new Date(2022, 1, 10));
        DB.addBook(con, "El Camino de lo reyes", "Brandon Sanderson",  new Date(2006, 3, 15), true);
        DB.addBook(con, "El Imperio Final", "Sandon Branderson",  new Date(2006, 3, 15), true);
    }

    @Test
    public void testUserLogin(){
        WebTarget donationsWebTarget = webTarget.path("users/login");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity("a@mail", MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);
        Assert.assertEquals(u1.getName(), "Alex");
        Assert.assertEquals(u1.getEmail(), "a@mail");
        Assert.assertEquals(u1.getPassword(), "1234");
    }

    @Test
    public void testCreateUser(){
        User u0 = new User("Ruben", "r@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>());
        WebTarget donationsWebTarget = webTarget.path("users/createUser");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(u0, MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);
        Assert.assertEquals(u1.getName(), "Ruben");
        Assert.assertEquals(u1.getEmail(), "r@mail");
        Assert.assertEquals(u1.getPassword(), "4321");
    }

    @Test
    public void testGetBooks(){
        WebTarget donationsWebTarget = webTarget.path("users/books");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        User u1 = response.readEntity(User.class);
        System.out.println(u1.getBooks().get(0));
    }

    @Test
    public void testAddBook(){
        Book b0 = new Book();
        b0.setName("A");
        b0.setAuthor("auth");
        b0.setAvailable(true);
        b0.setPublishDate(new Date(2022, 1, 10));
        WebTarget donationsWebTarget = webTarget.path("users/addBook");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(b0, MediaType.APPLICATION_JSON));
        Book b1 = response.readEntity(Book.class);
        Assert.assertEquals(b1.getName(), "A");
        Assert.assertEquals(b1.getAuthor(), "auth");
        Assert.assertEquals(b1.getAvailable(), true);
        Assert.assertEquals(b1.getPublishDate(), new Date(2022, 1, 10));
    }

    @Test
    public void testUpdateBook(){
        Book b0 = new Book();
        b0.setName("A");
        b0.setAuthor("auth");
        b0.setAvailable(false);
        b0.setPublishDate(new Date(2022, 1, 10));
        WebTarget donationsWebTarget = webTarget.path("users/addBook");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(b0, MediaType.APPLICATION_JSON));
        Book b1 = response.readEntity(Book.class);
        Assert.assertEquals(b1.getName(), "A");
        Assert.assertEquals(b1.getAuthor(), "auth");
        Assert.assertEquals(b1.getAvailable(), false);
        Assert.assertEquals(b1.getPublishDate(), new Date(2022, 1, 10));
    }

    @Test
    public void testGetResp(){
        WebTarget donationsWebTarget = webTarget.path("users/response");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String str = response.readEntity(String.class);
        Assert.assertEquals("Response", str);
    }
}