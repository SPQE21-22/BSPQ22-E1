package remote;

import remote.IRemoteFacade;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientServiceLocator {
    private IRemoteFacade service;

    public void setService(String ip, String port, String name) {
        try {
            Registry registry = LocateRegistry.getRegistry(null);
            IRemoteFacade stub = (IRemoteFacade) registry.lookup("Prueba");
            System.out.println(stub.getA());
        } catch (Exception ex) {
            System.err.println("# Error locating remote facade: " + ex);
        }
    }

    public IRemoteFacade getService() {
        return this.service;
    }
}