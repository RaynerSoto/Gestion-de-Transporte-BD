package services;

import java.sql.SQLException;

import utils.Connection;



public class ServicesLocator {
	
	public static java.sql.Connection getConnection(){
		Connection connection = null;
		try {
			connection = new Connection("localhost", "transbus", "postgres", "0000");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection.getConnection();
	}
}
