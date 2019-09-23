package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.Moneda;

/**
 * Moneda de tipo EJB que implementa los metodos cargados de la interface de un Moneda
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class MonedaDaoImpl implements MonedaDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Monedas de la base de datos
	 * @return una lista de Monedas
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Moneda> findAllMonedas() {
		return em.createNamedQuery("Moneda.findAll").getResultList();
	}

	/**
	 * Metodo para traer una Moneda a partir de un identificador
	 * @param Moneda a encontrar
	 * @return la Moneda encontrado
	 */
	@Override
	public Moneda findMonedaByIdentifica(Moneda moneda) {
		return em.find(Moneda.class, moneda.getIdentifica());
	}

	/**
     * Metodo para insertar una Moneda a la base de datos
     * @param Moneda a insertar
     */
	@Override
	public void insertMoneda(Moneda moneda) {
		em.persist(moneda);

	}

	 /**
     * Metodo para actualizar un Moneda a la base de datos
     * @param Moneda a actualizar
     */
	@Override
	public void updateMoneda(Moneda moneda) {
		em.merge(moneda);

	}

	 /**
     * Metodo para eliminar un Moneda a la base de datos
     * @param Moneda a eliminar
     */
	@Override
	public void deleteMoneda(Moneda moneda) {
		moneda = em.find(Moneda.class, moneda.getIdentifica());
		em.remove(moneda);

	}

}
