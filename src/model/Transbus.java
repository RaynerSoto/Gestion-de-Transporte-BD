package model;


public class Transbus {
	private static Transbus trans;
	public static final String servidor="localhost";
	public static final String base_datos="transbus";
	public static final String usuario="postgres";
	public static final String contrasenna="0000";
	public static final String puerto="5432";
	
	public long usuario_rol;
	public String usuario_nombre;
	
	private Transbus() {}
	
	public static Transbus getTransbus(){
		if(trans == null)
			trans = new Transbus();
		return trans;
	}

}
