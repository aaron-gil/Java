package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Clase;
import com.wf.f1.wfactura1.model.Grupo;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Clase
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface ClaseDao {
	
	//Metodo abstractos
	
	public List<Clase> findAllClases(); 
	 
    public Clase findClaseByIdentifica(Clase clase); 
 
    public void insertClase(Clase clase); 
 
    public void updateClase(Clase clase); 
 
    public void deleteClase(Clase clase);
    
    public List<Clase> listarClasesPorGrupo(Grupo grupo);
    
}
