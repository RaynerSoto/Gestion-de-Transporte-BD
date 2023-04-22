package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.ConnectionManager;

public class FlotillaServices {
	public static void insertar_flotilla(String nombre) throws SQLException {
		String consulta = "SELECT insertar_flotillas(?)";
		PreparedStatement preparaCons = ServicesLocator.getConnection().prepareStatement(consulta);
		preparaCons.setString(1,nombre);
		preparaCons.execute();
	}
	
	public static ArrayList<String>listado_flotilla() throws SQLException{
		ArrayList<String>listado = new ArrayList<String>();
		String consulta = "Select nombre From flotillas";
		PreparedStatement prepareCons = ServicesLocator.getConnection().prepareStatement(consulta);
		ResultSet resultado = prepareCons.executeQuery();
		while(resultado.next()) {
			listado.add(resultado.getString(1));
		}
		return listado;
	}
	
	public static void eliminar_flotilla(long id) throws SQLException, ClassNotFoundException {
		String consulta = "SELECT eliminar_flotilla(?)";
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		preparaCons.setLong(1,id);
		preparaCons.execute();
	}
	
	public static long buscar_flotilla_ID(String nombre) throws ClassNotFoundException, SQLException {
		long id = -1;
		String consulta = "SELECT id From flotillas where flotillas.nombre = ?";
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		preparaCons.setString(1,nombre);
		ResultSet resultado = preparaCons.executeQuery();
		while(resultado.next()) {
			id = resultado.getLong(1);
		}
		return id;
	}

}
