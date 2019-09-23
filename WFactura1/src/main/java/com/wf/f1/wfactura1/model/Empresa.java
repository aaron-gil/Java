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
 * Clase que define los atributos y metodos de Empresa
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e ORDER BY e.identifica") })
@Table(name="EMP_EMPRESA")
public class Empresa implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "EMP_IDENTIFICA", nullable = false) 
	private Integer identifica;
	@Column(name = "EMP_NOMBRECOMERCIAL",length=250) 
	private String nombreComercial;
	@Column(name = "EMP_TIPOPERSONA",length=5) 
	private String tipoPersona;
	@Column(name = "EMP_NOMBRE",length=50) 
	private String nombre;
	@Column(name = "EMP_SEGUNDONOMBRE",length=50) 
	private String segundoNombre;
	@Column(name = "EMP_APELLIDOS",length=50) 
	private String apellidos;
	@Column(name = "EMP_RAZONSOCIAL",length=250) 
	private String razonSocial;
	@Column(name = "EMP_RFC",length=20) 
	private String rfc;
	@Column(name = "EMP_DIGITOVERIFICADOR",length=3) 
	private String digitoVerificador;
	@Column(name = "EMP_RESPONSABLECREACION",nullable = false,length=20) 
	private String responsableCreacion;
	@Column(name = "EMP_FECHACREACION", nullable = false) 
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;
	@Column(name = "EMP_STATUS", nullable = false) 
	private boolean status;
	@Column(name = "EMP_REGIMENFISCAL",length=3) 
	private String regimenFiscal;
	
	/*@OneToMany(mappedBy="empresa")
	private Set<Usuario> usuarios;*/
	
	@OneToMany(mappedBy="empresa")
	private Set<Sucursal> sucursal;
	
	@OneToMany(mappedBy="empresa")
	private Set<Certificado> certificado;
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Empresa(){
		
	}

	/**
	 * Constructor utilizado para insertar un empresa a la BD 
	 * @param nombreComercial 
	 * @param tipoPersona persona natural o juridica
	 * @param nombre
	 * @param segundoNombre 
	 * @param apellidos
	 * @param razonSocial
	 * @param nit
	 * @param digitoVerificador digito que acompaña el nit
	 * @param responsableCreacion usuario responsable de la creacion de la empresa
	 * @param fechaCreacion fecha de creacion de la empresa
	 */
	public Empresa(String nombreComercial, String tipoPersona, String nombre, String segundoNombre, String apellidos,
			String razonSocial, String rfc, String digitoVerificador, String responsableCreacion, Date fechaCreacion,boolean status) {
		this.nombreComercial = nombreComercial;
		this.tipoPersona = tipoPersona;
		this.nombre = nombre;
		this.segundoNombre = segundoNombre;
		this.apellidos = apellidos;
		this.razonSocial = razonSocial;
		this.rfc = rfc;
		this.digitoVerificador = digitoVerificador;
		this.responsableCreacion = responsableCreacion;
		this.fechaCreacion = fechaCreacion;
		this.status = status;
	}

	//Metodos getters y setters
	
	public Integer getIdentifica() {
		return identifica;
	}

	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}

	public String getNombreComercial() {
		return nombreComercial;
	}

	public void setNombreComercial(String nombreComercial) {
		this.nombreComercial = nombreComercial;
	}

	public String getTipoPersona() {
		return tipoPersona;
	}

	public void setTipoPersona(String tipoPersona) {
		this.tipoPersona = tipoPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSegundoNombre() {
		return segundoNombre;
	}

	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	public String getDigitoVerificador() {
		return digitoVerificador;
	}

	public void setDigitoVerificador(String digitoVerificador) {
		this.digitoVerificador = digitoVerificador;
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

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}
	

	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */
	/*@Override
	public String toString() {
		return "Empresa [identifica=" + identifica + ", nombreComercial=" + nombreComercial + ", tipoPersona="
				+ tipoPersona + ", nombre=" + nombre + ", segundoNombre=" + segundoNombre + ", apellidos=" + apellidos
				+ ", razonSocial=" + razonSocial + ", rfc=" + rfc + ", digitoVerificador=" + digitoVerificador
				+ ", responsableCreacion=" + responsableCreacion + ", fechaCreacion=" + fechaCreacion + ", status="
				+ status + ", sucursal=" + sucursal + ", certificado=" + certificado + "]";
	}*/
}
