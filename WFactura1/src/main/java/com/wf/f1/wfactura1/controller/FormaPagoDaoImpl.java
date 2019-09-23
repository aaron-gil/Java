package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.FormaPago;

/**
 * FormaPago de tipo EJB que implementa los metodos cargados de la interface de un FormaPago
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class FormaPagoDaoImpl implements FormaPagoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los FormasPago de la base de datos
	 * @return una lista de FormasPago
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<FormaPago> findAllFormasPago() {
		return em.createNamedQuery("FormaPago.findAll").getResultList();
	}

	/**
	 * Metodo para traer una FormaPago a partir de un identificador
	 * @param FormaPago a encontrar
	 * @return la FormaPago encontrado
	 */
	@Override
	public FormaPago findFormaPagoByIdentifica(FormaPago formaPago) {
		return em.find(FormaPago.class, formaPago.getIdentifica());
	}

	/**
     * Metodo para insertar una FormaPago a la base de datos
     * @param FormaPago a insertar
     */
	@Override
	public void insertFormaPago(FormaPago formaPago) {
		em.persist(formaPago);

	}

	 /**
     * Metodo para actualizar un FormaPago a la base de datos
     * @param FormaPago a actualizar
     */
	@Override
	public void updateFormaPago(FormaPago formaPago) {
		em.merge(formaPago);

	}

	 /**
     * Metodo para eliminar un FormaPago a la base de datos
     * @param formaPago a eliminar
     */
	@Override
	public void deleteFormaPago(FormaPago formaPago) {
		formaPago = em.find(FormaPago.class, formaPago.getIdentifica());
		em.remove(formaPago);

	}

}
