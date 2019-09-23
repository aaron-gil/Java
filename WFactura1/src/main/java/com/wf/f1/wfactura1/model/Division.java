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
 * Clase que define los atributos y metodos de un Division
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Division.findAll", query = "SELECT d FROM Division d ORDER BY d.identifica") })
@Table(name="DIV_DIVISION")
public class Division implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@Column(name = "DIV_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	
	@ManyToOne
	@JoinColumn(name="DIV_TIPO",nullable = false)
	private Tipo tipo;
	
	@Column(name = "DIV_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="division")
	private Set<Grupo> grupos;
	
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Division(){
		
	}
	
	/**
	 * Constructor utilizado para la insercion de los datos de la Division a la base de datos
	 * @param id
	 * @param tipo
	 * @param descripcion
	 */
	public Division(String identifica, Tipo tipo, String descripcion) {
		this.identifica = identifica;
		this.tipo = tipo;
		this.descripcion = descripcion;
	}

	//Metodos getters y setters
	public String getIdentifica() {
		return identifica;
	}

	public void setIdentifica(String identifica) {
		this.identifica = identifica;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
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
		return "Division [identifica=" + identifica + ", tipo=" + tipo + ", descripcion=" + descripcion + "]";
	}*/
	
}
