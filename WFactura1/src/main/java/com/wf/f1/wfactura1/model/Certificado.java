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
 * Clase que define los atributos y metodos de un Certificado
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Certificado.findAll", query = "SELECT c FROM Certificado c ORDER BY c.identifica") })
@Table(name="CER_CERTIFICADO")
public class Certificado implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "CER_IDENTIFICA",nullable = false) 
	private Integer identifica;
	
	@ManyToOne
	@JoinColumn(name="CER_EMPRESA",nullable = false)
	private Empresa empresa;
	
	@Column(name = "CER_NROCERTIFICADO",nullable=false,length=50) 
	private String nroCertificado;
	@Column(name = "CER_CERTIFICADO",length=50) 
	private String certificado;
	@Column(name = "CER_CONTRASENA",length=50) 
	private String contrasena;
	@Column(name = "CER_NROSERIE",length=100) 
	private String nroSerie;
	@Column(name = "CER_CERBASE64") 
	private String cerBase64;
	@Column(name = "CER_INICIO")
	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;
	@Column(name = "CER_VENCE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date vence;
	@Column(name = "CER_RESPONSABLECREACION",nullable=false,length=20) 
	private String responsableCreacion;
	@Column(name = "CER_FECHACREACION",nullable=false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Certificado(){
		
	}

	/**
	 * Constructor utilizado para la insercion de los datos del Certificado a la base de datos
	 * @param empresa a la cual le corresponde el certificado
	 * @param nroCertificado corresponde al numero del certificado
	 * @param certificado corresponde al contenido del certificado
	 * @param contrasena 
	 * @param nroSerie 
	 * @param cerBase64 
	 * @param inicio fecha de inicio de vigencia del certificado
	 * @param vence fecha final de vigencia del certificado
	 * @param responsableCreacion usuario responsable de la creacion del certificado
	 * @param fechaCreacion fecha de creacion del certificado
	 */
	public Certificado(Empresa empresa, String nroCertificado, String certificado, String contrasena, String nroSerie,
			String cerBase64, Date inicio, Date vence, String responsableCreacion, Date fechaCreacion) {
		this.empresa = empresa;
		this.nroCertificado = nroCertificado;
		this.certificado = certificado;
		this.contrasena = contrasena;
		this.nroSerie = nroSerie;
		this.cerBase64 = cerBase64;
		this.inicio = inicio;
		this.vence = vence;
		this.responsableCreacion = responsableCreacion;
		this.fechaCreacion = fechaCreacion;
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

	public String getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(String nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getNroSerie() {
		return nroSerie;
	}

	public void setNroSerie(String nroSerie) {
		this.nroSerie = nroSerie;
	}

	public String getCerBase64() {
		return cerBase64;
	}

	public void setCerBase64(String cerBase64) {
		this.cerBase64 = cerBase64;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getVence() {
		return vence;
	}

	public void setVence(Date vence) {
		this.vence = vence;
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

	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */
	@Override
	public String toString() {
		return "Certificado [identifica=" + identifica + ", empresa=" + empresa + ", nroCertificado=" + nroCertificado
				+ ", certificado=" + certificado + ", contrasena=" + contrasena + ", nroSerie=" + nroSerie
				+ ", cerBase64=" + cerBase64 + ", inicio=" + inicio + ", vence=" + vence + ", responsableCreacion="
				+ responsableCreacion + ", fechaCreacion=" + fechaCreacion + "]";
	}
}
