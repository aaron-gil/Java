package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.RegimenFiscal;

/**
 * RegimenFiscal de RegimenFiscal EJB que implementa los metodos cargados de la interface de un RegimenFiscal
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class RegimenFiscalDaoImpl implements RegimenFiscalDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los RegimenesFiscal de la base de datos
	 * @return una lista de RegimenFiscals
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<RegimenFiscal> findAllRegimenesFiscal() {
		return em.createNamedQuery("RegimenFiscal.findAll").getResultList();
	}

	/**
	 * Metodo para traer un RegimenFiscal a partir de un identificador
	 * @param RegimenFiscal a encontrar
	 * @return el RegimenFiscal encontrado
	 */
	@Override
	public RegimenFiscal findRegimenFiscalByIdentifica(RegimenFiscal regimenFiscal) {
		return em.find(RegimenFiscal.class, regimenFiscal.getIdentifica());
	}

	/**
     * Metodo para insertar un RegimenFiscal a la base de datos
     * @param RegimenFiscal a insertar
     */
	@Override
	public void insertRegimenFiscal(RegimenFiscal regimenFiscal) {
		em.persist(regimenFiscal);

	}

	 /**
     * Metodo para actualizar un RegimenFiscal a la base de datos
     * @param RegimenFiscal a actualizar
     */
	@Override
	public void updateRegimenFiscal(RegimenFiscal regimenFiscal) {
		em.merge(regimenFiscal);

	}

	 /**
     * Metodo para eliminar un RegimenFiscal a la base de datos
     * @param RegimenFiscal a eliminar
     */
	@Override
	public void deleteRegimenFiscal(RegimenFiscal regimenFiscal) {
		regimenFiscal = em.find(RegimenFiscal.class, regimenFiscal.getIdentifica());
		em.remove(regimenFiscal);

	}
}
