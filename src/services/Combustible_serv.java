package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import de.java2html.Java2Html;
import model.Combustible;
import utils.ConnectionManager;

public class Combustible_serv {
	
	public static ArrayList<Combustible> Cargar_combustible() throws ClassNotFoundException, SQLException {
		ArrayList<Combustible> combustible=new ArrayList<Combustible>();
		String consulta = "Select * from combustibles order by id ASC";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			Combustible c = new Combustible(rs.getInt("id"), rs.getString("nombre"));
			combustible.add(c);
		}
		return combustible;	
	}
	 // Añadir
    public static void añadir_combustible(String nombre) throws ClassNotFoundException, SQLException {
	 String consulta= "Select insertar_combustible(?)";
	 PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
	 prepararcons.setString(1, nombre);
	 prepararcons.execute();
	 
}
    // Eliminar
    public static void eliminar_combustible(long id) throws ClassNotFoundException, SQLException {
	String consulta = "Select eliminar_combustible(?)";
	PreparedStatement prepararcons= ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
	prepararcons.setLong(1, id);
	prepararcons.execute();
}
    // Modificar
    public static void modificar(String nombre, long id ) throws ClassNotFoundException, SQLException {
    	String consulta = "Select modificar_combustible(?,?)";
    	PreparedStatement prepararcons = ServicesLocator.getConnection().prepareStatement(consulta);
    	prepararcons.setLong(1,id);
    	prepararcons.setString(2, nombre);
    	prepararcons.execute();
    	
    }
    
    public static int cantidad_combustibles() throws ClassNotFoundException, SQLException {
    	int valor = 0;
    	String consulta = "select cantidad_combustibles()";
    	PreparedStatement preparcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
    	ResultSet result = preparcons.executeQuery();
    	while(result.next()) {
    		valor = result.getInt(1);
    	}
    	return valor;
    }
    public static long id_buscar_nombre(String nombre) throws ClassNotFoundException, SQLException {
    	long id = -1;
    	String consulta = "Select id From combustibles Where combustibles.nombre = ?";
    	PreparedStatement prepararCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
    	prepararCons.setString(1,nombre);
    	ResultSet resultado = prepararCons.executeQuery();
    	while(resultado.next()){
    		id = resultado.getLong(1);
    	}
    	return id;
    }
	
}
