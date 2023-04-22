package model;

import com.toedter.calendar.JCalendar;

public class Servicios {
	private long identificador;
	private long id_pais;
	private double kms;
	private long id_hoja_ruta;
	private String numero_contrato;
	private JCalendar fecha_hora_inicio;
	private double importe;
	private double combustible;
	
	
	public Servicios(long identificador, long id_pais, double kms, long id_hoja_ruta, String numero_contrato,JCalendar fecha_hora_inicio, double importe, double combustible) {
		this.identificador = identificador;
		this.id_pais = id_pais;
		this.kms = kms;
		this.id_hoja_ruta = id_hoja_ruta;
		this.numero_contrato = numero_contrato;
		this.fecha_hora_inicio = fecha_hora_inicio;
		this.importe = importe;
		this.combustible = combustible;
	}
	/**
	 * @return the id
	 */
	public long getId() {
		return identificador;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.identificador = id;
	}
	/**
	 * @return the id_pais
	 */
	public long getId_pais() {
		return id_pais;
	}
	/**
	 * @param id_pais the id_pais to set
	 */
	public void setId_pais(long id_pais) {
		this.id_pais = id_pais;
	}
	/**
	 * @return the kms
	 */
	public double getKms() {
		return kms;
	}
	/**
	 * @param kms the kms to set
	 */
	public void setKms(double kms) {
		this.kms = kms;
	}
	/**
	 * @return the id_hoja_ruta
	 */
	public long getId_hoja_ruta() {
		return id_hoja_ruta;
	}
	/**
	 * @param id_hoja_ruta the id_hoja_ruta to set
	 */
	public void setId_hoja_ruta(long id_hoja_ruta) {
		this.id_hoja_ruta = id_hoja_ruta;
	}
	/**
	 * @return the numero_contrato
	 */
	public String getNumero_contrato() {
		return numero_contrato;
	}
	/**
	 * @param numero_contrato the numero_contrato to set
	 */
	public void setNumero_contrato(String numero_contrato) {
		this.numero_contrato = numero_contrato;
	}
	/**
	 * @return the fecha_hora_inicio
	 */
	public JCalendar getFecha_hora_inicio() {
		return fecha_hora_inicio;
	}
	/**
	 * @param fecha_hora_inicio the fecha_hora_inicio to set
	 */
	public void setFecha_hora_inicio(JCalendar fecha_hora_inicio) {
		this.fecha_hora_inicio = fecha_hora_inicio;
	}
	/**
	 * @return the importe
	 */
	public double getImporte() {
		return importe;
	}
	/**
	 * @param importe the importe to set
	 */
	public void setImporte(double importe) {
		this.importe = importe;
	}
	/**
	 * @return the combustible
	 */
	public double getCombustible() {
		return combustible;
	}
	/**
	 * @param combustible the combustible to set
	 */
	public void setCombustible(double combustible) {
		this.combustible = combustible;
	}
	
	
}
