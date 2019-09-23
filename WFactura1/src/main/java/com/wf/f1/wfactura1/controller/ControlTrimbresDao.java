/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.ControlTrimbres;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author WF Consulting
 */
@Local
public interface ControlTrimbresDao {

    void create(ControlTrimbres controlTrimbres);

    void edit(ControlTrimbres controlTrimbres);

    void remove(ControlTrimbres controlTrimbres);

    ControlTrimbres find(Object id);

    List<ControlTrimbres> findAll();

    List<ControlTrimbres> findRange(int[] range);

    int count();
    
    public ControlTrimbres buscarControl(String razon);
    
}
