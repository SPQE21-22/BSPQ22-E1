package client;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import util.DonationException;

public class Host{

    public static void main(String[] args){
        String hostname = args[0];
        String port = args[1];
        System.out.println("Hola soy el cliente");
    }
}
