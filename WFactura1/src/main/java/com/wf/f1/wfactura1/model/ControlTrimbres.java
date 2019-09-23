/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WF Consulting
 */
@Entity
@Table(name = "controlTrimbres", catalog = "CODICE", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ControlTrimbres.findAll", query = "SELECT c FROM ControlTrimbres c")
    , @NamedQuery(name = "ControlTrimbres.findByIdControlT", query = "SELECT c FROM ControlTrimbres c WHERE c.idControlT = :idControlT")
    , @NamedQuery(name = "ControlTrimbres.findByUsuario", query = "SELECT c FROM ControlTrimbres c WHERE c.usuario = :usuario")
    , @NamedQuery(name = "ControlTrimbres.findByRazonSocial", query = "SELECT c FROM ControlTrimbres c WHERE c.razonSocial = :razonSocial")
    , @NamedQuery(name = "ControlTrimbres.findByCorreo", query = "SELECT c FROM ControlTrimbres c WHERE c.correo = :correo")
    , @NamedQuery(name = "ControlTrimbres.findByTipoCompra", query = "SELECT c FROM ControlTrimbres c WHERE c.tipoCompra = :tipoCompra")
    , @NamedQuery(name = "ControlTrimbres.findByTotTimCom", query = "SELECT c FROM ControlTrimbres c WHERE c.totTimCom = :totTimCom")
    , @NamedQuery(name = "ControlTrimbres.findByFechaCompra", query = "SELECT c FROM ControlTrimbres c WHERE c.fechaCompra = :fechaCompra")
    , @NamedQuery(name = "ControlTrimbres.findByFechaFin", query = "SELECT c FROM ControlTrimbres c WHERE c.fechaFin = :fechaFin")
    , @NamedQuery(name = "ControlTrimbres.findByUtilizados", query = "SELECT c FROM ControlTrimbres c WHERE c.utilizados = :utilizados")
    , @NamedQuery(name = "ControlTrimbres.findByRestantes", query = "SELECT c FROM ControlTrimbres c WHERE c.restantes = :restantes")
    , @NamedQuery(name = "ControlTrimbres.findByAviso", query = "SELECT c FROM ControlTrimbres c WHERE c.aviso = :aviso")
    , @NamedQuery(name = "ControlTrimbres.findByBloqueo", query = "SELECT c FROM ControlTrimbres c WHERE c.bloqueo = :bloqueo")})
public class ControlTrimbres implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idControlT")
    private Integer idControlT;
    @Basic(optional = false)
    @NotNull
    @Column(name = "usuario")
    private int usuario;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "razonSocial")
    private String razonSocial;
    @Size(max = 200)
    @Column(name = "correo")
    private String correo;
    @Size(max = 100)
    @Column(name = "tipoCompra")
    private String tipoCompra;
    @Column(name = "totTimCom")
    private Integer totTimCom;
    @Column(name = "fechaCompra")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCompra;
    @Column(name = "fechaFin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaFin;
    @Column(name = "utilizados")
    private Integer utilizados;
    @Column(name = "restantes")
    private Integer restantes;
    @Column(name = "aviso")
    private Integer aviso;
    @Column(name = "bloqueo")
    private Integer bloqueo;

    public ControlTrimbres() {
    }

    public ControlTrimbres(Integer idControlT) {
        this.idControlT = idControlT;
    }

    public ControlTrimbres(Integer idControlT, int usuario, String razonSocial) {
        this.idControlT = idControlT;
        this.usuario = usuario;
        this.razonSocial = razonSocial;
    }

    public Integer getIdControlT() {
        return idControlT;
    }

    public void setIdControlT(Integer idControlT) {
        this.idControlT = idControlT;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipoCompra() {
        return tipoCompra;
    }

    public void setTipoCompra(String tipoCompra) {
        this.tipoCompra = tipoCompra;
    }

    public Integer getTotTimCom() {
        return totTimCom;
    }

    public void setTotTimCom(Integer totTimCom) {
        this.totTimCom = totTimCom;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Integer getUtilizados() {
        return utilizados;
    }

    public void setUtilizados(Integer utilizados) {
        this.utilizados = utilizados;
    }

    public Integer getRestantes() {
        return restantes;
    }

    public void setRestantes(Integer restantes) {
        this.restantes = restantes;
    }

    public Integer getAviso() {
        return aviso;
    }

    public void setAviso(Integer aviso) {
        this.aviso = aviso;
    }

    public Integer getBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(Integer bloqueo) {
        this.bloqueo = bloqueo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idControlT != null ? idControlT.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ControlTrimbres)) {
            return false;
        }
        ControlTrimbres other = (ControlTrimbres) object;
        if ((this.idControlT == null && other.idControlT != null) || (this.idControlT != null && !this.idControlT.equals(other.idControlT))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wf.web.factura1.model.ControlTrimbres[ idControlT=" + idControlT + " ]";
    }
    
}
