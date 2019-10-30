/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.model.Usuario;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "menuV")
@ViewScoped
public class menuV implements Serializable {

    private Usuario usuarioSeleccionado;

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    @PostConstruct
    public void inicializar() {
        usuarioSeleccionado = new Usuario();
        verificarSesion();

    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioSeleccionado == null) {
                context.getExternalContext().redirect("index.xhtml");
            }
        } catch (Exception localException) {
        }
    }

    public String cfdi() {
        System.out.println("ir a cfdi33");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioElegido", usuarioSeleccionado);
        return "CFDI33?faces-redirect=true";
    }

    public String principal() {
        System.out.println("ir a inicio");
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioElegido", usuarioSeleccionado);
        return "inicio?faces-redirect=true";
    }

    public String sessionC() {
        System.out.println("cerrar session");
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioElegido");
          FacesContext.getCurrentInstance().getExternalContext().getSessionMap().clear();
          FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return  "index?faces-redirect=true";

    }
}
