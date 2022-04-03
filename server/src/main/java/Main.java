
import remote.IRemoteFacade;
import remote.RemoteFacade;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Main {
    public static void main(String[] args){
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
        System.setProperty("java.rmi.server.hostname","192.168.1.2");
        try {
            IRemoteFacade remoteFacade = new RemoteFacade();
            Registry registry = LocateRegistry.createRegistry(8080);
            registry.rebind("hi server", remoteFacade);
            System.out.println(" - Library server '" + name + "' started");
        } catch (Exception e) {
            System.err.println(" - Library Server Exception: " + e.getMessage());
            e.printStackTrace();
        }


    }
}
