package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Perfil;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Perfil de usuario
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface PerfilDao {
	
	//Metodo abstractos
	public List<Perfil> findAllPerfiles(); 
	 
    public Perfil findPerfilByIdentifica(Perfil perfil); 
 
    public void insertPerfil(Perfil perfil); 
 
    public void updatePerfil(Perfil perfil); 
 
    public void deletePerfil(Perfil perfil);
    
    public List<Perfil> listarPerfilesPorStatusYUsuario(boolean status,Usuario usuario);

}
