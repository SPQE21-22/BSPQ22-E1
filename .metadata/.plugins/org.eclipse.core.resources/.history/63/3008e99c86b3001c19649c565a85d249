package es.deusto.ingenieria.sd.facebook.server.services;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.List;

import es.deusto.ingenieria.sd.facebook.server.data.dao.UserDAO;
import es.deusto.ingenieria.sd.facebook.server.data.domain.User;

/**
 * This class process the request of each client as a separated Thread.
 */
public class FacebookService extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket tcpSocket;

	public FacebookService(Socket socket) {
		try {
			this.tcpSocket = socket;
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			this.register("oiermentxaka@opendeusto.es", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			this.register("mail3@mail.com", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			this.register("hola", "7110eda4d09e062aa5e4a390b0a572ac0d2c0220");
			this.start();
		} catch (IOException e) {
			System.err.println("# EchoService - TCPConnection IO error:" + e.getMessage());
		}
	}
	
	public boolean login(String mail, String password) {

		User user = UserDAO.getInstance().find(mail);
		if(user!=null) {
			if(user.checkPassword(password)) {
				return true;
			}
		}if(user==null) {
			System.out.println("user==null");
			return false;		
		}
		return false;		
	}
	public void register(String mail, String password) {
		if(UserDAO.getInstance().find(mail)==null) {
			UserDAO.getInstance().save(new User(mail,password));
		}
	}
	
	public void run() {
		//Echo server
		try {
			//Read request from the client
			String data = this.in.readUTF();			
			System.out.println("   - EchoService - Received data from '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> '" + data + "'");		
			String[] parts = data.split("#");
			String mail = parts[0];
			String password = parts[1];
			boolean answer = login(mail,password);
			this.out.writeBoolean(answer);			
			//Send response to the client
			System.out.println("   - EchoService - Sent data to '" + tcpSocket.getInetAddress().getHostAddress() + ":" + tcpSocket.getPort() + "' -> " + answer);
		} catch (EOFException e) {
			System.err.println("   # EchoService - TCPConnection EOF error" + e.getMessage());
		} catch (IOException e) {
			System.err.println("   # EchoService - TCPConnection IO error:" + e.getMessage());
		} finally {
			try {
				tcpSocket.close();
			} catch (IOException e) {
				System.err.println("   # EchoService - TCPConnection IO error:" + e.getMessage());
			}
		}
	}
}