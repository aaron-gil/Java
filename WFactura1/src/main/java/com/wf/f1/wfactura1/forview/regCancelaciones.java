/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.controller.FacturaDao;
import com.wf.f1.wfactura1.model.Factura;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Fernando
 */

@ManagedBean(name = "regCancelaciones")
@ViewScoped
public class regCancelaciones implements Serializable{
        @EJB
    private FacturaDao facturaDao;
    private List<Factura> facturas33;
    private Usuario usuarioSeleccionado;

    public FacturaDao getFacturaDao() {
        return facturaDao;
    }

    public void setFacturaDao(FacturaDao facturaDao) {
        this.facturaDao = facturaDao;
    }

    public List<Factura> getFacturas33() {
        return facturas33;
    }

    public void setFacturas33(List<Factura> facturas33) {
        this.facturas33 = facturas33;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    @PostConstruct
    public void inicializar() {
        verificarSesion();
    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioSeleccionado == null) {
                context.getExternalContext().redirect("index.xhtml");
            } else {
                buscarcanceladas();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buscarcanceladas() {
        facturas33 = facturaDao.buscarcanceladas(usuarioSeleccionado);
    }
    public void cancelacion(Factura f) {

        
    }
    
}
