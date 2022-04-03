package es.deusto.ingenieria.sd.facebook.server.data.domain;

import javax.jdo.annotations.PersistenceCapable;

@PersistenceCapable(detachable="true")
public class User {	
	private String password;
	private String email;

	public User(String mail, String password) {
		this.email = mail;
		this.password = password;
	}

	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}

	public void setPassword(String password) {
		this.password = password;
	}	
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [password=" + password + ", email=" + email + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this.getClass().getName().equals(obj.getClass().getName())) {
			return this.email.equals(((User)obj).email);
		}

		return false;
	}
	
	
}