package es.deusto.ingenieria.sd.google.server.remote;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import es.deusto.ingenieria.sd.google.server.services.GoogleService;

public class GoogleFacade extends UnicastRemoteObject implements IGoogleFacade {	
	private static final long serialVersionUID = 1L;

	private GoogleService googleService = new GoogleService();

	public GoogleFacade() throws RemoteException {
		super();		
	}


	@Override
	public boolean loginGoogle(String mail, String password) throws RemoteException {
		System.out.println(" * RemoteFacade login : " + mail);

		if (googleService.login(mail, password)) {
			System.out.println(" * RemoteFacade login : " + mail + " -> true");
			return true;
		} else {
			System.out.println("You are not on googles server");
			return false;
		}
	}


	@Override
	public void register(String email, String password) throws RemoteException {
		googleService.register(email, password);
	}
}

