import data.domain.Book;
import remote.IRemoteFacade;
import remote.RemoteFacade;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public class Main {
    public static void main(String[] args){
        String name = "rmi://" + args[0] + ":" + args[1] + "/" + args[2];
        try {
            RemoteFacade remoteFacade = new RemoteFacade();
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind(name, remoteFacade);
            System.out.println(" - Library server '" + name + "' started");
        } catch (Exception e) {
            System.err.println(" - Library Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
