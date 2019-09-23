package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que define los atributos y metodos de un Serie
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Serie.findAll", query = "SELECT s FROM Serie s ORDER BY s.identifica") })
@Table(name="SER_SERIE")
public class Serie implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SER_IDENTIFICA",nullable = false) 
	private Integer identifica;
	@Column(name = "SER_NOMBRE",nullable=false,length=50) 
	private String nombre;
	@Column(name = "SER_FOLIOINICIAL") 
	private Integer folioInicial;
	@Column(name = "SER_FOLIOFINAL",nullable=false) 
	private Integer folioFinal;
	@Column(name = "SER_FOLIOACTUAL",nullable=false) 
	private Integer folioActual;
	@Column(name = "SER_RESPONSABLECREACION",nullable=false,length=20) 
	private String responsableCreacion;
	@Column(name = "SER_FECHACREACION") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	@ManyToOne
	@JoinColumn(name="SER_SUCURSAL",nullable = false)
	private Sucursal sucursal;
	
	@ManyToOne
	@JoinColumn(name="SER_TIPOCOMPROBANTE",nullable = false)
	private TipoComprobante tipoComprobante;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Serie(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la Serie a la base de datos
	 * @param nombre
	 * @param folioInicial
	 * @param folioFinal
	 * @param folioActual
	 * @param responsableCreacion
	 * @param fechaCreacion
	 * @param sucursal
	 */
	public Serie(String nombre, Integer folioInicial, Integer folioFinal, Integer folioActual,
			String responsableCreacion, Date fechaCreacion,Sucursal sucursal) {
		this.nombre = nombre;
		this.folioInicial = folioInicial;
		this.folioFinal = folioFinal;
		this.folioActual = folioActual;
		this.responsableCreacion = responsableCreacion;
		this.fechaCreacion = fechaCreacion;
		this.sucursal = sucursal;
	}



	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getFolioInicial() {
		return folioInicial;
	}

	public void setFolioInicial(Integer folioInicial) {
		this.folioInicial = folioInicial;
	}

	public Integer getFolioFinal() {
		return folioFinal;
	}

	public void setFolioFinal(Integer folioFinal) {
		this.folioFinal = folioFinal;
	}

	public Integer getFolioActual() {
		return folioActual;
	}

	public void setFolioActual(Integer folioActual) {
		this.folioActual = folioActual;
	}

	public String getResponsableCreacion() {
		return responsableCreacion;
	}

	public void setResponsableCreacion(String responsableCreacion) {
		this.responsableCreacion = responsableCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public TipoComprobante getTipoComprobante() {
		return tipoComprobante;
	}

	public void setTipoComprobante(TipoComprobante tipoComprobante) {
		this.tipoComprobante = tipoComprobante;
	}


	/**
	 * Metodo para mostrar los valoSER de los atributos de la clase
	 */
	
	/*@Override
	public String toString() {
		return "Serie [identifica=" + identifica + ", nombre=" + nombre + ", folioInicial=" + folioInicial
				+ ", folioFinal=" + folioFinal + ", folioActual=" + folioActual + ", responsableCreacion="
				+ responsableCreacion + ", fechaCreacion=" + fechaCreacion + ", sucursal=" + sucursal + "]";
	}*/
}
