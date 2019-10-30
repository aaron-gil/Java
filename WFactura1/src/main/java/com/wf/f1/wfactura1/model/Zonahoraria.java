/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author WF Consulting
 */
@Entity
@Table(name = "ZONAHORARIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Zonahoraria.findAll", query = "SELECT z FROM Zonahoraria z")
    , @NamedQuery(name = "Zonahoraria.findByIdZonahoraria", query = "SELECT z FROM Zonahoraria z WHERE z.idZonahoraria = :idZonahoraria")
    , @NamedQuery(name = "Zonahoraria.findByCp", query = "SELECT z FROM Zonahoraria z WHERE z.cp = :cp")
    , @NamedQuery(name = "Zonahoraria.findByHoras", query = "SELECT z FROM Zonahoraria z WHERE z.horas = :horas")
    , @NamedQuery(name = "Zonahoraria.findByZona", query = "SELECT z FROM Zonahoraria z WHERE z.zona = :zona")})
public class Zonahoraria implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_ZONAHORARIA")
    private Integer idZonahoraria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "CP")
    private String cp;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "HORAS")
    private String horas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ZONA")
    private String zona;

    public Zonahoraria() {
    }

    public Zonahoraria(Integer idZonahoraria) {
        this.idZonahoraria = idZonahoraria;
    }

    public Zonahoraria(Integer idZonahoraria, String cp, String horas, String zona) {
        this.idZonahoraria = idZonahoraria;
        this.cp = cp;
        this.horas = horas;
        this.zona = zona;
    }

    public Integer getIdZonahoraria() {
        return idZonahoraria;
    }

    public void setIdZonahoraria(Integer idZonahoraria) {
        this.idZonahoraria = idZonahoraria;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idZonahoraria != null ? idZonahoraria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Zonahoraria)) {
            return false;
        }
        Zonahoraria other = (Zonahoraria) object;
        if ((this.idZonahoraria == null && other.idZonahoraria != null) || (this.idZonahoraria != null && !this.idZonahoraria.equals(other.idZonahoraria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.wf.f1.wfactura1.model.Zonahoraria[ idZonahoraria=" + idZonahoraria + " ]";
    }
    
}
