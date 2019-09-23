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
 * MetodoPago que define los atributos y metodos de MetodoPago
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "MetodoPago.findAll", query = "SELECT m FROM MetodoPago m ORDER BY m.identifica") })
@Table(name="MET_METODOPAGO")
public class MetodoPago implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "MET_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	
	@Column(name = "MET_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="MetodoPago")
	private Set<Factura> facturas;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public MetodoPago(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la MetodoPago a la base de datos
	 * @param identifica
	 * @param descripcion
	 */
	public MetodoPago(String identifica, Grupo grupo, String descripcion) {
		this.identifica = identifica;
		this.descripcion = descripcion;
	}

	//Metodos getters y setters
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

	/**
	 * Metodo para mostrar los valores de los atributos de la MetodoPago
	 */
	@Override
	public String toString() {
		return "MetodoPago [identifica=" + identifica + ", descripcion=" + descripcion + "]";
	}
	
}
