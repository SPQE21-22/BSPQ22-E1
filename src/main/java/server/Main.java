package server;

import server.data.domain.Book;
import server.data.domain.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    private Client client;
    private WebTarget webTarget;
    private List<User> usersList;
    private List<Book> booksList;

    private Connection con;

    public Main(String hostname, String port)  throws ServerException{
        client = ClientBuilder.newClient();
        webTarget = client.target(String.format("http://%s:%s/rest", hostname, port));
        //Cosas de la interfaz de server

        System.out.println("Obtaining data from server...");
        try {
            con = DB.initBD();
            DB.createTables(con);
            usersList = DB.getUsersList(con);
            booksList = DB.getBooksList(con);
        } catch (DBException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(usersList.get(0));

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
