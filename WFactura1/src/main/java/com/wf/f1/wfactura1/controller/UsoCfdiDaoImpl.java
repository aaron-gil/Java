package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.converterbeans.UsoCfdiBean;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.wf.f1.wfactura1.model.UsoCfdi;
import java.util.ArrayList;
import javax.persistence.Query;

/**
 * UsoCfdi de tipo EJB que implementa los metodos cargados de la interface de un
 * UsoCfdi
 *
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
@Stateless
public class UsoCfdiDaoImpl implements UsoCfdiDao {

    /**
     * Atributo que define la unidad de persistencia para conectar la aplicacion
     * a la BD
     */
    @PersistenceContext(unitName = "codicePU")
    EntityManager em;

    /**
     * Metodo para obtener todos los UsoCfdies de la base de datos
     *
     * @return una lista de UsoCfdies
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<UsoCfdi> findAllUsoCfdies() {
        return em.createNamedQuery("UsoCfdi.findAll").getResultList();
    }

    /**
     * Metodo para traer una UsoCfdi a partir de un identificador
     *
     * @param UsoCfdi a encontrar
     * @return la UsoCfdi encontrado
     */
    @Override
    public UsoCfdi findUsoCfdiByIdentifica(UsoCfdi usoCfdi) {
        return em.find(UsoCfdi.class, usoCfdi.getIdentifica());
    }

    /**
     * Metodo para insertar una UsoCfdi a la base de datos
     *
     * @param UsoCfdi a insertar
     */
    @Override
    public void insertUsoCfdi(UsoCfdi usoCfdi) {
        em.persist(usoCfdi);

    }

    /**
     * Metodo para actualizar un UsoCfdi a la base de datos
     *
     * @param UsoCfdi a actualizar
     */
    @Override
    public void updateUsoCfdi(UsoCfdi usoCfdi) {
        em.merge(usoCfdi);

    }

    /**
     * Metodo para eliminar un UsoCfdi a la base de datos
     *
     * @param usoCfdi a eliminar
     */
    @Override
    public void deleteUsoCfdi(UsoCfdi usoCfdi) {
        usoCfdi = em.find(UsoCfdi.class, usoCfdi.getIdentifica());
        em.remove(usoCfdi);

    }

    
    @Override
    public UsoCfdiBean buscardato(Integer dato){
        UsoCfdiBean uso= new UsoCfdiBean();
        List<UsoCfdiBean> allusos=buscarUsos();
        for(UsoCfdiBean us: allusos){
            if(us.getId()==dato){
                uso=us;
                break;
            }
        } 
        return uso;
    }
    
    @Override
    public List<UsoCfdiBean> buscarUsos(){
        List<UsoCfdiBean> usos = new ArrayList<UsoCfdiBean>();
        try {
            Query query = em.createQuery("from UsoCfdi u order by u.identifica");
            List<UsoCfdi> usosL = query.getResultList();
            if (usosL.size() > 0) {
                for(int i=0; i<usosL.size();i++){
                    UsoCfdi u=usosL.get(i);
                    UsoCfdiBean ub= new UsoCfdiBean();
                    ub.setId(i);
                    ub.setIdentifica(u.getIdentifica());
                    ub.setDescripcion(u.getDescripcion());
                    usos.add(ub);
                }
            }
        } catch (Exception e) {
            usos = null;
            e.printStackTrace();
        }
        return usos;
    }

}
