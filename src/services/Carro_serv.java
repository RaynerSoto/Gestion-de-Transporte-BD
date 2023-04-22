package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Carro;
import utils.ConnectionManager;

public class Carro_serv {
	public static ArrayList<Carro> cargar_carro() throws ClassNotFoundException, SQLException{
		ArrayList<Carro> carros = new ArrayList<Carro>();
		String consulta = "Select * from carros order by id ASC";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			Carro carro = new Carro(rs.getLong("id_flotilla"), rs.getString("placa"), rs.getLong("id"), rs.getLong("id_marca"));
			carros.add(carro);
		}
		
		return carros;
	}
	// añadir
	 public static void añadir_carros(String placa,long id_marca, long id_flotilla) throws ClassNotFoundException, SQLException {
		 String consulta= "Select insertar_carro(?,?,?)";
		 PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		 prepararcons.setString(1, placa);
		 prepararcons.setLong(2, id_marca);
		 prepararcons.setLong(3, id_flotilla);
		 prepararcons.execute();
		 
	}
	    // Eliminar
	    public static void eliminar_carros(long id) throws ClassNotFoundException, SQLException {
		String consulta = "Select eliminar_carro(?)";
		PreparedStatement prepararcons= ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		prepararcons.setLong(1, id);
		prepararcons.execute();
	}
	    // Modificar
	    public static void modificar(String placa,long id_marca, long id_flotilla, long id ) throws ClassNotFoundException, SQLException {
	    	String consulta = "Select modificar_carro(?,?,?,?)";
	    	PreparedStatement  prepararcons =  ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
	    	prepararcons.setLong(1, id);
	    	prepararcons.setString(2, placa);
			 prepararcons.setLong(3, id_marca);
			 prepararcons.setLong(4, id_flotilla);
	    	prepararcons.execute();
	    }
	    public static long buscar_carro_ID(String placa) throws ClassNotFoundException, SQLException {
	    	long id =-2;
	    	String consulta = "Select id From carros where carros.placa = ? ";
	    	PreparedStatement prepararcons= ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
	    	prepararcons.setString(1,placa);
			ResultSet resultado = prepararcons.executeQuery();
			while(resultado.next()) {
				id = resultado.getLong(1);
			}
			return id;
	    }

}
