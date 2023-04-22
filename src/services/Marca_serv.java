package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Marca;
import utils.ConnectionManager;

public class Marca_serv {
	
	public static ArrayList<Marca> cargar_marcar() throws ClassNotFoundException, SQLException{
		ArrayList<Marca>marcas = new ArrayList<Marca>();
		String consulta = "Select * from marcas order by id ASC";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			Marca m = new Marca(rs.getLong("id"), rs.getString("nombre"),rs.getInt("cantidad_asientos"),
					rs.getLong("id_combustible"), rs.getDouble("consumo"));
			marcas.add(m);
		
		}
		return marcas;
	}
	 // Añadir
    public static void añadir_marcas(String nombre,int cant_asientos,long id_combustible, double consumo) throws ClassNotFoundException, SQLException {
	 String consulta= "Select insertar_marca(?,?,?,?)";
	 PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
	 prepararcons.setString(1, nombre);
	 prepararcons.setInt(2, cant_asientos);
	 prepararcons.setLong(3, id_combustible);
	 prepararcons.setDouble(4, consumo);
	 prepararcons.execute();
	 
}
    // Eliminar
    public static void eliminar_marcas(long id) throws ClassNotFoundException, SQLException {
	String consulta = "Select eliminar_marcas(?)";
	PreparedStatement prepararcons= ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
	prepararcons.setLong(1, id);
	prepararcons.execute();
}
    // Modificar
    public static void modificar(String nombre, long id,long id_combustible,double consumo,int cant_asientos ) throws ClassNotFoundException, SQLException {
    	String consulta = "Select modificar_marca(?,?,?,?,?)";
    	PreparedStatement  prepararcons =  ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
    	prepararcons.setLong(1, id);
    	 prepararcons.setString(2, nombre);
    	prepararcons.setInt(3, cant_asientos);
   	 	prepararcons.setLong(4, id_combustible);
   	 	prepararcons.setDouble(5, consumo);
    	prepararcons.execute();
    }
    //Cargar Nombre de marcas
	public static ArrayList<String> cargar_marcar_nombre() throws ClassNotFoundException, SQLException{
		ArrayList<String>marcas = new ArrayList<String>();
		String consulta = "Select nombre from marcas";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			marcas.add(rs.getString(1));
		}
		return marcas;
	}
	
	public static long buscar_marca_ID(String nombre) throws ClassNotFoundException, SQLException {
		long id = -1;
		String consulta = "SELECT marcas.id From marcas where marcas.nombre = ?";
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		preparaCons.setString(1,nombre);
		ResultSet resultado = preparaCons.executeQuery();
		while(resultado.next()) {
			id = resultado.getLong(1);
		}
		return id;
	}
}