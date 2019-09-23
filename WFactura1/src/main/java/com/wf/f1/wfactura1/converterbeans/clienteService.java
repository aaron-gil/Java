/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.converterbeans;

import com.wf.f1.wfactura1.controller.ClienteDao;
import com.wf.f1.wfactura1.model.Cliente;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "clienteService")
@ApplicationScoped
public class clienteService implements Serializable{
    
    @EJB
    private ClienteDao clienteDao;
    private List<Cliente> clientes;
    private Usuario usuarioSeleccionado;

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
        clientes= new ArrayList<Cliente>();
        clientes=clienteDao.listarClientesPorStatusYUsuario(true, usuarioSeleccionado);        
    }
    
    public Cliente buscarCliente(Integer id){
        
        for(Cliente c : clientes){
            if(c.getIdentifica()==id){
                return c;
            }
        }
        return null;
        
    }
}
