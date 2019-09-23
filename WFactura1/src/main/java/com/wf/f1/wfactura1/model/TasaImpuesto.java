package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.math.BigDecimal;
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

/**
 * Clase que define los atributos y metodos de un TasaImpuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "TasaImpuesto.findAll", query = "SELECT t FROM TasaImpuesto t ORDER BY t.valorMaximo") })
@Table(name="TAI_TasaImpuesto")
public class TasaImpuesto implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "TAI_IDENTIFICA",nullable = false) 
	private Integer identifica;
	@Column(name = "TAI_RANGOFIJO",nullable = false,length=5) 
	private String rangoFijo;
	@Column(name = "TAI_VALORMINIMO") 
	private BigDecimal valorMinimo;
	@Column(name = "TAI_VALORMAXIMO") 
	private BigDecimal valorMaximo;
	
	@ManyToOne
	@JoinColumn(name="TAI_TIPOIMPUESTO",nullable = false)
	private TipoImpuesto tipoImpuesto;
	@ManyToOne
	@JoinColumn(name="TAI_TIPOFACTOR",nullable = false)
	private TipoFactor tipoFactor;
	
	@Column(name = "TAI_TRASLADO",nullable = false) 
	private boolean traslado;
	@Column(name = "TAI_RETENCION",nullable = false) 
	private boolean retencion;
	
	@Column(name = "TAI_DESCRIPCION",nullable = false) 
	private String descripcion;
	
	@OneToMany(mappedBy="tasaImpuesto")
	private Set<Impuesto> impuestos;
	
	
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public TasaImpuesto(){
		
	}

	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}


	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}


	public TipoFactor getTipoFactor() {
		return tipoFactor;
	}


	public void setTipoFactor(TipoFactor tipoFactor) {
		this.tipoFactor = tipoFactor;
	}


	public TipoImpuesto getTipoImpuesto() {
		return tipoImpuesto;
	}


	public void setTipoImpuesto(TipoImpuesto tipoImpuesto) {
		this.tipoImpuesto = tipoImpuesto;
	}

	public String getRangoFijo() {
		return rangoFijo;
	}

	public void setRangoFijo(String rangoFijo) {
		this.rangoFijo = rangoFijo;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}

	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}

	public boolean isTraslado() {
		return traslado;
	}

	public void setTraslado(boolean traslado) {
		this.traslado = traslado;
	}

	public boolean isRetencion() {
		return retencion;
	}

	public void setRetencion(boolean retencion) {
		this.retencion = retencion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	/**
	 * Metodo para mostrar los valores de los atributos de la clase
	 */
	
}
