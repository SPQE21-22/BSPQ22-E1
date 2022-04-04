package server;

import server.data.domain.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.security.auth.login.LoginException;
import javax.swing.JButton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class Main implements Runnable  {
    private Client client;
    private WebTarget webTarget;

    private Thread thread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public Main(String hostname, String port)  {
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
        //Cosas de la interfaz de server

        thread = new Thread(this);
        thread.start();
    }


    public void run() {
        running.set(true);
        while(running.get()) {
            try {
                Thread.sleep(2000);
                System.out.println("Obtaining data from server...");
                WebTarget donationsWebTarget = webTarget.path("users/r@mail");
                Invocation.Builder invocationBuilder = donationsWebTarget.request(MediaType.APPLICATION_JSON);
                Response response = invocationBuilder.get();
                if (response.getStatus() == Status.OK.getStatusCode()) {
                    User userInfo = response.readEntity(User.class);
                    System.out.println(userInfo.getName());
                } else {
                    throw new ServerException("" + response.getStatus());
                }
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        this.running.set(false);
    }

    public static void main(String[] args) {
        String hostname = args[0]; //Prestar atención a los properties, empieza en 1
        String port = args[1];

        new Main(hostname, port);
    }
}
