package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Empresa;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de empresa
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class EmpresaDaoImpl implements EmpresaDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para encontrar las empresas registradas en la base de datos
	 * @return una lista de empresas
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Empresa> findAllEmpresas() {
		return em.createNamedQuery("Empresa.findAll").getResultList();
	}

	/**
	 * Metodo para encontrar una empresa basado en el identificador
	 * @param empresa a encontrar
	 * @return el empresa encontrado
	 */
	@Override
	public Empresa findEmpresaByIdentifica(Empresa empresa) {
		return em.find(Empresa.class, empresa.getIdentifica());
	}
	
	/**
     * Metodo para insertar un Empresa a la base de datos
     * @param empresa a insertar
     */
	@Override
	public void insertEmpresa(Empresa empresa) {
		em.persist(empresa);

	}

	/**
     * Metodo para actualizar cualquier dato de un Empresa a la base de datos
     * @param empresa a actualizar
     */
	@Override
	public void updateEmpresa(Empresa empresa) {
		em.merge(empresa);

	}

    /**
     * Metodo para eliminar una empresa de la base de datos
     * @param empresa a eliminar
     */
	@Override
	public void deleteEmpresa(Empresa empresa) {
		empresa = em.find(Empresa.class, empresa.getIdentifica());
		em.remove(empresa);

	}
	
	/**
     * Metodo para listar todos los Empresas que tengan un determinado status desde la BD
     * @param status estado de actividad del Empresa
     * @return una lista de Empresas
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Empresa> listarEmpresasPorStatusYUsuario(boolean status,Usuario usuario){
		List<Empresa> empresas = null;
		try{
			Query query = em.createQuery("from Empresa e where e.status =:status and e.responsableCreacion =:nombre");
			query.setParameter("status", status);
			query.setParameter("nombre", usuario.getNombre());
			empresas = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return empresas;
	}

}
