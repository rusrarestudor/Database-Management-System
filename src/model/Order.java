package model;

public class Order {
	private int id;
	private String client;
	private String product;
	private int size;
	
	public Order(int id, String client, String product, int size) {
		this.id = id;
		this.client = client;
		this.product = product;
		this.size = size;
	}
	
	public Order(String id, String client, String product, String size) {
		this.id = Integer.parseInt(id);
		this.client = client;
		this.product = product;
		this.size = Integer.parseInt(size);
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getProductID() {
		return product;
	}

	public void setProductID(String product) {
		this.product = product;
	}
}
