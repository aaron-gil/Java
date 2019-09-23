package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Sucursal;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Sucursal
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class SucursalDaoImpl implements SucursalDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Sucursales de la base de datos
	 * @return una lista de Sucursales
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Sucursal> findAllSucursales() {
		return em.createNamedQuery("Sucursal.findAll").getResultList();
	}

	/**
	 * Metodo para traer una Sucursal a partir de un identificador
	 * @param Sucursal a encontrar
	 * @return el Sucursal encontrado
	 */
	@Override
	public Sucursal findSucursalByIdentifica(Sucursal sucursal) {
		return em.find(Sucursal.class, sucursal.getIdentifica());
	}

	/**
     * Metodo para insertar una Sucursal a la base de datos
     * @param Sucursal a insertar
     */
	@Override
	public void insertSucursal(Sucursal sucursal) {
		em.persist(sucursal);

	}

	 /**
     * Metodo para actualizar una Sucursal a la base de datos
     * @param Sucursal a actualizar
     */
	@Override
	public void updateSucursal(Sucursal sucursal) {
		em.merge(sucursal);

	}

	 /**
     * Metodo para eliminar una Sucursal a la base de datos
     * @param Sucursal a eliminar
     */
	@Override
	public void deleteSucursal(Sucursal sucursal) {
		sucursal = em.find(Sucursal.class, sucursal.getIdentifica());
		em.remove(sucursal);

	}
	
	/**
     * Metodo para listar todos las sucursales que tengan un determinado status desde la BD
     * @param status estado de actividad de la sucursal
     * @return una lista de sucursales
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Sucursal> listarSucursalesPorStatusYUsuario(boolean status,Usuario usuario){
		List<Sucursal> sucursales = null;
		try{
			Query query = em.createQuery("from Sucursal s where s.status =:status and s.responsableCreacion =:nombre");
			query.setParameter("status", status);
			query.setParameter("nombre", usuario.getNombre());
			sucursales = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return sucursales;
	}

}
