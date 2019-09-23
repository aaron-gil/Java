/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.model;

import java.math.BigDecimal;

/**
 *
 * @author WF
 */
public class ImpuestoTrasladado {
    private BigDecimal Base;
    private String Impuesto;
    private String TipoFactor;
    private BigDecimal Importe;
    private BigDecimal TasaOCuota;

    public ImpuestoTrasladado() {
    }

    public ImpuestoTrasladado(BigDecimal Base, String Impuesto, String TipoFactor, BigDecimal Importe, BigDecimal TasaOCuota) {
        this.Base = Base;
        this.Impuesto = Impuesto;
        this.TipoFactor = TipoFactor;
        this.Importe = Importe;
        this.TasaOCuota = TasaOCuota;
    }

     public ImpuestoTrasladado(BigDecimal Base, String Impuesto, String TipoFactor) {
        this.Base = Base;
        this.Impuesto = Impuesto;
        this.TipoFactor = TipoFactor;
       
    }
    
    
    public BigDecimal getBase() {
        return Base;
    }

    public void setBase(BigDecimal Base) {
        this.Base = Base;
    }

    public String getImpuesto() {
        return Impuesto;
    }

    public void setImpuesto(String Impuesto) {
        this.Impuesto = Impuesto;
    }

    public String getTipoFactor() {
        return TipoFactor;
    }

    public void setTipoFactor(String TipoFactor) {
        this.TipoFactor = TipoFactor;
    }

    public BigDecimal getImporte() {
        return Importe;
    }

    public void setImporte(BigDecimal Importe) {
        this.Importe = Importe;
    }

    public BigDecimal getTasaOCuota() {
        return TasaOCuota;
    }

    public void setTasaOCuota(BigDecimal TasaOCuota) {
        this.TasaOCuota = TasaOCuota;
    }
    
    
    
    
}
