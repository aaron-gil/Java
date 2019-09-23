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
 * Clase que define los atributos y metodos de Menu
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m ORDER BY m.identifica") })
@Table(name="MEN_MENU")
public class Menu implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "MEN_IDENTIFICA", nullable = false) 
	private Integer identifica;
	@Column(name = "MEN_MENUPADRE") 
	private Integer menuPadre;
	@Column(name = "MEN_DESCRIPCION",length=250) 
	private String descripcion;
	@Column(name = "MEN_HABILITADO")
	private boolean habilitado;
	@Column(name = "MEN_URL", nullable = false,length=5) 
	private String url;
	@Column(name = "MEN_FECHACREACION", nullable = false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "MEN_RESPONSABLECREACION",nullable = false,length=20) 
	private String responsableCreacion;
	
	@OneToMany(mappedBy="menu")
	private Set<Permiso> permisos;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Menu(){
		
	}

	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public Integer getMenuPadre() {
		return menuPadre;
	}

	public void setMenuPadre(Integer menuPadre) {
		this.menuPadre = menuPadre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
	
}
