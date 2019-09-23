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
 * TipoComprobante que define los atributos y metodos de TipoComprobante
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "TipoComprobante.findAll", query = "SELECT t FROM TipoComprobante t ORDER BY t.identifica") })
@Table(name="TIC_TIPOCOMPROBANTE")
public class TipoComprobante implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id      
	@Column(name = "TIC_IDENTIFICA",nullable = false,length=2) 
	private String identifica;
	@Column(name = "TIC_NOMBRE",nullable = false,length = 250) 
	private String nombre;
	
	@OneToMany(mappedBy="tipoComprobante")
	private Set<Serie> series;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public TipoComprobante(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la TipoComprobante a la base de datos
	 * @param identifica
	 * @param nombre
	 */
	public TipoComprobante(String identifica, String nombre) {
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
	 * Metodo para mostrar los valores de los atributos de la TipoComprobante
	 */
	/*@Override
	public String toString() {
		return "TipoComprobante [identifica=" + identifica + ", descripcion=" + descripcion + "]";
	}*/
	
	
}
