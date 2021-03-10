package presentation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.itextpdf.text.DocumentException;

import businessLogic.*;
import dataAccess.DataAccess;
import model.Client;
import model.Product;

public class Instructions {

	public Instructions() {
		
	}
	
	public void readInstructions(String path) throws SQLException, FileNotFoundException, DocumentException {
		
		ControllerMethods cm = new ControllerMethods();
		Reports rep = new Reports();
		int idC = 0;
		int idP = 0;
		int idO = 0;
		
		try {		
			Scanner myReader = new Scanner(new File(path));
			while (myReader.hasNext()) {
				String linie = myReader.nextLine();
				String instr = linie.substring(0, linie.indexOf(" "));
				
				if(instr.contains("Insert")) {
					String item = linie.substring(linie.indexOf(" ") + 1,linie.indexOf(":"));
					if(item.contains("client")) {
							String elem1 = "'" + linie.substring(linie.indexOf(":") + 2, linie.indexOf(",")) + "'";
							String elem2 = "'" + linie.substring(linie.indexOf(",") + 2 , linie.length()) + "'";
							String c = elem1 + ", " + elem2 ;
							cm.insertClientF(c);
							System.out.println(c);
						}
						else if(item.contains("product")) {
							String elem1 = "'" + linie.substring(linie.indexOf(":") + 2, linie.indexOf(",")) + "'";
							String elem2 = "'" + linie.substring(linie.indexOf(",") + 2 , linie.lastIndexOf(",")) + "'";
							String elem3 = "'" + linie.substring(linie.lastIndexOf(",") + 2 , linie.length()) + "'";
							String c = elem1 + ", " + elem2 + ", " + elem3;
							cm.insertProductF(c);
							System.out.println(c);
						}	
				}
				
				else if(instr.contains("Delete")) {
					String item = linie.substring(linie.indexOf(" ") + 1,linie.indexOf(":"));
					if(item.contains("client")) {
							String elem1 = "'" + linie.substring(linie.indexOf(":") + 2, linie.indexOf(",")) + "'";
							String elem2 = "'" + linie.substring(linie.indexOf(",") + 2 , linie.length()) + "'";
							String c = elem1 + ", " + elem2 ;
							System.out.println(c);
							cm.deleteClientF(c);
						}
						else if(item.contains("product")) {
							String elem1 = "'" + linie.substring(linie.indexOf(":") + 2, linie.indexOf(",")) + "'";
							String elem2 = "'" + linie.substring(linie.indexOf(",") + 2 , linie.lastIndexOf(",")) + "'";
							String elem3 = "'" + linie.substring(linie.lastIndexOf(",") + 2 , linie.length()) + "'";
							String c = elem1 + ", " + elem2 + ", " + elem3;
							System.out.println(c);
							cm.deleteProductF(elem1);
						}
				}
				
				else if(instr.contains("Report")) {
					String item = linie.substring(linie.indexOf(" ") + 1, linie.length());
					if(item.contains("client")) {
							idC++;
							rep.reportClients(idC);
						}
						else if(item.contains("product")) {
							idP++;
							rep.reportProducts(idP);
						}
						else if(item.contains("order")) {
							idO++;
							rep.reportOrders(idO);
						}
				}
				
				else if(instr.contains("Create")) {
					String item = linie.substring(linie.indexOf(" ") + 1, linie.length());
					if(item.contains("order")) {
							String elem1 = "'" + linie.substring(linie.indexOf(":") + 2, linie.indexOf(",")) + "'";
							String elem2 = "'" + linie.substring(linie.indexOf(",") + 2 , linie.lastIndexOf(",")) + "'";
							String elem3 = "'" + linie.substring(linie.lastIndexOf(",") + 2 , linie.length()) + "'";
							String c = elem1 + ", " + elem2 + ", " + elem3;
							System.out.println(c);
							String c1 = elem1 + ", " + elem2 + ", " + elem3;
							cm.insertCart(c1);
							boolean ok = cm.makeOrderF(c);
							int cod = 0;
						    if (ok == true) {			
						    	cod++;
						    	rep.makeBill(linie.substring(linie.indexOf(":") + 2, linie.indexOf(",")), linie.substring(linie.indexOf(",") + 2 , linie.lastIndexOf(",")), linie.substring(linie.lastIndexOf(",") + 2 , linie.length()), cod);
						    }
						    else
						    	rep.makeBill(elem1, elem2, elem3, -1);
					}
				}
				else{
					System.out.println("ERROR!!!");
				}
			}	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	
}
