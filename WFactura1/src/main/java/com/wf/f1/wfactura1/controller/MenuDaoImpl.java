package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.Menu;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Menu
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class MenuDaoImpl implements MenuDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para encontrar todos los Menus registrados en la base de datos
	 * @return una lista de Menus
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Menu> findAllMenus() {
		return em.createNamedQuery("Menu.findAll").getResultList();
	}

	/**
	 * Metodo para encontrar un Menu basado en el identificador
	 * @param Menu a encontrar
	 * @return el Menu encontrado
	 */
	@Override
	public Menu findMenuByIdentifica(Menu menu) {
		return em.find(Menu.class, menu.getIdentifica());
	}
	
	/**
     * Metodo para insertar un Menu a la base de datos
     * @param Menu a insertar
     */
	@Override
	public void insertMenu(Menu menu) {
		em.persist(menu);

	}

	/**
     * Metodo para actualizar cualquier dato de un Menu a la base de datos
     * @param Menu a actualizar
     */
	@Override
	public void updateMenu(Menu menu) {
		em.merge(menu);

	}

    /**
     * Metodo para eliminar un Menu de la base de datos
     * @param Menu a eliminar
     */
	@Override
	public void deleteMenu(Menu menu) {
		menu = em.find(Menu.class, menu.getIdentifica());
		em.remove(menu);

	}
}
