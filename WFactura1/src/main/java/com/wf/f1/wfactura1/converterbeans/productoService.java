/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.converterbeans;

import com.wf.f1.wfactura1.controller.ProductoDao;
import com.wf.f1.wfactura1.model.Producto;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "productoService")
@ApplicationScoped
public class productoService implements Serializable{
    
    @EJB 
    private ProductoDao productoDao;
    
    private List<Producto> productos;
    private Usuario usuarioSeleccionado;

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
    
    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
        productos=productoDao.listarProductosPorStatusYUsuario(true, usuarioSeleccionado);
    }
    
    public Producto buscarProducto(Integer idSat){
        for(Producto prod: productos){            
            if(Objects.equals(prod.getIdentifica(), idSat)){               
                return prod;
            }
        }
        return null;
    }
}
