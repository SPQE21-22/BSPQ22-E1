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
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
        try {
            RemoteFacade remoteFacade = new RemoteFacade();
            IRemoteFacade stub = (IRemoteFacade) UnicastRemoteObject.exportObject(remoteFacade, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Prueba", stub);
            System.out.println(" - Library server '" + name + "' started");
        } catch (Exception e) {
            System.err.println(" - Library Server Exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
