package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Medida;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Medida
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface MedidaDao {
	
	//Metodo abstractos
	
	public List<Medida> findAllMedidas(); 
	 
    public Medida findMedidaByIdentifica(Medida medida); 
 
    public void insertMedida(Medida medida); 
 
    public void updateMedida(Medida medida); 
 
    public void deleteMedida(Medida medida); 
    
    public List<Medida> listarMedidasComunes();
}
