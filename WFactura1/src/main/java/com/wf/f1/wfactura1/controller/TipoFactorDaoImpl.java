package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.TipoFactor;

/**
 * TipoFactor de tipo EJB que implementa los metodos cargados de la interface de un TipoFactor
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class TipoFactorDaoImpl implements TipoFactorDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los TipoFactor de la base de datos
	 * @return una lista de TipoFactors
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<TipoFactor> findAllTipoFactor() {
		return em.createNamedQuery("TipoFactor.findAll").getResultList();
	}

	/**
	 * Metodo para traer un TipoFactor a partir de un identificador
	 * @param TipoFactor a encontrar
	 * @return el TipoFactor encontrado
	 */
	@Override
	public TipoFactor findTipoFactorByIdentifica(TipoFactor tipoFactor) {
		return em.find(TipoFactor.class, tipoFactor.getIdentifica());
	}

	/**
     * Metodo para insertar un TipoFactor a la base de datos
     * @param TipoFactor a insertar
     */
	@Override
	public void insertTipoFactor(TipoFactor tipoFactor) {
		em.persist(tipoFactor);

	}

	 /**
     * Metodo para actualizar un TipoFactor a la base de datos
     * @param TipoFactor a actualizar
     */
	@Override
	public void updateTipoFactor(TipoFactor tipoFactor) {
		em.merge(tipoFactor);

	}

	 /**
     * Metodo para eliminar un TipoFactor a la base de datos
     * @param tipoFactor a eliminar
     */
	@Override
	public void deleteTipoFactor(TipoFactor tipoFactor) {
		tipoFactor = em.find(TipoFactor.class, tipoFactor.getIdentifica());
		em.remove(tipoFactor);

	}
}
