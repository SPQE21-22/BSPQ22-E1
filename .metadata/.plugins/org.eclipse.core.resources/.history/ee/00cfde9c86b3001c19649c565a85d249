package es.deusto.ingenieria.sd.google.server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

//This interface defines the API of the Server. It represents the Remote Facade pattern
public interface IGoogleFacade extends Remote {	
	//log in
	public boolean loginGoogle(String email, String password) throws RemoteException;
	public void register(String email, String password) throws RemoteException;
}