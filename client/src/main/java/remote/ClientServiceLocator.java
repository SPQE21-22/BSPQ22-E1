package remote;

import remote.IRemoteFacade;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientServiceLocator {
    private IRemoteFacade service;

    public void setService(String ip, String port, String hostname) {
        String name = "rmi://" + ip + ":" + port + "/" + hostname;
        try {
            IRemoteFacade server = (IRemoteFacade) Naming.lookup(name);
            System.out.println("eooo");
            this.service = server;
            System.out.println(this.service.getA());
        } catch (Exception ex) {
            System.err.println("# Error locating remote facade: " + ex);
        }
    }

    public IRemoteFacade getService() {
        return this.service;
    }
}