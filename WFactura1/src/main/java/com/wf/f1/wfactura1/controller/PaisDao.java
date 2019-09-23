package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Pais;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Pais
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface PaisDao {
	
	//Metodo abstractos
	public List<Pais> findAllPaises(); 
	 
    public Pais findPaisByIdentifica(Pais pais); 
 
    public void insertPais(Pais pais); 
 
    public void updatePais(Pais pais); 
 
    public void deletePais(Pais pais);
    
}
