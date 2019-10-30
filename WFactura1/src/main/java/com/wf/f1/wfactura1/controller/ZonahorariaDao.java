/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.Zonahoraria;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface ZonahorariaDao {

    void create(Zonahoraria zonahoraria);

    void edit(Zonahoraria zonahoraria);

    void remove(Zonahoraria zonahoraria);

    Zonahoraria find(Object id);

    List<Zonahoraria> findAll();

    List<Zonahoraria> findRange(int[] range);

    int count();
    
    public String buscarCP(String cp);
}
