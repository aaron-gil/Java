package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.TasaImpuesto;
import com.wf.f1.wfactura1.model.TipoImpuesto;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un TasaImpuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface TasaImpuestoDao {
	
	//Metodo abstractos
	
	public List<TasaImpuesto> findAllTasaImpuestos(); 
	 
    public TasaImpuesto findTasaImpuestoByIdentifica(TasaImpuesto tasaImpuesto); 
 
    public void insertTasaImpuesto(TasaImpuesto tasaImpuesto); 
 
    public void updateTasaImpuesto(TasaImpuesto tasaImpuesto); 
 
    public void deleteTasaImpuesto(TasaImpuesto tasaImpuesto);
    
    public List<TasaImpuesto> listarTasaImpuestosPorTipoImpuestoYTraslado(TipoImpuesto tipoImpuesto,boolean traslado);
    
    public List<TasaImpuesto> listarTasaImpuestosPorTipoImpuestoYRetencion(TipoImpuesto tipoImpuesto,boolean retencion);
    
}
