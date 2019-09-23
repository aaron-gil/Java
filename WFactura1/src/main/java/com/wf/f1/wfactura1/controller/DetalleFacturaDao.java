package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.DetalleFactura; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un DetalleFactura
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface DetalleFacturaDao {
	
	//Metodo abstractos
	
	public List<DetalleFactura> findAllDetalleFacturas(); 
	 
    public DetalleFactura findDetalleFacturaByIdentifica(DetalleFactura detalleFactura); 
 
    public void insertDetalleFactura(DetalleFactura detalleFactura); 
 
    public void updateDetalleFactura(DetalleFactura detalleFactura); 
 
    public void deleteDetalleFactura(DetalleFactura detalleFactura);

}
