package model;

public class Cart {
	private int id;
	private String client;
	private String product;
	private int total;
	
	public Cart(int id, String client, String product, int total) {
		this.id = id;
		this.client = client;
		this.product = product;
		this.total = total;
	}
	
	public Cart(String id, String client, String product, String total) {
		this.id = Integer.parseInt(id);
		this.client = client;
		this.product = product;
		this.total = Integer.parseInt(total);
	}
	
	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id = id;
	}
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
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