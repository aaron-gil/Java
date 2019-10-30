/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.controller.UsuarioDao;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "indexV")
@ViewScoped
public class indexV implements Serializable {

    @EJB
    private UsuarioDao usuarioFL;
    private String usuario="";
    private String contrasena="";
    private Usuario usuarioSeleccionado;

    public indexV() {
    }

    public indexV(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @PostConstruct
    public void inicializar() {
        System.out.println("iniciando . . . . . ");
        usuarioSeleccionado= new Usuario();
        contrasena="";
        usuario="";
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioElegido");
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public String validarInicioSesion() {
        System.out.println("intento de ingresar " + usuario + "  " + contrasena);
        FacesContext context = FacesContext.getCurrentInstance();
        if (usuario.equals("")) {
            System.out.println("sin usuario");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","Debe ingresar un nombre de usuario "));
        } else if (contrasena.equals("")) {
            System.out.println("sin contrasena");
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","Debe ingresar un password"));
        } else {
            return iniciarSesion();
        }
        return null;
    }

    public String iniciarSesion() {
        System.out.println("verificando datos "+ usuario+" "+ contrasena);
        String redireccion = null;
        usuarioSeleccionado= new Usuario();
        try {
            usuarioSeleccionado=usuarioFL.buscarUsuarioYContrasena(usuario, contrasena);
            if (this.usuarioSeleccionado != null) {                
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioElegido", usuarioSeleccionado);
                redireccion = "inicio?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","Usuario o Password Incorrecto"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso","No hay conexion"));
        }
        return redireccion;
    }

}
