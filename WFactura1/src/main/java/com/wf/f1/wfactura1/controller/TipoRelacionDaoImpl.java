package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.TipoRelacion;

/**
 * TipoRelacion de tipo EJB que implementa los metodos cargados de la interface de un TipoRelacion
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class TipoRelacionDaoImpl implements TipoRelacionDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los TipoRelacions de la base de datos
	 * @return una lista de TipoRelacions
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<TipoRelacion> findAllTipoRelaciones() {
		return em.createNamedQuery("TipoRelacion.findAll").getResultList();
	}

	/**
	 * Metodo para traer un TipoRelacion a partir de un identificador
	 * @param TipoRelacion a encontrar
	 * @return el TipoRelacion encontrado
	 */
	@Override
	public TipoRelacion findTipoRelacionByIdentifica(TipoRelacion tipoRelacion) {
		return em.find(TipoRelacion.class, tipoRelacion.getIdentifica());
	}

	/**
     * Metodo para insertar un TipoRelacion a la base de datos
     * @param TipoRelacion a insertar
     */
	@Override
	public void insertTipoRelacion(TipoRelacion tipoRelacion) {
		em.persist(tipoRelacion);

	}

	 /**
     * Metodo para actualizar un TipoRelacion a la base de datos
     * @param TipoRelacion a actualizar
     */
	@Override
	public void updateTipoRelacion(TipoRelacion tipoRelacion) {
		em.merge(tipoRelacion);

	}

	 /**
     * Metodo para eliminar un TipoRelacion a la base de datos
     * @param tipoRelacion a eliminar
     */
	@Override
	public void deleteTipoRelacion(TipoRelacion tipoRelacion) {
		tipoRelacion = em.find(TipoRelacion.class, tipoRelacion.getIdentifica());
		em.remove(tipoRelacion);

	}
}
