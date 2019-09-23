package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Cliente;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un cliente
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface ClienteDao {
	
	//Metodo abstractos
	
	public List<Cliente> findAllClientes(); 
	 
    public Cliente findClienteByIdentifica(Cliente cliente); 
 
    public void insertCliente(Cliente cliente); 
 
    public void updateCliente(Cliente cliente); 
 
    public void deleteCliente(Cliente cliente);
    
    public List<Cliente> listarClientesPorStatusYUsuario(boolean status,Usuario usuario);
    
    public List<Cliente> autocomplete(String dato, Usuario usuario);

}
