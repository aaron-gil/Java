package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Empresa;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de una Empresa
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface EmpresaDao {
	
	//Metodo abstractos
	public List<Empresa> findAllEmpresas(); 
	 
    public Empresa findEmpresaByIdentifica(Empresa empresa); 
 
    public void insertEmpresa(Empresa empresa); 
 
    public void updateEmpresa(Empresa empresa); 
 
    public void deleteEmpresa(Empresa empresa);
    
    public List<Empresa> listarEmpresasPorStatusYUsuario(boolean status,Usuario usuario);

}
