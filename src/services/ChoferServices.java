package services;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import utils.ConnectionManager;

public class ChoferServices {
	 public static void añadir_chofer_cubre_franco(String CI,String nombre,String direccion,String telefono,long id_distrito,long id_marca) throws ClassNotFoundException, SQLException {
		 String consulta= "Select chofer_insert(?,?,?,?,?,?)";
		 PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		 prepararcons.setString(1, CI);
		 prepararcons.setString(2, nombre);
		 prepararcons.setString(3, direccion);
		 prepararcons.setString(4, telefono);
		 prepararcons.setLong(5, id_distrito);
		 prepararcons.setLong(6, id_marca);
		 prepararcons.execute(); 
		 String consulta2 = "Select chofer_cubre_franco_insert(?)";
		 PreparedStatement prepararCons2 = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta2);
		 prepararCons2.setString(1,CI);
		 prepararCons2.execute();
	}
	 
	 public static void añadir_chofer_cubre_fijo(String CI,String nombre,String direccion,String telefono,long id_distrito,long id_marca, long id_carro) throws ClassNotFoundException, SQLException {
		 String consulta= "Select chofer_insert(?,?,?,?,?,?)";
		 PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		 prepararcons.setString(1, CI);
		 prepararcons.setString(2, nombre);
		 prepararcons.setString(3, direccion);
		 prepararcons.setString(4, telefono);
		 prepararcons.setLong(5, id_distrito);
		 prepararcons.setLong(6, id_marca);
		 prepararcons.execute(); 
	}
}
