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
public class DestinatarioCompComExt {
    private String numRegIdTrib;
    private String nombre;
    private List<DomicilioCompComExt> domicilio;

    public String getNumRegIdTrib() {
        return numRegIdTrib;
    }

    public void setNumRegIdTrib(String numRegIdTrib) {
        this.numRegIdTrib = numRegIdTrib;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<DomicilioCompComExt> getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(List<DomicilioCompComExt> domicilio) {
        this.domicilio = domicilio;
    }
    
}
