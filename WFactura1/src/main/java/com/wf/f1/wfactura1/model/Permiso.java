package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Clase que define los atributos y metodos de Permiso
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Permiso.findAll", query = "SELECT p FROM Permiso p ORDER BY p.identifica") })
@Table(name="PRS_PERMISO")
public class Permiso implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "PRS_IDENTIFICA", nullable = false) 
	private Integer identifica;
	
	@ManyToOne
	@JoinColumn(name="PRS_PERFIL",nullable = false)
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="PRS_MENU",nullable = false)
	private Menu menu;
	
	@Column(name = "PRS_FECHACREACION",nullable = false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "PRS_RESPONSABLECREACION",nullable = false,length=20) 
	private String responsableCreacion;
	
	@Column(name = "PRS_VALORPERMISO") 
	private boolean valorPermiso;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Permiso(){
		
	}

	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
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

	public boolean isValorPermiso() {
		return valorPermiso;
	}

	public void setValorPermiso(boolean valorPermiso) {
		this.valorPermiso = valorPermiso;
	}
}
