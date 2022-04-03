package es.deusto.ingenieria.sd.facebook.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

import es.deusto.ingenieria.sd.facebook.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.facebook.server.data.domain.User;
import es.deusto.ingenieria.sd.facebook.server.services.FacebookService;

public class MainProgram {
	
	private static int numClients = 0;
	
	public static void main(String args[]) {
		if (args.length < 3) {
			System.err.println(" # Usage: TCPSocketSever [PORT]");
			System.exit(1);
		}
		
		//args[1] = Server socket port
		int serverPort = Integer.parseInt(args[1]);
		
		/**
		 * NOTE: try-with resources Statement - https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
		 * Try statement that declares one or more resources. A resource is an object that must be closed after the program is 
		 * finished with it. The try-with-resources statement ensures that each resource is closed at the end of the statement.
		 * Any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable, 
		 * can be used as a resource.
		 */
		
		//Declaration of the ServerSocket (only a port number is needed)
		try (ServerSocket tcpServerSocket = new ServerSocket(serverPort);) {
			System.out.println(" - TCPSocketServer: Waiting for connections '" + tcpServerSocket.getInetAddress().getHostAddress() + ":" + tcpServerSocket.getLocalPort() + "' ...");
			//The main thread is always waiting for connections
			while (true) {
				//When a connection from a client is received, a new EchoService is created. The "accept()" method returns the socket to
				new FacebookService(tcpServerSocket.accept());
				//communicate with the client.  Execution is stopped at the accept() method, waiting for a connection
				System.out.println(" - TCPSocketServer: New client connection accepted. Client Number: " + numClients++);
			}
		} catch (IOException e) {
			System.err.println("# TCPSocketServer: IO error:" + e.getMessage());
		}
	}
}