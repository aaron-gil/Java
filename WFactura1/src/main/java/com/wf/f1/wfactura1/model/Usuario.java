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
 * Clase que define los atributos y metodos de un Usuario
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.identifica") })
@Table(name="USU_USUARIO")
public class Usuario implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "USU_IDENTIFICA",nullable = false) 
	private Integer identifica;
	
	@ManyToOne
	@JoinColumn(name="USU_PERFIL",nullable = false)
	private Perfil perfil;
	
	@ManyToOne
	@JoinColumn(name="USU_SUCURSAL",nullable = false)
	private Sucursal sucursal;
	@Column(name = "USU_NOMBRE",nullable=false,length=20) 
	private String nombre;
	@Column(name = "USU_NOMBRECOMPLETO",nullable=false,length=250) 
	private String nombreCompleto;
	@Column(name = "USU_PASSWORD",nullable=false,length=50) 
	private String password;
	@Column(name = "USU_STATUS",nullable=false) 
	private boolean status;
	@Column(name = "USU_FECHACREACION",nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "USU_ULTIMOACCESO",nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date ultimoAcceso;
	@Column(name = "USU_RESPONSABLECREACION",nullable=false,length=20) 
	private String responsableCreacion;
	@Column(name = "USU_EMAIL",nullable=false,length=50) 
	private String email;
	@Column(name = "USU_TELEFONO",length=20) 
	private String telefono;
	@Column(name = "USU_MUNICIPIO",length=25) 
	private String municipio;
	
	@Column(name = "USU_PAIS",length=3) 
	private String pais;
	@Column(name = "USU_TELEFONOOFICINA",length=20) 
	private String telefonoOficina;
	@Column(name = "USU_TELEFONOMOVIL",length=20) 
	private String telefonoMovil;
	@Column(name = "USU_HORADESDE",length=5) 
	private String horaDesde;
	@Column(name = "USU_HORAHASTA",length=5) 
	private String horaHasta;
	@Column(name = "USU_NOMBRECONTACTO",length=50) 
	private String nombreContacto;
	@Column(name = "USU_CALLE",length=20) 
	private String calle;
	@Column(name = "USU_NROEXTERIOR",length=20) 
	private String nroExterior;
	@Column(name = "USU_NROINTERIOR",length=20) 
	private String nroInterior;
	@Column(name = "USU_ENTRECALLE",length=20) 
	private String entreCalle;
	@Column(name = "USU_YCALLE",length=20) 
	private String yCalle;
	@Column(name = "USU_CP",length=20) 
	private String cp;
	@Column(name = "USU_COLONIA",length=20) 
	private String colonia;
	@Column(name = "USU_LOCALIDAD",length=20) 
	private String localidad;
	@Column(name = "USU_REGIMENFISCAL",length=3) 
	private String regimenFiscal;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Usuario(){
		
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Date getUltimoAcceso() {
		return ultimoAcceso;
	}

	public void setUltimoAcceso(Date ultimoAcceso) {
		this.ultimoAcceso = ultimoAcceso;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getResponsableCreacion() {
		return responsableCreacion;
	}

	public void setResponsableCreacion(String responsableCreacion) {
		this.responsableCreacion = responsableCreacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getTelefonoOficina() {
		return telefonoOficina;
	}

	public void setTelefonoOficina(String telefonoOficina) {
		this.telefonoOficina = telefonoOficina;
	}

	public String getTelefonoMovil() {
		return telefonoMovil;
	}

	public void setTelefonoMovil(String telefonoMovil) {
		this.telefonoMovil = telefonoMovil;
	}

	public String getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(String horaDesde) {
		this.horaDesde = horaDesde;
	}

	public String getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(String horaHasta) {
		this.horaHasta = horaHasta;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
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

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */
	
	
}
