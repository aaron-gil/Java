package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.RegimenFiscal; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un RegimenFiscal
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface RegimenFiscalDao {
	
	//Metodo abstractos
	public List<RegimenFiscal> findAllRegimenesFiscal(); 
	 
    public RegimenFiscal findRegimenFiscalByIdentifica(RegimenFiscal regimenFiscal); 
 
    public void insertRegimenFiscal(RegimenFiscal regimenFiscal); 
 
    public void updateRegimenFiscal(RegimenFiscal regimenFiscal); 
 
    public void deleteRegimenFiscal(RegimenFiscal regimenFiscal);
    
}
