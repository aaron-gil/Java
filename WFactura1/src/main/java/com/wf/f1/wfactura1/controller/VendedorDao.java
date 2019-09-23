package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Usuario;
import com.wf.f1.wfactura1.model.Vendedor; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Vendedor
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface VendedorDao {
	
	//Metodo abstractos
	
	public List<Vendedor> findAllVendedores(); 
	 
    public Vendedor findVendedorByIdentifica(Vendedor vendedor); 
 
    public void insertVendedor(Vendedor vendedor); 
 
    public void updateVendedor(Vendedor vendedor); 
 
    public void deleteVendedor(Vendedor vendedor);
    
    public List<Vendedor> listarVendedoresPorStatusYUsuario(boolean status,Usuario usuario);

}
