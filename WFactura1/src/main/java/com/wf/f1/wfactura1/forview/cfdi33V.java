/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.beanext.conceptos;
import com.wf.f1.wfactura1.beanext.impRetenidos;
import com.wf.f1.wfactura1.beanext.impTrasladados;
import com.wf.f1.wfactura1.controller.FormaPagoDao;
import com.wf.f1.wfactura1.controller.MetodoPagoDao;
import com.wf.f1.wfactura1.controller.MonedaDao;
import com.wf.f1.wfactura1.controller.UsoCfdiDao;
import com.wf.f1.wfactura1.converterbeans.UsoCfdiBean;
import com.wf.f1.wfactura1.converterbeans.clienteService;
import com.wf.f1.wfactura1.converterbeans.productoService;
import com.wf.f1.wfactura1.converterbeans.usoCfdiService;
import com.wf.f1.wfactura1.model.Cliente;
import com.wf.f1.wfactura1.model.FormaPago;
import com.wf.f1.wfactura1.model.MetodoPago;
import com.wf.f1.wfactura1.model.Moneda;
import com.wf.f1.wfactura1.model.Producto;
import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.UsoCfdi;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "cfdi33V")
@ViewScoped
public class cfdi33V implements Serializable {

    @EJB
    private UsoCfdiDao usosDao;
    @EJB
    private MetodoPagoDao metodoPagoService;
    @EJB
    private MonedaDao monedaService;
    @EJB
    private FormaPagoDao formaPagoService;
    private Usuario usuarioSeleccionado;
    private String fechaActual;
    private List<Serie> seriesSeleccionadas;
    private List<String> opcSeries;
    private Serie sSelecion;
    private Integer idSerie;
    private Integer nextFolio = 0;
    private Cliente cliente;
    private UsoCfdiBean usoCfdiBean;
    private List<UsoCfdiBean> todoUsos;
    private List<MetodoPago> listaMetodosPago;
    private MetodoPago metodoPago;
    private Moneda moneda;
    private List<Moneda> listaMonedas;
    private String FormaPagoS;
    private List<FormaPago> listaFormasPago;
    private Producto producto;
    private List<conceptos> conceptos;
    private BigDecimal cantidad;
    private BigDecimal porcentajeDes;
    private BigDecimal importe;
    private BigDecimal descuento;
    private String noPedimento;
    private BigDecimal baseT;
    private String ivaT;
    private String tipoFactorT;
    private BigDecimal tasaOCuotaT;
    private BigDecimal importeT;
    private BigDecimal baseR;
    private String ivaR;
    private String tipoFactorR;
    private BigDecimal tasaOCuotaR;
    private BigDecimal importeR;
    private List<impTrasladados> listTras;
    private List<impRetenidos> listRete;
    private boolean checkRetenidos;
    private boolean checktrasladados;
    private Map<String, BigDecimal> listTCReten;
    private Map<String, BigDecimal> listTCTras;
    private boolean tipoFactorRActiv;
    private boolean tipoFactorTActiv;
    private List<String> tipoFactorRL;
    private List<String> tipoFactorTL;
    private boolean cuotaTActv;
    private boolean tipoTasaOCRActiv;
    private boolean tipoTasaOCTActiv;
    private boolean activChecksImp;
    private boolean impRetPan;
    private boolean impTraPan;

    @ManagedProperty(value = "#{usoCfdiService}")
    private usoCfdiService service;
    @ManagedProperty(value = "#{clienteService}")
    private clienteService serviceC;
    @ManagedProperty(value = "#{productoService}")
    private productoService serviceP;

    @PostConstruct
    public void inicializar() {
        importe = new BigDecimal(0);
        cantidad = new BigDecimal(0);
        porcentajeDes = new BigDecimal(0);
        descuento = new BigDecimal(0);
        tipoFactorRActiv = true;
        tipoTasaOCRActiv = true;
        tipoFactorTActiv = true;
        tipoTasaOCTActiv = true;
        usuarioSeleccionado = new Usuario();
        seriesSeleccionadas = new ArrayList<Serie>();
        opcSeries = new ArrayList<String>();
        sSelecion = new Serie();
        nextFolio = 0;
        idSerie = 0;
        usoCfdiBean = new UsoCfdiBean();
        todoUsos = new ArrayList<UsoCfdiBean>();
        //moneda.setIdentifica("MXN");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd'T'hh:mm:ss");
        fechaActual = formatter.format(date);
        verificarSesion();
        listarUsos();
        listarMetodosPago();
        listarMonedas();
        listarFormasPago();

    }

    public boolean isImpRetPan() {
        return impRetPan;
    }

    public void setImpRetPan(boolean impRetPan) {
        this.impRetPan = impRetPan;
    }

    public boolean isImpTraPan() {
        return impTraPan;
    }

    public void setImpTraPan(boolean impTraPan) {
        this.impTraPan = impTraPan;
    }

    public boolean isActivChecksImp() {
        return activChecksImp;
    }

    public void setActivChecksImp(boolean activChecksImp) {
        this.activChecksImp = activChecksImp;
    }

    public Map<String, BigDecimal> getListTCReten() {
        return listTCReten;
    }

    public void setListTCReten(Map<String, BigDecimal> listTCReten) {
        this.listTCReten = listTCReten;
    }

    public List<String> getTipoFactorRL() {
        return tipoFactorRL;
    }

    public void setTipoFactorRL(List<String> tipoFactorRL) {
        this.tipoFactorRL = tipoFactorRL;
    }

    public List<String> getTipoFactorTL() {
        return tipoFactorTL;
    }

    public void setTipoFactorTL(List<String> tipoFactorTL) {
        this.tipoFactorTL = tipoFactorTL;
    }

    public boolean isCuotaTActv() {
        return cuotaTActv;
    }

    public void setCuotaTActv(boolean cuotaTActv) {
        this.cuotaTActv = cuotaTActv;
    }

    public boolean isTipoTasaOCRActiv() {
        return tipoTasaOCRActiv;
    }

    public void setTipoTasaOCRActiv(boolean tipoTasaOCRActiv) {
        this.tipoTasaOCRActiv = tipoTasaOCRActiv;
    }

    public boolean isTipoTasaOCTActiv() {
        return tipoTasaOCTActiv;
    }

    public void setTipoTasaOCTActiv(boolean tipoTasaOCTActiv) {
        this.tipoTasaOCTActiv = tipoTasaOCTActiv;
    }

    public String getIvaR() {
        return ivaR;
    }

    public void setIvaR(String ivaR) {
        this.ivaR = ivaR;
    }

    public boolean isTipoFactorRActiv() {
        return tipoFactorRActiv;
    }

    public void setTipoFactorRActiv(boolean tipoFactorRActiv) {
        this.tipoFactorRActiv = tipoFactorRActiv;
    }

    public boolean isTipoFactorTActiv() {
        return tipoFactorTActiv;
    }

    public void setTipoFactorTActiv(boolean tipoFactorTActiv) {
        this.tipoFactorTActiv = tipoFactorTActiv;
    }

    public Map<String, BigDecimal> getListTCTras() {
        return listTCTras;
    }

    public void setListTCTras(Map<String, BigDecimal> listTCTras) {
        this.listTCTras = listTCTras;
    }

    public boolean isCheckRetenidos() {
        return checkRetenidos;
    }

    public void setCheckRetenidos(boolean checkRetenidos) {
        this.checkRetenidos = checkRetenidos;
    }

    public boolean isChecktrasladados() {
        return checktrasladados;
    }

    public void setChecktrasladados(boolean checktrasladados) {
        this.checktrasladados = checktrasladados;
    }

    public List<conceptos> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<conceptos> conceptos) {
        this.conceptos = conceptos;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPorcentajeDes() {
        return porcentajeDes;
    }

    public void setPorcentajeDes(BigDecimal porcentajeDes) {
        this.porcentajeDes = porcentajeDes;
    }

    public BigDecimal getImporte() {
        return importe;
    }

    public void setImporte(BigDecimal importe) {
        this.importe = importe;
    }

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    public String getNoPedimento() {
        return noPedimento;
    }

    public void setNoPedimento(String noPedimento) {
        this.noPedimento = noPedimento;
    }

    public BigDecimal getBaseT() {
        return baseT;
    }

    public void setBaseT(BigDecimal baseT) {
        this.baseT = baseT;
    }

    public String getIvaT() {
        return ivaT;
    }

    public void setIvaT(String ivaT) {
        this.ivaT = ivaT;
    }

    public String getTipoFactorT() {
        return tipoFactorT;
    }

    public void setTipoFactorT(String tipoFactorT) {
        this.tipoFactorT = tipoFactorT;
    }

    public BigDecimal getTasaOCuotaT() {
        return tasaOCuotaT;
    }

    public void setTasaOCuotaT(BigDecimal tasaOCuotaT) {
        this.tasaOCuotaT = tasaOCuotaT;
    }

    public BigDecimal getImporteT() {
        return importeT;
    }

    public void setImporteT(BigDecimal importeT) {
        this.importeT = importeT;
    }

    public BigDecimal getBaseR() {
        return baseR;
    }

    public void setBaseR(BigDecimal baseR) {
        this.baseR = baseR;
    }

    public String getTipoFactorR() {
        return tipoFactorR;
    }

    public void setTipoFactorR(String tipoFactorR) {
        this.tipoFactorR = tipoFactorR;
    }

    public BigDecimal getTasaOCuotaR() {
        return tasaOCuotaR;
    }

    public void setTasaOCuotaR(BigDecimal tasaOCuotaR) {
        this.tasaOCuotaR = tasaOCuotaR;
    }

    public BigDecimal getImporteR() {
        return importeR;
    }

    public void setImporteR(BigDecimal importeR) {
        this.importeR = importeR;
    }

    public List<impTrasladados> getListTras() {
        return listTras;
    }

    public void setListTras(List<impTrasladados> listTras) {
        this.listTras = listTras;
    }

    public List<impRetenidos> getListRete() {
        return listRete;
    }

    public void setListRete(List<impRetenidos> listRete) {
        this.listRete = listRete;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public productoService getServiceP() {
        return serviceP;
    }

    public void setServiceP(productoService serviceP) {
        this.serviceP = serviceP;
    }

    public String getFormaPagoS() {
        return FormaPagoS;
    }

    public void setFormaPagoS(String FormaPagoS) {
        this.FormaPagoS = FormaPagoS;
    }

    public List<FormaPago> getListaFormasPago() {
        return listaFormasPago;
    }

    public void setListaFormasPago(List<FormaPago> listaFormasPago) {
        this.listaFormasPago = listaFormasPago;
    }

    public Moneda getMoneda() {
        return moneda;
    }

    public void setMoneda(Moneda moneda) {
        this.moneda = moneda;
    }

    public List<Moneda> getListaMonedas() {
        return listaMonedas;
    }

    public void setListaMonedas(List<Moneda> listaMonedas) {
        this.listaMonedas = listaMonedas;
    }

    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

    public List<MetodoPago> getListaMetodosPago() {
        return listaMetodosPago;
    }

    public void setListaMetodosPago(List<MetodoPago> listaMetodosPago) {
        this.listaMetodosPago = listaMetodosPago;
    }

    public clienteService getServiceC() {
        return serviceC;
    }

    public void setServiceC(clienteService serviceC) {
        this.serviceC = serviceC;
    }

    public usoCfdiService getService() {
        return service;
    }

    public void setService(usoCfdiService service) {
        this.service = service;
    }

    public UsoCfdiBean getUsoCfdiBean() {
        return usoCfdiBean;
    }

    public void setUsoCfdiBean(UsoCfdiBean usoCfdiBean) {
        this.usoCfdiBean = usoCfdiBean;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFechaActual() {
        return fechaActual;
    }

    public void setFechaActual(String fechaActual) {
        this.fechaActual = fechaActual;
    }

    public Integer getNextFolio() {
        return nextFolio;
    }

    public void setNextFolio(Integer nextFolio) {
        this.nextFolio = nextFolio;
    }

    public Integer getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(Integer idSerie) {
        this.idSerie = idSerie;
    }

    public Serie getsSelecion() {
        return sSelecion;
    }

    public void setsSelecion(Serie sSelecion) {
        this.sSelecion = sSelecion;
    }

    public List<Serie> getSeriesSeleccionadas() {
        return seriesSeleccionadas;
    }

    public void setSeriesSeleccionadas(List<Serie> seriesSeleccionadas) {
        this.seriesSeleccionadas = seriesSeleccionadas;
    }

    public List<String> getOpcSeries() {
        return opcSeries;
    }

    public void setOpcSeries(List<String> opcSeries) {
        this.opcSeries = opcSeries;
    }

    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void listarMetodosPago() {
        listaMetodosPago = metodoPagoService.findAllMetodosPago();
    }

    public void listarMonedas() {
        listaMonedas = monedaService.findAllMonedas();
    }

    public void listarFormasPago() {
        listaFormasPago = formaPagoService.findAllFormasPago();
    }

    private void listarUsos() {
        todoUsos = new ArrayList<UsoCfdiBean>();
        List<UsoCfdi> todoUsosD = usosDao.findAllUsoCfdies();
        for (int i = 0; i < todoUsosD.size(); i++) {
            UsoCfdiBean usB = new UsoCfdiBean();
            usB.setId(i);
            usB.setIdentifica(todoUsosD.get(i).getIdentifica());
            usB.setDescripcion(todoUsosD.get(i).getDescripcion());
            todoUsos.add(usB);
        }
    }

    public void verificarSesion() {
        System.out.println("verificando..................");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioSeleccionado == null) {
                context.getExternalContext().redirect("index.xhtml");
            } else {
                seriesSeleccionadas = (List<Serie>) context.getExternalContext().getSessionMap().get("seriesSeleccionadas");
                System.out.println("series .... " + seriesSeleccionadas.get(0).getNombre() + " total de  series " + seriesSeleccionadas.size());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void folioSiguiente() {
        for (Serie ser : seriesSeleccionadas) {
            if (ser.getIdentifica() == idSerie) {
                sSelecion = ser;
                nextFolio = ser.getFolioActual() + 1;
            }
        }
    }

    public List<UsoCfdiBean> completeUso(String dato) {

        List<UsoCfdiBean> allUsos = service.getUsos();
        List<UsoCfdiBean> filteredUsos = new ArrayList<UsoCfdiBean>();
        for (UsoCfdiBean usoF : allUsos) {
            if (usoF.getIdentifica().toLowerCase().contains(dato.toLowerCase())) {
                filteredUsos.add(usoF);
            } else if (usoF.getDescripcion().toLowerCase().contains(dato.toLowerCase())) {
                filteredUsos.add(usoF);
            }
        }
        return filteredUsos;
    }

    public List<Cliente> completeCliente(String dato) {
        List<Cliente> allClientes = serviceC.getClientes();
        List<Cliente> filteredCliente = new ArrayList<Cliente>();
        for (Cliente c : allClientes) {
            if (c.getRfc().toLowerCase().contains(dato.toLowerCase())) {
                filteredCliente.add(c);
            } else if (c.getRazonSocial().toLowerCase().contains(dato.toLowerCase())) {
                filteredCliente.add(c);
            }
        }
        return filteredCliente;
    }

    public List<Producto> completeProducto(String dato) {
        List<Producto> allProducto = serviceP.getProductos();
        List<Producto> filteredProducto = new ArrayList<Producto>();
        for (Producto p : allProducto) {
            if (p.getDescripcion().toLowerCase().contains(dato.toLowerCase())) {
                filteredProducto.add(p);
            } else if (p.getCodigoSat().toLowerCase().contains(dato.toLowerCase())) {
                filteredProducto.add(p);
            }
        }
        return filteredProducto;
    }

    public void cantidadXvalor() {
        try {
            importe = cantidad.multiply(producto.getValorUnitario());
            if (cantidad.intValue() > 0) {
                activChecksImp = true;
                baseR = importe;
                baseT = importe;
            } else {
                activChecksImp = false;
            }
        } catch (NullPointerException e) {
            importe = new BigDecimal(0);
        }
    }

    public void descuentoProd() {
        try {
            descuento = (porcentajeDes.multiply(new BigDecimal(0.01))).multiply(importe);
        } catch (NullPointerException e) {
            descuento = new BigDecimal(0);
        }
    }

    public void agregarVRetenciones() {
        if (!checkRetenidos && listRete.size() == 0) {
            baseR = new BigDecimal(0);
            ivaR = "";
            tipoFactorR = "";
            tasaOCuotaR = new BigDecimal(0);
            importeR = new BigDecimal(0);
            tipoFactorRActiv = true;
            tipoTasaOCRActiv = true;
        }
    }

    public void agregarVTrasladados() {
        if (!checktrasladados && listTras.size() == 0) {
            baseT = new BigDecimal(0);
            ivaT = "";
            tipoFactorT = "";
            tasaOCuotaT = new BigDecimal(0);
            importeT = new BigDecimal(0);
            tipoFactorTActiv = true;
            tipoTasaOCTActiv = true;
        }
    }

    public void activTipFacRet() {
        tipoFactorRL = new ArrayList<String>();

        if (ivaR != null && !ivaR.equals("")) {
            tipoFactorRActiv = false;
            if (ivaR.equals("ieps")) {
                tipoFactorRL.add("Tasa");
                tipoFactorRL.add("Cuota");
            } else {
                tipoFactorRL.add("Tasa");
            }
        } else {
            tipoFactorRActiv = true;
        }
    }

    public void activTipFacTra() {
        tipoFactorTL = new ArrayList<String>();

        if (ivaT != null && !ivaT.equals("")) {
            tipoFactorTActiv = false;
            if (ivaT.equals("ieps")) {
                tipoFactorTL.add("Tasa");
                tipoFactorTL.add("Cuota");
                tipoFactorTL.add("Exento");
            } else {
                tipoFactorTL.add("Tasa");
                tipoFactorTL.add("Exento");
            }
        } else {
            tipoFactorTActiv = true;
        }
    }

    public void tasaOCuotaRetList() {
        listTCReten = new HashMap<String, BigDecimal>();
        if (ivaR.equals("iva")) {
            System.out.println("iva " + tipoFactorR);
            if (tipoFactorR.equals("Tasa")) {
                listTCReten.put("0", new BigDecimal("0.000000"));
                listTCReten.put("8", new BigDecimal("0.080000"));
                listTCReten.put("16", new BigDecimal("0.160000"));
                tipoTasaOCRActiv = false;
            } else if (tipoFactorR.equals("Exento")) {
                listTCReten.put("", new BigDecimal("0.000000"));
                tipoTasaOCRActiv = false;
            } else {
                tipoTasaOCRActiv = true;
            }

        } else if (ivaR.equals("ieps")) {
            System.out.println("ieps");
            if (tipoFactorR.equals("Tasa")) {

                listTCReten.put("0.06", new BigDecimal("0.060000"));
                listTCReten.put("0.07", new BigDecimal("0.070000"));
                listTCReten.put("0.08", new BigDecimal("0.080000"));
                listTCReten.put("0.09", new BigDecimal("0.090000"));
                listTCReten.put("0.25", new BigDecimal("0.250000"));
                listTCReten.put("0.265", new BigDecimal("0.2650000"));
                listTCReten.put("0.3", new BigDecimal("0.300000"));
                listTCReten.put("0.304", new BigDecimal("0.304000"));
                listTCReten.put("0.5", new BigDecimal("0.500000"));
                listTCReten.put("0.53", new BigDecimal("0.530000"));
                listTCReten.put("1.6", new BigDecimal("1.600000"));
                tipoTasaOCRActiv = false;

            } else if (tipoFactorR.equals("Cuota")) {
                listTCReten.put("0", new BigDecimal("0.000000"));
                listTCReten.put("0.06", new BigDecimal("0.060000"));
                listTCReten.put("0.07", new BigDecimal("0.070000"));
                listTCReten.put("0.08", new BigDecimal("0.080000"));
                listTCReten.put("0.09", new BigDecimal("0.090000"));
                listTCReten.put("0.16", new BigDecimal("0.160000"));
                listTCReten.put("0.25", new BigDecimal("0.250000"));
                listTCReten.put("0.265", new BigDecimal("0.2650000"));
                listTCReten.put("0.3", new BigDecimal("0.300000"));
                listTCReten.put("0.304", new BigDecimal("0.304000"));
                listTCReten.put("0.5", new BigDecimal("0.500000"));
                listTCReten.put("0.53", new BigDecimal("0.530000"));
                tipoTasaOCRActiv = false;
            } else {
                tipoTasaOCRActiv = true;
            }

        } else if (ivaR.equals("isr")) {
            if (tipoFactorR.equals("Tasa")) {
                listTCReten.put("0", new BigDecimal("0.000000"));
                listTCReten.put("0.06", new BigDecimal("0.060000"));
                listTCReten.put("0.07", new BigDecimal("0.070000"));
                listTCReten.put("0.08", new BigDecimal("0.080000"));
                listTCReten.put("0.09", new BigDecimal("0.090000"));
                listTCReten.put("0.10", new BigDecimal("0.100000"));
                listTCReten.put("0.16", new BigDecimal("0.160000"));
                listTCReten.put("0.25", new BigDecimal("0.250000"));
                listTCReten.put("0.265", new BigDecimal("0.2650000"));
                listTCReten.put("0.3", new BigDecimal("0.300000"));
                listTCReten.put("0.304", new BigDecimal("0.304000"));
                listTCReten.put("0.35", new BigDecimal("0.350000"));
                tipoTasaOCRActiv = false;
            }
        }
    }

    public void tasaOCuotaTraList() {
        listTCTras = new HashMap<String, BigDecimal>();
        if (ivaT.equals("iva")) {
            System.out.println("iva " + tipoFactorT);
            if (tipoFactorT.equals("Tasa")) {
                listTCTras.put("0", new BigDecimal("0.000000"));
                listTCTras.put("8", new BigDecimal("0.080000"));
                listTCTras.put("16", new BigDecimal("0.160000"));
                tipoTasaOCTActiv = false;
            } else if (tipoFactorT.equals("Exento")) {
                listTCTras.put("", new BigDecimal("0.000000"));
                tipoTasaOCTActiv = false;
            } else {
                tipoTasaOCTActiv = true;
            }

        } else if (ivaT.equals("ieps")) {
            System.out.println("ieps");
            if (tipoFactorT.equals("Tasa")) {
                listTCTras.put("0", new BigDecimal("0.000000"));
                listTCTras.put("0.03", new BigDecimal("0.030000"));
                listTCTras.put("0.06", new BigDecimal("0.060000"));
                listTCTras.put("0.07", new BigDecimal("0.070000"));
                listTCTras.put("0.08", new BigDecimal("0.080000"));
                listTCTras.put("0.09", new BigDecimal("0.090000"));
                listTCTras.put("0.16", new BigDecimal("0.160000"));
                listTCTras.put("0.25", new BigDecimal("0.250000"));
                listTCTras.put("0.265", new BigDecimal("0.2650000"));
                listTCTras.put("0.304", new BigDecimal("0.304000"));
                listTCTras.put("1.6", new BigDecimal("1.600000"));
                tipoTasaOCTActiv = false;

            } else if (tipoFactorT.equals("Exento")) {
                listTCTras.put("", new BigDecimal("0.000000"));
                tipoTasaOCTActiv = false;
            } else if (tipoFactorT.equals("Cuota")) {
                listTCTras.put("0", new BigDecimal("0.000000"));
                tipoTasaOCTActiv = false;
            } else {
                tipoTasaOCTActiv = true;
            }

        }
    }

    public void calcularImpuestosRetenidos() {
        System.out.println("tasa o cuota " + tasaOCuotaR);
        try {
            Double br = baseR.doubleValue();
            Double tc = tasaOCuotaR.doubleValue();
            importeR = new BigDecimal(br * tc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularImpuestosTrasladados() {
        System.out.println("tasa o cuota " + tasaOCuotaT);
        try {
            Double br = baseT.doubleValue();
            Double tc = tasaOCuotaT.doubleValue();
            importeT = new BigDecimal(br * tc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarImpRete() {
        if (!ivaR.equals("") && !tipoFactorR.equals("")) {
            try {
                impRetenidos im = new impRetenidos();
                im.setBase(baseR);
                im.setImporte(importe);
                im.setIva(ivaR);
                im.setTasaOCuota(tasaOCuotaR);
                im.setTipoFactor(tipoFactorR);
                listRete.add(im);
                importe = new BigDecimal(0);
                ivaR = "";
                tasaOCuotaR = new BigDecimal(0);
                tipoFactorR = "";
                tipoFactorRActiv = true;
                tipoTasaOCRActiv = true;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
    
    public void eliminarImpRet(impRetenidos ipR){
        listRete.remove(ipR);
        if(listRete.size()==0){
            impRetPan=false;
        }
    }

    public void agregarImpTras() {
        if (!ivaT.equals("") && !tipoFactorT.equals("")) {
            try {
                impTrasladados im = new impTrasladados();
                im.setBase(baseT);
                im.setImporte(importe);
                im.setIva(ivaT);
                im.setTasaOCuota(tasaOCuotaT);
                im.setTipoFactor(tipoFactorT);
                listTras.add(im);
                importe = new BigDecimal(0);
                ivaT = "";
                tasaOCuotaT = new BigDecimal(0);
                tipoFactorT = "";
                tipoFactorTActiv = true;
                tipoTasaOCTActiv = true;
            } catch (NullPointerException e) {
            }
        }
        if(listTras.size()>0){
            impTraPan=true;
        }
    }

    public void eliminarImpTra(impTrasladados ipT){
        listTras.remove(ipT);
        if(listTras.size()==0){
            impTraPan=false;
        }
    }
    public void agregarConcepto() {
        try {
            if (cantidad.intValue() > 0) {
                conceptos c = new conceptos();
                c.setCantidad(cantidad);
                c.setDescripcion("");
                c.setDescuento(descuento);
                c.setImporte(importe);
                c.setPorcentaje(porcentajeDes);
                c.setProducto(producto);
                if (listRete.size() > 0) {
                    c.setRetenidos(listRete);
                }
                if (listTras.size() > 0) {
                    c.setTrasladados(listTras);
                }
                conceptos.add(c);
                cantidad = new BigDecimal(0);
                descuento = new BigDecimal(0);
                importe = new BigDecimal(0);
                porcentajeDes = new BigDecimal(0);
                producto = new Producto();
                listRete = new ArrayList<>();
                listTras = new ArrayList<>();
            }
        } catch (NullPointerException e) {

        }
    }

    public void eliminarConcepto(conceptos c) {
        conceptos.remove(c);
    }
}
