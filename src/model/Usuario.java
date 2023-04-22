package model;

public class Usuario {
	private String nombre;
	private String contrasenna;
	private String rol;

	public Usuario(String nombre, String contrasenna, String id_rol) {
		super();
		this.nombre = nombre;
		this.contrasenna = contrasenna;
		this.rol = id_rol;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the contrasenna
	 */
	public String getContrasenna() {
		return contrasenna;
	}
	/**
	 * @param contrasenna the contrasenna to set
	 */
	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}
	/**
	 * @return the id_rol
	 */
	public String getId_rol() {
		return rol;
	}
	/**
	 * @param id_rol the id_rol to set
	 */
	public void setId_rol(String id_rol) {
		this.rol = id_rol;
	}
	
}
