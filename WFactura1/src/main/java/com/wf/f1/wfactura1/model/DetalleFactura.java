package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
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
 * Clase que define los atributos y metodos de un DetalleFactura
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "DetalleFactura.findAll", query = "SELECT d FROM DetalleFactura d ORDER BY d.identifica")})
@Table(name = "DET_DETALLEFACTURA")
public class DetalleFactura implements Serializable {

    //Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DET_IDENTIFICA", nullable = false)
    private Integer identifica;
    @Column(name = "DET_RETENCION")
    private boolean retencion;
    @Column(name = "DET_CANTIDAD", nullable = false)
    private int cantidad;
    @ManyToOne
    @JoinColumn(name = "DET_PRODUCTO")
    private Producto producto;
    @Column(name = "DET_DESCUENTO")
    private BigDecimal descuento;
    @Column(name = "DET_RETENCIONISR")
    private Integer retencionIsr;
    @Column(name = "DET_RETENCIONIVA")
    private Integer retencionIva;
    @Column(name = "DET_RETENCIONIEPS")
    private Integer retencionIeps;

    @Column(name = "DET_DESCRIPCIONRETENCIONISR", length = 5)
    private String descripcionRetencionIsr;
    @Column(name = "DET_DESCRIPCIONRETENCIONIVA", length = 5)
    private String descripcionRetencionIva;
    @Column(name = "DET_DESCRIPCIONRETENCIONIEPS", length = 5)
    private String descripcionRetencionIeps;
    @Column(name = "DET_DESCRIPCIONIVA", length = 5)
    private String descripcionIva;
    @Column(name = "DET_IMPORTE")
    private BigDecimal importe;
    @Column(name = "DET_PEDIMENTO", length = 50)
    private String pedimento;
    @Column(name = "DET_FECHAPEDIMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaPedimento;
    @Column(name = "DET_ADUANA", length = 50)
    private String aduana;
    @Column(name = "DET_RESPONSABLECREACION", nullable = false, length = 20)
    private String responsableCreacion;
    @Column(name = "DET_FECHACREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "DET_STATUS", nullable = false)
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "DET_FACTURA")
    private Factura factura;

    @OneToMany(mappedBy = "detalleFactura")
    private Set<Impuesto> impuestos;

    private String impuestoF;
    private BigDecimal Base;
    private BigDecimal TasaOCuota;
    private String TipoFactor;

    private String impuestoRete;
    private BigDecimal BaseRete;
    private BigDecimal TasaOCuotaRete;
    private String TipoFactorRete;
    private BigDecimal ImporteImpTras = new BigDecimal(0);
    private BigDecimal ImporteImpRet  = new BigDecimal(0);
    private ArrayList <ImpuestoRetenido> ListaImpuestosRetenidos;
    private ArrayList <ImpuestoTrasladado> ListaImpuestosTrasladados;
    private BigDecimal CantidadReal;
    private String desccripcionCliente;
    
    /*Impuestos Locales */
    
    private BigDecimal tasaImpuestoLocalRet;
    private String nameImpuestoLocalRet;
    private BigDecimal totalImpuestoLocalRet;
    private BigDecimal tasaImpuestoLocalTras;
    private String nameImpuestoLocalTras;
    private BigDecimal totalImpuestoLocalTras;

    public BigDecimal getTasaImpuestoLocalRet() {
        return tasaImpuestoLocalRet;
    }

    public void setTasaImpuestoLocalRet(BigDecimal tasaImpuestoLocalRet) {
        this.tasaImpuestoLocalRet = tasaImpuestoLocalRet;
    }

    public String getNameImpuestoLocalRet() {
        return nameImpuestoLocalRet;
    }

    public void setNameImpuestoLocalRet(String nameImpuestoLocalRet) {
        this.nameImpuestoLocalRet = nameImpuestoLocalRet;
    }

    public BigDecimal getTotalImpuestoLocalRet() {
        return totalImpuestoLocalRet;
    }

    public void setTotalImpuestoLocalRet(BigDecimal totalImpuestoLocalRet) {
        this.totalImpuestoLocalRet = totalImpuestoLocalRet;
    }

    public BigDecimal getTasaImpuestoLocalTras() {
        return tasaImpuestoLocalTras;
    }

    public void setTasaImpuestoLocalTras(BigDecimal tasaImpuestoLocalTras) {
        this.tasaImpuestoLocalTras = tasaImpuestoLocalTras;
    }

    public String getNameImpuestoLocalTras() {
        return nameImpuestoLocalTras;
    }

    public void setNameImpuestoLocalTras(String nameImpuestoLocalTras) {
        this.nameImpuestoLocalTras = nameImpuestoLocalTras;
    }

    public BigDecimal getTotalImpuestoLocalTras() {
        return totalImpuestoLocalTras;
    }

    public void setTotalImpuestoLocalTras(BigDecimal totalImpuestoLocalTras) {
        this.totalImpuestoLocalTras = totalImpuestoLocalTras;
    }
    
    
    //agregar los totales de los impuestos locales 
    public DetalleFactura() {

    }

    public DetalleFactura(Integer identifica, Producto producto, boolean botonEliminar) {
        this.identifica = identifica;
        this.producto = producto;
    }

    public String getDesccripcionCliente() {
        return desccripcionCliente;
    }

    public void setDesccripcionCliente(String desccripcionCliente) {
        this.desccripcionCliente = desccripcionCliente;
    }
    
    

    public Set<Impuesto> getImpuestos() {
        return impuestos;
    }

    public BigDecimal getCantidadReal() {
        return CantidadReal;
    }

    public void setCantidadReal(BigDecimal CantidadReal) {
        this.CantidadReal = CantidadReal;
    }
    

    public void setImpuestos(Set<Impuesto> impuestos) {
        this.impuestos = impuestos;
    }

    public ArrayList<ImpuestoRetenido> getListaImpuestosRetenidos() {
        return ListaImpuestosRetenidos;
    }

    public void setListaImpuestosRetenidos(ArrayList<ImpuestoRetenido> ListaImpuestosRetenidos) {
        this.ListaImpuestosRetenidos = ListaImpuestosRetenidos;
    }

    public ArrayList<ImpuestoTrasladado> getListaImpuestosTrasladados() {
        return ListaImpuestosTrasladados;
    }

    public void setListaImpuestosTrasladados(ArrayList<ImpuestoTrasladado> ListaImpuestosTrasladados) {
        this.ListaImpuestosTrasladados = ListaImpuestosTrasladados;
    }

    
    
    public BigDecimal getImporteImpTras() {
        return ImporteImpTras;
    }

    public void setImporteImpTras(BigDecimal ImporteImpTras) {
        this.ImporteImpTras = ImporteImpTras;
    }

    public BigDecimal getImporteImpRet() {
        return ImporteImpRet;
    }

    public void setImporteImpRet(BigDecimal ImporteImpRet) {
        this.ImporteImpRet = ImporteImpRet;
    }

    
    public String getImpuestoRete() {
        return impuestoRete;
    }

    public void setImpuestoRete(String impuestoRete) {
        this.impuestoRete = impuestoRete;
    }

    public BigDecimal getBaseRete() {
        return BaseRete;
    }

    public void setBaseRete(BigDecimal BaseRete) {
        this.BaseRete = BaseRete;
    }

    public BigDecimal getTasaOCuotaRete() {
        return TasaOCuotaRete;
    }

    public void setTasaOCuotaRete(BigDecimal TasaOCuotaRete) {
        this.TasaOCuotaRete = TasaOCuotaRete;
    }

    public String getTipoFactorRete() {
        return TipoFactorRete;
    }

    public void setTipoFactorRete(String TipoFactorRete) {
        this.TipoFactorRete = TipoFactorRete;
    }
    
    public String getImpuestoF() {
        return impuestoF;
    }

    public void setImpuestoF(String impuestoF) {
        this.impuestoF = impuestoF;
    }

    public BigDecimal getBase() {
        return Base;
    }

    public void setBase(BigDecimal Base) {
        this.Base = Base;
    }

    public BigDecimal getTasaOCuota() {
        return TasaOCuota;
    }

    public void setTasaOCuota(BigDecimal TasaOCuota) {
        this.TasaOCuota = TasaOCuota;
    }

    public String getTipoFactor() {
        return TipoFactor;
    }

    public void setTipoFactor(String TipoFactor) {
        this.TipoFactor = TipoFactor;
    }

    public Integer getIdentifica() {
        return identifica;
    }

    public void setIdentifica(Integer identifica) {
        this.identifica = identifica;
    }

    public boolean isRetencion() {
        return retencion;
    }

    public void setRetencion(boolean retencion) {
        this.retencion = retencion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public Integer getRetencionIsr() {
        return retencionIsr;
    }

    public void setRetencionIsr(Integer retencionIsr) {
        this.retencionIsr = retencionIsr;
    }

    public Integer getRetencionIva() {
        return retencionIva;
    }

    public void setRetencionIva(Integer retencionIva) {
        this.retencionIva = retencionIva;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public String getPedimento() {
        return pedimento;
    }

    public void setPedimento(String pedimento) {
        this.pedimento = pedimento;
    }

    public Date getFechaPedimento() {
        return fechaPedimento;
    }

    public void setFechaPedimento(Date fechaPedimento) {
        this.fechaPedimento = fechaPedimento;
    }

    public String getAduana() {
        return aduana;
    }

    public void setAduana(String aduana) {
        this.aduana = aduana;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getRetencionIeps() {
        return retencionIeps;
    }

    public void setRetencionIeps(Integer retencionIeps) {
        this.retencionIeps = retencionIeps;
    }

    public String getDescripcionRetencionIsr() {
        return descripcionRetencionIsr;
    }

    public void setDescripcionRetencionIsr(String descripcionRetencionIsr) {
        this.descripcionRetencionIsr = descripcionRetencionIsr;
    }

    public String getDescripcionRetencionIva() {
        return descripcionRetencionIva;
    }

    public void setDescripcionRetencionIva(String descripcionRetencionIva) {
        this.descripcionRetencionIva = descripcionRetencionIva;
    }

    public String getDescripcionRetencionIeps() {
        return descripcionRetencionIeps;
    }

    public void setDescripcionRetencionIeps(String descripcionRetencionIeps) {
        this.descripcionRetencionIeps = descripcionRetencionIeps;
    }

    public String getDescripcionIva() {
        return descripcionIva;
    }

    public void setDescripcionIva(String descripcionIva) {
        this.descripcionIva = descripcionIva;
    }

    @Override
    public String toString() {
        return "DetalleFactura{" + "identifica=" + identifica + ", retencion=" + retencion + ", cantidad=" + cantidad + ", producto=" + producto + ", descuento=" + descuento + ", retencionIsr=" + retencionIsr + ", retencionIva=" + retencionIva + ", retencionIeps=" + retencionIeps + ", descripcionRetencionIsr=" + descripcionRetencionIsr + ", descripcionRetencionIva=" + descripcionRetencionIva + ", descripcionRetencionIeps=" + descripcionRetencionIeps + ", descripcionIva=" + descripcionIva + ", importe=" + importe + ", pedimento=" + pedimento + ", fechaPedimento=" + fechaPedimento + ", aduana=" + aduana + ", responsableCreacion=" + responsableCreacion + ", fechaCreacion=" + fechaCreacion + ", status=" + status + ", factura=" + factura + ", impuestos=" + impuestos + '}';
    }

}
