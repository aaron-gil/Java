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
public class ImpuestoRetenidoHijoTotal {
   private String Impuesto;
    private String TipoFactor;
    private BigDecimal Importe;
    private BigDecimal TasaOCuota; 
    private String llavePrimaria;

    public ImpuestoRetenidoHijoTotal() {
    }

   
    
    
    
    public ImpuestoRetenidoHijoTotal(String Impuesto, String TipoFactor, BigDecimal Importe, BigDecimal TasaOCuota, String llavePrimaria) {
        this.Impuesto = Impuesto;
        this.TipoFactor = TipoFactor;
        this.Importe = Importe;
        this.TasaOCuota = TasaOCuota;
        this.llavePrimaria = llavePrimaria;
    }

    public String getLlavePrimaria() {
        return llavePrimaria;
    }

    public void setLlavePrimaria(String llavePrimaria) {
        this.llavePrimaria = llavePrimaria;
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

    public BigDecimal getTasaOCuota() {
        return TasaOCuota;
    }

    public void setTasaOCuota(BigDecimal TasaOCuota) {
        this.TasaOCuota = TasaOCuota;
    }

    public BigDecimal getImporte() {
        return Importe;
    }

    public void setImporte(BigDecimal Importe) {
        this.Importe = Importe;
    }
    
    
    
    
}
