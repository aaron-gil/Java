/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.beanext;

import java.util.List;

/**
 *
 * @author WF Consulting
 */
public class MercanciasCompComExt {
    private String noIdentificacion;
    private String fraccionArancelaria;
    private String cantidadAduana;
    private String unidadAduana;
    private String valorUnitarioAduana;
    private String valorDolares;
    private List<DescEspecCompComExt> descEspec;

    public String getNoIdentificacion() {
        return noIdentificacion;
    }

    public void setNoIdentificacion(String noIdentificacion) {
        this.noIdentificacion = noIdentificacion;
    }

    public String getFraccionArancelaria() {
        return fraccionArancelaria;
    }

    public void setFraccionArancelaria(String fraccionArancelaria) {
        this.fraccionArancelaria = fraccionArancelaria;
    }

    public String getCantidadAduana() {
        return cantidadAduana;
    }

    public void setCantidadAduana(String cantidadAduana) {
        this.cantidadAduana = cantidadAduana;
    }

    public String getUnidadAduana() {
        return unidadAduana;
    }

    public void setUnidadAduana(String unidadAduana) {
        this.unidadAduana = unidadAduana;
    }

    public String getValorUnitarioAduana() {
        return valorUnitarioAduana;
    }

    public void setValorUnitarioAduana(String valorUnitarioAduana) {
        this.valorUnitarioAduana = valorUnitarioAduana;
    }

    public String getValorDolares() {
        return valorDolares;
    }

    public void setValorDolares(String valorDolares) {
        this.valorDolares = valorDolares;
    }

    public List<DescEspecCompComExt> getDescEspec() {
        return descEspec;
    }

    public void setDescEspec(List<DescEspecCompComExt> descEspec) {
        this.descEspec = descEspec;
    }
    
    
}
