package dataAccess;

import java.sql.*;
import model.*;

public class ClientDAO {
	
	private Connection con = ConnectionFactory.getConnection();
	private DataAccess dataAccess = new DataAccess();
	
	public void insertClient(String toAdd) {
		dataAccess.insert(Client.class, con, toAdd);
	}
	
	public void deleteClient(String s) {		
		int poz = s.indexOf(",");
		
		String s1 = s.substring(0, poz);
		String s2 = s.substring(poz + 2, s.length());
		Statement stat;
		try {
			stat = con.createStatement();
			String q = "Select id from ptdatabase.Client where (name='" + s1 + "')"+ 
					   " and (city='" + s2 +"')";	
			ResultSet rs = stat.executeQuery(q);
			rs.next();
			int id = rs.getInt(1);			
			dataAccess.delete(Client.class, con, id);
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateClient(Client c) {
		dataAccess.update(c, con);
	}
	
	public Client findByID(int id) {
		String[] rez = dataAccess.findByID(Client.class, con, id);
		Client c = new Client(rez[0], rez[1], rez[2]);
		return c;
	}
	
	
	public String[][] selectAll(){
		return dataAccess.selectAll(Client.class, con);
	}
	
	public int nrRows() {
		return dataAccess.nbOfRows(Client.class, con);
	}
		
}

