package server;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.swing.JButton;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


public class Main implements ActionListener, Runnable  {
    private Client client;
    private WebTarget webTarget;

    private Thread thread;
    private final AtomicBoolean running = new AtomicBoolean(false);

    public Main(String hostname, String port) {
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
                //Cosas que hace el server

                //DonationInfo donationInfo = getDonationInfo();
                //this.donation.setText(Integer.toString(donationInfo.getLast()));
                //this.total.setText(Integer.toString(donationInfo.getTotal()));

            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
        }
    }

    public void stop() {
        this.running.set(false);
    }

    public static void main(String[] args) {
        String hostname = args[0];
        String port = args[1];

        new Main(hostname, port);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Acciones de los botones
    }
}
