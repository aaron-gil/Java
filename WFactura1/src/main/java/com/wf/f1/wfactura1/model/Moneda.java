package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Moneda que define los atributos y metodos de Moneda
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Moneda.findAll", query = "SELECT m FROM Moneda m ORDER BY m.identifica") })
@Table(name="MON_MONEDA")
public class Moneda implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "MON_IDENTIFICA",nullable = false,length=4) 
	private String identifica;
	
	@Column(name = "MON_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@Column(name = "MON_DECIMALES",nullable = false) 
	private Byte decimales;
	
	@Column(name = "MON_PORCENTAJEVARIACION",nullable = false,length=4) 
	private String porcentajeVariacion;
	
	@OneToMany(mappedBy="Moneda")
	private Set<Factura> facturas;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Moneda(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la Moneda a la base de datos
	 * @param identifica
	 * @param descripcion
	 * @param decimales
	 * @param porcentajeVariacion
	 */
	public Moneda(String identifica, String descripcion, Byte decimales, String porcentajeVariacion) {
		this.identifica = identifica;
		this.descripcion = descripcion;
		this.decimales = decimales;
		this.porcentajeVariacion = porcentajeVariacion;
	}



	//MONodos getters y setters
	public String getIdentifica() {
		return identifica;
	}

	public void setIdentifica(String identifica) {
		this.identifica = identifica;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Byte getDecimales() {
		return decimales;
	}

	public void setDecimales(Byte decimales) {
		this.decimales = decimales;
	}

	public String getPorcentajeVariacion() {
		return porcentajeVariacion;
	}

	public void setPorcentajeVariacion(String porcentajeVariacion) {
		this.porcentajeVariacion = porcentajeVariacion;
	}

	/**
	 * Moneda para mostrar los valores de los atributos de la Moneda
	 */
	@Override
	public String toString() {
		return "Moneda [identifica=" + identifica + ", descripcion=" + descripcion + ", decimales=" + decimales
				+ ", porcentajeVariacion=" + porcentajeVariacion + "]";
	}
}
