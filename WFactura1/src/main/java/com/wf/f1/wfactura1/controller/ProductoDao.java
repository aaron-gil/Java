package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Producto;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Producto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface ProductoDao {
	
	//Metodo abstractos
	
	public List<Producto> findAllProductos(); 
	 
    public Producto findProductoByIdentifica(Producto producto); 
 
    public void insertProducto(Producto producto); 
 
    public void updateProducto(Producto producto); 
 
    public void deleteProducto(Producto producto);
    
    public List<Producto> listarProductosPorStatusYUsuario(boolean status,Usuario usuario);
    
    public Producto encontrarSiExisteIdentificador(String identificador);
}
