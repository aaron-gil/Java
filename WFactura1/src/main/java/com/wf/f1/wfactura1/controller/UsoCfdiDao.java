package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.converterbeans.UsoCfdiBean;
import java.util.List;

import com.wf.f1.wfactura1.model.UsoCfdi;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un UsoCfdi
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface UsoCfdiDao {
	
	//Metodo abstractos
	public List<UsoCfdi> findAllUsoCfdies(); 
	 
    public UsoCfdi findUsoCfdiByIdentifica(UsoCfdi usoCfdi); 
 
    public void insertUsoCfdi(UsoCfdi usoCfdi); 
 
    public void updateUsoCfdi(UsoCfdi usoCfdi); 
 
    public void deleteUsoCfdi(UsoCfdi usoCfdi);    
    
    public UsoCfdiBean buscardato(Integer dato);
    
    public List<UsoCfdiBean> buscarUsos();
    
}
