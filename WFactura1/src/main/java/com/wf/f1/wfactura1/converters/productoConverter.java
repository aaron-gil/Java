/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.converters;

import com.wf.f1.wfactura1.converterbeans.productoService;
import com.wf.f1.wfactura1.model.Producto;
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
@FacesConverter("productoConverter")
public class productoConverter implements Converter{
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (context == null) {
            throw new NullPointerException("context");
        }
        if (component == null) {
            throw new NullPointerException("componet");
        }
        FacesContext fc = FacesContext.getCurrentInstance();
        ValueExpression ve = fc.getApplication().getExpressionFactory().createValueExpression(fc.getELContext(), "#{productoService}", productoService.class);
        productoService bean = (productoService) ve.getValue(fc.getELContext());
        Producto pac = new Producto();
        try {
            pac = bean.buscarProducto(new Integer(value).intValue());
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
            return "1";
        }
            return ((Producto) value).getIdentifica().toString();
    }
}
