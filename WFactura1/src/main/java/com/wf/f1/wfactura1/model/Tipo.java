package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@NamedQuery(name = "Tipo.findAll", query = "SELECT t FROM Tipo t ORDER BY t.identifica") })
@Table(name="TIP_TIPO")
public class Tipo implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name = "TIP_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	@Column(name = "TIP_DESCRIPCION",nullable = false,length = 250) 
	private String descripcion;
	
	@OneToMany(mappedBy="tipo")
	private Set<Division> divisiones;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Tipo(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la Tipo a la base de datos
	 * @param identifica
	 * @param descripcion
	 */
	public Tipo(String identifica, String descripcion) {
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
	 * Metodo para mostrar los valores de los atributos de la Tipo
	 */
	/*@Override
	public String toString() {
		return "Tipo [identifica=" + identifica + ", descripcion=" + descripcion + "]";
	}*/
	
	
}
