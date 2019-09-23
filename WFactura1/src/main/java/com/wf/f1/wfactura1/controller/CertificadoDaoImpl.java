package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.Certificado;

/**
 * Clase de tipo EJB que implementa los metodos cargados de la interface de un Certificado
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class CertificadoDaoImpl implements CertificadoDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Certificados de la base de datos
	 * @return una lista de Certificados
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Certificado> findAllCertificados() {
		return em.createNamedQuery("Certificado.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Certificado a partir de un identificador
	 * @param Certificado a encontrar
	 * @return el Certificado encontrado
	 */
	@Override
	public Certificado findCertificadoByIdentifica(Certificado certificado) {
		return em.find(Certificado.class, certificado.getIdentifica());
	}

	/**
     * Metodo para insertar un Certificado a la base de datos
     * @param Certificado a insertar
     */
	@Override
	public void insertCertificado(Certificado certificado) {
		em.persist(certificado);

	}

	 /**
     * Metodo para actualizar un Certificado a la base de datos
     * @param Certificado a actualizar
     */
	@Override
	public void updateCertificado(Certificado certificado) {
		em.merge(certificado);

	}

	 /**
     * Metodo para eliminar un Certificado a la base de datos
     * @param Certificado a eliminar
     */
	@Override
	public void deleteCertificado(Certificado certificado) {
		certificado = em.find(Certificado.class, certificado.getIdentifica());
		em.remove(certificado);

	}

}
