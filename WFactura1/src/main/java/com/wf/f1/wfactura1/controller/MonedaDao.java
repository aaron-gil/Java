package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Moneda;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Moneda
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface MonedaDao {
	
	//Metodo abstractos
	public List<Moneda> findAllMonedas(); 
	 
    public Moneda findMonedaByIdentifica(Moneda moneda); 
 
    public void insertMoneda(Moneda moneda); 
 
    public void updateMoneda(Moneda moneda); 
 
    public void deleteMoneda(Moneda moneda);
    
}
