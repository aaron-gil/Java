/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.ControlTrimbres;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author WF Consulting
 */
@Stateless
public class ControlTrimbDaoImpl extends AbstractFacade<ControlTrimbres> implements ControlTrimbresDao {

    @PersistenceContext(unitName = "codicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ControlTrimbDaoImpl() {
        super(ControlTrimbres.class);
    }
    
    @Override
    public ControlTrimbres buscarControl(String razon){
        ControlTrimbres control=new ControlTrimbres();
        try{
            Query query = em.createQuery("from ControlTrimbres c where c.razonSocial=:razon");
            query.setParameter("razon", razon);
            List<ControlTrimbres>lCT=query.getResultList();
            if(lCT.size()>0){
                control=lCT.get(0);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return control;
    }
    
}
