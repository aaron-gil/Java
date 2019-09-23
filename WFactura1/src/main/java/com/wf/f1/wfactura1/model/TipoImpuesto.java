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
 * TipoImpuesto que define los atributos y metodos de TipoImpuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "TipoImpuesto.findAll", query = "SELECT t FROM TipoImpuesto t ORDER BY t.identifica") })
@Table(name="TII_TIPOIMPUESTO")
public class TipoImpuesto implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "TII_IDENTIFICA",nullable = false,length=3) 
	private String identifica;
	
	@Column(name = "TII_DESCRIPCION",nullable = false,length=4) 
	private String descripcion;
	
	@OneToMany(mappedBy="tipoImpuesto")
	private Set<TasaImpuesto> tasaImpuestos;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public TipoImpuesto(){
		
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
	 * Metodo para mostrar los valores de los atributos de la TipoImpuesto
	 */
	
}
