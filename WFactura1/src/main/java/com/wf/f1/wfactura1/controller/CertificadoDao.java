package com.wf.f1.wfactura1.controller;

import com.wf.f1.wfactura1.model.Certificado;
import java.util.List;



/**
 * Interface para el patron DAO que define los metodos abstractos CRUD de un Certificado
 * @author Ing. Luis Fernando Pedroza Taborda
 * @version 1.0 19-oct-2017
 */
public interface CertificadoDao {
	
	//Metodo abstractos
	public List<Certificado> findAllCertificados(); 
	 
    public Certificado findCertificadoByIdentifica(Certificado certificado); 
 
    public void insertCertificado(Certificado certificado); 
 
    public void updateCertificado(Certificado certificado); 
 
    public void deleteCertificado(Certificado certificado);

}
