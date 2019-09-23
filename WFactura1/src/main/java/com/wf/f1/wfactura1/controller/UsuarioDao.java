package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un usuario
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface UsuarioDao {
	
	//Metodo abstractos
	
	public List<Usuario> findAllUsuarios(); 
	 
    public Usuario findUsuarioByIdentifica(Usuario usuario); 
 
    public void insertUsuario(Usuario usuario); 
 
    public void updateUsuario(Usuario usuario); 
 
    public void deleteUsuario(Usuario usuario);
    
    public Usuario encontrarUsuarioPorNombreYPassword(Usuario usuario);
    
    public List<Usuario> listarUsuariosPorStatusYUsuario(boolean status,Usuario usuario);
    
    public Usuario buscarUsuarioYContrasena(String nombre, String contrasena);

}
