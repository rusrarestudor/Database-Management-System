package dataAccess;

import java.sql.Connection;

import model.Cart;

public class CartDAO {

	private Connection con = ConnectionFactory.getConnection();
	private DataAccess dataAccess = new DataAccess();
	
	public void insertCart(String toAdd) {
		dataAccess.insert(Cart.class, con, toAdd);
	}
		
	public void updateCart(Cart o) {
		dataAccess.update(o, con);
	}
	
	public Cart findByID(int id) {
		String[] rez = dataAccess.findByID(Cart.class, con, id);
		Cart o = new Cart(rez[0], rez[1], rez[2], rez[3]);
		return o;
	}
	
	public String[][] selectAll(){
		return dataAccess.selectAll(Cart.class, con);
	}
	public int nrRows() {
		return dataAccess.nbOfRows(Cart.class, con);
	}
	
}
