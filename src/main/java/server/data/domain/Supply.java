package server.data.domain;
/**
 * Supply class.
 * @author Alex Egaña, Eneko Eguiguren, Rubén García, Aida Gomezbueno & Tyler de Mier - BSPQ22-E1
 * @version 1.0
 * @since 2022-03-20
 */

public class Supply {

	private int id;
    private static int count = 0;
	private String name;
	private double price;
	private String type;
	
	public Supply() {
		super();
	}

	/**
	 * Supply constructor.
	 * @param name
	 * @param price
	 * @param type
	 */
	public Supply(String name, double price, String type) {
		super();
		this.id = count++;
		this.name = name;
		this.price = price;
		this.type = type;
	}
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	@Override
	public String toString() {
		return name;
	}
	
}
