package server;

import server.data.domain.User;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.apache.logging.log4j.*;

public class Main implements Runnable{
    private Client client;
    private WebTarget webTarget;
    private Thread thread;
    private final AtomicBoolean running = new AtomicBoolean(false);
    private static final Logger logger = LogManager.getLogger(Main.class);


    public Main(String hostname, String port)  throws ServerException{
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));

        WebTarget donationsWebTarget = webTarget.path("users/response");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == Status.OK.getStatusCode()) {
            String answer = response.readEntity(String.class);
            logger.info(answer);
        } else {
            logger.info("////////");
        }

        //Cosas de la interfaz de server
        logger.info("Server ready");
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
                    logger.info("User received");
                } else {
                    logger.info("////////");
                }

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                logger.error("Thread was interrupted, Failed to complete operation");
            }
        }
    }

    public static void main(String[] args) {
        String hostname = args[0]; //Prestar atención a los properties, empieza en 1
        String port = args[1];

        try {
            new Main(hostname, port);
        } catch (ServerException e) {
            e.printStackTrace();
        }

    }
}
