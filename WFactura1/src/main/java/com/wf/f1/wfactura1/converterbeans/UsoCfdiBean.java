/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.converterbeans;

/**
 *
 * @author WF Consulting
 */
public class UsoCfdiBean {
    
    private int id;
    private String identifica;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifica() {
        return identifica;
    }

    public void setIdentifica(String identifica) {
        this.identifica = identifica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public UsoCfdiBean(int id, String identifica, String descripcion) {
        this.id = id;
        this.identifica = identifica;
        this.descripcion = descripcion;
    }

    public UsoCfdiBean() {
    }

    @Override
    public String toString() {
        return id +"";
    }

    
    
}
