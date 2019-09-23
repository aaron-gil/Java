package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.TipoImpuesto;

/**
 * TipoImpuesto de tipo EJB que implementa los metodos cargados de la interface de un TipoImpuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class TipoImpuestoDaoImpl implements TipoImpuestoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los TipoImpuestos de la base de datos
	 * @return una lista de TipoImpuestos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<TipoImpuesto> findAllTipoImpuestos() {
		return em.createNamedQuery("TipoImpuesto.findAll").getResultList();
	}

	/**
	 * Metodo para traer un TipoImpuesto a partir de un identificador
	 * @param TipoImpuesto a encontrar
	 * @return el TipoImpuesto encontrado
	 */
	@Override
	public TipoImpuesto findTipoImpuestoByIdentifica(TipoImpuesto tipoImpuesto) {
		return em.find(TipoImpuesto.class, tipoImpuesto.getIdentifica());
	}

	/**
     * Metodo para insertar un TipoImpuesto a la base de datos
     * @param TipoImpuesto a insertar
     */
	@Override
	public void insertTipoImpuesto(TipoImpuesto tipoImpuesto) {
		em.persist(tipoImpuesto);

	}

	 /**
     * Metodo para actualizar un TipoImpuesto a la base de datos
     * @param TipoImpuesto a actualizar
     */
	@Override
	public void updateTipoImpuesto(TipoImpuesto tipoImpuesto) {
		em.merge(tipoImpuesto);

	}

	 /**
     * Metodo para eliminar un TipoImpuesto a la base de datos
     * @param tipoImpuesto a eliminar
     */
	@Override
	public void deleteTipoImpuesto(TipoImpuesto tipoImpuesto) {
		tipoImpuesto = em.find(TipoImpuesto.class, tipoImpuesto.getIdentifica());
		em.remove(tipoImpuesto);

	}
}
