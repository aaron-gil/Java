package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.TipoComprobante;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Serie
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class SerieDaoImpl implements SerieDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Seriees de la base de datos
	 * @return una lista de Seriees
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Serie> findAllSeries() {
		return em.createNamedQuery("Serie.findAll").getResultList();
	}

	/**
	 * Metodo para traer una Serie a partir de un identificador
	 * @param Serie a encontrar
	 * @return el Serie encontrado
	 */
	@Override
	public Serie findSerieByIdentifica(Serie serie) {
		return em.find(Serie.class, serie.getIdentifica());
	}

	/**
     * Metodo para insertar una Serie a la base de datos
     * @param Serie a insertar
     */
	@Override
	public void insertSerie(Serie serie) {
		em.persist(serie);

	}

	 /**
     * Metodo para actualizar una Serie a la base de datos
     * @param Serie a actualizar
     */
	@Override
	public void updateSerie(Serie serie) {
		em.merge(serie);

	}

	 /**
     * Metodo para eliminar una Serie a la base de datos
     * @param Serie a eliminar
     */
	@Override
	public void deleteSerie(Serie serie) {
		serie = em.find(Serie.class, serie.getIdentifica());
		em.remove(serie);

	}
	
	/**
     * Metodo para listar todas las series que tengan un determinado status desde la BD
     * @param status estado de actividad de la serie
     * @return una lista de series
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Serie> listarSeriesPorUsuario(Usuario usuario){
		List<Serie> series = null;
		try{
			Query query = em.createQuery("from Serie s where s.responsableCreacion =:nombre");
			query.setParameter("nombre", usuario.getNombre());
			series = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return series;
	}
	
	/**
     * Metodo que permite listar las series por tipo de comprobante
     * @param tipoComprobante corresponde al tipo de comprobante
     * @return una lista de series
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Serie> listarSeriesPorTipoComprobanteYUsuario(TipoComprobante tipoComprobante,Usuario usuario){
		List<Serie> series = null;
		try{
			Query query = em.createQuery("from Serie s where s.tipoComprobante =:tipoComprobante and s.responsableCreacion =:nombre");
			query.setParameter("tipoComprobante", tipoComprobante);
			query.setParameter("nombre", usuario.getNombre());
			series = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return series;
	}
}
