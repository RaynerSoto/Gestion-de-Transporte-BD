package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import utils.ConnectionManager;

public class DistritoSerices {
	
	public static int buscar_distrito_id(String nombre) throws ClassNotFoundException, SQLException {
		int id = -1;
		String consulta = "SELECT id From provincias where provincias.nombre = ?";
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		preparaCons.setString(1,nombre);
		ResultSet resultado = preparaCons.executeQuery();
		while(resultado.next()) {
			id = resultado.getInt(1);
		}
		return id;
	}
}
