package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Division;
import com.wf.f1.wfactura1.model.Grupo;

/**
 * Grupo de tipo EJB que implementa los metodos cargados de la interface de un Grupo
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class GrupoDaoImpl implements GrupoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Grupos de la base de datos
	 * @return una lista de Grupos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Grupo> findAllGrupos() {
		return em.createNamedQuery("Grupo.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Grupo a partir de un identificador
	 * @param Grupo a encontrar
	 * @return el Grupo encontrado
	 */
	@Override
	public Grupo findGrupoByIdentifica(Grupo Grupo) {
		return em.find(Grupo.class, Grupo.getIdentifica());
	}

	/**
     * Metodo para insertar un Grupo a la base de datos
     * @param Grupo a insertar
     */
	@Override
	public void insertGrupo(Grupo Grupo) {
		em.persist(Grupo);

	}

	 /**
     * Metodo para actualizar un Grupo a la base de datos
     * @param Grupo a actualizar
     */
	@Override
	public void updateGrupo(Grupo Grupo) {
		em.merge(Grupo);

	}

	 /**
     * Metodo para eliminar un Grupo a la base de datos
     * @param Grupo a eliminar
     */
	@Override
	public void deleteGrupo(Grupo Grupo) {
		Grupo = em.find(Grupo.class, Grupo.getIdentifica());
		em.remove(Grupo);

	}
	
	/**
     * Metodo que permite listar los grupos por division
     * @param division
     * @return lista de grupos
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> listarGruposPorDivision(Division division){
		List<Grupo> Grupos = null;
		try{
			Query query = em.createQuery("from Grupo g where g.division =:division");
			query.setParameter("division", division);
			Grupos = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Grupos;
	}

}
