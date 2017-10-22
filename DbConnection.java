package chad;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	private static Connection theCon;
	public static Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.jdbc.Driver");
		String conString = "jdbc:mysql://localhost:3306/chad";  //dbsource
		if(theCon == null)
		{
			theCon = DriverManager.getConnection(conString);
			System.out.println("Database Connected");
		}
		return theCon;
	}
	public void closeConnection() throws SQLException
	{
		theCon.close();
	}

}
