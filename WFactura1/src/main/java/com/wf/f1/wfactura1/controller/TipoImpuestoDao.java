package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.TipoImpuesto;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un TipoImpuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface TipoImpuestoDao {
	
	//Metodo abstractos
	
	public List<TipoImpuesto> findAllTipoImpuestos(); 
	 
    public TipoImpuesto findTipoImpuestoByIdentifica(TipoImpuesto tipoImpuesto); 
 
    public void insertTipoImpuesto(TipoImpuesto tipoImpuesto); 
 
    public void updateTipoImpuesto(TipoImpuesto tipoImpuesto); 
 
    public void deleteTipoImpuesto(TipoImpuesto tipoImpuesto);
    
}
