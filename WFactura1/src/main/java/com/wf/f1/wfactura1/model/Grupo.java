package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Clase que define los atributos y metodos de un Grupo
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Grupo.findAll", query = "SELECT g FROM Grupo g ORDER BY g.identifica") })
@Table(name="GRU_GRUPO")
public class Grupo implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id    
	@Column(name = "GRU_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	
	@ManyToOne
	@JoinColumn(name="GRU_DIVISION",nullable = false)
	private Division division;
	
	@Column(name = "GRU_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="grupo")
	private Set<Clase> clases;
	
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Grupo(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la Grupo a la base de datos
	 * @param identifica
	 * @param division
	 * @param descripcion
	 */
	public Grupo(String identifica, Division division, String descripcion) {
		this.identifica = identifica;
		this.division = division;
		this.descripcion = descripcion;
	}

	//Metodos getters y setters
	public String getIdentifica() {
		return identifica;
	}

	public void setIdentifica(String identifica) {
		this.identifica = identifica;
	}

	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */
	/*@Override
	public String toString() {
		return "Grupo [identifica=" + identifica + ", division=" + division + ", descripcion=" + descripcion + "]";
	}*/
	
}
