package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.TipoRelacion;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un TipoRelacion
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface TipoRelacionDao {
	
	//Metodo abstractos
	
	public List<TipoRelacion> findAllTipoRelaciones(); 
	 
    public TipoRelacion findTipoRelacionByIdentifica(TipoRelacion tipoRelacion); 
 
    public void insertTipoRelacion(TipoRelacion tipoRelacion); 
 
    public void updateTipoRelacion(TipoRelacion tipoRelacion); 
 
    public void deleteTipoRelacion(TipoRelacion tipoRelacion);
    
    
}
