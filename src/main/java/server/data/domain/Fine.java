package server.data.domain;
/**
 * Fine class.
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */
public class Fine {
	
	private int id;
    private static int count = 0;
	private User user;
	private double quantity;
	
	public Fine() {
		super();
		//
		// TODO Auto-generated constructor stub
	}

	/**
	 * Fine constructor.
	 * @param user
	 * @param quantity
	 */
	public Fine(User user, double quantity) {
		super();
		this.id = count++;
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
