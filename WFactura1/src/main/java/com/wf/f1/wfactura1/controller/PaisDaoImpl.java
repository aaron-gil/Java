package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.Pais;

/**
 * Pais de tipo EJB que implementa los metodos cargados de la interface de un Pais
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class PaisDaoImpl implements PaisDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los paises de la base de datos
	 * @return una lista de paises
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Pais> findAllPaises() {
		return em.createNamedQuery("Pais.findAll").getResultList();
	}

	/**
	 * Metodo para traer una Pais a partir de un identificador
	 * @param Pais a encontrar
	 * @return la Pais encontrado
	 */
	@Override
	public Pais findPaisByIdentifica(Pais pais) {
		return em.find(Pais.class, pais.getIdentifica());
	}

	/**
     * Metodo para insertar una Pais a la base de datos
     * @param Pais a insertar
     */
	@Override
	public void insertPais(Pais pais) {
		em.persist(pais);

	}

	 /**
     * Metodo para actualizar un Pais a la base de datos
     * @param Pais a actualizar
     */
	@Override
	public void updatePais(Pais pais) {
		em.merge(pais);

	}

	 /**
     * Metodo para eliminar un Pais a la base de datos
     * @param pais a eliminar
     */
	@Override
	public void deletePais(Pais pais) {
		pais = em.find(Pais.class, pais.getIdentifica());
		em.remove(pais);

	}

}
