package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.MetodoPago;

/**
 * MetodoPago de tipo EJB que implementa los metodos cargados de la interface de un MetodoPago
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class MetodoPagoDaoImpl implements MetodoPagoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Metodos de Pago de la base de datos
	 * @return una lista de FormasPago
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<MetodoPago> findAllMetodosPago() {
		return em.createNamedQuery("MetodoPago.findAll").getResultList();
	}

	/**
	 * Metodo para traer una MetodoPago a partir de un identificador
	 * @param MetodoPago a encontrar
	 * @return la MetodoPago encontrado
	 */
	@Override
	public MetodoPago findMetodoPagoByIdentifica(MetodoPago metodoPago) {
		return em.find(MetodoPago.class, metodoPago.getIdentifica());
	}

	/**
     * Metodo para insertar una MetodoPago a la base de datos
     * @param MetodoPago a insertar
     */
	@Override
	public void insertMetodoPago(MetodoPago metodoPago) {
		em.persist(metodoPago);

	}

	 /**
     * Metodo para actualizar un MetodoPago a la base de datos
     * @param MetodoPago a actualizar
     */
	@Override
	public void updateMetodoPago(MetodoPago metodoPago) {
		em.merge(metodoPago);

	}

	 /**
     * Metodo para eliminar un MetodoPago a la base de datos
     * @param metodoPago a eliminar
     */
	@Override
	public void deleteMetodoPago(MetodoPago metodoPago) {
		metodoPago = em.find(MetodoPago.class, metodoPago.getIdentifica());
		em.remove(metodoPago);

	}

}
