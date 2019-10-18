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
public class ComplementoComExt {
    private String tipoOperacion;
    private String motivoTraslado;
    private String claveDePedimento;
    private String certificadoOrigen;
    private String numCertificadoOrigen;
    private String numeroExportacionConfiable;
    private String incoterm;
    private String subDivicion;
    private String observaciones;
    private String tipoCambioUSD;
    private String totalUSD;
    private String curp;
    private DomicilioCompComExt domicilioE;
    private List<PropietarioCompComExt> propietarios;
    private String numRegIdTribR;
    private DomicilioCompComExt domicilioR;
    private List<DestinatarioCompComExt> destinatarios;
    private List<MercanciasCompComExt> mercancias;

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getMotivoTraslado() {
        return motivoTraslado;
    }

    public void setMotivoTraslado(String motivoTraslado) {
        this.motivoTraslado = motivoTraslado;
    }

    public String getClaveDePedimento() {
        return claveDePedimento;
    }

    public void setClaveDePedimento(String claveDePedimento) {
        this.claveDePedimento = claveDePedimento;
    }

    public String getCertificadoOrigen() {
        return certificadoOrigen;
    }

    public void setCertificadoOrigen(String certificadoOrigen) {
        this.certificadoOrigen = certificadoOrigen;
    }

    public String getNumCertificadoOrigen() {
        return numCertificadoOrigen;
    }

    public void setNumCertificadoOrigen(String numCertificadoOrigen) {
        this.numCertificadoOrigen = numCertificadoOrigen;
    }

    public String getNumeroExportacionConfiable() {
        return numeroExportacionConfiable;
    }

    public void setNumeroExportacionConfiable(String numeroExportacionConfiable) {
        this.numeroExportacionConfiable = numeroExportacionConfiable;
    }

    public String getIncoterm() {
        return incoterm;
    }

    public void setIncoterm(String incoterm) {
        this.incoterm = incoterm;
    }

    public String getSubDivicion() {
        return subDivicion;
    }

    public void setSubDivicion(String subDivicion) {
        this.subDivicion = subDivicion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getTipoCambioUSD() {
        return tipoCambioUSD;
    }

    public void setTipoCambioUSD(String tipoCambioUSD) {
        this.tipoCambioUSD = tipoCambioUSD;
    }

    public String getTotalUSD() {
        return totalUSD;
    }

    public void setTotalUSD(String totalUSD) {
        this.totalUSD = totalUSD;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public DomicilioCompComExt getDomicilioE() {
        return domicilioE;
    }

    public void setDomicilioE(DomicilioCompComExt domicilioE) {
        this.domicilioE = domicilioE;
    }

    public List<PropietarioCompComExt> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(List<PropietarioCompComExt> propietarios) {
        this.propietarios = propietarios;
    }

    public String getNumRegIdTribR() {
        return numRegIdTribR;
    }

    public void setNumRegIdTribR(String numRegIdTribR) {
        this.numRegIdTribR = numRegIdTribR;
    }

    public DomicilioCompComExt getDomicilioR() {
        return domicilioR;
    }

    public void setDomicilioR(DomicilioCompComExt domicilioR) {
        this.domicilioR = domicilioR;
    }

    public List<DestinatarioCompComExt> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<DestinatarioCompComExt> destinatarios) {
        this.destinatarios = destinatarios;
    }

    public List<MercanciasCompComExt> getMercancias() {
        return mercancias;
    }

    public void setMercancias(List<MercanciasCompComExt> mercancias) {
        this.mercancias = mercancias;
    }
    
}
