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
 * RegimenFiscal que define los atributos y metodos de RegimenFiscal
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "RegimenFiscal.findAll", query = "SELECT r FROM RegimenFiscal r ORDER BY r.identifica") })
@Table(name="REG_REGIMENFISCAL")
public class RegimenFiscal implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "REG_IDENTIFICA",nullable = false,length=3) 
	private String identifica;
	@Column(name = "REG_DESCRIPCION",nullable = false,length = 250) 
	private String descripcion;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public RegimenFiscal(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos de la RegimenFiscal a la base de datos
	 * @param identifica
	 * @param descripcion
	 */
	public RegimenFiscal(String identifica, String descripcion) {
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
	 * Metodo para mostrar los valores de los atributos de la RegimenFiscal
	 */
	/*@Override
	public String toString() {
		return "RegimenFiscal [identifica=" + identifica + ", descripcion=" + descripcion + "]";
	}*/
	
	
}
