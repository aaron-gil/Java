package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Perfil;
import com.wf.f1.wfactura1.model.Permiso;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un permiso
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface PermisoDao {
	
	//Metodo abstractos
	public List<Permiso> findAllPermisos(); 
	 
    public Permiso findPermisoByIdentifica(Permiso permiso); 
 
    public void insertPermiso(Permiso permiso); 
 
    public void updatePermiso(Permiso permiso); 
 
    public void deletePermiso(Permiso permiso);
    
    public List<Permiso> encontrarPermisosMenuPorPerfil(Usuario usuario);
    
    public List<Permiso> encontrarPermisosPorPerfil(Perfil perfil);

}
