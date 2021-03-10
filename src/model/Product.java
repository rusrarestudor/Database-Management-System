package model;

public class Product {
	
	private int id;
	private String name;
	private int quantity;
	private int price;

	public Product(int id, String name, int quantity, int price) {
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.price = price;
	}
	
	public Product(String id, String name, String quantity, String price) {
		this.id = Integer.parseInt(id);
		this.name = name;
		this.quantity = Integer.parseInt(quantity);
	}
	
	public int getID() {
		return this.id;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public int getPrice() {
		return this.price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}
	
}
