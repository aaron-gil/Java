package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Perfil;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Perfil de Perfil
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class PerfilDaoImpl implements PerfilDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para encontrar todos los perfiles registrados en la base de datos
	 * @return una lista de perfiles
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Perfil> findAllPerfiles() {
		return em.createNamedQuery("Perfil.findAll").getResultList();
	}

	/**
	 * Metodo para encontrar un perfil basado en el identificador
	 * @param perfil a encontrar
	 * @return el perfil encontrado
	 */
	@Override
	public Perfil findPerfilByIdentifica(Perfil perfil) {
		return em.find(Perfil.class, perfil.getIdentifica());
	}
	
	/**
     * Metodo para insertar un perfil a la base de datos
     * @param perfil a insertar
     */
	@Override
	public void insertPerfil(Perfil perfil) {
		em.persist(perfil);

	}

	/**
     * Metodo para actualizar cualquier dato de un perfil a la base de datos
     * @param perfil a actualizar
     */
	@Override
	public void updatePerfil(Perfil perfil) {
		em.merge(perfil);

	}

    /**
     * Metodo para eliminar un perfil de la base de datos
     * @param perfil a eliminar
     */
	@Override
	public void deletePerfil(Perfil perfil) {
		perfil = em.find(Perfil.class, perfil.getIdentifica());
		em.remove(perfil);

	}
	
	/**
     * Metodo para listar todos los perfiles que tengan un determinado status desde la BD
     * @param status estado de actividad del Perfil
     * @return una lista de Perfils
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Perfil> listarPerfilesPorStatusYUsuario(boolean status,Usuario usuario){
		List<Perfil> perfiles = null;
		try{
			Query query = em.createQuery("from Perfil p where p.status =:status and p.responsableCreacion =:nombre");
			query.setParameter("status", status);
			query.setParameter("nombre", usuario.getNombre());
			perfiles = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return perfiles;
	}

}
