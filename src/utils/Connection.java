package utils;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Connection {
	private static java.sql.Connection connection;
	
	public Connection(String serveradress,String datebase,String user,String pass) throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + serveradress+ ":5432/"+ datebase;
		connection = DriverManager.getConnection(url, user, pass);
	}
	
	 public java.sql.Connection getConnection() {
			return connection;
		}


}
