package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Clase;
import com.wf.f1.wfactura1.model.DescripcionClase;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de una descripcion Clase
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface DescripcionClaseDao {
	
	//Metodo abstractos
	
	public List<DescripcionClase> findAllDescripcionClases(); 
	 
    public DescripcionClase findDescripcionClaseByIdentifica(DescripcionClase descripcionClase); 
 
    public void insertDescripcionClase(DescripcionClase descripcionClase); 
 
    public void updateDescripcionClase(DescripcionClase descripcionClase); 
 
    public void deleteDescripcionClase(DescripcionClase descripcionClase);
    
    public List<DescripcionClase> listarDescripcionClasesPorClase(Clase clase);
    
}
