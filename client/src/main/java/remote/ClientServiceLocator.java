package remote;

import remote.IRemoteFacade;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class ClientServiceLocator {
    private IRemoteFacade service;

    public void setService(String ip, String port, String hostname) throws  RemoteException{
        String name = "//" + ip + ":" + port + "/" + hostname;
        try {
            System.out.println("eooo");
            this.service = (IRemoteFacade) Naming.lookup(name);
            System.out.println(this.service.getA());
        } catch (Exception ex) {
            System.err.println("# Error locating remote facade: " + ex);
        }
    }

    public IRemoteFacade getService() {
        return this.service;
    }
}