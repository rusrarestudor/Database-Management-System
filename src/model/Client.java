package model;

public class Client {
	private int id;
	private String name;
	private String city;
	
	public Client(int id, String name, String city) {
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public Client(String id, String name, String city) {
		this.id = Integer.parseInt(id);
		this.name = name;
		this.city = city;
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
	
	public String getCity() {
		return this.city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
}