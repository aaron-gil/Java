package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Clase;
import com.wf.f1.wfactura1.model.DescripcionClase;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Clase
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class DescripcionClaseDaoImpl implements DescripcionClaseDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos las descripciones Clases de la base de datos
	 * @return una lista de descripcion clase
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<DescripcionClase> findAllDescripcionClases() {
		return em.createNamedQuery("DescripcionClase.findAll").getResultList();
	}

	/**
	 * Metodo para traer un descripcion clase a partir de un identificador
	 * @param descripcionClase a encontrar
	 * @return el descripcionClase encontrado
	 */
	@Override
	public DescripcionClase findDescripcionClaseByIdentifica(DescripcionClase descripcionClase) {
		return em.find(DescripcionClase.class, descripcionClase.getIdentifica());
	}

	/**
     * Metodo para insertar una descripcion Clase a la base de datos
     * @param descripcion Clase a insertar
     */
	@Override
	public void insertDescripcionClase(DescripcionClase descripcionClase) {
		em.persist(descripcionClase);

	}

	 /**
     * Metodo para actualizar una descripcion Clase a la base de datos
     * @param descripcion Clase a actualizar
     */
	@Override
	public void updateDescripcionClase(DescripcionClase descripcionClase) {
		em.merge(descripcionClase);

	}

	 /**
     * Metodo para eliminar una descripcion Clase a la base de datos
     * @param descripcion Clase a eliminar
     */
	@Override
	public void deleteDescripcionClase(DescripcionClase descripcionClase) {
		descripcionClase = em.find(DescripcionClase.class, descripcionClase.getIdentifica());
		em.remove(descripcionClase);

	}
	
	/**
     * Metodo que permite listar las descripciones clases por clase
     * @param clase
     * @return una lista de descripciones clases
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<DescripcionClase> listarDescripcionClasesPorClase(Clase clase){
		List<DescripcionClase> descripcionClases = null;
		try{
			Query query = em.createQuery("from DescripcionClase dc where dc.clase =:clase");
			query.setParameter("clase", clase);
			descripcionClases = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return descripcionClases;
	}
}
