package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.DetalleFactura;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un DetalleFactura
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class DetalleFacturaDaoImpl implements DetalleFacturaDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los DetalleFacturas de la base de datos
	 * @return una lista de DetalleFacturaes
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<DetalleFactura> findAllDetalleFacturas() {
		return em.createNamedQuery("DetalleFactura.findAll").getResultList();
	}

	/**
	 * Metodo para traer un DetalleFactura a partir de un identificador
	 * @param DetalleFactura a encontrar
	 * @return el DetalleFactura encontrado
	 */
	@Override
	public DetalleFactura findDetalleFacturaByIdentifica(DetalleFactura detalleFactura) {
		return em.find(DetalleFactura.class, detalleFactura.getIdentifica());
	}

	/**
     * Metodo para insertar un DetalleFactura a la base de datos
     * @param DetalleFactura a insertar
     */
	@Override
	public void insertDetalleFactura(DetalleFactura detalleFactura) {
		em.persist(detalleFactura);

	}

	 /**
     * Metodo para actualizar un DetalleFactura a la base de datos
     * @param DetalleFactura a actualizar
     */
	@Override
	public void updateDetalleFactura(DetalleFactura detalleFactura) {
		em.merge(detalleFactura);

	}

	 /**
     * Metodo para eliminar un DetalleFactura a la base de datos
     * @param DetalleFactura a eliminar
     */
	@Override
	public void deleteDetalleFactura(DetalleFactura detalleFactura) {
		detalleFactura = em.find(DetalleFactura.class, detalleFactura.getIdentifica());
		em.remove(detalleFactura);

	}

}
