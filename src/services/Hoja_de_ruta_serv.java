package services;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import com.toedter.calendar.JCalendar;

import model.Hoja_de_ruta;
import utils.ConnectionManager;

public class Hoja_de_ruta_serv {
	public static ArrayList<Hoja_de_ruta> cargar_hoja_ruta() throws ClassNotFoundException, SQLException{
		ArrayList<Hoja_de_ruta> hoja_ruta = new ArrayList<Hoja_de_ruta>();
		String consulta = "Select * from hojas_ruta order by id ASC";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			Hoja_de_ruta h = new Hoja_de_ruta(rs.getLong("id"),rs.getLong( "id_carro"), rs.getDate("fecha"), rs.getDouble("kms"),rs.getString("ci"));
			hoja_ruta.add(h);
		}
		return hoja_ruta;
	}
	
	public static void insertar(long id_carro,Date fecha,String ci) throws ClassNotFoundException, SQLException {
		String consulta = "INSERT INTO public.hojas_ruta(\r\n" + 
				"	 id_carro, fecha, ci)\r\n" + 
				"	VALUES (?, ?, ?);";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		prepararcons.setLong(1, id_carro);
		prepararcons.setDate(2,(Date) fecha);
		prepararcons.setString(3, ci);
		prepararcons.execute();
		
	}
	public static void eliminar(long id) throws ClassNotFoundException, SQLException {
		String consulta = "Select hojas_ruta_delete(?)";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		prepararcons.setLong(1, id);
		prepararcons.execute();
	}
	public static void modificar(long id,long id_carro,Date fecha, double kms,String ci) throws ClassNotFoundException, SQLException {
		String consulta = "Select hojas_ruta_update(?,?,?,?,?)";
		PreparedStatement prepararcons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet rs = prepararcons.executeQuery();
		while(rs.next()) {
			prepararcons.setLong(1, id);
			prepararcons.setLong(2, id_carro);
			prepararcons.setDate(3, fecha);
			prepararcons.setDouble(4, kms);
			prepararcons.setString(5, ci);
			prepararcons.execute();
		}
	}
	
	public static ArrayList<String> choferes(long marca) throws SQLException, ClassNotFoundException {
		ArrayList<String> List = new ArrayList<String>();
		String function = "{?= call choferes_de_un_carro_o_marca(?)}";
		java.sql.Connection connection = ServicesLocator.getConnection();
		connection.setAutoCommit(false);
	CallableStatement prepared=connection.prepareCall(function);
	connection.setAutoCommit(false);
		prepared.registerOutParameter(1, java.sql.Types.OTHER); /*Se especifica que el primer ? es de tipo varchar, es decir que el valor de retorno es un varchar */
		prepared.setLong(2, marca); /*Se establece que el segundo ? será la variable idObrero y que será el parámetro que se pase a la función creada en postgres*/
        prepared.execute();
		ResultSet resultSet = (ResultSet) prepared.getObject(1);
		while (resultSet.next()){
			List.add(resultSet.getString(1));
		}
		resultSet.close();
		prepared.close();

		
		return List;
	}
	
	
	

	
}
