/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.Zonahoraria;
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
public class ZonahorariaDaoImpl extends AbstractFacade<Zonahoraria> implements ZonahorariaDao {

    @PersistenceContext(unitName = "codicePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ZonahorariaDaoImpl() {
        super(Zonahoraria.class);
    }

    @Override
    public String buscarCP(String cp) {
        String z = "";
        try {
            Query query = em.createQuery("from Zonahoraria s where s.cp=:cp");
            query.setParameter("cp", cp);
            List<Zonahoraria> lS = query.getResultList();
            if (lS.size() > 0) {
                z = lS.get(0).getZona();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }

}
