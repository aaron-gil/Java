package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Menu; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Menu
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface MenuDao {
	
	//Metodo abstractos
	public List<Menu> findAllMenus(); 
	 
    public Menu findMenuByIdentifica(Menu menu); 
 
    public void insertMenu(Menu menu); 
 
    public void updateMenu(Menu menu); 
 
    public void deleteMenu(Menu menu);


}
