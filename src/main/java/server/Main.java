package server;

import server.data.domain.User;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
//import org.apache.logging.log4j.*;
import server.sql.DB;
import server.sql.DBException;

public class Main implements Runnable{
    private Client client;
    private WebTarget webTarget;
    private Thread thread;
    private Connection con;
    private DB db = new DB();
    private final AtomicBoolean running = new AtomicBoolean(false);
    //private static final Logger logger = LogManager.getLogger(Main.class);


    public Main(String hostname, String port) throws SQLException {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
        try {
            con = DB.initBD();
            DB.createTables(con);
            DB.addUser(con, "Alex", "a@mail", "1234",new Date(2022, 1, 10));
            DB.addUser(con, "Aida", "ai@mail", "1234", new Date(2001, 2, 10));
            DB.addBook(con, "El nombre del Viento", "Path",  new Date(2006, 3, 15), true);
            DB.addBook(con, "El Camino de lo reyes", "Brandon Sanderson",  new Date(2006, 3, 15), true);
            DB.addBook(con, "El Imperio Final", "Brandon Sanderson",  new Date(2006, 3, 15), true);
            DB.addBook(con, "Asesinato en el Orient Express", "Agatha Christie",  new Date(2006, 3, 15), true);
            DB.addBook(con, "Viaje al centro de la tierra", "Julio Verne",  new Date(2006, 3, 15), true);
            DB.addBook(con, "Estudio en escarlata", "Arthur Conan Doyle",  new Date(2006, 3, 15), true);
            DB.addBook(con, "El ojo del mundo", "Robert Jordan",  new Date(2006, 3, 15), true);
            DB.addBook(con, "Dracula", "Bran Stroker",  new Date(2006, 3, 15), true);
        } catch (DBException e) {
            e.printStackTrace();
        }
        //Cosas de la interfaz de server
        //logger.info("Server ready");
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        running.set(true);
        while(running.get()) {
            try {
                Thread.sleep(2000);
                WebTarget donationsWebTarget = webTarget.path("users/user");
                Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    User answer = response.readEntity(User.class);
                    //logger.info("User received");
                } else {
                    //logger.info("////////");
                }

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                //logger.error("Thread was interrupted, Failed to complete operation");
            }
        }
    }

    public static void main(String[] args) {
        String hostname = args[0]; //Prestar atención a los properties, empieza en 1
        String port = args[1];

        try {
            new Main(hostname, port);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
