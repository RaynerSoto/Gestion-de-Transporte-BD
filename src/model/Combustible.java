package model;

public class Combustible {
	private long id;
	private String nombre;
	
	public Combustible(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

}
