package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.TipoFactor;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un TipoFactor
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface TipoFactorDao {
	
	//Metodo abstractos
	
	public List<TipoFactor> findAllTipoFactor(); 
	 
    public TipoFactor findTipoFactorByIdentifica(TipoFactor tipoFactor); 
 
    public void insertTipoFactor(TipoFactor tipoFactor); 
 
    public void updateTipoFactor(TipoFactor tipoFactor); 
 
    public void deleteTipoFactor(TipoFactor tipoFactor);
    
}
