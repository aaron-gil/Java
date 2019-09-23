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
 * TipoRelacion que define los atributos y metodos de TipoRelacion
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "TipoRelacion.findAll", query = "SELECT t FROM TipoRelacion t ORDER BY t.identifica") })
@Table(name="TPR_TIPORELACION")
public class TipoRelacion implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "TPR_IDENTIFICA",nullable = false,length=2) 
	private String identifica;
	
	@Column(name = "TPR_DESCRIPCION",nullable = false,length=250) 
	private String descripcion;
	
	@OneToMany(mappedBy="tipoRelacion")
	private Set<Factura> facturas;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public TipoRelacion(){
		
	}

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
	 * Metodo para mostrar los valores de los atributos de la TipoRelacion
	 */
	
}
