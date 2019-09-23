package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Division;
import com.wf.f1.wfactura1.model.Tipo;

/**
 * Division de tipo EJB que implementa los metodos cargados de la interface de un Division
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class DivisionDaoImpl implements DivisionDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Divisiones de la base de datos
	 * @return una lista de Divisiones
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Division> findAllDivisiones() {
		return em.createNamedQuery("Division.findAll").getResultList();
	}

	/**
	 * Metodo para traer una Division a partir de un identificador
	 * @param Division a encontrar
	 * @return la Division encontrado
	 */
	@Override
	public Division findDivisionByIdentifica(Division division) {
		return em.find(Division.class, division.getIdentifica());
	}

	/**
     * Metodo para insertar una Division a la base de datos
     * @param Division a insertar
     */
	@Override
	public void insertDivision(Division division) {
		em.persist(division);

	}

	 /**
     * Metodo para actualizar un Division a la base de datos
     * @param Division a actualizar
     */
	@Override
	public void updateDivision(Division division) {
		em.merge(division);

	}

	 /**
     * Metodo para eliminar un Division a la base de datos
     * @param division a eliminar
     */
	@Override
	public void deleteDivision(Division division) {
		division = em.find(Division.class, division.getIdentifica());
		em.remove(division);

	}
	
	/**
     * Metodo que permite listar las divisiones por tipo de concepto ya sea producto o servicio
     * @param tipo corresponde al tipo de concepto
     * @return una lista de divisiones
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Division> listarDivisionesPorTipo(Tipo tipo){
		List<Division> divisiones = null;
		try{
			Query query = em.createQuery("from Division d where d.tipo =:tipo");
			query.setParameter("tipo", tipo);
			divisiones = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return divisiones;
	}

}
