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
 * FormaPago que define los atributos y metodos de FormaPago
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "FormaPago.findAll", query = "SELECT f FROM FormaPago f ORDER BY f.identifica") })
@Table(name="FOR_FORMAPAGO")
public class FormaPago implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "FOR_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	
	@Column(name = "FOR_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="formaPago")
	private Set<Cliente> clientes;
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public FormaPago(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la FormaPago a la base de datos
	 * @param identifica
	 * @param descripcion
	 */
	public FormaPago(String identifica, Grupo grupo, String descripcion) {
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

	/*@Override
	public String toString() {
		return descripcion;
	}*/

	/**
	 * Metodo para mostrar los valores de los atributos de la FormaPago
	 */
	/*@Override
	public String toString() {
		return "FormaPago [identifica=" + identifica + ", descripcion=" + descripcion + "]";
	}*/
	
	
	
}
