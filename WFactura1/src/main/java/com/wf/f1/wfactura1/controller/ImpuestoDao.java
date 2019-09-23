package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Impuesto;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Impuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface ImpuestoDao {
	
	//Metodo abstractos
	
	public List<Impuesto> findAllImpuestos(); 
	 
    public Impuesto findImpuestoByIdentifica(Impuesto impuesto); 
 
    public void insertImpuesto(Impuesto impuesto); 
 
    public void updateImpuesto(Impuesto impuesto); 
 
    public void deleteImpuesto(Impuesto impuesto);
    
}
