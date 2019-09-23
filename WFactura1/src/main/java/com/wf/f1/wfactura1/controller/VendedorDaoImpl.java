package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Usuario;
import com.wf.f1.wfactura1.model.Vendedor;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Vendedor
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class VendedorDaoImpl implements VendedorDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Vendedores de la base de datos
	 * @return una lista de Vendedores
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Vendedor> findAllVendedores() {
		return em.createNamedQuery("Vendedor.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Vendedor a partir de un identificador
	 * @param Vendedor a encontrar
	 * @return el Vendedor encontrado
	 */
	@Override
	public Vendedor findVendedorByIdentifica(Vendedor vendedor) {
		return em.find(Vendedor.class, vendedor.getIdentifica());
	}

	/**
     * Metodo para insertar un Vendedor a la base de datos
     * @param Vendedor a insertar
     */
	@Override
	public void insertVendedor(Vendedor vendedor) {
		em.persist(vendedor);

	}

	 /**
     * Metodo para actualizar un Vendedor a la base de datos
     * @param Vendedor a actualizar
     */
	@Override
	public void updateVendedor(Vendedor vendedor) {
		em.merge(vendedor);

	}

	 /**
     * Metodo para eliminar un Vendedor a la base de datos
     * @param Vendedor a eliminar
     */
	@Override
	public void deleteVendedor(Vendedor vendedor) {
		vendedor = em.find(Vendedor.class, vendedor.getIdentifica());
		em.remove(vendedor);

	}
	
	/**
     * Metodo para listar todos los clienrs que tengan un determinado status desde la BD
     * @param status estado de actividad del vendedor
     * @return una lista de Vendedores
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Vendedor> listarVendedoresPorStatusYUsuario(boolean status,Usuario usuario){
		List<Vendedor> Vendedores = null;
		try{
			Query query = em.createQuery("from Vendedor v where v.status =:status and v.responsableCreacion =:nombre");
			query.setParameter("status", status);
			query.setParameter("nombre", usuario.getNombre());
			Vendedores = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return Vendedores;
	}

}
