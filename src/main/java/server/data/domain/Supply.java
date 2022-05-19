package server.data.domain;

import java.util.Date;

public class Supply {

	private int id;
    private static int count = 0;
	private String name;
	private double price;
	private Date arrivingDate;
	
	public Supply() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Supply(String name, double price, Date arrivingDate) {
		super();
		this.id = count++;
		this.name = name;
		this.price = price;
		this.arrivingDate = arrivingDate;
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
	
	public Date getArrivingDate() {
		return arrivingDate;
	}
	
	public void setArrivingDate(Date arrivingDate) {
		this.arrivingDate = arrivingDate;
	}
	
	
	@Override
	public String toString() {
		return "Supply [name=" + name + ", price=" + price + ", arrivingDate=" + arrivingDate + "]";
	}
	
}
