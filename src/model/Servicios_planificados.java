package model;

import com.toedter.calendar.JCalendar;

public class Servicios_planificados extends Servicios{
	private long id;
	private String co_servicio;
	private String nombre;
	private String co_grupo;
	private String lugar_recogida;
	private JCalendar fecha_hora;
	
	
	public Servicios_planificados(long identificador, long id_pais, double kms, long id_hoja_ruta,String numero_contrato, JCalendar fecha_hora_inicio, double importe, double combustible, long id,String co_servicio, String nombre, String co_grupo, String lugar_recogida, JCalendar fecha_hora) {
		super(identificador, id_pais, kms, id_hoja_ruta, numero_contrato, fecha_hora_inicio, importe, combustible);
		this.id = id;
		this.co_servicio = co_servicio;
		this.nombre = nombre;
		this.co_grupo = co_grupo;
		this.lugar_recogida = lugar_recogida;
		this.fecha_hora = fecha_hora;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the co_servicio
	 */
	public String getCo_servicio() {
		return co_servicio;
	}
	/**
	 * @param co_servicio the co_servicio to set
	 */
	public void setCo_servicio(String co_servicio) {
		this.co_servicio = co_servicio;
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
	 * @return the co_grupo
	 */
	public String getCo_grupo() {
		return co_grupo;
	}
	/**
	 * @param co_grupo the co_grupo to set
	 */
	public void setCo_grupo(String co_grupo) {
		this.co_grupo = co_grupo;
	}
	/**
	 * @return the lugar_recogida
	 */
	public String getLugar_recogida() {
		return lugar_recogida;
	}
	/**
	 * @param lugar_recogida the lugar_recogida to set
	 */
	public void setLugar_recogida(String lugar_recogida) {
		this.lugar_recogida = lugar_recogida;
	}
	/**
	 * @return the fecha_hora
	 */
	public JCalendar getFecha_hora() {
		return fecha_hora;
	}
	/**
	 * @param fecha_hora the fecha_hora to set
	 */
	public void setFecha_hora(JCalendar fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	
	
}
