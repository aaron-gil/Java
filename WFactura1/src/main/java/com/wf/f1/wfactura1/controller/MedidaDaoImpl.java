package com.wf.f1.wfactura1.controller;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.wf.f1.wfactura1.model.Medida;

/**
 * Medida de tipo EJB que implementa los metodos cargados de la interface de un Medida
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class MedidaDaoImpl implements MedidaDao {
	
	/**
	 * Atributo que define la unidad de persistencia para conectar la aplicacion a la BD
	 */
	@PersistenceContext(unitName = "codicePU")     
	EntityManager em; 
	
	/**
	 * Metodo para obtener todos los Medidas de la base de datos
	 * @return una lista de Medidas
	 */
	@SuppressWarnings("unchecked") 
	@Override
	public List<Medida> findAllMedidas() {
		return em.createNamedQuery("Medida.findAll").getResultList();
	}

	/**
	 * Metodo para traer un Medida a partir de un identificador
	 * @param Medida a encontrar
	 * @return el Medida encontrado
	 */
	@Override
	public Medida findMedidaByIdentifica(Medida medida) {
		return em.find(Medida.class, medida.getIdentifica());
	}

	/**
     * Metodo para insertar un Medida a la base de datos
     * @param Medida a insertar
     */
	@Override
	public void insertMedida(Medida medida) {
		em.persist(medida);

	}

	 /**
     * Metodo para actualizar un Medida a la base de datos
     * @param Medida a actualizar
     */
	@Override
	public void updateMedida(Medida medida) {
		em.merge(medida);

	}

	 /**
     * Metodo para eliminar un Medida a la base de datos
     * @param medida a eliminar
     */
	@Override
	public void deleteMedida(Medida medida) {
		medida = em.find(Medida.class, medida.getIdentifica());
		em.remove(medida);

	}
	
	/**
     * Metodo que permite listar las medidas comunes
     * @return una lista de medidas
     */
	@SuppressWarnings("unchecked")
	@Override
	public List<Medida> listarMedidasComunes(){
		List<Medida> medidas = null;
		try{
			Query query = em.createQuery("from Medida m where m.identifica in('H87','ACT','E48','KGM','GRM','MTR','MTK','MTQ','LTR','NMP','XBX','XKI','XOK','SET')");
			medidas = query.getResultList();
		}catch(Exception e){
			e.printStackTrace();
		}
		return medidas;
	}
	
	
}
