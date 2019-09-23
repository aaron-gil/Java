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
 * UsoCfdi que define los atributos y metodos de UsoCfdi
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "UsoCfdi.findAll", query = "SELECT u FROM UsoCfdi u ORDER BY u.identifica") })
@Table(name="USO_USOCFDI")
public class UsoCfdi implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "USO_IDENTIFICA",nullable = false,length=50) 
	private String identifica;
	
	@Column(name = "USO_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="usoCfdi")
	private Set<Factura> facturas;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public UsoCfdi(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la UsoCfdi a la base de datos
	 * @param identifica
	 * @param descripcion
	 */
	public UsoCfdi(String identifica, String descripcion) {
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
	 * Metodo para mostrar los valores de los atributos de la UsoCfdi
	 */
	@Override
	public String toString() {
		return "UsoCfdi [identifica=" + identifica + ", descripcion=" + descripcion + "]";
	}
}
