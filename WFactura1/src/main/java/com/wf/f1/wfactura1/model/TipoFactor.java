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
 * Tipo que define los atributos y metodos de Tipo
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "TipoFactor.findAll", query = "SELECT t FROM TipoFactor t ORDER BY t.identifica") })
@Table(name="TIF_TIPOFACTOR")
public class TipoFactor implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "TIF_IDENTIFICA",nullable = false,length=2) 
	private String identifica;
	@Column(name = "TIF_DESCRIPCION",nullable = false,length = 10) 
	private String descripcion;
	
	@OneToMany(mappedBy="tipoFactor")
	private Set<TasaImpuesto> tasaImpuestos;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public TipoFactor(){
		
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
	 * Metodo para mostrar los valores de los atributos de la Tipo
	 */
	
	
	
}
