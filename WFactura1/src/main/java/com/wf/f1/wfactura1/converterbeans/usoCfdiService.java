/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.converterbeans;

import com.wf.f1.wfactura1.controller.UsoCfdiDao;
import com.wf.f1.wfactura1.model.UsoCfdi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "usoCfdiService")
@ApplicationScoped
public class usoCfdiService implements Serializable {

    @EJB
    private UsoCfdiDao usoCfdiDao;
    private List<UsoCfdiBean> usos;
    
    @PostConstruct
    public void init() {
        System.out.println("cargando usos");
        usos=new ArrayList<UsoCfdiBean>();
        usos=usoCfdiDao.buscarUsos();
        System.out.println("usos encontrados "+ usos.size());
    }
    public UsoCfdiBean buscarIdDato(String dato) {
        for(UsoCfdiBean us:usos){
          if(us.getIdentifica().equals(dato))  {
              return us;
          }
        }
        return null;
    }

    public List<UsoCfdiBean> getUsos() {
        return usos;
    }

    public void setUsos(List<UsoCfdiBean> usos) {
        this.usos = usos;
    }
}
