package dataAccess;

import java.sql.*;
import model.*;

public class OrderDAO {
	
	private Connection con = ConnectionFactory.getConnection();
	private DataAccess dataAccess = new DataAccess();
	
	public void insertOrder(String toAdd) {
		dataAccess.insert(Order.class, con, toAdd);
	}
		
	public void updateOrder(Order o) {
		dataAccess.update(o, con);
	}
	
	public Order findByID(int id) {
		String[] rez = dataAccess.findByID(Order.class, con, id);
		Order o = new Order(rez[0], rez[1], rez[2], rez[3]);
		return o;
	}
	
	public String[][] selectAll(){
		return dataAccess.selectAll(Order.class, con);
	}
	public int nrRows() {
		return dataAccess.nbOfRows(Order.class, con);
	}
	
}

