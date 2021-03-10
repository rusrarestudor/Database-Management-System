package presentation;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.*;
import model.*;
import dataAccess.*;


public class Reports {
	
	public Reports() {}
	
	public void reportClients(int id) throws DocumentException {
		String fileName = "C:\\Users\\User12\\eclipse-workspace\\Assigment3Project\\ReportClients" + id + ".pdf";
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.open();
		Paragraph intro = new Paragraph("\n - Report of the Clients - \n");	
		doc.add(intro);
		PdfPTable table = new PdfPTable(2);
		String[][] clientList = new String[99][99];
		ClientDAO c = new ClientDAO();
		clientList = c.selectAll();
		PdfPCell c1 = new PdfPCell(new Paragraph("Name"));
		PdfPCell c2 = new PdfPCell(new Paragraph("City"));
		table.addCell(c1);
		table.addCell(c2);
		for(int i = 0; i < c.nrRows(); i++){
			 c1 = new PdfPCell(new Paragraph(clientList[i][1]));
			 c2 = new PdfPCell(new Paragraph(clientList[i][2]));
			 table.addCell(c1);
			 table.addCell(c2);
		}		
		try {
			doc.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.close();
	}
	
	public void reportProducts(int id) throws DocumentException {
		String fileName = "C:\\Users\\User12\\eclipse-workspace\\Assigment3Project\\ReportProducts" + id + ".pdf";
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.open();
		Paragraph intro = new Paragraph("\n - Report of the Products - \n");	
		doc.add(intro);
		PdfPTable table = new PdfPTable(3);
		String[][] productList = new String[99][99];
		ProductDAO c = new ProductDAO();
		productList = c.selectAll();
		PdfPCell c1 = new PdfPCell(new Paragraph("Name"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Stock"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Price/Unit"));
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		for(int i = 0; i < c.nrRows(); i++){
			 c1 = new PdfPCell(new Paragraph(productList[i][1]));
			 c2 = new PdfPCell(new Paragraph(productList[i][2]));
			 c3= new PdfPCell(new Paragraph(productList[i][3]));
			 table.addCell(c1);
			 table.addCell(c2);
			 table.addCell(c3);
		}		
		try {
			doc.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.close();
	
	}
	
	public void reportOrders(int id) throws DocumentException {
		String fileName = "C:\\Users\\User12\\eclipse-workspace\\Assigment3Project\\ReportOrders" + id + ".pdf";
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		doc.open();
		Paragraph intro = new Paragraph("\n - Report of the Orders - \n");	
		doc.add(intro);
		PdfPTable table = new PdfPTable(3);
		String[][] orderList = new String[99][99];
		OrderDAO c = new OrderDAO();
		orderList = c.selectAll();
		PdfPCell c1 = new PdfPCell(new Paragraph("Buyer"));
		PdfPCell c2 = new PdfPCell(new Paragraph("Item"));
		PdfPCell c3 = new PdfPCell(new Paragraph("Quantity"));
		table.addCell(c1);
		table.addCell(c2);
		table.addCell(c3);
		for(int i = 0; i < c.nrRows(); i++){
			 c1 = new PdfPCell(new Paragraph(orderList[i][1]));
			 c2 = new PdfPCell(new Paragraph(orderList[i][2]));
			 c3= new PdfPCell(new Paragraph(orderList[i][3]));
			 table.addCell(c1);
			 table.addCell(c2);
			 table.addCell(c3);
		}		
		try {
			doc.add(table);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		doc.close();
	
	}
	
	public void makeBill(String elem1, String elem2, String elem3, int cod) throws DocumentException {		
		String fileName = "C:\\Users\\User12\\eclipse-workspace\\Assigment3Project\\BillOf" + elem1 +"_" + cod + ".pdf";
		Document doc = new Document();
		try {
			PdfWriter.getInstance(doc, new FileOutputStream(fileName));		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}		
		doc.open();
		if (cod != -1) {
			Paragraph intro = new Paragraph("\n - Bill of " + elem1 +  " - \n");	
			doc.add(intro);
			PdfPTable table = new PdfPTable(3);
			ProductDAO c = new ProductDAO();
			PdfPCell c1 = new PdfPCell(new Paragraph("Client"));
			PdfPCell c2 = new PdfPCell(new Paragraph("Item"));
			PdfPCell c3 = new PdfPCell(new Paragraph("Pay"));
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);
			c1 = new PdfPCell(new Paragraph(elem1));
			c2 = new PdfPCell(new Paragraph(elem2));
			int price = c.getPrice("'" + elem2 + "'");
			c3= new PdfPCell(new Paragraph(Integer.parseInt(elem3) * price + "$"));
			table.addCell(c1);
			table.addCell(c2);
			table.addCell(c3);		
			try {
				doc.add(table);
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			Paragraph intro = new Paragraph("\n - Bill of " + elem1 +  " - was not processed due to amount of items in stock");	
			doc.add(intro);
		}
		doc.close();	
	}
}
