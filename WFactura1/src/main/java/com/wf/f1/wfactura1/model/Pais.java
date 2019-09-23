package com.wf.f1.wfactura1.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Pais que define los atributos y metodos de Pais
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p ORDER BY p.nombre") })
@Table(name="PAI_PAIS")
public class Pais implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "PAI_IDENTIFICA",nullable = false,length=3) 
	private String identifica;
	
	@Column(name = "PAI_NOMBRE",nullable = false,length=250) 
	private String nombre;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Pais(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la Pais a la base de datos
	 * @param identifica
	 * @param descripcion
	 */
	public Pais(String identifica, String nombre) {
		this.identifica = identifica;
		this.nombre = nombre;
	}

	//Metodos getters y setters
	public String getIdentifica() {
		return identifica;
	}

	public void setIdentifica(String identifica) {
		this.identifica = identifica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo para mostrar los valores de los atributos de la Pais
	 */
	@Override
	public String toString() {
		return "Pais [identifica=" + identifica + ", nombre=" + nombre + "]";
	}

	
}
