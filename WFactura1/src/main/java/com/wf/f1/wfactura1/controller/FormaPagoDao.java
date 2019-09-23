package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.FormaPago;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un FormaPago
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface FormaPagoDao {
	
	//Metodo abstractos
	public List<FormaPago> findAllFormasPago(); 
	 
    public FormaPago findFormaPagoByIdentifica(FormaPago formaPago); 
 
    public void insertFormaPago(FormaPago formaPago); 
 
    public void updateFormaPago(FormaPago formaPago); 
 
    public void deleteFormaPago(FormaPago formaPago);
    
}
