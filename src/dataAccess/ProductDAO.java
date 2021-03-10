package dataAccess;

import java.sql.*;
import model.*;

public class ProductDAO {
	
	private Connection con = ConnectionFactory.getConnection();
	private DataAccess dataAccess = new DataAccess();
	
	public void insertProduct(String toAdd) {
		dataAccess.insert(Product.class, con, toAdd);
	}
	
	public void deleteProduct(String s) {		;
		Statement stat;
		try {
			stat = con.createStatement();
			String q = "Select * from javadbconnection.Product where (name= '" + s +"')";			
			ResultSet rs = stat.executeQuery(q);
			rs.next();
			int id = rs.getInt(1);

			dataAccess.delete(Client.class, con, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int getPrice(String s) {
		Statement stat;
		try {
			stat = con.createStatement();
			
			String q = "Select price from ptdatabase.Product where (name=" + s + ")";
			ResultSet rs = stat.executeQuery(q);
			rs.next();
			int price = rs.getInt(1);	
			return price;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
	public boolean updateProduct(String s) {
		int nr = 0;
		int poz1 = s.indexOf(",");		
		String s1 = s.substring(0, poz1);
		String s2 = s.substring(poz1 + 3, s.length()-1);
		int n2 = Integer.parseInt(s2);
		
		Statement stat;
		try {
			stat = con.createStatement();
			
			String q = "Select id,quantity,price from ptdatabase.Product where (name=" + s1 + ")";
			ResultSet rs = stat.executeQuery(q);
			rs.next();
			int id = rs.getInt(1);	
			int quantity = rs.getInt(2);
			int price = rs.getInt(3);	
			String s3 = s1.substring(1 , s1.length() - 1);
			Product p = new Product(0,s3,0,0);
			if(quantity - n2 <= 0)
				p = new Product(id,s3,quantity,price);	
			else
				p = new Product(id,s3,quantity - n2,price);
			dataAccess.update(p, con);
			nr = quantity - n2;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		if(nr < 0)
			return false;
		else 
			return true;

	}
	
	public Product findByID(int id) {
		String[] rez = dataAccess.findByID(Product.class, con, id);
		Product p = new Product(rez[0], rez[1], rez[2], rez[3]);
		return p;
	}
	
	public String[][] selectAll(){
		return dataAccess.selectAll(Product.class, con);  
	}
	
	public int nrRows() {
		return dataAccess.nbOfRows(Product.class, con);
	}
	
}
