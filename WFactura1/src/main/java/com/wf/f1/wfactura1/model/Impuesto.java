package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.math.BigDecimal;

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

/**
 * Impuesto que define los atributos y metodos de Impuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Impuesto.findAll", query = "SELECT i FROM Impuesto i ORDER BY i.identifica") })
@Table(name="IMP_IMPUESTO")
public class Impuesto implements Serializable{

	//Atributos
	private static final long serialVersionUID = 1L;
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)     
	@Column(name = "IMP_IDENTIFICA",nullable = false) 
	private Integer identifica;
	@ManyToOne
	@JoinColumn(name="IMP_TASAIMPUESTO",nullable = false)
	private TasaImpuesto tasaImpuesto;
	@ManyToOne
	@JoinColumn(name="IMP_DETALLEFACTURA",nullable = false)
	private DetalleFactura detalleFactura;
	@Column(name = "IMP_IMPORTE",nullable = false) 
	private BigDecimal importe;
	@Column(name = "IMP_BASE",nullable = false) 
	private BigDecimal base;
	/**
	 * Constructor vacio que debe tener la entidad por defecto
	 */
	public Impuesto(){
		
	}
	
	//Metodos getters y setters
	public Integer getIdentifica() {
		return identifica;
	}
	public void setIdentifica(Integer identifica) {
		this.identifica = identifica;
	}
	public TasaImpuesto getTasaImpuesto() {
		return tasaImpuesto;
	}
	public void setTasaImpuesto(TasaImpuesto tasaImpuesto) {
		this.tasaImpuesto = tasaImpuesto;
	}
	public DetalleFactura getDetalleFactura() {
		return detalleFactura;
	}
	public void setDetalleFactura(DetalleFactura detalleFactura) {
		this.detalleFactura = detalleFactura;
	}
	public BigDecimal getImporte() {
		return importe;
	}
	public void setImporte(BigDecimal importe) {
		this.importe = importe;
	}
	public BigDecimal getBase() {
		return base;
	}
	public void setBase(BigDecimal base) {
		this.base = base;
	}
	
	
	/**
	 * Metodo para mostrar los valores de los atributos de la Impuesto
	 */
}
