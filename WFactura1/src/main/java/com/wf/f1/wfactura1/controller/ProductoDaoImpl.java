package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Producto;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Producto
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class ProductoDaoImpl implements ProductoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Productos de la base de datos
	 * @return una lista de Productos
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Producto> findAllProductos() {
		return em.createNamedQuery("Producto.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Producto a partir de un identificador
	 * @param Producto a encontrar
	 * @return el Producto encontrado
	 */
	@Override
	public Producto findProductoByIdentifica(Producto producto) {
		return em.find(Producto.class, producto.getIdentifica());
	}

	/**
     * Metodo para insertar un Producto a la base de datos
     * @param Producto a insertar
     */
	@Override
	public void insertProducto(Producto producto) {
		em.persist(producto);

	}

	 /**
     * Metodo para actualizar un Producto a la base de datos
     * @param Producto a actualizar
     */
	@Override
	public void updateProducto(Producto producto) {
		em.merge(producto);

	}

	 /**
     * Metodo para eliminar un Producto a la base de datos
     * @param producto a eliminar
     */
	@Override
	public void deleteProducto(Producto producto) {
		producto = em.find(Producto.class, producto.getIdentifica());
		em.remove(producto);

	}
	
	/**
     * Metodo para listar todos los Productos que tengan un determinado status desde la BD
     * @param status estado de actividad del Producto
     * @return una lista de Productos
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> listarProductosPorStatusYUsuario(boolean status,Usuario usuario){
		List<Producto> productos = null;
		try{
			Query query = em.createQuery("select p from Producto p where p.status =:status and p.responsableCreacion =:nombre");
			query.setParameter("status", status);
			query.setParameter("nombre", usuario.getNombre());
			productos = query.getResultList();

		}catch(Exception e){
			//throw e;
		}
		return productos;
	}
	
	
	@Override
	public Producto encontrarSiExisteIdentificador(String identificador) {
		Producto producto;
		try{
			Query query = em.createQuery("from Producto p where p.numeroIdentificador =:identificador");
			query.setParameter("identificador", identificador);
			producto = (Producto)query.getSingleResult(); 
		}catch(NoResultException e) {
	        producto = null;
	    }
		return producto;
	}

}
