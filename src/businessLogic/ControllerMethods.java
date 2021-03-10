package businessLogic;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dataAccess.*;
import model.Client;
import model.Product;


public class ControllerMethods {
	
	private ClientDAO cd = new ClientDAO();
	private ProductDAO pd = new ProductDAO();
	private OrderDAO od = new OrderDAO();
	private CartDAO ca = new CartDAO();

	public void insertClientF(String c) {
		int nrRows = cd.nrRows() + 1;
		String toAdd = ("(" + nrRows + ", " + c + ")");
		cd.insertClient(toAdd);
	}
		
	public void deleteClientF(String c) {
		cd.deleteClient(c);
	}
		
	public void insertProductF(String c) {
		int nrRows = pd.nrRows() + 1;
		String toAdd = ("(" + nrRows + ", " + c + ")");
		pd.insertProduct(toAdd);
	}
	
	public void deleteProductF(String product) {
		pd.deleteProduct(product);
	}
	
	public void insertCart(String c) {
		int cartID = ca.nrRows() + 1;
		String toAdd = "(" + cartID + ", " + c + ")";
		ca.insertCart(toAdd);
	}
		
	public boolean makeOrderF( String c) {
		int orderID = od.nrRows() + 1;
		String toAdd = "(" + orderID + ", " + c + ")";
		od.insertOrder(toAdd);
		int l = c.lastIndexOf(",");
		String orderedQuantity = c.substring(l + 2, c.length());	
		String aux = c.substring(0, l);
		int l2 = aux.lastIndexOf(",");
		String productName = c.substring(l2 + 2, aux.length());			
		String x = (new StringBuilder()).append(productName).append(", ").append(orderedQuantity).toString();		
		if( pd.updateProduct(x) == true)
			return true;
		else 
			return false;
	}
	
	
	
}
