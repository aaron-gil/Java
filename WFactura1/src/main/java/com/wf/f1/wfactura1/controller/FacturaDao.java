package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.Cliente;
import java.util.List;

import com.wf.f1.wfactura1.model.Factura;
import com.wf.f1.wfactura1.model.Usuario;

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Factura
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface FacturaDao {
	
	//Metodo abstractos
	public List<Factura> findAllFacturas(); 
	 
    public Factura findFacturaByIdentifica(Factura factura); 
 
    public void insertFactura(Factura factura); 
 
    public void updateFactura(Factura factura); 
 
    public void deleteFactura(Factura factura);
    
    public List<Factura> listarFacturasPorEstadoPagoYUsuario(String estadoPago,Usuario usuario);
    
     public List<Factura> listarFacturasPorEstadoPagoYUsuarioPagos(String estadoPago,Usuario usuario);
     
     public Factura encontrarFacturaPorUuid(String uuid);
     
     public List<Factura> listarFacturasPorEstadoUsuarioPagosRFC(String estadoPago, Usuario usuario,Cliente identifica,String uuid);
     
     public Factura consultaPagos(String uuid);
     
     public List<Factura> buscarFacturas (Usuario usuario);
     
     public List<Factura> buscarcanceladas (Usuario usuario);

}
