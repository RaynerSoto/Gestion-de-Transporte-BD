package utils;

import java.sql.DriverManager;
import java.sql.SQLException;

import model.Transbus;

public  class ConnectionManager 
{
	private static java.sql.Connection connection;
	private static ConnectionManager cm;
	
	public ConnectionManager() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		String url = "jdbc:postgresql://" + Transbus.servidor+ ":"+Transbus.puerto+"/"+ Transbus.base_datos;
		connection = DriverManager.getConnection(url, Transbus.usuario, Transbus.contrasenna);
	}
	
	 public static ConnectionManager GetInstance() throws ClassNotFoundException, SQLException {
		    if(cm == null)
		    	cm = new ConnectionManager();
			return cm;
		}
	 
	 public java.sql.Connection GetConnection(){
		 return connection;
	 }


}
