package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Division;
import com.wf.f1.wfactura1.model.Grupo;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Grupo
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface GrupoDao {
	
	//Metodo abstractos
	
	public List<Grupo> findAllGrupos(); 
	 
    public Grupo findGrupoByIdentifica(Grupo Grupo); 
 
    public void insertGrupo(Grupo Grupo); 
 
    public void updateGrupo(Grupo Grupo); 
 
    public void deleteGrupo(Grupo Grupo);
    
    public List<Grupo> listarGruposPorDivision(Division division);
    
}
