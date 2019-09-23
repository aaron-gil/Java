package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.Tipo;

/**
 * Tipo de tipo EJB que implementa los metodos cargados de la interface de un Tipo
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class TipoDaoImpl implements TipoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Tipos de la base de datos
	 * @return una lista de Tipos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Tipo> findAllTipos() {
		return em.createNamedQuery("Tipo.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Tipo a partir de un identificador
	 * @param tipo a encontrar
	 * @return el Tipo encontrado
	 */
	@Override
	public Tipo findTipoByIdentifica(Tipo tipo) {
		return em.find(Tipo.class, tipo.getIdentifica());
	}

	/**
     * Metodo para insertar un Tipo a la base de datos
     * @param Tipo a insertar
     */
	@Override
	public void insertTipo(Tipo tipo) {
		em.persist(tipo);

	}

	 /**
     * Metodo para actualizar un Tipo a la base de datos
     * @param Tipo a actualizar
     */
	@Override
	public void updateTipo(Tipo tipo) {
		em.merge(tipo);

	}

	 /**
     * Metodo para eliminar un Tipo a la base de datos
     * @param Tipo a eliminar
     */
	@Override
	public void deleteTipo(Tipo tipo) {
		tipo = em.find(Tipo.class, tipo.getIdentifica());
		em.remove(tipo);

	}
}
