package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que define los atributos y metodos de un Perfil de usuario
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Perfil.findAll", query = "SELECT p FROM Perfil p ORDER BY p.identifica") })
@Table(name = "PER_PERFIL")
public class Perfil implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PER_IDENTIFICA", nullable = false)
	private Integer identifica;
	@Column(name = "PER_NOMBRE", nullable = false, length = 25)
	private String nombre;
	@Column(name = "PER_DESCRIPCION", nullable = false, length = 250)
	private String descripcion;
	@Column(name = "PER_STATUS", nullable = false)
	private boolean status;
	@Column(name = "PER_FECHACREACION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "PER_RESPONSABLECREACION", nullable = false, length = 20)
	private String responsableCreacion;
	
	@OneToMany(mappedBy="perfil")
	private Set<Usuario> usuarios;
	
	@OneToMany(mappedBy="perfil")
	private Set<Permiso> permisos;

	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Perfil() {

	}
	
	/**
	 * Constructor utilizado para insertar un perfil a la BD 
	 * @param nombre del perfil
	 * @param descripcion del perfil
	 * @param status estado del perfil
	 * @param fechaCreacion fecha de creacion del perfil
	 * @param responsableCreacion usuario responsable de la creacion del perfil
	 */
	public Perfil(String nombre, String descripcion, boolean status, Date fechaCreacion, String responsableCreacion) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.status = status;
		this.fechaCreacion = fechaCreacion;
		this.responsableCreacion = responsableCreacion;
	}

	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}


	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getResponsableCreacion() {
		return responsableCreacion;
	}

	public void setResponsableCreacion(String responsableCreacion) {
		this.responsableCreacion = responsableCreacion;
	}

	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */
	@Override
	public String toString() {
		return "Perfil [identifica=" + identifica + ", nombre=" + nombre + ", descripcion=" + descripcion + ", status="
				+ status + ", fechaCreacion=" + fechaCreacion + ", responsableCreacion=" + responsableCreacion + "]";
	}
}
