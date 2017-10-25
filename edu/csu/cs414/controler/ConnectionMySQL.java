package edu.csu.cs414.controler;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionMySQL {
	ResultSet res = null;
	Statement statement = null;
	Connection connect = null;
	
	public void ConnectionMySQL(){
		try {
		      Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/chadgame","root","123456");
		      System.out.println("Success connect Mysql server!");
		      Statement stmt = connect.createStatement();
		      ResultSet rs = stmt.executeQuery("select * from Player");	
		      while (rs.next()) {
		          System.out.println(rs.getString("UserName"));
		      }
		}
		catch (Exception e) {
		      System.out.print("get data error!");
		      e.printStackTrace();
		}
	}
	public static void main(String[] args) throws ClassNotFoundException {  
		ConnectionMySQL conn = new ConnectionMySQL();  
		conn.ConnectionMySQL();
	}
	
}
