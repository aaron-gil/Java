package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Division;
import com.wf.f1.wfactura1.model.Tipo;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Division
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface DivisionDao {
	
	//Metodo abstractos
	public List<Division> findAllDivisiones(); 
	 
    public Division findDivisionByIdentifica(Division division); 
 
    public void insertDivision(Division division); 
 
    public void updateDivision(Division division); 
 
    public void deleteDivision(Division division);
    
    public List<Division> listarDivisionesPorTipo(Tipo tipo);
    
}
