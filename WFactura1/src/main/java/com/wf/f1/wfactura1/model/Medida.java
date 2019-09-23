package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Medida que define los atributos y metodos de Medida
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Medida.findAll", query = "SELECT m FROM Medida m ORDER BY m.nombre") })
@Table(name="MED_MEDIDA")
public class Medida implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	//@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "MED_IDENTIFICA",nullable = false,length=3) 
	private String identifica;
	@Lob
	@Column(name = "MED_NOMBRE",nullable = false) 
	private String nombre;
	@Lob
	@Column(name = "MED_DESCRIPCION",nullable = false) 
	private String descripcion;
	@Lob
	@Column(name = "MED_NOTA",nullable = false) 
	private String nota;
	@Column(name = "MED_FECHAINICIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaInicio;
	@Column(name = "MED_FECHAFIN")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaFin;
	
	@OneToMany(mappedBy="medida")
	private Set<Producto> productos;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Medida(){
		
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNota() {
		return nota;
	}

	public void setNota(String nota) {
		this.nota = nota;
	}

	
	/**
	 * Metodo para mostrar los valores de los atributos de la Medida
	 */
	
	
}
