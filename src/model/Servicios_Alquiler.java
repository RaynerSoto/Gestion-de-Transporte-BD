package model;

import com.toedter.calendar.JCalendar;

public class Servicios_Alquiler extends Servicios{
	private long id;
	private String nombre;
	private JCalendar fecha_hora_fin;
	
	public Servicios_Alquiler(long identificador, long id_pais, double kms, long id_hoja_ruta, String numero_contrato,JCalendar fecha_hora_inicio, double importe, double combustible, long id, String nombre,JCalendar fecha_hora_fin) {
		super(identificador, id_pais, kms, id_hoja_ruta, numero_contrato, fecha_hora_inicio, importe, combustible);
		this.id = id;
		this.nombre = nombre;
		this.fecha_hora_fin = fecha_hora_fin;
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
	 * @return the fecha_hora_fin
	 */
	public JCalendar getFecha_hora_fin() {
		return fecha_hora_fin;
	}
	/**
	 * @param fecha_hora_fin the fecha_hora_fin to set
	 */
	public void setFecha_hora_fin(JCalendar fecha_hora_fin) {
		this.fecha_hora_fin = fecha_hora_fin;
	}
}
