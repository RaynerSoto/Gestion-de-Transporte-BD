package services;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Rol;
import model.Usuario;
import services.ServicesLocator;
import utils.ConnectionManager;
import utils.Encription;

public class UserServices {
	
	public static String getLoginUser(String usuario, char[] contraseña) {
		
		String rol ="";
		try{
			
			String sqlsentencia = "SELECT Roles.Nombre del rol" + "FROM Usuario" +
			"INNER JOIN ID_ROl ON Usuario.ID_ROl = Role.ID" +
			"WHERE Usuario.Nombre = ?" + "AND Usuario.contraseña = ?";
			
			PreparedStatement prepararCons = ServicesLocator.getConnection().prepareStatement(sqlsentencia);
			prepararCons.setString(1, usuario); 
			String passs = new String(contraseña);
			String pass = Encription.getMd5(passs);
			prepararCons.setString(2, pass);
			prepararCons.execute();
			ResultSet resultado = prepararCons.getResultSet();
			
			while (resultado.next()) {
				rol = resultado.getString(1);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	
		return rol;
	}
	
	public static void insertar_usuario(String usuario,String contrasenna,int valor) throws ClassNotFoundException, SQLException {
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement("select insertar_usuario(?,?,?)");
		preparaCons.setString(1,usuario);
		preparaCons.setString(2,Encription.getMd5(contrasenna));
		preparaCons.setInt(3,valor);
		preparaCons.execute();
	}
	
	public static void modificar_usuario(long iD,String usuario,String contrasenna,int valor) throws ClassNotFoundException, SQLException {
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement("select modificar_usuario(?,?,?,?)");
		preparaCons.setString(1,usuario);
		preparaCons.setString(2,Encription.getMd5(contrasenna));
		preparaCons.setInt(3,valor);
		preparaCons.setLong(4,iD);
		preparaCons.execute();
	}
	
	public static void eliminar(long id) throws ClassNotFoundException, SQLException {
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement("select eliminar_usuario(?)");
		preparaCons.setLong(1,id);
		preparaCons.execute();
	}
	
	public static ArrayList<Usuario>listado_usuarios() throws ClassNotFoundException, SQLException{
		ArrayList<Usuario>listado = new ArrayList<Usuario>();
		String consulta = "SELECT usuarios.nombre, roles.nombre From usuarios Inner Join roles on usuarios.id_rol = roles.id"; 
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		ResultSet resultado = preparaCons.executeQuery();
		while(resultado.next()) {
			Usuario user = new Usuario(resultado.getString(1),null,resultado.getString(2));
			listado.add(user);
		}
		return listado;
	}
	
	public static Usuario buscar_usuario(String nombre) throws ClassNotFoundException, SQLException {
		Usuario user = new Usuario("","","");
		String consulta = "SELECT usuarios.nombre, usuarios.contrasenna , Roles.nombre From usuarios Inner Join roles on usuarios.id_rol = roles.id where usuarios.nombre = ?";
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		preparaCons.setString(1,nombre);
		ResultSet resultado = preparaCons.executeQuery();
		while(resultado.next()) {
			user.setNombre(resultado.getString(1));
			user.setContrasenna(Encription.getMd5(resultado.getString(2)));
			user.setId_rol(resultado.getString(3));
		}
		return user;
	}
	public static int buscar_usuario_ID(String nombre) throws ClassNotFoundException, SQLException {
		int id = -1;
		String consulta = "SELECT usuarios.id From usuarios where usuarios.nombre = ?";
		PreparedStatement preparaCons = ConnectionManager.GetInstance().GetConnection().prepareStatement(consulta);
		preparaCons.setString(1,nombre);
		ResultSet resultado = preparaCons.executeQuery();
		while(resultado.next()) {
			id = resultado.getInt(1);
		}
		return id;
	}
}
