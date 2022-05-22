import org.apache.log4j.Logger;
import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import server.data.domain.*;
import server.sql.DB;
import server.sql.DBException;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
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
        DB.addFine(con, 35, "a@mail");
        DB.addFine(con, 20, "a@mail");
        DB.addSupply(con,"Pure",50,"Drink");
        DB.addSupply(con,"Bizcocho",12,"Pastry");
        DB.addSupply(con,"Lentejas",50,"Main Course");
        DB.addSupply(con,"Pollo",12,"Starter");
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
    public void testCreateUser() throws InterruptedException {
        logger.info("Started CreateUser");
        User u0 = new User("Aida", "ai@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        WebTarget donationsWebTarget = webTarget.path("users/createUser");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(u0, MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);
        Assert.assertEquals(u1.getName(), "Aida");
        Assert.assertEquals(u1.getEmail(), "ai@mail");
        Assert.assertEquals(u1.getPassword(), "4321");
        DB.deleteUser(con,"Aida");
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
        Assert.assertEquals(u1.getBooks().size(), 2);
        Assert.assertEquals(u1.getBooks().get(0).getName(), "El Camino de lo reyes");
        logger.info(u1.getBooks().get(0));
        logger.info("Finished GetBooks");
    }
    

    @Test
    @PerfTest(invocations = 15, threads = 2)
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
    public void testUpdateBook(){
        logger.info("Started addBook");
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
    public void testAddRoom(){
        logger.info("Started AddRoom");
        User u0 = new User("Ruben", "r@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        Room r0 = new Room("Reuniones",u0, 1, "Enero", 7, 12, false);
        WebTarget donationsWebTarget = webTarget.path("users/addRoom");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(r0, MediaType.APPLICATION_JSON));
        Room r1 = response.readEntity(Room.class);
        Assert.assertEquals(r1.getName(), "Reuniones");
        Assert.assertEquals(r1.getUser().getName(), "Ruben");
        Assert.assertEquals(r1.getDay(), 1);
        Assert.assertEquals(r1.getMonth(), "Enero");
        Assert.assertEquals(r1.getHourBeg(), 7);
        Assert.assertEquals(r1.getHourEnd(), 12);
        Assert.assertEquals(r1.getBooked(), false);
        logger.info("Finished AddRoom");
    }

    @Test
    public void testAddFine(){
        logger.info("Started AddFine");
        User u0 = new User("Ruben", "r@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        Fine f = new Fine(u0, 40);
        WebTarget donationsWebTarget = webTarget.path("users/fine");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(f, MediaType.APPLICATION_JSON));
        Fine f1 = response.readEntity(Fine.class);
        Assert.assertEquals(f1.getUser().getName(), "Ruben");
        Assert.assertEquals(f1.getQuantity(), 40,0.000001d);
        logger.info("Finished AddFine");
    }

    @Test
    public void testGetFines() throws InterruptedException {
        logger.info("Started GetFines");
        User ej = new User("Alex", "a@mail", "1234",new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        WebTarget donationsWebTarget = webTarget.path("users/fines");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(ej, MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);
        System.out.println(u1);
        Assert.assertEquals(u1.getFines().size(), 2);
        Assert.assertEquals(u1.getFines().get(0).getQuantity(), 35,0.000001d);
        Assert.assertEquals(u1.getFines().get(1).getQuantity(), 20,0.000001d);
        Assert.assertEquals(u1.getName(), u1.getFines().get(0).getUser().getName());
        logger.info("Finished GetFines");
    }

    @Test
    public void testGetSupplies(){
        logger.info("Started GetSupplies");
        WebTarget donationsWebTarget = webTarget.path("users/supplies");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        Menu men = response.readEntity(Menu.class);
        logger.info(men.getDrink().get(0));
        Assert.assertEquals(men.getDrink().get(0).getName(), "Pure");
        Assert.assertEquals(men.getDrink().get(0).getType(), "Drink");
        Assert.assertEquals(men.getPastry().get(0).getName(), "Bizcocho");
        Assert.assertEquals(men.getPastry().get(0).getType(), "Pastry");
        Assert.assertEquals(men.getMain().get(0).getName(), "Lentejas");
        Assert.assertEquals(men.getMain().get(0).getType(), "Main Course");
        Assert.assertEquals(men.getStarter().get(0).getName(), "Pollo");
        Assert.assertEquals(men.getStarter().get(0).getType(), "Starter");
        logger.info("Finished GetSupplies");
    }


    @Test
    public void testAddSupply(){
        logger.info("Started AddSupply");
        Supply s0 = new Supply("Tarta",10,"Pastry");
        WebTarget donationsWebTarget = webTarget.path("users/addSupply");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(s0, MediaType.APPLICATION_JSON));
        Supply s1 = response.readEntity(Supply.class);
        Assert.assertEquals(s1.getName(), "Tarta");
        Assert.assertEquals(s1.getPrice(), 10, 0.000001d);
        Assert.assertEquals(s1.getType(), "Pastry");
        logger.info("Finished AddSupply");
    }

    @Test
    public void testCreateMenu(){
        logger.info("Started CreateMenu");
        Menu menu = new Menu();
        Supply primero = new Supply("Fabada",10,"Starter");
        Supply main = new Supply("Chuleton",10,"Main Course");
        Supply postre = new Supply("Manzana",10,"Pastry");
        Supply beber = new Supply("Vino",10,"Drink");
        menu.getStarter().add(primero);
        menu.getMain().add(main);
        menu.getPastry().add(postre);
        menu.getDrink().add(beber);

        WebTarget donationsWebTarget = webTarget.path("users/createMenu");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(menu, MediaType.APPLICATION_JSON));
        Menu b1 = response.readEntity(Menu.class);
        Assert.assertEquals(b1.getStarter().get(0).getName(), "Fabada");
        Assert.assertEquals(b1.getMain().get(0).getName(), "Chuleton");
        Assert.assertEquals(b1.getPastry().get(0).getName(), "Manzana");
        Assert.assertEquals(b1.getDrink().get(0).getName(), "Vino");
        logger.info("Finished CreateMenu");
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

    @Test
    public void testUserCreation(){
        logger.info("Started Integration test User");
        User u0 = new User("Aida", "ai@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        WebTarget donationsWebTarget = webTarget.path("users/createUser");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(u0, MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);

        donationsWebTarget = webTarget.path("users/login");
        invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.post(Entity.entity(u1.getEmail(), MediaType.APPLICATION_JSON));
        User u2 = response.readEntity(User.class);
        Assert.assertEquals(u2.getName(), "Aida");
        Assert.assertEquals(u2.getEmail(), "ai@mail");
        Assert.assertEquals(u2.getPassword(), "4321");

        Assert.assertEquals(u1.getName(), u2.getName());
        Assert.assertEquals(u1.getEmail(), u2.getEmail());
        Assert.assertEquals(u1.getPassword(), u2.getPassword());
        logger.info("Finished Integration test User");
    }

    @Test
    public void testCheckLibrary(){
        logger.info("Started Integration test Books");
        Book b0 = new Book();
        b0.setName("A");
        b0.setAuthor("auth");
        b0.setAvailable(true);
        b0.setPublishDate(new Date(2022, 1, 10));
        WebTarget donationsWebTarget = webTarget.path("users/addBook");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        invocationBuilder.post(Entity.entity(b0, MediaType.APPLICATION_JSON));

        donationsWebTarget = webTarget.path("users/books");
        invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();

        User u1 = response.readEntity(User.class);
        Assert.assertEquals(u1.getBooks().size(), 3);
        Assert.assertEquals(u1.getBooks().get(2).getName(), "A");
        Assert.assertEquals(u1.getBooks().get(2).getAuthor(), "auth");
        Assert.assertEquals(u1.getBooks().get(2).getAvailable(), true);
        logger.info("Finished Integration test Books");
    }

    @Test
    public void testCheckFines(){
        logger.info("Started Integration test fines");
        User u0 = new User("Ruben", "r@mail", "4321", new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        Fine f = new Fine(u0, 40);
        WebTarget donationsWebTarget = webTarget.path("users/fine");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(f, MediaType.APPLICATION_JSON));
        Fine f1 = response.readEntity(Fine.class);
        Assert.assertEquals(f1.getUser().getName(), "Ruben");
        Assert.assertEquals(f1.getQuantity(), 40,0.000001d);

        User ej = new User("Alex", "a@mail", "1234",new Date(2022, 1, 10), new ArrayList<Book>(),new ArrayList<Fine>());
        donationsWebTarget = webTarget.path("users/fines");
        invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.post(Entity.entity(ej, MediaType.APPLICATION_JSON));
        User u1 = response.readEntity(User.class);

        Assert.assertEquals(u1.getFines().size(), 2);
        Assert.assertEquals(u1.getFines().get(0).getQuantity(), 35,0.000001d);
        Assert.assertEquals(u1.getFines().get(1).getQuantity(), 20,0.000001d);
        Assert.assertEquals(u1.getName(), u1.getFines().get(0).getUser().getName());
        logger.info("Started Integration test fines");
    }

    @Test
    public void testCheckSupplies(){
        logger.info("Started Integration test supplies");
        Supply s0 = new Supply("Tarta",10,"Pastry");
        WebTarget donationsWebTarget = webTarget.path("users/addSupply");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.post(Entity.entity(s0, MediaType.APPLICATION_JSON));
        Supply s1 = response.readEntity(Supply.class);
        Assert.assertEquals(s1.getName(), "Tarta");
        Assert.assertEquals(s1.getPrice(), 10, 0.000001d);
        Assert.assertEquals(s1.getType(), "Pastry");

        donationsWebTarget = webTarget.path("users/supplies");
        invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        response = invocationBuilder.get();
        Menu men = response.readEntity(Menu.class);
        Assert.assertEquals(men.getDrink().get(0).getName(), "Pure");
        Assert.assertEquals(men.getDrink().get(0).getType(), "Drink");
        Assert.assertEquals(men.getPastry().get(0).getName(), "Bizcocho");
        Assert.assertEquals(men.getPastry().get(0).getType(), "Pastry");
        Assert.assertEquals(men.getMain().get(0).getName(), "Lentejas");
        Assert.assertEquals(men.getMain().get(0).getType(), "Main Course");
        Assert.assertEquals(men.getStarter().get(0).getName(), "Pollo");
        Assert.assertEquals(men.getStarter().get(0).getType(), "Starter");
        logger.info("Finished Integration test supplies");
    }
}