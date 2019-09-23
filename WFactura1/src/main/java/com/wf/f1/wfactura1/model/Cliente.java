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
 * Clase que define los atributos y formas de un Cliente
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c ORDER BY c.identifica") })
@Table(name="CLI_CLIENTE")
public class Cliente implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "CLI_IDENTIFICA",nullable = false) 
	private Integer identifica;
	
	@Column(name = "CLI_EMPRESA") 
	private int empresa;
	
	@Column(name = "CLI_TIPO",length=5) 
	private String tipo;
	@Column(name = "CLI_RAZONSOCIAL",length=250) 
	private String razonSocial;
	@Column(name = "CLI_NOMBRE",length=50) 
	private String nombre;
	@Column(name = "CLI_APELLIDOPATERNO",length=50) 
	private String apellidoPaterno;
	@Column(name = "CLI_APELLIDOMATERNO",length=50) 
	private String apellidoMaterno;
	@Column(name = "CLI_TELEFONO",length=20) 
	private String telefono;
	@Column(name = "CLI_MUNICIPIO",length=25) 
	private String municipio;
	
	@Column(name = "CLI_PAIS",length=3) 
	private String pais;
	
	@Column(name = "CLI_EMAIL",length=50) 
	private String email;
	@Column(name = "CLI_RESPONSABLECREACION",nullable=false,length=20) 
	private String responsableCreacion;
	@Column(name = "CLI_FECHACREACION",nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "CLI_RFC",nullable=false,length=20) 
	private String rfc;
	@Column(name = "CLI_CURP",length=20) 
	private String curp;
	@Column(name = "CLI_TELEFONOOFICINA",length=20) 
	private String telefonoOficina;
	@Column(name = "CLI_TELEFONOMOVIL",length=20) 
	private String telefonoMovil;
	@Column(name = "CLI_HORADESDE",length=5) 
	private String horaDesde;
	@Column(name = "CLI_HORAHASTA",length=5) 
	private String horaHasta;
	@Column(name = "CLI_STATUS",nullable=false) 
	private boolean status;
	@Column(name = "CLI_NOMBREREPRESENTANTELEGAL",length=50) 
	private String nombreRepresentanteLegal;
	@Column(name = "CLI_NOMBRECONTACTO",length=50) 
	private String nombreContacto;
	@Column(name = "CLI_RFCREPRESENTANTELEGAL",length=20) 
	private String rfcRepresentanteLegal;
	@Column(name = "CLI_CURPREPRESENTANTELEGAL",length=20) 
	private String curpRepresentanteLegal;
	@Column(name = "CLI_EMAILREPRESENTANTELEGAL",length=50) 
	private String emailRepresentanteLegal;
	
	@ManyToOne
	@JoinColumn(name="CLI_FORMAPAGO")
	private FormaPago formaPago;
	
	@Column(name = "CLI_NROCUENTA",length=20) 
	private String nroCuenta;
	@Column(name = "CLI_CALLE",length=20) 
	private String calle;
	@Column(name = "CLI_NROEXTERIOR",length=20) 
	private String nroExterior;
	@Column(name = "CLI_NROINTERIOR",length=20) 
	private String nroInterior;
	@Column(name = "CLI_ENTRECALLE",length=20) 
	private String entreCalle;
	@Column(name = "CLI_YCALLE",length=20) 
	private String yCalle;
	@Column(name = "CLI_CP",length=20) 
	private String cp;
	@Column(name = "CLI_COLONIA",length=20) 
	private String colonia;
	@Column(name = "CLI_LOCALIDAD",length=20) 
	private String localidad;
	@Column(name = "CLI_ENTIDADFEDERATIVA",length=20) 
	private String entidadFederativa;
	
	@Column(name = "CLI_REGIMENFISCAL",length=3) 
	private String regimenFiscal;
	
	@OneToMany(mappedBy="cliente")
	private Set<Factura> facturas;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Cliente(){
		
	}

	//formas getters y setters
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public int getEmpresa() {
		return empresa;
	}

	public void setEmpresa(int empresa) {
		this.empresa = empresa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getCurp() {
		return curp;
	}

	public void setCurp(String curp) {
		this.curp = curp;
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

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getNombreRepresentanteLegal() {
		return nombreRepresentanteLegal;
	}

	public void setNombreRepresentanteLegal(String nombreRepresentanteLegal) {
		this.nombreRepresentanteLegal = nombreRepresentanteLegal;
	}

	public String getNombreContacto() {
		return nombreContacto;
	}

	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}

	public String getRfcRepresentanteLegal() {
		return rfcRepresentanteLegal;
	}

	public void setRfcRepresentanteLegal(String rfcRepresentanteLegal) {
		this.rfcRepresentanteLegal = rfcRepresentanteLegal;
	}

	public String getCurpRepresentanteLegal() {
		return curpRepresentanteLegal;
	}

	public void setCurpRepresentanteLegal(String curpRepresentanteLegal) {
		this.curpRepresentanteLegal = curpRepresentanteLegal;
	}

	public String getEmailRepresentanteLegal() {
		return emailRepresentanteLegal;
	}

	public void setEmailRepresentanteLegal(String emailRepresentanteLegal) {
		this.emailRepresentanteLegal = emailRepresentanteLegal;
	}

	public FormaPago getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(FormaPago formaPago) {
		this.formaPago = formaPago;
	}

	public String getNroCuenta() {
		return nroCuenta;
	}

	public void setNroCuenta(String nroCuenta) {
		this.nroCuenta = nroCuenta;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
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

	public String getEntidadFederativa() {
		return entidadFederativa;
	}

	public void setEntidadFederativa(String entidadFederativa) {
		this.entidadFederativa = entidadFederativa;
	}

	/**
	 * forma para mostrar los valores de los atributos de la clase
	 */
}
