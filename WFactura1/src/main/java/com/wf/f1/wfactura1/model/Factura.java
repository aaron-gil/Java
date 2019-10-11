package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que define los atributos y metodos de un Factura
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Factura.findAll", query = "SELECT f FROM Factura f ORDER BY f.identifica")})
@Table(name = "FAC_FACTURA")
public class Factura implements Serializable {

    //Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FAC_IDENTIFICA", nullable = false)
    private Integer identifica;

    @Column(name = "FAC_RELACIONADO")
    private boolean relacionado;

    @ManyToOne
    @JoinColumn(name = "FAC_SUCURSAL")
    private Sucursal sucursal;

    @ManyToOne
    @JoinColumn(name = "FAC_CLIENTE")
    private Cliente cliente;

    @Column(name = "FAC_TIPOCOMPROBANTE", length = 20)
    private String tipoComprobante;

    @ManyToOne
    @JoinColumn(name = "FAC_METODOPAGO")
    private MetodoPago metodoPago;

    @Column(name = "FAC_ESTADO", length = 50)
    private String estado;

    @Column(name = "FAC_ESTATUS", length = 50)
    private String estatus;

    @ManyToOne
    @JoinColumn(name = "FAC_MONEDA")
    private Moneda moneda;

    @ManyToOne
    @JoinColumn(name = "FAC_USOCFDI")
    private UsoCfdi usoCfdi;

    @ManyToOne
    @JoinColumn(name = "FAC_TIPORELACION")
    private TipoRelacion tipoRelacion;

    @Column(name = "FAC_SUBTOTAL")
    private BigDecimal subtotal;
    @Column(name = "FAC_TOTALIMPUESTOTRASLADADOS")
    private BigDecimal totalImpuestoTrasladados;
    @Column(name = "FAC_TOTALIMPUESTORETENIDOS")
    private BigDecimal totalImpuestoRetenidos;
    @Column(name = "FAC_TOTAL")
    private BigDecimal total;
    @Column(name = "FAC_DESCUENTO")
    private BigDecimal descuento;
    @Column(name = "FAC_MOTIVODESCUENTO", length = 250)
    private String motivoDescuento;
    @Column(name = "FAC_UUID", length = 50)
    private String uuid;

    @Lob
    @Column(name = "FAC_XML")
    private String xml;
    @Lob
    @Column(name = "FAC_XMLTIMBRADO")
    private String xmlTimbrado;
    @Column(name = "FAC_MONTOLETRA", length = 250)
    private String montoLetra;

    @Column(name = "FAC_FECHACREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "FAC_RESPONSABLECREACION", nullable = false, length = 20)
    private String responsableCreacion;
    @Column(name = "FAC_LUGAREXPEDICION", length = 20)
    private String lugarExpedicion;

    @Column(name = "FAC_CONDICIONPAGO", length = 100)
    private String condicionPago;
    @Column(name = "FAC_TIPOCAMBIO")
    private BigDecimal tipoCambio;

    @Column(name = "FAC_OBSERVACIONES", length = 250)
    private String observaciones;

    @Column(name = "FAC_FOLIO", length = 50)
    private String folio;

    @Column(name = "FAC_CONSECUTIVO")
    private Integer consecutivo;
    @Column(name = "FAC_SERIE")
    private Integer serie;
    @Lob
    @Column(name = "FAC_ERROR")
    private String error;

    @OneToMany(mappedBy = "factura")
    private Set<DetalleFactura> detallesFactura;

    //PAGOS
    @Column(name = "NUMPARCIALIDAD")
    private Integer numParcialidad;
    @Column(name = "IMPSALDOANT")
    private BigDecimal impSaldoAnt;
    @Column(name = "IMPPAGADO")
    private BigDecimal impPagado;
    @Column(name = "IMPSALDOINSOLUTO")
    private BigDecimal impSaldoInsoluto;
    @Column(name = "UUIDPAGO", length = 50)
    private String uuidPago;
    @Column(name = "UUIDPAGOA", length = 50)
    private String uuidPagoA;
    @Column(name = "FORMAPAGO", length = 50)
    private String formaPago;
    @Column(name = "CONFIRMACION", length = 100)
    private String confirmacion;

    /**
     * Constructor vacio que debe tener la entidad por defecto
     */
    public Factura() {

    }

    public String getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(String confirmacion) {
        this.confirmacion = confirmacion;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    //Metodos getters y setters
    public Integer getIdentifica() {
        return identifica;
    }

    public void setIdentifica(Integer identifica) {
        this.identifica = identifica;
    }

    public boolean isRelacionado() {
        return relacionado;
    }

    public void setRelacionado(boolean relacionado) {
        this.relacionado = relacionado;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Set<DetalleFactura> getDetallesFactura() {
        return detallesFactura;
    }

    public void setDetallesFactura(Set<DetalleFactura> detallesFactura) {
        this.detallesFactura = detallesFactura;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTotalImpuestoTrasladados() {
        return totalImpuestoTrasladados;
    }

    public void setTotalImpuestoTrasladados(BigDecimal totalImpuestoTrasladados) {
        this.totalImpuestoTrasladados = totalImpuestoTrasladados;
    }

    public BigDecimal getTotalImpuestoRetenidos() {
        return totalImpuestoRetenidos;
    }

    public void setTotalImpuestoRetenidos(BigDecimal totalImpuestoRetenidos) {
        this.totalImpuestoRetenidos = totalImpuestoRetenidos;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getMontoLetra() {
        return montoLetra;
    }

    public void setMontoLetra(String montoLetra) {
        this.montoLetra = montoLetra;
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

    public String getLugarExpedicion() {
        return lugarExpedicion;
    }

    public void setLugarExpedicion(String lugarExpedicion) {
        this.lugarExpedicion = lugarExpedicion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getMotivoDescuento() {
        return motivoDescuento;
    }

    public void setMotivoDescuento(String motivoDescuento) {
        this.motivoDescuento = motivoDescuento;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public UsoCfdi getUsoCfdi() {
        return usoCfdi;
    }

    public void setUsoCfdi(UsoCfdi usoCfdi) {
        this.usoCfdi = usoCfdi;
    }

    public String getCondicionPago() {
        return condicionPago;
    }

    public void setCondicionPago(String condicionPago) {
        this.condicionPago = condicionPago;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getXmlTimbrado() {
        return xmlTimbrado;
    }

    public void setXmlTimbrado(String xmlTimbrado) {
        this.xmlTimbrado = xmlTimbrado;
    }

    public Integer getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(Integer consecutivo) {
        this.consecutivo = consecutivo;
    }

    public Integer getSerie() {
        return serie;
    }

    public void setSerie(Integer serie) {
        this.serie = serie;
    }

    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setImpPagado(BigDecimal impPagado) {
        this.impPagado = impPagado;
    }

    public BigDecimal getImpPagado() {
        return impPagado;
    }

    public void setImpSaldoAnt(BigDecimal impSaldoAnt) {
        this.impSaldoAnt = impSaldoAnt;
    }

    public BigDecimal getImpSaldoAnt() {
        return impSaldoAnt;
    }

    public void setImpSaldoInsoluto(BigDecimal impSaldoInsoluto) {
        this.impSaldoInsoluto = impSaldoInsoluto;
    }

    public BigDecimal getImpSaldoInsoluto() {
        return impSaldoInsoluto;
    }

    public void setNumParcialidad(Integer numParcialidad) {
        this.numParcialidad = numParcialidad;
    }

    public Integer getNumParcialidad() {
        return numParcialidad;
    }

    public void setUuidPago(String uuidPago) {
        this.uuidPago = uuidPago;
    }

    public String getUuidPago() {
        return uuidPago;
    }

    public void setUuidPagoA(String uuidPagoA) {
        this.uuidPagoA = uuidPagoA;
    }

    public String getUuidPagoA() {
        return uuidPagoA;
    }

}
