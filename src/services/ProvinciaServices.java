package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import utils.ConnectionManager;

public class ProvinciaServices {
	public static ArrayList<String>cargar_provincias() throws ClassNotFoundException, SQLException{
		ArrayList<String>provincias = new ArrayList<String>();
		String consulta = "Select nombre From provincias";
		PreparedStatement prepaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet resultado = prepaCons.executeQuery();
		while(resultado.next()) {
			provincias.add(resultado.getString(1));
		}
		return provincias;
	}
}
