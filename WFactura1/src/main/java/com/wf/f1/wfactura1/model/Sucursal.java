package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que define los atributos y metodos de un Sucursal
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Sucursal.findAll", query = "SELECT s FROM Sucursal s ORDER BY s.identifica") })
@Table(name="SUC_SUCURSAL")
public class Sucursal implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "SUC_IDENTIFICA",nullable = false) 
	private Integer identifica;
	
	@ManyToOne
	@JoinColumn(name="SUC_EMPRESA",nullable = false)
	private Empresa empresa;
	
	@Column(name = "SUC_NOMBRE",length=50) 
	private String nombre;
	@Column(name = "SUC_NOMBRECOMERCIAL",length=50) 
	private String nombreComercial;
	@Column(name = "SUC_CALLE",nullable = false,length=20) 
	private String calle;
	@Column(name = "SUC_NROEXTERIOR",nullable = false,length=20) 
	private String nroExterior;
	@Column(name = "SUC_NROINTERIOR",length=20) 
	private String nroInterior;
	@Column(name = "SUC_ENTRECALLE",length=20) 
	private String entreCalle;
	@Column(name = "SUC_YCALLE",length=20) 
	private String yCalle;
	@Column(name = "SUC_CP",nullable=false,length = 5) 
	private String cp;
	@Column(name = "SUC_COLONIA",length=20) 
	private String colonia;
	@Column(name = "SUC_MUNICIPIO",length=25) 
	private String municipio;
	@Column(name = "SUC_LOCALIDAD",length=20) 
	private String localidad;
	@Column(name = "SUC_ESTADO",length=20) 
	private String estado;
	@Column(name = "SUC_STATUS",nullable=false) 
	private boolean status;
	@Column(name = "SUC_RESPONSABLECREACION",nullable=false,length=20) 
	private String responsableCreacion;
	@Column(name = "SUC_FECHACREACION",nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "SUC_NOMBRECONTACTO",nullable=false,length=50) 
	private String nombreContacto;
	@Column(name = "SUC_EMAIL",nullable=false,length=50) 
	private String email;
	@Column(name = "SUC_TELEFONOOFICINA",nullable=false,length=20) 
	private String telefonoOficina;
	@Column(name = "SUC_TELEFONOCASA",length=20) 
	private String telefonoCasa;
	@Column(name = "SUC_TELEFONOMOVIL",length=20) 
	private String telefonoMovil;
	@Column(name = "SUC_FAX",length=20) 
	private String fax;
	@Column(name = "SUC_HORARIOSCONTACTO",length=50) 
	private String horariosContacto;
	
	@OneToMany(mappedBy="sucursal")
	private Set<Factura> facturas;
	
	@OneToMany(mappedBy="sucursal")
	private Set<Vendedor> vendedores;
	
	@OneToMany(mappedBy="sucursal")
	private Set<Serie> series;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Sucursal(){
		
	}

	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
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

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
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

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefonoOficina() {
		return telefonoOficina;
	}

	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}

	public String getTelefonoCasa() {
		return telefonoCasa;
	}

	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getHorariosContacto() {
		return horariosContacto;
	}

	public void setHorariosContacto(String horariosContacto) {
		this.horariosContacto = horariosContacto;
	}

	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */

}
