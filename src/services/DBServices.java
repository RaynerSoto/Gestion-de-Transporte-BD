package services;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.ConnectionManager;
import utils.Encription;

public class DBServices {
	
	public static boolean Login(String usuario, String contrasenna) throws ClassNotFoundException, SQLException
	{	
			PreparedStatement prepararCons = ConnectionManager.GetInstance().GetConnection().prepareStatement("select login(?,?)");
			prepararCons.setString(1,usuario); 
			prepararCons.setString(2, Encription.getMd5(contrasenna));
			prepararCons.execute();
			ResultSet resultado = prepararCons.getResultSet();
			while (resultado.next()) {
				return resultado.getBoolean(1);
			}
		return false;
	}
	
	public static long Rol(String usuario) throws Exception
	{	
			PreparedStatement prepararCons = ConnectionManager.GetInstance().GetConnection().prepareStatement("select r.id from roles r inner join usuarios u on u.id_rol = r.id where u.nombre = ?");
			prepararCons.setString(1,usuario); 
			prepararCons.execute();
			ResultSet resultado = prepararCons.getResultSet();
			while (resultado.next()) {
				return resultado.getLong(1);
			}
		throw new Exception("Error fatal");
	}
}
