package com.wf.f1.wfactura1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * DescripcionClase que define los atributos y metodos de DescripcionClase
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "DescripcionClase.findAll", query = "SELECT dc FROM DescripcionClase dc ORDER BY dc.identifica") })
@Table(name="DES_DESCRIPCIONCLASE")
public class DescripcionClase implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "DES_IDENTIFICA",nullable = false) 
	private String identifica;
	
	@ManyToOne
	@JoinColumn(name="DES_CLASE")
	private Clase clase;
	
	@Column(name = "DES_DESCRIPCION",length=250) 
	private String descripcion;

	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public DescripcionClase(){
		
	}

	//Metodos getters y setters
	public Clase getClase() {
		return clase;
	}

	public String getIdentifica() {
		return identifica;
	}

	public void setIdentifica(String identifica) {
		this.identifica = identifica;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo para mostrar los valores de los atributos de la DescripcionClase
	 */
	/*@Override
	public String toString() {
		return "DescripcionClase [identifica=" + identifica + ", clase=" + clase + ", descripcion=" + descripcion + "]";
	}*/
}
