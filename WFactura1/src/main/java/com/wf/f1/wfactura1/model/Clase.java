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
 * Clase que define los atributos y metodos de Clase
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Clase.findAll", query = "SELECT c FROM Clase c ORDER BY c.identifica") })
@Table(name="CLA_CLASE")
public class Clase implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name = "CLA_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	
	@ManyToOne
	@JoinColumn(name="CLA_GRUPO",nullable = false)
	private Grupo grupo;
	
	@Column(name = "CLA_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="clase")
	private Set<DescripcionClase> descripcionClases;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Clase(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la Clase a la base de datos
	 * @param identifica
	 * @param grupo
	 * @param descripcion
	 */
	public Clase(String identifica, Grupo grupo, String descripcion) {
		this.identifica = identifica;
		this.grupo = grupo;
		this.descripcion = descripcion;
	}

	//Metodos getters y setters
	public String getIdentifica() {
		return identifica;
	}

	public void setIdentifica(String identifica) {
		this.identifica = identifica;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
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
		return "Clase [identifica=" + identifica + ", grupo=" + grupo + ", descripcion=" + descripcion + "]";
	}*/
	
}
