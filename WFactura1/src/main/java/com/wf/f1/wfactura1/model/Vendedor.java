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
 * Clase que define los atributos y metodos de un Vendedor
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 29-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v ORDER BY v.identifica") })
@Table(name="VEN_VENDEDOR")
public class Vendedor implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "VEN_IDENTIFICA",nullable = false) 
	private Integer identifica;
	
	@ManyToOne
	@JoinColumn(name="VEN_SUCURSAL",nullable = false)
	private Sucursal sucursal;
	
	@Column(name = "VEN_NOMBRE",length=50) 
	private String nombre;
	@Column(name = "VEN_APELLIDOPATERNO",nullable=false,length=50) 
	private String apellidoPaterno;
	@Column(name = "VEN_APELLIDOMATERNO",nullable=false,length=50) 
	private String apellidoMaterno;
	@Column(name = "VEN_TELEFONO",nullable=false,length=20) 
	private String telefono;
	@Column(name = "VEN_EMAIL",nullable=false,length=50) 
	private String email;
	@Column(name = "VEN_CALLE",length=20) 
	private String calle;
	@Column(name = "VEN_NROEXTERIOR",length=20) 
	private String nroExterior;
	@Column(name = "VEN_NROINTERIOR",length=20) 
	private String nroInterior;
	@Column(name = "VEN_ENTRECALLE",length=20) 
	private String entreCalle;
	@Column(name = "VEN_YCALLE",length=20) 
	private String yCalle;
	@Column(name = "VEN_CP",length=20) 
	private String cp;
	@Column(name = "VEN_COLONIA",length=20) 
	private String colonia;
	@Column(name = "VEN_LOCALIDAD",length=20) 
	private String localidad;
	@Column(name = "VEN_MUNICIPIO",length=25) 
	private String municipio;
	@Column(name = "VEN_ESTADO",length=20) 
	private String estado;
	@Column(name = "VEN_PAIS",length=50) 
	private String pais;
	@Column(name = "VEN_RESPONSABLECREACION",nullable=false,length=20) 
	private String responsableCreacion;
	@Column(name = "VEN_FECHACREACION",nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "VEN_STATUS",nullable=false) 
	private boolean status;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Vendedor(){
		
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

	public String getApellidoPaterno() {
		return apellidoPaterno;
	}

	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}

	public String getApellidoMaterno() {
		return apellidoMaterno;
	}

	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public String getNroExterior() {
		return nroExterior;
	}

	public void setNroExterior(String nroExterior) {
		this.nroExterior = nroExterior;
	}

	public String getNroInterior() {
		return nroInterior;
	}

	public void setNroInterior(String nroInterior) {
		this.nroInterior = nroInterior;
	}

	public String getEntreCalle() {
		return entreCalle;
	}

	public void setEntreCalle(String entreCalle) {
		this.entreCalle = entreCalle;
	}

	public String getyCalle() {
		return yCalle;
	}

	public void setyCalle(String yCalle) {
		this.yCalle = yCalle;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getColonia() {
		return colonia;
	}

	public void setColonia(String colonia) {
		this.colonia = colonia;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getResponsableCreacion() {
		return responsableCreacion;
	}

	public void setResponsableCreacion(String responsableCreacion) {
		this.responsableCreacion = responsableCreacion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

}
