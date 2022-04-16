package server;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class Main implements Runnable{
    private Client client;
    private WebTarget webTarget;
    private Thread thread;
    private final AtomicBoolean running = new AtomicBoolean(false);


    public Main(String hostname, String port)  throws ServerException{
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));

        WebTarget donationsWebTarget = webTarget.path("users/response");
        Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
        Response response = invocationBuilder.get();
        if (response.getStatus() == Status.OK.getStatusCode()) {
            String answer = response.readEntity(String.class);
            System.out.println(answer);
        } else {
            System.out.println("///////////");
        }

        //Cosas de la interfaz de server
        System.out.println("Server ready");
        thread = new Thread(this);
        thread.start();
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

    @Override
    public void run() {
        running.set(true);
        while(running.get()) {
            try {
                Thread.sleep(2000);
                System.out.println("Obtaining data from server...");

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }
    }
}
