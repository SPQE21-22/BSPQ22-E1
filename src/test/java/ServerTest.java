import org.apache.log4j.Logger;
import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import server.data.domain.Book;
import server.data.domain.Fine;
import server.data.domain.Room;
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

    private static final Logger logger = Logger.getLogger(ServerTest.class.getName());


    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    @Before
    public void setUp() throws DBException, SQLException {
        logger.info("Started Setup");
    	client = ClientBuilder.newClient();
    	webTarget = client.target(String.format("http://%s:%s/rest", "127.0.0.1", "8080"));
        con = DB.initBD();
        DB.createTables(con);
        DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
        DB.addUser(con, "Ruben", "r@mail", "4321",new Date(2022, 1, 10));
        DB.addBook(con, "El Camino de lo reyes", "Brandon Sanderson",  new Date(2006, 3, 15), true);
        DB.addBook(con, "El Imperio Final", "Sandon Branderson",  new Date(2006, 3, 15), true);
        logger.info("Finished Setup");
    }

    @Test
    public void testUserLogin(){
        logger.info("Started UserLogin");
        WebTarget donationsWebTarget = webTarget.path("users/login");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity("a@mail", MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);
        Assert.assertEquals(u1.getName(), "Alex");
        Assert.assertEquals(u1.getEmail(), "a@mail");
        Assert.assertEquals(u1.getPassword(), "1234");
        logger.info("Finished UserLogin");
    }

    @Test
    @PerfTest(invocations = 30, threads = 3)
    @Required(max = 1000, average = 500)
    public void testCreateUser() throws InterruptedException {
        logger.info("Started CreateUser");
        User u0 = new User(1,"Ruben", "r@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        WebTarget donationsWebTarget = webTarget.path("users/createUser");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(u0, MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);
        Assert.assertEquals(u1.getName(), "Ruben");
        Assert.assertEquals(u1.getEmail(), "r@mail");
        Assert.assertEquals(u1.getPassword(), "4321");
        logger.info("Finished CreateUser");
        Thread.sleep(30);
    }

    @Test
    public void testGetBooks(){
        logger.info("Started GetBooks");
        WebTarget donationsWebTarget = webTarget.path("users/books");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        User u1 = response.readEntity(User.class);
        logger.info(u1.getBooks().get(0));
        logger.info("Finished GetBooks");
    }
    

    @Test
    @PerfTest(invocations = 20, threads = 2)
    @Required(max = 1000, average = 500)
    public void testAddBook(){
        logger.info("Started AddBook");
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
        logger.info("Finished AddBook");
    }

    @Test
    @PerfTest(invocations = 20, threads = 3)
    @Required(max = 1000, average = 500)
    public void testUpdateBook(){
        logger.info("Started UpdateBook");
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
        logger.info("Finished UpdateBook");
    }
    
    @Test
    @PerfTest(invocations = 20, threads = 2)
    @Required(max = 500, average = 500)
    public void testAddRoom(){
        logger.info("Started AddRoom");
        Room r0 = new Room();
        r0.setName("Sala de reuniones");
        r0.setDay(1);
        r0.setMonth("Enero");
        r0.setHourBeg(7);
        r0.setHourEnd(12);
        r0.setBooked(false);
        WebTarget donationsWebTarget = webTarget.path("users/addRoom");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(r0, MediaType.APPLICATION_JSON));
        Room r1 = response.readEntity(Room.class);
        Assert.assertEquals(r1.getName(), "Sala de reuniones");
        Assert.assertEquals(r1.getMonth(), "Enero");
        Assert.assertEquals(r1.getBooked(), false);
        logger.info("Finished AddRoom");
    }

    @Test
    public void testGetResp(){
        logger.info("Started GetResp");
        WebTarget donationsWebTarget = webTarget.path("users/response");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        String str = response.readEntity(String.class);
        Assert.assertEquals("Response", str);
        logger.info("Finished GetResp");
    }
}