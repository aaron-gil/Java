/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.beanext;

import com.wf.f1.wfactura1.model.Producto;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author WF Consulting
 */
public class conceptos {
    
    private Producto producto;
    private BigDecimal cantidad;
    private BigDecimal importe;
    private BigDecimal porcentaje;
    private BigDecimal descuento;
    private String descripcion;
    private List<impTrasladados> trasladados;
    private List<impRetenidos> retenidos;

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(BigDecimal porcentaje) {
        this.porcentaje = porcentaje;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<impTrasladados> getTrasladados() {
        return trasladados;
    }

    public void setTrasladados(List<impTrasladados> trasladados) {
        this.trasladados = trasladados;
    }

    public List<impRetenidos> getRetenidos() {
        return retenidos;
    }

    public void setRetenidos(List<impRetenidos> retenidos) {
        this.retenidos = retenidos;
    }
    
    public conceptos() {
    }
    
    
}
