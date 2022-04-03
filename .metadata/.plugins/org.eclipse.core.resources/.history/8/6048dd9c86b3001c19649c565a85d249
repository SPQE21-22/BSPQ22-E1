package es.deusto.ingenieria.sd.google.server;

import java.rmi.Naming;

import es.deusto.ingenieria.sd.google.server.remote.IGoogleFacade;
import es.deusto.ingenieria.sd.google.server.remote.GoogleFacade;

public class MainProgram { 
	public static void main(String[] args) {	
		//Activate Security Manager. It is needed for RMI.
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}

		//args[0] = RMIRegistry IP
		//args[1] = RMIRegistry Port
		//args[2] = Server Name
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];		
		//Bind remote facade instance to a service name using RMIRegistry
		try {
			IGoogleFacade remoteFacade = new GoogleFacade();			
			remoteFacade.register("oiermentxaka@opendeusto.es", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			remoteFacade.register("hola", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			remoteFacade.register("1234", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			Naming.rebind(name, remoteFacade);
			System.out.println(" * Google '" + name + "' started, congratulations!");
		} catch (Exception ex) {
			System.err.println(" # Google Server Exception: " + ex.getMessage());
		}
	}

}
