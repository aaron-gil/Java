package com.wf.f1.wfactura1.controller;

import java.util.List;

import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.TipoComprobante;
import com.wf.f1.wfactura1.model.Usuario; 

/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Serie
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface SerieDao {
	
	//Metodo abstractos
	
	public List<Serie> findAllSeries(); 
	 
    public Serie findSerieByIdentifica(Serie serie); 
 
    public void insertSerie(Serie serie); 
 
    public void updateSerie(Serie serie); 
 
    public void deleteSerie(Serie serie);
    
    public List<Serie> listarSeriesPorUsuario(Usuario usuario);
    
    public List<Serie> listarSeriesPorTipoComprobanteYUsuario(TipoComprobante tipoComprobante,Usuario usuario);
    
    public Serie buscarId(Usuario usuario, Integer id);

}
