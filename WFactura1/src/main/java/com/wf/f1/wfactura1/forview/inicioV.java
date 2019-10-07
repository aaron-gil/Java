/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.entities.HibernateUtil;
import com.wf.f1.wfactura1.entities.Certs;
import com.wf.f1.wfactura1.controller.ControlTrimbresDao;
import com.wf.f1.wfactura1.controller.SerieDao;
import com.wf.f1.wfactura1.model.ControlTrimbres;
import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "inicioV")
@SessionScoped
public class inicioV implements Serializable {

    @EJB
    private ControlTrimbresDao controlTimbresD;
    @EJB
    private SerieDao serieDao;
    private Usuario usuarioSeleccionado;
    private List<Serie> seriesSeleccionado;
    private Date fechaActual;
    private Certs certificado;
    private String estatusCert;
    private ControlTrimbres controlTim;
    private String series;
    private String folios;

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getFolios() {
        return folios;
    }

    public void setFolios(String folios) {
        this.folios = folios;
    }

    public List<Serie> getSeriesSeleccionado() {
        return seriesSeleccionado;
    }

    public void setSeriesSeleccionado(List<Serie> seriesSeleccionado) {
        this.seriesSeleccionado = seriesSeleccionado;
    }

    public ControlTrimbres getControlTim() {
        return controlTim;
    }

    public void setControlTim(ControlTrimbres controlTim) {
        this.controlTim = controlTim;
    }

    public String getEstatusCert() {
        return estatusCert;
    }

    public void setEstatusCert(String estatusCert) {
        this.estatusCert = estatusCert;
    }

    public Certs getCertificado() {
        return certificado;
    }

    public void setCertificado(Certs certificado) {
        this.certificado = certificado;
    }

    public Date getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(Date fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    @PostConstruct
    public void inicializar() {
        fechaActual = new Date();
        verificarSesion();

    }

    public void verificarSesion() {
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioSeleccionado == null) {
                context.getExternalContext().redirect("index.xhtml");
            } else {
                seriesSeleccionado = serieDao.listarSeriesPorUsuario(usuarioSeleccionado);
                buscarCerts();
            }
        } catch (Exception localException) {
        }
    }

    public void buscarCerts() {
        String RFC = usuarioSeleccionado.getNombre();
        Session sess = HibernateUtil.getSessionFactory().openSession();
        try {
            System.out.println("RFC " + RFC);
            Query QueryCert = sess.createQuery("from Certs as C WHERE C.rfc =:RFC order by C.fin desc").setString("RFC", RFC);
            List<Certs> Certificados = (List<Certs>) QueryCert.list();
            if (Certificados != null) {
                certificado = Certificados.get(0);
                if (certificado.getEstatusCer() == null) {
                    estatusCert = "Inactivo";
                } else if (certificado.getEstatusCer().equals("A")) {
                    estatusCert = "Activo";
                } else if (certificado.getEstatusCer().equals("C")) {
                    estatusCert = "Cancelado";
                } else {
                    estatusCert = "Error";
                }
                controlTim = controlTimbresD.buscarControl(usuarioSeleccionado.getNombreCompleto());
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar certificado del rfc = " + RFC);
            e.printStackTrace();
        } finally {

            sess.clear();
            sess.close();
        }
        if (seriesSeleccionado == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso Es necesario Agregar una Serie",null));
        } else if (seriesSeleccionado.size() > 0) {
            series = "";
            folios = "";
            for (Serie ser : seriesSeleccionado) {
                series += ser.getNombre() + "\t";
                folios += ser.getFolioActual() + "\t";
            }
        }
    }

}
