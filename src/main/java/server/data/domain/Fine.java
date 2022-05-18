package server.data.domain;

public class Fine {
	
	private User user;
	private double quantity;
	
	public Fine() {
		super();
		//
		// TODO Auto-generated constructor stub
	}
	
	public Fine(User user, double quantity) {
		super();
		this.user = user;
		this.quantity = quantity;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	
	@Override
	public String toString() {
		return "Fine = " + quantity + "€";
	}

}
