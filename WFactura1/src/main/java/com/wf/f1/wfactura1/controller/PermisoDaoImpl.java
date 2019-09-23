package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Perfil;
import com.wf.f1.wfactura1.model.Permiso;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un permiso
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class PermisoDaoImpl implements PermisoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em;
	
	/**
	 * Metodo para obtener todos los permisos de la base de datos
	 * @return una lista de permisos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Permiso> findAllPermisos() {
		return em.createNamedQuery("Permiso.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Permiso a partir de un identificador
	 * @param Permiso a encontrar
	 * @return el Permiso encontrado
	 */
	@Override
	public Permiso findPermisoByIdentifica(Permiso permiso) {
		return em.find(Permiso.class, permiso.getIdentifica());
	}

	/**
     * Metodo para insertar un Permiso a la base de datos
     * @param Permiso a insertar
     */
	@Override
	public void insertPermiso(Permiso permiso) {
		em.persist(permiso);

	}

	/**
     * Metodo para actualizar un Permiso a la base de datos
     * @param Permiso a actualizar
     */
	@Override
	public void updatePermiso(Permiso permiso) {
		em.merge(permiso);

	}

	/**
     * Metodo para eliminar un Permiso a la base de datos
     * @param Permiso a eliminar
     */
	@Override
	public void deletePermiso(Permiso permiso) {
		permiso = em.find(Permiso.class, permiso.getIdentifica());
		em.remove( permiso );

	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public List<Permiso> encontrarPermisosMenuPorPerfil(Usuario usuario){
		List<Permiso> permisosMenu = null;
		Query query = em.createQuery("from Permiso p join p.menu m where p.perfil = :perfil");
		query.setParameter("perfil", usuario.getPerfil());
		permisosMenu = query.getResultList();
		return permisosMenu;
	}
	
	@SuppressWarnings("unchecked") 
	@Override
	public List<Permiso> encontrarPermisosPorPerfil(Perfil perfil){
		List<Permiso> permisosMenu = null;
		Query query = em.createQuery("from Permiso p join p.menu m where p.perfil = :perfil and m.menuPadre <> 0");
		query.setParameter("perfil", perfil);
		permisosMenu = query.getResultList();
		return permisosMenu;
	}

}
