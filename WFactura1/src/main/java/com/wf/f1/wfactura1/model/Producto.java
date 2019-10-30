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
 * Clase que define los atributos y metodos de un PROcepto
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Producto.findAll", query = "SELECT p FROM Producto p ORDER BY p.identifica")})
@Table(name = "PRO_PRODUCTO")
public class Producto implements Serializable {

    //Atributos
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRO_IDENTIFICA", nullable = false)
    private Integer identifica;

    /*@ManyToOne
	@JoinColumn(name="PRO_EMPRESA")
	private Empresa empresa;*/
    @ManyToOne
    @JoinColumn(name = "PRO_MEDIDA")
    private Medida medida;

    @Column(name = "PRO_CODIGOSAT", length = 50)
    private String codigoSat;

    @Column(name = "PRO_DESCRIPCIONCODIGOSAT", length = 250)
    private String descripcionCodigoSat;

    @Column(name = "PRO_DESCRIPCION", length = 250)
    private String descripcion;
    @Column(name = "PRO_VALORUNITARIO")
    private BigDecimal valorUnitario;
    @Column(name = "PRO_IVA", length = 10)
    private Integer iva;
    @Column(name = "PRO_NUMEROPREDIAL", length = 50)
    private String numeroPredial;
    @Column(name = "PRO_NUMEROIDENTIFICADOR", length = 50)
    private String numeroIdentificador;
    @Column(name = "PRO_RESPONSABLECREACION", nullable = false, length = 20)
    private String responsableCreacion;
    @Column(name = "PRO_FECHACREACION", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Column(name = "PRO_STATUS", nullable = false)
    private boolean status;

    @Column(name = "PRO_EMPRESA")
    private int empresa;

    @OneToMany(mappedBy = "producto")
    private Set<DetalleFactura> detallesFactura;

    @Lob
    @Column(name = "PRO_NPEDIMENTO")
    private String npedimento;

    /**
     * PROstructor vacio que debe tener la entidad por defecto
     */
    public Producto() {

    }

    //Metodos getters y setters
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

    public Medida getMedida() {
        return medida;
    }

    public void setMedida(Medida medida) {
        this.medida = medida;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public Integer getIva() {
        return iva;
    }

    public void setIva(Integer iva) {
        this.iva = iva;
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

    public String getNumeroPredial() {
        return numeroPredial;
    }

    public void setNumeroPredial(String numeroPredial) {
        this.numeroPredial = numeroPredial;
    }

    public String getNumeroIdentificador() {
        return numeroIdentificador;
    }

    public void setNumeroIdentificador(String numeroIdentificador) {
        this.numeroIdentificador = numeroIdentificador;
    }

    public String getCodigoSat() {
        return codigoSat;
    }

    public void setCodigoSat(String codigoSat) {
        this.codigoSat = codigoSat;
    }

    public String getDescripcionCodigoSat() {
        return descripcionCodigoSat;
    }

    public void setDescripcionCodigoSat(String descripcionCodigoSat) {
        this.descripcionCodigoSat = descripcionCodigoSat;
    }
    
    @Override
    public String toString() {
        return "Producto{" + "identifica=" + identifica + ", medida=" + medida + ", codigoSat=" + codigoSat + ", descripcionCodigoSat=" + descripcionCodigoSat + ", descripcion=" + descripcion + ", valorUnitario=" + valorUnitario + ", iva=" + iva + ", numeroPredial=" + numeroPredial + ", numeroIdentificador=" + numeroIdentificador + ", responsableCreacion=" + responsableCreacion + ", fechaCreacion=" + fechaCreacion + ", status=" + status + ", empresa=" + empresa + ", detallesFactura=" + detallesFactura + '}';
    }

    public String getNpedimento() {
        return npedimento;
    }

    public void setNpedimento(String npedimento) {
        this.npedimento = npedimento;
    }
    
    

    /**
     * Metodo para mostrar los valores de los atributos de la clase
     */
}
