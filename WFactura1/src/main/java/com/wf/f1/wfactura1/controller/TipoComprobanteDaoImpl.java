package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.TipoComprobante;

/**
 * TipoComprobante de TipoComprobante EJB que implementa los metodos cargados de la interface de un TipoComprobante
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class TipoComprobanteDaoImpl implements TipoComprobanteDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los TipoComprobantes de la base de datos
	 * @return una lista de TipoComprobantes
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<TipoComprobante> findAllTipoComprobantes() {
		return em.createNamedQuery("TipoComprobante.findAll").getResultList();
	}

	/**
	 * Metodo para traer un TipoComprobante a partir de un identificador
	 * @param TipoComprobante a encontrar
	 * @return el TipoComprobante encontrado
	 */
	@Override
	public TipoComprobante findTipoComprobanteByIdentifica(TipoComprobante tipoComprobante) {
		return em.find(TipoComprobante.class, tipoComprobante.getIdentifica());
	}

	/**
     * Metodo para insertar un TipoComprobante a la base de datos
     * @param TipoComprobante a insertar
     */
	@Override
	public void insertTipoComprobante(TipoComprobante tipoComprobante) {
		em.persist(tipoComprobante);

	}

	 /**
     * Metodo para actualizar un TipoComprobante a la base de datos
     * @param TipoComprobante a actualizar
     */
	@Override
	public void updateTipoComprobante(TipoComprobante tipoComprobante) {
		em.merge(tipoComprobante);

	}

	 /**
     * Metodo para eliminar un TipoComprobante a la base de datos
     * @param TipoComprobante a eliminar
     */
	@Override
	public void deleteTipoComprobante(TipoComprobante tipoComprobante) {
		tipoComprobante = em.find(TipoComprobante.class, tipoComprobante.getIdentifica());
		em.remove(tipoComprobante);

	}
}
