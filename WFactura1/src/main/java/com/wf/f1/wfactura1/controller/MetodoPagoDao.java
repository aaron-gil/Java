package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.MetodoPago;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un MetodoPago
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface MetodoPagoDao {
	
	//Metodo abstractos
	public List<MetodoPago> findAllMetodosPago(); 
	 
    public MetodoPago findMetodoPagoByIdentifica(MetodoPago metodoPago); 
 
    public void insertMetodoPago(MetodoPago metodoPago); 
 
    public void updateMetodoPago(MetodoPago metodoPago); 
 
    public void deleteMetodoPago(MetodoPago metodoPago);
    
}
