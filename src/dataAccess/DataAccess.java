package dataAccess;

import java.sql.*;
import model.*;
import java.lang.reflect.*; 

public class DataAccess {
	
	public void insert(Class cls, Connection con, String toAdd) {
		Field[] fields = cls.getDeclaredFields(); 
		String fieldNames = "(";
		
		for(int i = 0; i < fields.length; i++) {  
			String aux = fields[i].getName();
			fieldNames = fieldNames.concat(aux);
			if(i != fields.length - 1)
				fieldNames = fieldNames.concat(", ");
			else
				fieldNames = fieldNames.concat(")");
		}
		
		Statement stat;
		try {
			stat = con.createStatement();
			String sql = "Insert ptdatabase." + cls.getSimpleName() + fieldNames + " Values" + toAdd; 
			stat.executeLargeUpdate(sql);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
	}
	
	public void delete(Class cls, Connection con, int id){
		try {
			Field field = cls.getDeclaredField("id");
			Statement stat = con.createStatement();
			String sql = "Delete from ptdatabase." + cls.getSimpleName() + " where id =" + id;
			stat.executeLargeUpdate(sql);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void update(Object obj, Connection con) {
		Field[] fields = obj.getClass().getDeclaredFields();
		fields[0].setAccessible(true);
		String changedFields = "set ";
		for(int i = 0; i < fields.length - 1; i++) {
			fields[i + 1].setAccessible(true);
			Class<?> fldTip = fields[i + 1].getType(); 
			String fldName = fields[i + 1].getName();
			try {
				if(fldTip.getTypeName() == "java.lang.String")
				{
					String fldVal = (String) fields[i + 1].get(obj);
					changedFields = changedFields.concat(fldName);
					changedFields = changedFields.concat("=");
					changedFields = changedFields.concat("'" + fldVal + "'");
					if(i != fields.length-2)
					{
						changedFields = changedFields.concat(", ");
					}
				}
				else if(fldTip.getTypeName() == "int") {  
					int fldVal = (int) fields[i + 1].get(obj);
					changedFields = changedFields.concat(fldName);
					changedFields = changedFields.concat("=");
					changedFields = changedFields.concat("'" + Integer.toString(fldVal) + "'");
					if(i != fields.length - 2)
					{
						changedFields = changedFields.concat(", ");
					}
				}
			}catch(IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			Statement stat = con.createStatement();
			String sql = "update ptdatabase." + obj.getClass().getSimpleName() + " " + changedFields +" where id = " + fields[0].get(obj);
			stat.executeLargeUpdate(sql);
		} catch (SQLException | IllegalArgumentException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[] findByID(Class cls, Connection con, int id) {
		Field[] fields = cls.getDeclaredFields();
		String[] returnedObj = new String[10];
		Statement stat;
		try {
			stat = con.createStatement();
			String sql = "Select * from ptdatabase." + cls.getSimpleName() + " " + "where id = " + id ;
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				for(int i=0; i<fields.length; i++) {
					Class<?> fldType = fields[i].getType();
					String fldName = fields[i].getName();
					if(fldType.getTypeName() == "java.lang.String")
					{
						returnedObj[i] = rs.getString(fldName);
					}
					else if(fldType.getTypeName() == "int")
					{
						returnedObj[i] = Integer.toString(rs.getInt(fldName));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return returnedObj;
	}
	
	public String[][] selectAll(Class cls, Connection con) {
		Field[] fields = cls.getDeclaredFields();
		String[][] tableObjs = new String[30][30];
		String sql = "Select * from ptdatabase." + cls.getSimpleName();
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			int it = 0;
			while(rs.next()) {
				for(int i=0; i<fields.length; i++) {
					Class<?> fldType = fields[i].getType();  
					String fldName = fields[i].getName();
					if(fldType.getTypeName() == "java.lang.String")
					{
						tableObjs[it][i] = rs.getString(fldName);
					}
					else if(fldType.getTypeName() == "int")
					{
						tableObjs[it][i] = Integer.toString(rs.getInt(fldName));  
					}
				}
				it++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tableObjs;  
	}
	
	public int nbOfRows(Class cls, Connection con) {
		Statement stat;
		try {
			stat = con.createStatement();
			String sql = "select count(*) from ptdatabase." + cls.getSimpleName();
			ResultSet rs = stat.executeQuery(sql);
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}	

}
