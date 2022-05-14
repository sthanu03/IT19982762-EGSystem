package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {

	public Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			con= DriverManager.getConnection("jdbc:mysql://localhost:3306/egsystem", "root", "");
			
			//For testing
			System.out.print("Successfully connected");
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
	    return con;
	}
		
	public String insertUnit(String cus_id,String cus_name,String cus_phone,String new_read,String last_read,String used_unit)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for inserting.";
			 }
			 // create a prepared statement
			 String query = " insert into unitchargers(`id`,`cus_id`,`cus_name`,`cus_phone`,`new_read`,`last_read`,`used_unit`)" + " values (?, ?, ?, ?, ?, ?)";
					 PreparedStatement preparedStmt = con.prepareStatement(query);
					 // binding values
					 preparedStmt.setInt(1, 0);
					 preparedStmt.setString(2, cus_name);
					 preparedStmt.setString(3, cus_phone);
					 preparedStmt.setString(4, new_read);
					 preparedStmt.setString(5, last_read);
					 preparedStmt.setString(6,used_unit);
					
					 // execute the statement
					 preparedStmt.execute();
					 con.close();
					 String newUnits = readUnits();
					 output = "{\"status\":\"success\", \"data\": \"" +newUnits + "\"}";
					 }
					 catch (Exception e)
					 {
					 output = "{\"status\":\"error\", \"data\":\"Error while inserting the unit.\"}";
					 System.err.println(e.getMessage());
					 }
					 return output;
					 } 

			 		
	public String readUnits()
	{
		String output = "";
		try
		 {
			 Connection con = connect();
			 	if (con == null)
			 	{
			 		return "Error while connecting to the database for reading.";
			 	}
			 	
				 // Prepare the html table to be displayed
				 output = "<table border=\'1\'><tr><th>cus_id</th><th>cus_name</th>"
				 		+ "<th>cus_phone</th><th>new_read</th><th>last_read</th>"
				 		+ "<th>used_unit</th><th>Update</th><th>Remove</th></tr>";
			
				 
				 String query = "select * from unitchargers";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
				 // iterate through the rows in the result set
				 while (rs.next())
				 {
					 String id = Integer.toString(rs.getInt("id"));
					 String cus_id= rs.getString("cus_id");
					 String cus_name= rs.getString("cus_name");
					 String cus_phone = rs.getString("cus_phone");
					 String new_read = rs.getString("new_read");
					 String last_read = rs.getString("last_read");
					 String used_unit = rs.getString("used_unit");
					
					
					 
					 // Add a row into the html table
					 output += "<tr><td><input id=\'hidUnitIDUpdate\' name='hidUnitIDUpdate\' type='hidden\' value=\'" + id + "'>"
					 		+ cus_id + "</td>";
					 output += "<td>" + cus_name + "</td>";
					 output += "<td>" + cus_phone + "</td>";
					 output += "<td>" + new_read + "</td>";
					 output += "<td>" + last_read + "</td>";
					 output += "<td>" + used_unit + "</td>";
					 
					 
					// buttons
					 output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"
					 		+"<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-unitid='" + id + "'></td></tr>";

				  }
				 con.close();
				 // Complete the html table
				 output += "</table>";
		  }
		  catch (Exception e)
		  {
			 output = "Error while reading the units.";
			 System.err.println(e.getMessage());
		  }
		return output;
	}
	
	public String deleteUnit(String id)
	{
		 String output = "";
		 try
		 {
			 Connection con = connect();
			 if (con == null)
			 {
				 return "Error while connecting to the database for deleting.";
			 }
			 
			 // create a prepared statement
			 String query = "delete from unitchargers where id=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(id));
		
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 
			 
			 String new_read = readUnits();
			 output = "{\"status\":\"success\", \"data\": \"" +new_read + "\"}"; 

		 }
		 catch (Exception e)
		 {
		
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the unit.\"}"; 
			 System.err.println(e.getMessage());
		 }
		return output;
	}
	
	//method to update bill details in db
	public String updateUnits(String id,String cus_id,String cus_name, String cus_phone, String new_read, String last_read, String used_unit)
	{
		 String output = "";
			 try
			 {
				 Connection con = connect();
				 if (con == null)
				 {
					 return "Error while connecting to the database for updating."; 
				 }
				 
				 // create a prepared statement
				 String query = "UPDATE unit SET cus_id=?,cus_name=?,cus_phone=?,new_read=?,last_read=?,used_units=? where id=?";
				 PreparedStatement preparedStmt = con.prepareStatement(query);
				 
				 // binding values
				 preparedStmt.setString(1, cus_id);
				 preparedStmt.setString(2, cus_name);
				 preparedStmt.setString(3, cus_phone);
				 preparedStmt.setString(4, new_read);
				 preparedStmt.setString(5, last_read);
				 preparedStmt.setString(6, used_unit);
				 preparedStmt.setInt(8, Integer.parseInt(id)); 
				 
				 // execute the statement
				 preparedStmt.execute();
				 con.close();
				 
				 String newUnits = readUnits();
				 output = "{\"status\":\"success\", \"data\": \"" +newUnits + "\"}"; 

			}
			catch (Exception e)
			{
				 
				output = "{\"status\":\"error\", \"data\":\"Error while updating the unit.\"}"; 
				 System.err.println(e.getMessage());
			 }
			 return output;
		}
	
}
