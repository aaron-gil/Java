/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.converters;

import com.wf.f1.wfactura1.controller.UsoCfdiDao;
import com.wf.f1.wfactura1.converterbeans.UsoCfdiBean;
import com.wf.f1.wfactura1.converterbeans.usoCfdiService;
import javax.ejb.EJB;
import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author WF Consulting
 */
@FacesConverter("usoCfdiConverter")
public class usoCfdiConverter implements Converter {

    @EJB
    private UsoCfdiDao usoCfdiDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("componet");
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueExpression ve = fc.getApplication().getExpressionFactory().createValueExpression(fc.getELContext(), "#{usoCfdiService}", usoCfdiService.class);
        usoCfdiService bean = (usoCfdiService) ve.getValue(fc.getELContext());
        UsoCfdiBean pac = new UsoCfdiBean();
        try {
            pac = bean.buscarIdDato(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (pac == null) {
            throw new ConverterException("ERROR");
        }
        return pac;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("componet");
        }
        if ((value == null)) {
            return "";
        }
        return ((UsoCfdiBean) value).getIdentifica();
    }

}
