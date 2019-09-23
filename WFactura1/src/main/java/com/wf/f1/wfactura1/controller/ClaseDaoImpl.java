package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Clase;
import com.wf.f1.wfactura1.model.Grupo;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Clase
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class ClaseDaoImpl implements ClaseDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Clases de la base de datos
	 * @return una lista de Clases
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Clase> findAllClases() {
		return em.createNamedQuery("Clase.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Clase a partir de un identificador
	 * @param Clase a encontrar
	 * @return el Clase encontrado
	 */
	@Override
	public Clase findClaseByIdentifica(Clase Clase) {
		return em.find(Clase.class, Clase.getIdentifica());
	}

	/**
     * Metodo para insertar un Clase a la base de datos
     * @param Clase a insertar
     */
	@Override
	public void insertClase(Clase Clase) {
		em.persist(Clase);

	}

	 /**
     * Metodo para actualizar un Clase a la base de datos
     * @param Clase a actualizar
     */
	@Override
	public void updateClase(Clase Clase) {
		em.merge(Clase);

	}

	 /**
     * Metodo para eliminar un Clase a la base de datos
     * @param Clase a eliminar
     */
	public void deleteClase(Clase Clase) {
		Clase = em.find(Clase.class, Clase.getIdentifica());
		em.remove(Clase);

	}
	
	/**
     * Metodo que permite listar las clases por grupo
     * @param grupo
     * @return una lista de clases
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Clase> listarClasesPorGrupo(Grupo grupo){
		List<Clase> Clases = null;
		try{
			Query query = em.createQuery("from Clase c where c.grupo =:grupo");
			query.setParameter("grupo", grupo);
			Clases = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Clases;
	}
}
