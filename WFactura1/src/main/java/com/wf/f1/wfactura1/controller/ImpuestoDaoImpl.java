package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.Impuesto;

/**
 * Impuesto de tipo EJB que implementa los metodos cargados de la interface de un Impuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class ImpuestoDaoImpl implements ImpuestoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Impuestos de la base de datos
	 * @return una lista de Impuestos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Impuesto> findAllImpuestos() {
		return em.createNamedQuery("Impuesto.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Impuesto a partir de un identificador
	 * @param Impuesto a encontrar
	 * @return el Impuesto encontrado
	 */
	@Override
	public Impuesto findImpuestoByIdentifica(Impuesto impuesto) {
		return em.find(Impuesto.class, impuesto.getIdentifica());
	}

	/**
     * Metodo para insertar un Impuesto a la base de datos
     * @param Impuesto a insertar
     */
	@Override
	public void insertImpuesto(Impuesto impuesto) {
		em.persist(impuesto);

	}

	 /**
     * Metodo para actualizar un Impuesto a la base de datos
     * @param Impuesto a actualizar
     */
	@Override
	public void updateImpuesto(Impuesto impuesto) {
		em.merge(impuesto);

	}

	 /**
     * Metodo para eliminar un Impuesto a la base de datos
     * @param impuesto a eliminar
     */
	@Override
	public void deleteImpuesto(Impuesto impuesto) {
		impuesto = em.find(Impuesto.class, impuesto.getIdentifica());
		em.remove(impuesto);

	}
}
