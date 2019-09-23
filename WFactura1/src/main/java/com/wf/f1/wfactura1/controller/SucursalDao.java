package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Sucursal;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Sucursal
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface SucursalDao {
	
	//Metodo abstractos
	
	public List<Sucursal> findAllSucursales(); 
	 
    public Sucursal findSucursalByIdentifica(Sucursal sucursal); 
 
    public void insertSucursal(Sucursal sucursal); 
 
    public void updateSucursal(Sucursal sucursal); 
 
    public void deleteSucursal(Sucursal sucursal);
    
    public List<Sucursal> listarSucursalesPorStatusYUsuario(boolean status,Usuario usuario);

}
