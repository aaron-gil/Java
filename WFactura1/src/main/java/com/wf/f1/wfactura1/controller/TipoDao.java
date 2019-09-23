package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Tipo; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Tipo
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface TipoDao {
	
	//Metodo abstractos
	public List<Tipo> findAllTipos(); 
	 
    public Tipo findTipoByIdentifica(Tipo tipo); 
 
    public void insertTipo(Tipo tipo); 
 
    public void updateTipo(Tipo tipo); 
 
    public void deleteTipo(Tipo tipo);
    
}
