package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.TasaImpuesto;
import com.wf.f1.wfactura1.model.TipoImpuesto;

/**
 * TasaImpuesto de tipo EJB que implementa los metodos cargados de la interface de un TasaImpuesto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class TasaImpuestoDaoImpl implements TasaImpuestoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los TasaImpuestos de la base de datos
	 * @return una lista de TasaImpuestos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<TasaImpuesto> findAllTasaImpuestos() {
		return em.createNamedQuery("TasaImpuesto.findAll").getResultList();
	}

	/**
	 * Metodo para traer un TasaImpuesto a partir de un identificador
	 * @param TasaImpuesto a encontrar
	 * @return el TasaImpuesto encontrado
	 */
	@Override
	public TasaImpuesto findTasaImpuestoByIdentifica(TasaImpuesto tasaImpuesto) {
		return em.find(TasaImpuesto.class, tasaImpuesto.getIdentifica());
	}

	/**
     * Metodo para insertar un TasaImpuesto a la base de datos
     * @param TasaImpuesto a insertar
     */
	@Override
	public void insertTasaImpuesto(TasaImpuesto tasaImpuesto) {
		em.persist(tasaImpuesto);

	}

	 /**
     * Metodo para actualizar un TasaImpuesto a la base de datos
     * @param TasaImpuesto a actualizar
     */
	@Override
	public void updateTasaImpuesto(TasaImpuesto tasaImpuesto) {
		em.merge(tasaImpuesto);

	}

	 /**
     * Metodo para eliminar un TasaImpuesto a la base de datos
     * @param tasaImpuesto a eliminar
     */
	@Override
	public void deleteTasaImpuesto(TasaImpuesto tasaImpuesto) {
		tasaImpuesto = em.find(TasaImpuesto.class, tasaImpuesto.getIdentifica());
		em.remove(tasaImpuesto);

	}
	
	/**
     * Metodo que permite listar las TasaImpuestos por tipo de impuesto
     * @param tipoImpuesto
     * @param traslado
     * @return una lista de TasaImpuestos
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<TasaImpuesto> listarTasaImpuestosPorTipoImpuestoYTraslado(TipoImpuesto tipoImpuesto,boolean traslado){
		List<TasaImpuesto> tasaImpuestos = null;
		try{
			Query query = em.createQuery("from TasaImpuesto t where t.tipoImpuesto =:tipoImpuesto and t.traslado =:traslado");
			query.setParameter("tipoImpuesto", tipoImpuesto);
			query.setParameter("traslado", traslado);
			tasaImpuestos = query.getResultList();
			System.out.println(tasaImpuestos);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tasaImpuestos;
	}
	
	/**
     * Metodo que permite listar las TasaImpuestos por tipo de impuesto y retencion
     * @param tipoImpuesto
     * @param retencion
     * @return una lista de TasaImpuestos
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<TasaImpuesto> listarTasaImpuestosPorTipoImpuestoYRetencion(TipoImpuesto tipoImpuesto,boolean retencion){
		List<TasaImpuesto> tasaImpuestos = null;
		try{
			Query query = em.createQuery("from TasaImpuesto t where t.tipoImpuesto =:tipoImpuesto and t.retencion =:retencion");
			query.setParameter("tipoImpuesto", tipoImpuesto);
			query.setParameter("retencion", retencion);
			tasaImpuestos = query.getResultList();
			System.out.println(tasaImpuestos);
		}catch(Exception e){
			e.printStackTrace();
		}
		return tasaImpuestos;
	}
}
