package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.TipoComprobante; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un TipoComprobante
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface TipoComprobanteDao {
	
	//Metodo abstractos
	public List<TipoComprobante> findAllTipoComprobantes(); 
	 
    public TipoComprobante findTipoComprobanteByIdentifica(TipoComprobante tipoComprobante); 
 
    public void insertTipoComprobante(TipoComprobante tipoComprobante); 
 
    public void updateTipoComprobante(TipoComprobante tipoComprobante); 
 
    public void deleteTipoComprobante(TipoComprobante tipoComprobante);
    
}
