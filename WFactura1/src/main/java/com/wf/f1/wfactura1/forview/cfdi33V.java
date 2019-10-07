/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.beanext.Conceptos;
import com.wf.f1.wfactura1.beanext.ImpuestosLocalesC;
import com.wf.f1.wfactura1.beanext.impRetenidos;
import com.wf.f1.wfactura1.beanext.impTrasladados;
import com.wf.f1.wfactura1.controller.FormaPagoDao;
import com.wf.f1.wfactura1.controller.MetodoPagoDao;
import com.wf.f1.wfactura1.controller.MonedaDao;
import com.wf.f1.wfactura1.controller.SerieDao;
import com.wf.f1.wfactura1.controller.TipoComprobanteDao;
import com.wf.f1.wfactura1.controller.TipoRelacionDao;
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
import com.wf.f1.wfactura1.model.TipoComprobante;
import com.wf.f1.wfactura1.model.TipoRelacion;
import com.wf.f1.wfactura1.model.UsoCfdi;
import com.wf.f1.wfactura1.model.Usuario;
import com.wf.f1.wfactura1.utileria.MontoLetra;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
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
    @EJB
    private TipoComprobanteDao tipoComprobanteService;
    @EJB
    private SerieDao serieDao;
    @EJB
    private TipoRelacionDao tipoRelacionService;

    private Usuario usuarioSeleccionado;
    private String fechaActual;
    private List<Serie> seriesSeleccionadas;
    private List<String> opcSeries;
    private Serie serie;
    private Integer serieS;
    private Integer nextFolio = 0;
    private Cliente cliente;
    private UsoCfdiBean usoCfdiBean;
    private List<UsoCfdiBean> todoUsos;
    private List<MetodoPago> listaMetodosPago;
    private MetodoPago metodoPago;
    private String metodoPagoS;
    private Moneda moneda;
    private String monedaS;
    private BigDecimal tipoCambio;
    private List<Moneda> listaMonedas;
    private String formaPagoS;
    private List<FormaPago> listaFormasPago;
    private Producto producto;
    private List<Conceptos> conceptos;
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
    private boolean conceptosTableActv;
    private boolean btnAgrRet;
    private boolean btnAgrTra;
    private boolean btnAgrCon;
    private boolean inputCon;
    private BigDecimal subTotal;
    private BigDecimal descuentoTotal;
    private BigDecimal totalTrasl;
    private BigDecimal totalReten;
    private BigDecimal total;
    private String decripCompImpLocRet;
    private Integer tasaCompImpLocRet;
    private BigDecimal importeCompImpLocRet;
    private String decripCompImpLocTra;
    private Integer tasaCompImpLocTra;
    private BigDecimal importeCompImpLocTra;
    private List<ImpuestosLocalesC> listILCR;
    private List<ImpuestosLocalesC> listILCT;
    private Boolean renderILCRT;
    private Boolean renderILCRI;
    private Boolean renderILCRB;
    private Boolean renderILCTT;
    private Boolean renderILCTI;
    private Boolean renderILCTB;
    private Boolean renderILCRTable;
    private Boolean renderILCTTable;
    private String CURP;
    private String Alumno;
    private String RFCAl;
    private String autRVOE;
    private String nivelEduc;
    private Boolean renderCurp;
    private Boolean renderAlu;
    private Boolean renderRfc;
    private Boolean renderAut;
    private Boolean renderNEdu;
    private List<TipoComprobante> listaTiposComprobante;
    private String tipoComp;
    private Boolean renderSerie;
    private TipoComprobante tcc = new TipoComprobante();
    private String totalFacturaEnLetras;
    private String condicionesPago;
    private Boolean checkRelacionados;
    private TipoRelacion tipoRelacion;
    private String tipoRelacionS;
    private List<TipoRelacion> listaTiposRelacion;
    private List<String> uuidRelacionados = new ArrayList<>();
    private Boolean relacionForm;
    private Boolean relacionTable;
    private Boolean relacionSelect;
    private String uuidRela;
    private FormaPago formapago;

    @ManagedProperty(value = "#{usoCfdiService}")
    private usoCfdiService service;
    @ManagedProperty(value = "#{clienteService}")
    private clienteService serviceC;
    @ManagedProperty(value = "#{productoService}")
    private productoService serviceP;

    @PostConstruct
    public void inicializar() {
        moneda= new Moneda();
        moneda.setDecimales(new Byte("2"));
        moneda.setDescripcion("Peso Mexicano");
        moneda.setIdentifica("MXN");
        moneda.setPorcentajeVariacion("35%");
        monedaS="";
        metodoPagoS="";
        formapago = new FormaPago();
        formaPagoS="";
        tipoRelacionS = "";
        uuidRela = "";
        tipoRelacion = new TipoRelacion();
        condicionesPago = "";
        tipoCambio = new BigDecimal(1.0);
        totalFacturaEnLetras = "";
        listILCR = new ArrayList<ImpuestosLocalesC>();
        listILCT = new ArrayList<ImpuestosLocalesC>();
        decripCompImpLocTra = "";
        tasaCompImpLocTra = 0;
        importeCompImpLocTra = new BigDecimal(0);
        decripCompImpLocRet = "";
        tasaCompImpLocRet = 0;
        importeCompImpLocRet = new BigDecimal(0);
        subTotal = new BigDecimal(0);
        descuentoTotal = new BigDecimal(0);
        totalReten = new BigDecimal(0);
        totalTrasl = new BigDecimal(0);
        total = new BigDecimal(0);
        inputCon = true;
        btnAgrRet = false;
        btnAgrTra = false;
        btnAgrCon = true;
        conceptos = new ArrayList<Conceptos>();
        listTras = new ArrayList<impTrasladados>();
        listRete = new ArrayList<impRetenidos>();
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
        nextFolio = 0;
        todoUsos = new ArrayList<UsoCfdiBean>();
        //moneda.setIdentifica("MXN");
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd'T'hh:mm:ss");
        fechaActual = formatter.format(date);
        verificarSesion();

    }

    public String getMonedaS() {
        return monedaS;
    }

    public void setMonedaS(String monedaS) {
        this.monedaS = monedaS;
    }

    public String getMetodoPagoS() {
        return metodoPagoS;
    }

    public void setMetodoPagoS(String metodoPagoS) {
        this.metodoPagoS = metodoPagoS;
    }
    

    public String getTipoRelacionS() {
        return tipoRelacionS;
    }

    public void setTipoRelacionS(String tipoRelacionS) {
        this.tipoRelacionS = tipoRelacionS;
    }

    public String getUuidRela() {
        return uuidRela;
    }

    public void setUuidRela(String uuidRela) {
        this.uuidRela = uuidRela;
    }

    public Boolean getRelacionSelect() {
        return relacionSelect;
    }

    public void setRelacionSelect(Boolean relacionSelect) {
        this.relacionSelect = relacionSelect;
    }

    public Boolean getRelacionForm() {
        return relacionForm;
    }

    public void setRelacionForm(Boolean relacionForm) {
        this.relacionForm = relacionForm;
    }

    public Boolean getRelacionTable() {
        return relacionTable;
    }

    public void setRelacionTable(Boolean relacionTable) {
        this.relacionTable = relacionTable;
    }

    public List<String> getUuidRelacionados() {
        return uuidRelacionados;
    }

    public void setUuidRelacionados(List<String> uuidRelacionados) {
        this.uuidRelacionados = uuidRelacionados;
    }

    public List<TipoRelacion> getListaTiposRelacion() {
        return listaTiposRelacion;
    }

    public void setListaTiposRelacion(List<TipoRelacion> listaTiposRelacion) {
        this.listaTiposRelacion = listaTiposRelacion;
    }

    public TipoRelacion getTipoRelacion() {
        return tipoRelacion;
    }

    public void setTipoRelacion(TipoRelacion tipoRelacion) {
        this.tipoRelacion = tipoRelacion;
    }

    public Boolean getCheckRelacionados() {
        return checkRelacionados;
    }

    public void setCheckRelacionados(Boolean checkRelacionados) {
        this.checkRelacionados = checkRelacionados;
    }

    public String getCondicionesPago() {
        return condicionesPago;
    }

    public void setCondicionesPago(String condicionesPago) {
        this.condicionesPago = condicionesPago;
    }

    public BigDecimal getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(BigDecimal tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public String getTotalFacturaEnLetras() {
        return totalFacturaEnLetras;
    }

    public void setTotalFacturaEnLetras(String totalFacturaEnLetras) {
        this.totalFacturaEnLetras = totalFacturaEnLetras;
    }

    public Boolean getRenderSerie() {
        return renderSerie;
    }

    public void setRenderSerie(Boolean renderSerie) {
        this.renderSerie = renderSerie;
    }

    public String getTipoComp() {
        return tipoComp;
    }

    public void setTipoComp(String tipoComp) {
        this.tipoComp = tipoComp;
    }

    public Boolean getRenderCurp() {
        return renderCurp;
    }

    public void setRenderCurp(Boolean renderCurp) {
        this.renderCurp = renderCurp;
    }

    public Boolean getRenderAlu() {
        return renderAlu;
    }

    public void setRenderAlu(Boolean renderAlu) {
        this.renderAlu = renderAlu;
    }

    public Boolean getRenderRfc() {
        return renderRfc;
    }

    public void setRenderRfc(Boolean renderRfc) {
        this.renderRfc = renderRfc;
    }

    public Boolean getRenderAut() {
        return renderAut;
    }

    public void setRenderAut(Boolean renderAut) {
        this.renderAut = renderAut;
    }

    public Boolean getRenderNEdu() {
        return renderNEdu;
    }

    public void setRenderNEdu(Boolean renderNEdu) {
        this.renderNEdu = renderNEdu;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public String getAlumno() {
        return Alumno;
    }

    public void setAlumno(String Alumno) {
        this.Alumno = Alumno;
    }

    public String getRFCAl() {
        return RFCAl;
    }

    public void setRFCAl(String RFCAl) {
        this.RFCAl = RFCAl;
    }

    public String getAutRVOE() {
        return autRVOE;
    }

    public void setAutRVOE(String autRVOE) {
        this.autRVOE = autRVOE;
    }

    public String getNivelEduc() {
        return nivelEduc;
    }

    public void setNivelEduc(String nivelEduc) {
        this.nivelEduc = nivelEduc;
    }

    public Boolean getRenderILCRTable() {
        return renderILCRTable;
    }

    public void setRenderILCRTable(Boolean renderILCRTable) {
        this.renderILCRTable = renderILCRTable;
    }

    public Boolean getRenderILCTTable() {
        return renderILCTTable;
    }

    public void setRenderILCTTable(Boolean renderILCTTable) {
        this.renderILCTTable = renderILCTTable;
    }

    public Boolean getRenderILCRT() {
        return renderILCRT;
    }

    public void setRenderILCRT(Boolean renderILCRT) {
        this.renderILCRT = renderILCRT;
    }

    public Boolean getRenderILCRI() {
        return renderILCRI;
    }

    public void setRenderILCRI(Boolean renderILCRI) {
        this.renderILCRI = renderILCRI;
    }

    public Boolean getRenderILCRB() {
        return renderILCRB;
    }

    public void setRenderILCRB(Boolean renderILCRB) {
        this.renderILCRB = renderILCRB;
    }

    public Boolean getRenderILCTT() {
        return renderILCTT;
    }

    public void setRenderILCTT(Boolean renderILCTT) {
        this.renderILCTT = renderILCTT;
    }

    public Boolean getRenderILCTI() {
        return renderILCTI;
    }

    public void setRenderILCTI(Boolean renderILCTI) {
        this.renderILCTI = renderILCTI;
    }

    public Boolean getRenderILCTB() {
        return renderILCTB;
    }

    public void setRenderILCTB(Boolean renderILCTB) {
        this.renderILCTB = renderILCTB;
    }

    public BigDecimal getTotalTrasl() {
        return totalTrasl;
    }

    public void setTotalTrasl(BigDecimal totalTrasl) {
        this.totalTrasl = totalTrasl;
    }

    public BigDecimal getTotalReten() {
        return totalReten;
    }

    public void setTotalReten(BigDecimal totalReten) {
        this.totalReten = totalReten;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getDescuentoTotal() {
        return descuentoTotal;
    }

    public void setDescuentoTotal(BigDecimal descuentoTotal) {
        this.descuentoTotal = descuentoTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public boolean isInputCon() {
        return inputCon;
    }

    public void setInputCon(boolean inputCon) {
        this.inputCon = inputCon;
    }

    public boolean isBtnAgrCon() {
        return btnAgrCon;
    }

    public void setBtnAgrCon(boolean btnAgrCon) {
        this.btnAgrCon = btnAgrCon;
    }

    public boolean isBtnAgrTra() {
        return btnAgrTra;
    }

    public void setBtnAgrTra(boolean btnAgrTra) {
        this.btnAgrTra = btnAgrTra;
    }

    public boolean isBtnAgrRet() {
        return btnAgrRet;
    }

    public void setBtnAgrRet(boolean btnAgrRet) {
        this.btnAgrRet = btnAgrRet;
    }

    public boolean isConceptosTableActv() {
        return conceptosTableActv;
    }

    public void setConceptosTableActv(boolean conceptosTableActv) {
        this.conceptosTableActv = conceptosTableActv;
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

    public List<Conceptos> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<Conceptos> conceptos) {
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
        return formaPagoS;
    }

    public void setFormaPagoS(String formaPagoS) {
        this.formaPagoS = formaPagoS;
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

    public String getDecripCompImpLocRet() {
        return decripCompImpLocRet;
    }

    public void setDecripCompImpLocRet(String decripCompImpLocRet) {
        this.decripCompImpLocRet = decripCompImpLocRet;
    }

    public Integer getTasaCompImpLocRet() {
        return tasaCompImpLocRet;
    }

    public void setTasaCompImpLocRet(Integer tasaCompImpLocRet) {
        this.tasaCompImpLocRet = tasaCompImpLocRet;
    }

    public BigDecimal getImporteCompImpLocRet() {
        return importeCompImpLocRet;
    }

    public void setImporteCompImpLocRet(BigDecimal importeCompImpLocRet) {
        this.importeCompImpLocRet = importeCompImpLocRet;
    }

    public String getDecripCompImpLocTra() {
        return decripCompImpLocTra;
    }

    public void setDecripCompImpLocTra(String decripCompImpLocTra) {
        this.decripCompImpLocTra = decripCompImpLocTra;
    }

    public Integer getTasaCompImpLocTra() {
        return tasaCompImpLocTra;
    }

    public void setTasaCompImpLocTra(Integer tasaCompImpLocTra) {
        this.tasaCompImpLocTra = tasaCompImpLocTra;
    }

    public BigDecimal getImporteCompImpLocTra() {
        return importeCompImpLocTra;
    }

    public void setImporteCompImpLocTra(BigDecimal importeCompImpLocTra) {
        this.importeCompImpLocTra = importeCompImpLocTra;
    }

    public List<ImpuestosLocalesC> getListILCR() {
        return listILCR;
    }

    public void setListILCR(List<ImpuestosLocalesC> listILCR) {
        this.listILCR = listILCR;
    }

    public List<ImpuestosLocalesC> getListILCT() {
        return listILCT;
    }

    public void setListILCT(List<ImpuestosLocalesC> listILCT) {
        this.listILCT = listILCT;
    }

    public List<TipoComprobante> getListaTiposComprobante() {
        return listaTiposComprobante;
    }

    public void setListaTiposComprobante(List<TipoComprobante> listaTiposComprobante) {
        this.listaTiposComprobante = listaTiposComprobante;
    }

    public Integer getSerieS() {
        return serieS;
    }

    public void setSerieS(Integer serieS) {
        this.serieS = serieS;
    }

    public void verificarSesion() {
        System.out.println("verificando..................");
        try {
            FacesContext context = FacesContext.getCurrentInstance();
            usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
            if (usuarioSeleccionado == null) {
                context.getExternalContext().redirect("index.xhtml");
            } else {

                listarTiposComprobante();
                listarUsos();
                listarMetodosPago();
                listarMonedas();
                listarFormasPago();
                listarTiposRelacion();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seriesSelc() {
        if (tipoComp != null && !tipoComp.equals("")) {

            for (TipoComprobante tc : listaTiposComprobante) {
                if (tc.getIdentifica().equals(tipoComp)) {
                    tcc = tc;
                }
            }
            seriesSeleccionadas = serieDao.listarSeriesPorTipoComprobanteYUsuario(tcc, usuarioSeleccionado);
            renderSerie = true;
        } else {
            renderSerie = false;
            nextFolio = 0;
        }
    }

    public void listarTiposComprobante() {
        listaTiposComprobante = tipoComprobanteService.listaTiposNormal();
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

    public void folioSiguiente() {
        for (Serie ss : seriesSeleccionadas) {
            if (ss.getIdentifica() == serieS) {
                serie = ss;
                nextFolio = serie.getFolioActual() + 1;
                break;
            } else {
                nextFolio = 0;
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
                btnAgrCon = false;
            } else {
                btnAgrCon = true;
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
            importeR = new BigDecimal(br * tc).setScale(2, RoundingMode.HALF_UP);
            btnAgrRet = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularImpuestosTrasladados() {
        System.out.println("tasa o cuota " + tasaOCuotaT);
        try {
            Double br = baseT.doubleValue();
            Double tc = tasaOCuotaT.doubleValue();
            importeT = new BigDecimal(br * tc).setScale(2, RoundingMode.HALF_UP);
            btnAgrTra = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarImpRete() {
        if (!ivaR.equals("") && !tipoFactorR.equals("")) {
            try {
                System.out.println("datos en imprt rete " + baseR + " " + importeR + " " + ivaR + " " + tasaOCuotaR + " " + tipoFactorR);
                impRetenidos im = new impRetenidos();
                im.setBase(baseR);
                im.setImporte(importeR);
                im.setIva(ivaR);
                im.setTasaOCuota(tasaOCuotaR);
                im.setTipoFactor(tipoFactorR);
                listRete.add(im);
                importeR = new BigDecimal(0);
                ivaR = "";
                tasaOCuotaR = new BigDecimal(0);
                tipoFactorR = "";
                tipoFactorRActiv = true;
                tipoTasaOCRActiv = true;
                btnAgrRet = false;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (listRete.size() > 0) {
            impRetPan = true;
        } else {
            impRetPan = false;
        }
    }

    public void eliminarImpRet(impRetenidos ipR) {

        try {
            listRete.remove(ipR);
            System.out.println("tamaño de la lista 1 " + listRete.size());
            if (listRete == null || listRete.size() == 0) {
                impRetPan = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarImpTras() {
        if (!ivaT.equals("") && !tipoFactorT.equals("")) {
            try {
                impTrasladados im = new impTrasladados();
                im.setBase(baseT);
                im.setImporte(importeT);
                im.setIva(ivaT);
                im.setTasaOCuota(tasaOCuotaT);
                im.setTipoFactor(tipoFactorT);
                listTras.add(im);
                importeT = new BigDecimal(0);
                ivaT = "";
                tasaOCuotaT = new BigDecimal(0);
                tipoFactorT = "";
                tipoFactorTActiv = true;
                tipoTasaOCTActiv = true;
                btnAgrTra = false;
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        if (listTras.size() > 0) {
            impTraPan = true;
        } else {
            impTraPan = false;
        }
    }

    public void eliminarImpTra(impTrasladados ipT) {

        try {
            listTras.remove(ipT);
            System.out.println("tamaño de la lista 2 " + listTras.size());
            if (listTras == null || listTras.size() == 0) {
                impTraPan = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarConcepto() {
        try {
            if (cantidad.intValue() > 0) {
                System.out.println("agregando ........");
                Conceptos c = new Conceptos();
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
                producto.setIdentifica(0);
                listRete = new ArrayList<>();
                listTras = new ArrayList<>();
                conceptosTableActv = true;
                btnAgrCon = true;
                checkRetenidos = false;
                checktrasladados = false;
                activChecksImp = false;
                impRetPan = false;
                impTraPan = false;
                btnAgrRet = false;
                btnAgrTra = false;
                inputCon = true;
                agregarVRetenciones();
                agregarVTrasladados();
                System.out.println("agregado ........");
            }
            calcularTotalGlobal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void calcularTotalGlobal() {
        if (conceptos.size() > 0) {
            Double importeC = 0.0;
            Double decuentoC = 0.0;
            Double retenidosC = 0.0;
            Double trasladadosC = 0.0;
            for (Conceptos c : conceptos) {
                System.out.println("importe ");
                importeC += c.getImporte().setScale(2, RoundingMode.HALF_UP).doubleValue();
                System.out.println("->" + c.getImporte());
                if (c.getDescuento() != null) {

                    decuentoC += c.getDescuento().setScale(2, RoundingMode.HALF_UP).doubleValue();
                    System.out.println("descuento " + decuentoC);
                }
                if (c.getRetenidos() != null && c.getRetenidos().size() > 0) {
                    System.out.println("retenidos ");
                    for (impRetenidos r : c.getRetenidos()) {

                        retenidosC += r.getImporte().setScale(2, RoundingMode.HALF_UP).doubleValue();
                        System.out.println("->" + retenidosC);
                    }
                }
                if (c.getTrasladados() != null && c.getTrasladados().size() > 0) {
                    System.out.println("trasladados ");
                    for (impTrasladados t : c.getTrasladados()) {

                        trasladadosC += t.getImporte().setScale(2, RoundingMode.HALF_UP).doubleValue();
                        System.out.println("->" + trasladadosC);
                    }
                }
            }
            subTotal = new BigDecimal(importeC);
            descuentoTotal = new BigDecimal(decuentoC);
            total = new BigDecimal((((subTotal.doubleValue() + trasladadosC + importeCompImpLocTra.doubleValue()) - retenidosC) - descuentoTotal.doubleValue()));
            System.out.println("si realizo la operacion " + total);
        } else {
            System.out.println("no realizo la operacion");
            subTotal = new BigDecimal(0);
            descuentoTotal = new BigDecimal(0);
            total = new BigDecimal(0);
        }
        montoLetra();
    }

    public void eliminarConcepto(Conceptos c) {
        conceptos.remove(c);
        if (conceptos.size() == 0) {
            conceptosTableActv = false;
        }
    }

    public void habilitarInputs() {
        inputCon = false;
    }

    public void agregarILCR() {
        if ((decripCompImpLocRet != null && !decripCompImpLocRet.equals("")) && (tasaCompImpLocRet > 0) && importeCompImpLocRet.intValue() > 0) {
            ImpuestosLocalesC imR = new ImpuestosLocalesC();
            imR.setDescripcion(decripCompImpLocRet);
            imR.setPorcentaje(tasaCompImpLocRet);
            imR.setImporte(importeCompImpLocRet);
            listILCR.add(imR);
            Double tt = total.doubleValue();
            total = new BigDecimal(tt - importeCompImpLocRet.doubleValue()).setScale(2, RoundingMode.HALF_UP);
            totalReten = new BigDecimal(totalReten.doubleValue() + importeCompImpLocRet.doubleValue());
            decripCompImpLocRet = "";
            tasaCompImpLocRet = 0;
            importeCompImpLocRet = new BigDecimal(0);
        }
        if (listILCR.size() > 0) {
            renderILCRTable = true;
        } else {
            renderILCRTable = false;
        }
        montoLetra();
        renderILCRT = false;
        renderILCRI = false;
        renderILCRB = false;
    }

    public void eliminarILCR(ImpuestosLocalesC imp) {

        Double tt = total.doubleValue();
        total = new BigDecimal(tt + imp.getImporte().doubleValue()).setScale(2, RoundingMode.HALF_UP);
        totalReten = new BigDecimal(totalReten.doubleValue() - imp.getImporte().doubleValue());
        listILCR.remove(imp);
        if (listILCR.size() > 0) {
            renderILCRTable = true;
        } else {
            renderILCRTable = false;
        }
        montoLetra();
    }

    public void agregarILCT() {
        if ((decripCompImpLocTra != null && !decripCompImpLocTra.equals("")) && (tasaCompImpLocTra > 0) && importeCompImpLocTra.intValue() > 0) {
            ImpuestosLocalesC imR = new ImpuestosLocalesC();
            imR.setDescripcion(decripCompImpLocTra);
            imR.setPorcentaje(tasaCompImpLocTra);
            imR.setImporte(importeCompImpLocTra);
            listILCT.add(imR);
            Double tt = total.doubleValue();
            total = new BigDecimal(tt + importeCompImpLocTra.doubleValue()).setScale(2, RoundingMode.HALF_UP);
            totalTrasl = new BigDecimal(totalTrasl.doubleValue() + importeCompImpLocTra.doubleValue());
            decripCompImpLocTra = "";
            tasaCompImpLocTra = 0;
            importeCompImpLocTra = new BigDecimal(0);
            if (listILCT.size() > 0) {
                renderILCTTable = true;
            } else {
                renderILCTTable = false;
            }
        }
        montoLetra();
        renderILCTT = false;
        renderILCTI = false;
        renderILCTB = false;
    }

    public void eliminarILCT(ImpuestosLocalesC imp) {
        Double tt = total.doubleValue();
        total = new BigDecimal(tt - imp.getImporte().doubleValue()).setScale(2, RoundingMode.HALF_UP);
        totalTrasl = new BigDecimal(totalTrasl.doubleValue() - imp.getImporte().doubleValue());
        listILCT.remove(imp);
        if (listILCT.size() > 0) {
            renderILCTTable = true;
        } else {
            renderILCTTable = false;
        }
        montoLetra();
    }

    public void habilitarRenderILCRT() {
        if (decripCompImpLocRet != null && !decripCompImpLocRet.equals("")) {
            renderILCRT = true;
        } else {
            renderILCRT = false;
        }
    }

    public void habilitarRenderILCRI() {
        if (tasaCompImpLocRet != null && tasaCompImpLocRet > 0) {
            renderILCRI = true;
        } else {
            renderILCRI = false;
        }
    }

    public void habilitarRenderILCRB() {
        Double impLCR = importeCompImpLocRet.doubleValue();
        if (impLCR > 0) {
            renderILCRB = true;
        } else {
            renderILCRB = false;
        }

    }

    public void habilitarRenderILCTT() {
        if (decripCompImpLocTra != null && !decripCompImpLocTra.equals("")) {
            renderILCTT = true;
        } else {
            renderILCTT = false;
        }
    }

    public void habilitarRenderILCTI() {
        if (tasaCompImpLocTra != null && tasaCompImpLocTra > 0) {
            renderILCTI = true;
        } else {
            renderILCTI = false;
        }
    }

    public void habilitarRenderILCTB() {
        Double impLCR = importeCompImpLocTra.doubleValue();
        if (impLCR > 0) {
            renderILCTB = true;
        } else {
            renderILCTB = false;
        }

    }

    public void listarTiposRelacion() {
        listaTiposRelacion = tipoRelacionService.findAllTipoRelaciones();
    }

    public void habilitarRelacionado() {
        if (checkRelacionados) {
            relacionForm = true;
        } else {
            relacionForm = false;
            tipoRelacion = new TipoRelacion();
            //uuidRel="";
        }
    }

    public void agregarRelacionado() {
        if (!uuidRela.equals("") && tipoRelacion != null) {
            uuidRelacionados.add(uuidRela);
            uuidRela = "";
            relacionSelect = true;
            relacionTable = true;
        } else {
            System.out.println("no entro");
        }
    }

    public void eliminarRelacionado(String uuid) {
        uuidRelacionados.remove(uuid);
        if (uuidRelacionados.size() == 0) {
            tipoRelacion = new TipoRelacion();
            uuidRela = "";
            relacionSelect = false;
            relacionTable = false;
            tipoRelacionS = "";
        }
    }

    public String mayus(String string) {
        string = string.toUpperCase();
        return string;
    }

    public void activarAlum() {
        if (CURP != null && !CURP.equals("")) {
            renderAlu = true;
            CURP = mayus(CURP);
        } else {
            renderAlu = false;
            renderRfc = false;
            renderAut = false;
            renderNEdu = false;
        }
    }

    public void activarRfc() {
        if (Alumno != null && !Alumno.equals("")) {
            renderRfc = true;
            Alumno = mayus(Alumno);
        } else {
            renderRfc = false;
            renderAut = false;
            renderNEdu = false;
        }
    }

    public void activarAutRVOE() {
        if (RFCAl != null && !RFCAl.equals("")) {
            renderAut = true;
            RFCAl = mayus(RFCAl);
        } else {
            renderAut = false;
            renderNEdu = false;
        }
    }

    public void activarNEduc() {
        if (autRVOE != null && !autRVOE.equals("")) {
            renderNEdu = true;
        } else {
            renderNEdu = false;
        }
    }

    public void montoLetra() {
        MontoLetra m = new MontoLetra();
        if (total.doubleValue() >= 0) {
            totalFacturaEnLetras = m.Convertir(total.toString(), true);
        }
    }

    public void validarCamposCFDI() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (tipoComp != null && !tipoComp.equals("")) {
            if (serie != null) {
                if (cliente != null) {
                    if (usoCfdiBean == null) {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Receptor) Elegir Uso de CFDI"));
                    } else {
                        System.out.println("esto es  "+usoCfdiBean.getDescripcion());
                        if (!formaPagoS.equals("")) {
                            if (metodoPago != null && !metodoPago.getIdentifica().equals("")) {
                                if (tipoCambio.doubleValue() > 0) {                                    
                                    if (conceptos.size()>0) {
                                        if(uuidRelacionados.size()>0){
                                            
                                        }
                                        if(listILCR.size()>0){
                                            
                                        }
                                        if(listILCT.size()>0){
                                            
                                        }
                                        
                                    } else {
                                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Conceptos) Elegir Producto"));
                                    }
                                } else {
                                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Forma de pago) Tipo de cambio debe de ser mayor a 0.0(cero)"));
                                }
                            } else {
                                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Forma de pago) Elegir Método de pago"));
                            }
                        } else {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Forma de pago) Elegir Forma de pago"));
                        }
                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Receptor) Elegir Receptor"));
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Emisor) Elegir Serie"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Emisor) Elegir tipo Documento"));
        }

    }

    public void tipoRel() {
        if (!tipoRelacionS.equals("")) {
            for (TipoRelacion tp : listaTiposRelacion) {
                if (tp.getIdentifica().equals(tipoRelacionS)) {
                    tipoRelacion = tp;
                }
            }
            tipoRelacionS = tipoRelacionS;
        } else {
            tipoRelacion = new TipoRelacion();
        }
    }

    public void verif() {
        uuidRela = uuidRela.toUpperCase();
    }

    public void formPag() {
        if (!formaPagoS.equals("")) {
            for(FormaPago fp:listaFormasPago){
                if(fp.getIdentifica().equals(formaPagoS)){
                    formapago=fp;
                }
            }
        }else{
            formapago= new FormaPago();
        }
        formaPagoS=formaPagoS;
    }
    
    public void metodoP(){
       if(!metodoPagoS.equals("")){
           for(MetodoPago mp:listaMetodosPago){
               if(mp.getIdentifica().equals(metodoPagoS)){
                   metodoPago=mp;
               }
           }
       } else{
           metodoPago= new MetodoPago();
       }
       metodoPagoS=metodoPagoS;
    }
    public void monedaM(){
        System.out.println(monedaS + "  ------------------->");
        if(!monedaS.equals("")){
            for(Moneda m: listaMonedas){
                if(m.getIdentifica().equals(monedaS)){
                    moneda=m;
                }
            }
        }else{
            moneda = new Moneda();
        }
        monedaS=monedaS;
    }
    
    public void usoCfdiBeanM(UsoCfdiBean us){
        usoCfdiBean=us;
    }
}
