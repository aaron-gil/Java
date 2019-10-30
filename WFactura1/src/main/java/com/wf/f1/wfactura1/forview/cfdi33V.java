/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.forview;

import com.wf.f1.wfactura1.beanext.ComplementoComExt;
import com.wf.f1.wfactura1.beanext.ComplementoSEP;
import com.wf.f1.wfactura1.beanext.Conceptos;
import com.wf.f1.wfactura1.beanext.DescEspecCompComExt;
import com.wf.f1.wfactura1.beanext.DestinatarioCompComExt;
import com.wf.f1.wfactura1.beanext.DomicilioCompComExt;
import com.wf.f1.wfactura1.beanext.ImpuestosLocalesC;
import com.wf.f1.wfactura1.beanext.MercanciasCompComExt;
import com.wf.f1.wfactura1.beanext.PropietarioCompComExt;
import com.wf.f1.wfactura1.beanext.impRetenidos;
import com.wf.f1.wfactura1.beanext.impTrasladados;
import com.wf.f1.wfactura1.controller.ClienteDao;
import com.wf.f1.wfactura1.controller.FacturaDao;
import com.wf.f1.wfactura1.controller.FormaPagoDao;
import com.wf.f1.wfactura1.controller.MetodoPagoDao;
import com.wf.f1.wfactura1.controller.MonedaDao;
import com.wf.f1.wfactura1.controller.ProductoDao;
import com.wf.f1.wfactura1.controller.SerieDao;
import com.wf.f1.wfactura1.controller.TipoComprobanteDao;
import com.wf.f1.wfactura1.controller.TipoRelacionDao;
import com.wf.f1.wfactura1.controller.UsoCfdiDao;
import com.wf.f1.wfactura1.controller.ZonahorariaDao;
import com.wf.f1.wfactura1.converterbeans.UsoCfdiBean;
import com.wf.f1.wfactura1.converterbeans.usoCfdiService;
import com.wf.f1.wfactura1.entities.Certs;
import com.wf.f1.wfactura1.entities.HibernateUtil;
import com.wf.f1.wfactura1.model.Cliente;
import com.wf.f1.wfactura1.model.Factura;
import com.wf.f1.wfactura1.model.FormaPago;
import com.wf.f1.wfactura1.model.MetodoPago;
import com.wf.f1.wfactura1.model.Moneda;
import com.wf.f1.wfactura1.model.Producto;
import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.TipoComprobante;
import com.wf.f1.wfactura1.model.TipoRelacion;
import com.wf.f1.wfactura1.model.UsoCfdi;
import com.wf.f1.wfactura1.model.Usuario;
import com.wf.f1.wfactura1.utileria.Conversion;
import com.wf.f1.wfactura1.utileria.CreacionArchivo;
import com.wf.f1.wfactura1.utileria.CrearXML;
import com.wf.f1.wfactura1.utileria.EnvioEmail;
import com.wf.f1.wfactura1.utileria.MontoLetra;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author WF Consulting
 */
@ManagedBean(name = "cfdi33V")
@ViewScoped
public class cfdi33V implements Serializable {

    private static final long serialVersionUID = 1L;

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
    @EJB
    private ZonahorariaDao zonahorariaDao;
    @EJB
    private FacturaDao facturaService;
    @EJB
    private ClienteDao clienteDao;
    @EJB
    private ProductoDao productoDao;
    @EJB
    private UsoCfdiDao usoCfdiDao;

    private Usuario usuarioSeleccionado;
    private List<Serie> seriesSeleccionadas;
    private List<String> opcSeries;
    private Serie serie;
    private Integer serieS;
    private Integer nextFolio = 0;
    private Cliente cliente;
    private UsoCfdiBean usoCfdiBean;
    private String numRegIdTrib;
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
    private Factura factura;
    private String observaciones;
    private ComplementoComExt compComExt;
    private List<PropietarioCompComExt> propietario;
    private List<DestinatarioCompComExt> destinatario;
    private List<DomicilioCompComExt> domicilioDestin;
    private List<MercanciasCompComExt> mercancia;
    private List<DescEspecCompComExt> descpEsp;
    private Boolean tablaPropietarios;
    private Boolean tablaDomicilios;
    private Boolean tablaDestinatarios;
    private Boolean tablaDescripciones;
    private Boolean tablaMercancias;
    private String tipoOperacionCE;
    private String motivoTrasladoCE;
    private String clavePedimentoCE;
    private String certificadoOrigenCE;
    private String numCertificadoOrigenCE;
    private String numExportConfiabCE;
    private String incotermCE;
    private String subdivisionCE;
    private String observacionesCE;
    private String tipoCambioUSDCE;
    private String totalUSDCE;
    private String CurpCE;
    private String CalleECE;
    private String numExtECE;
    private String numIntECE;
    private String coloniaECE;
    private String localidadECE;
    private String ReferenciaECE;
    private String municipioECE;
    private String estadoECE;
    private String paisECE;
    private String cpECE;
    private String CalleRCE;
    private String numExtRCE;
    private String numIntRCE;
    private String coloniaRCE;
    private String localidadRCE;
    private String ReferenciaRCE;
    private String municipioRCE;
    private String estadoRCE;
    private String paisRCE;
    private String cpRCE;
    private String numRegIdTribECE;
    private String numRegIdTribPCE;
    private String recidenciaFiscalPCE;
    private String numRegIdTribDCE;
    private String nombreDCE;
    private String CalleDCE;
    private String numExtDCE;
    private String numIntDCE;
    private String coloniaDCE;
    private String localidadDCE;
    private String ReferenciaDCE;
    private String municipioDCE;
    private String estadoDCE;
    private String paisDCE;
    private String cpDCE;
    private String noIdentificacionCE;
    private String fraccionArancelariaCE;
    private String cantidadAduanaCE;
    private String unidadAdunaCE;
    private String valorUnitarioAduanaCE;
    private String valorDolaresCE;
    private String marcaCE;
    private String modeloCE;
    private String submodeloCE;
    private String numSerieCE;
    private Boolean checkComplementoSEP;
    private Boolean checkComplementoCE;
    private Boolean checkComplementoIL;
    private String strComplementoSEP;
    private String strComplementoCE;
    private String strComplementoIL;
    private String strUUIDRel;
    private Boolean tipoCambioBool;
    private String descripcionProducto;
    private BigDecimal valorUnitarioB;
    private String xmlTimbrado;
    private StreamedContent filePdfVP;
    private String vpPdf;
    private Boolean vpPdfB;
    private Boolean btnsVE;
    private String errorTimbrado;
    private Boolean errorBol;
    private List<UsoCfdiBean> allUsos;
    private List<Cliente> allClientes;
    private List<Producto> allProducto;
    private String clienteString;
    private String productoString;

    @ManagedProperty(value = "#{usoCfdiService}")
    private usoCfdiService service;

    @PostConstruct
    public void inicializar() {
        clienteString = "";
        productoString = "";
        usuarioSeleccionado = new Usuario();
        FacesContext context = FacesContext.getCurrentInstance();
        usuarioSeleccionado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioElegido");
        allClientes = new ArrayList<Cliente>();
        allUsos = new ArrayList<UsoCfdiBean>();
        allProducto = new ArrayList<Producto>();
        vpPdf = "";//System.getProperty("java.io.tmpdir")+"\\facturaTem"+usuarioSeleccionado.getNombre()+".pdf";
        valorUnitarioB = new BigDecimal(0);
        descripcionProducto = "";
        tipoCambioBool = true;
        strComplementoSEP = "none";
        strComplementoCE = "none";
        strComplementoIL = "none";
        strUUIDRel = "none";
        tipoOperacionCE = "2";
        descpEsp = new ArrayList<>();
        mercancia = new ArrayList<>();
        destinatario = new ArrayList<>();
        domicilioDestin = new ArrayList<>();
        propietario = new ArrayList<>();
        compComExt = new ComplementoComExt();
        observaciones = "";
        renderAlu = true;
        renderRfc = true;
        renderAut = true;
        renderNEdu = true;
        numRegIdTrib = "";
        factura = new Factura();
        CURP = "";
        moneda = new Moneda();
        moneda.setDecimales(new Byte("2"));
        moneda.setDescripcion("Peso Mexicano");
        moneda.setIdentifica("MXN");
        moneda.setPorcentajeVariacion("35%");
        monedaS = "MXN";
        metodoPagoS = "";
        formapago = new FormaPago();
        formaPagoS = "";
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
        seriesSeleccionadas = new ArrayList<Serie>();
        opcSeries = new ArrayList<String>();
        nextFolio = 0;
        todoUsos = new ArrayList<UsoCfdiBean>();
        renderILCTT = true;
        renderILCTI = true;
        renderILCTB = true;
        renderILCRT = true;
        renderILCRI = true;
        renderILCRB = true;
        errorTimbrado = "";
        verificarSesion();
        cargar();

    }

    public void cargar() {
        List<UsoCfdi> allUsosB = usosDao.findAllUsoCfdies();
        int i = 1;
        for (UsoCfdi u : allUsosB) {
            UsoCfdiBean ub = new UsoCfdiBean();
            ub.setId(i);
            ub.setIdentifica(u.getIdentifica());
            ub.setDescripcion(u.getDescripcion());
            i++;
            allUsos.add(ub);
        }

        allClientes = clienteDao.listarClientesPorStatusYUsuario(true, usuarioSeleccionado);
        allProducto = productoDao.listarProductosPorStatusYUsuario(true, usuarioSeleccionado);
    }

    public String getProductoString() {
        return productoString;
    }

    public void setProductoString(String productoString) {
        this.productoString = productoString;
    }

    public String getClienteString() {
        return clienteString;
    }

    public void setClienteString(String clienteString) {
        this.clienteString = clienteString;
    }

    public Boolean getErrorBol() {
        return errorBol;
    }

    public void setErrorBol(Boolean errorBol) {
        this.errorBol = errorBol;
    }

    public String getErrorTimbrado() {
        return errorTimbrado;
    }

    public void setErrorTimbrado(String errorTimbrado) {
        this.errorTimbrado = errorTimbrado;
    }

    public Boolean getBtnsVE() {
        return btnsVE;
    }

    public void setBtnsVE(Boolean btnsVE) {
        this.btnsVE = btnsVE;
    }

    public Boolean getVpPdfB() {
        return vpPdfB;
    }

    public void setVpPdfB(Boolean vpPdfB) {
        this.vpPdfB = vpPdfB;
    }

    public String getVpPdf() {
        return vpPdf;
    }

    public void setVpPdf(String vpPdf) {
        this.vpPdf = vpPdf;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public Boolean getTipoCambioBool() {
        return tipoCambioBool;
    }

    public void setTipoCambioBool(Boolean tipoCambioBool) {
        this.tipoCambioBool = tipoCambioBool;
    }

    public String getStrComplementoSEP() {
        return strComplementoSEP;
    }

    public void setStrComplementoSEP(String strComplementoSEP) {
        this.strComplementoSEP = strComplementoSEP;
    }

    public String getStrComplementoCE() {
        return strComplementoCE;
    }

    public void setStrComplementoCE(String strComplementoCE) {
        this.strComplementoCE = strComplementoCE;
    }

    public String getStrComplementoIL() {
        return strComplementoIL;
    }

    public void setStrComplementoIL(String strComplementoIL) {
        this.strComplementoIL = strComplementoIL;
    }

    public String getStrUUIDRel() {
        return strUUIDRel;
    }

    public void setStrUUIDRel(String strUUIDRel) {
        this.strUUIDRel = strUUIDRel;
    }

    public Boolean getCheckComplementoSEP() {
        return checkComplementoSEP;
    }

    public void setCheckComplementoSEP(Boolean checkComplementoSEP) {
        this.checkComplementoSEP = checkComplementoSEP;
    }

    public Boolean getCheckComplementoCE() {
        return checkComplementoCE;
    }

    public void setCheckComplementoCE(Boolean checkComplementoCE) {
        this.checkComplementoCE = checkComplementoCE;
    }

    public Boolean getCheckComplementoIL() {
        return checkComplementoIL;
    }

    public void setCheckComplementoIL(Boolean checkComplementoIL) {
        this.checkComplementoIL = checkComplementoIL;
    }

    public List<PropietarioCompComExt> getPropietario() {
        return propietario;
    }

    public void setPropietario(List<PropietarioCompComExt> propietario) {
        this.propietario = propietario;
    }

    public List<DestinatarioCompComExt> getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(List<DestinatarioCompComExt> destinatario) {
        this.destinatario = destinatario;
    }

    public List<DomicilioCompComExt> getDomicilioDestin() {
        return domicilioDestin;
    }

    public void setDomicilioDestin(List<DomicilioCompComExt> domicilioDestin) {
        this.domicilioDestin = domicilioDestin;
    }

    public List<MercanciasCompComExt> getMercancia() {
        return mercancia;
    }

    public void setMercancia(List<MercanciasCompComExt> mercancia) {
        this.mercancia = mercancia;
    }

    public List<DescEspecCompComExt> getDescpEsp() {
        return descpEsp;
    }

    public void setDescpEsp(List<DescEspecCompComExt> descpEsp) {
        this.descpEsp = descpEsp;
    }

    public String getMunicipioRCE() {
        return municipioRCE;
    }

    public void setMunicipioRCE(String municipioRCE) {
        this.municipioRCE = municipioRCE;
    }

    public String getTipoOperacionCE() {
        return tipoOperacionCE;
    }

    public void setTipoOperacionCE(String tipoOperacionCE) {
        this.tipoOperacionCE = tipoOperacionCE;
    }

    public String getMotivoTrasladoCE() {
        return motivoTrasladoCE;
    }

    public void setMotivoTrasladoCE(String motivoTrasladoCE) {
        this.motivoTrasladoCE = motivoTrasladoCE;
    }

    public String getClavePedimentoCE() {
        return clavePedimentoCE;
    }

    public void setClavePedimentoCE(String clavePedimentoCE) {
        this.clavePedimentoCE = clavePedimentoCE;
    }

    public String getCertificadoOrigenCE() {
        return certificadoOrigenCE;
    }

    public void setCertificadoOrigenCE(String certificadoOrigenCE) {
        this.certificadoOrigenCE = certificadoOrigenCE;
    }

    public String getNumCertificadoOrigenCE() {
        return numCertificadoOrigenCE;
    }

    public void setNumCertificadoOrigenCE(String numCertificadoOrigenCE) {
        this.numCertificadoOrigenCE = numCertificadoOrigenCE;
    }

    public String getNumExportConfiabCE() {
        return numExportConfiabCE;
    }

    public void setNumExportConfiabCE(String numExportConfiabCE) {
        this.numExportConfiabCE = numExportConfiabCE;
    }

    public String getIncotermCE() {
        return incotermCE;
    }

    public void setIncotermCE(String incotermCE) {
        this.incotermCE = incotermCE;
    }

    public String getSubdivisionCE() {
        return subdivisionCE;
    }

    public void setSubdivisionCE(String subdivisionCE) {
        this.subdivisionCE = subdivisionCE;
    }

    public String getObservacionesCE() {
        return observacionesCE;
    }

    public void setObservacionesCE(String observacionesCE) {
        this.observacionesCE = observacionesCE;
    }

    public String getTipoCambioUSDCE() {
        return tipoCambioUSDCE;
    }

    public void setTipoCambioUSDCE(String tipoCambioUSDCE) {
        this.tipoCambioUSDCE = tipoCambioUSDCE;
    }

    public String getTotalUSDCE() {
        return totalUSDCE;
    }

    public void setTotalUSDCE(String totalUSDCE) {
        this.totalUSDCE = totalUSDCE;
    }

    public String getCurpCE() {
        return CurpCE;
    }

    public void setCurpCE(String CurpCE) {
        this.CurpCE = CurpCE;
    }

    public String getCalleECE() {
        return CalleECE;
    }

    public void setCalleECE(String CalleECE) {
        this.CalleECE = CalleECE;
    }

    public String getNumExtECE() {
        return numExtECE;
    }

    public void setNumExtECE(String numExtECE) {
        this.numExtECE = numExtECE;
    }

    public String getNumIntECE() {
        return numIntECE;
    }

    public void setNumIntECE(String numIntECE) {
        this.numIntECE = numIntECE;
    }

    public String getColoniaECE() {
        return coloniaECE;
    }

    public void setColoniaECE(String coloniaECE) {
        this.coloniaECE = coloniaECE;
    }

    public String getLocalidadECE() {
        return localidadECE;
    }

    public void setLocalidadECE(String localidadECE) {
        this.localidadECE = localidadECE;
    }

    public String getReferenciaECE() {
        return ReferenciaECE;
    }

    public void setReferenciaECE(String ReferenciaECE) {
        this.ReferenciaECE = ReferenciaECE;
    }

    public String getMunicipioECE() {
        return municipioECE;
    }

    public void setMunicipioECE(String municipioECE) {
        this.municipioECE = municipioECE;
    }

    public String getEstadoECE() {
        return estadoECE;
    }

    public void setEstadoECE(String estadoECE) {
        this.estadoECE = estadoECE;
    }

    public String getPaisECE() {
        return paisECE;
    }

    public void setPaisECE(String paisECE) {
        this.paisECE = paisECE;
    }

    public String getCpECE() {
        return cpECE;
    }

    public void setCpECE(String cpECE) {
        this.cpECE = cpECE;
    }

    public String getCalleRCE() {
        return CalleRCE;
    }

    public void setCalleRCE(String CalleRCE) {
        this.CalleRCE = CalleRCE;
    }

    public String getNumExtRCE() {
        return numExtRCE;
    }

    public void setNumExtRCE(String numExtRCE) {
        this.numExtRCE = numExtRCE;
    }

    public String getNumIntRCE() {
        return numIntRCE;
    }

    public void setNumIntRCE(String numIntRCE) {
        this.numIntRCE = numIntRCE;
    }

    public String getColoniaRCE() {
        return coloniaRCE;
    }

    public void setColoniaRCE(String coloniaRCE) {
        this.coloniaRCE = coloniaRCE;
    }

    public String getLocalidadRCE() {
        return localidadRCE;
    }

    public void setLocalidadRCE(String localidadRCE) {
        this.localidadRCE = localidadRCE;
    }

    public String getReferenciaRCE() {
        return ReferenciaRCE;
    }

    public void setReferenciaRCE(String ReferenciaRCE) {
        this.ReferenciaRCE = ReferenciaRCE;
    }

    public String getEstadoRCE() {
        return estadoRCE;
    }

    public void setEstadoRCE(String estadoRCE) {
        this.estadoRCE = estadoRCE;
    }

    public String getPaisRCE() {
        return paisRCE;
    }

    public void setPaisRCE(String paisRCE) {
        this.paisRCE = paisRCE;
    }

    public String getCpRCE() {
        return cpRCE;
    }

    public void setCpRCE(String cpRCE) {
        this.cpRCE = cpRCE;
    }

    public String getNumRegIdTribECE() {
        return numRegIdTribECE;
    }

    public void setNumRegIdTribECE(String numRegIdTribECE) {
        this.numRegIdTribECE = numRegIdTribECE;
    }

    public String getNumRegIdTribPCE() {
        return numRegIdTribPCE;
    }

    public void setNumRegIdTribPCE(String numRegIdTribPCE) {
        this.numRegIdTribPCE = numRegIdTribPCE;
    }

    public String getRecidenciaFiscalPCE() {
        return recidenciaFiscalPCE;
    }

    public void setRecidenciaFiscalPCE(String recidenciaFiscalPCE) {
        this.recidenciaFiscalPCE = recidenciaFiscalPCE;
    }

    public String getNumRegIdTribDCE() {
        return numRegIdTribDCE;
    }

    public void setNumRegIdTribDCE(String numRegIdTribDCE) {
        this.numRegIdTribDCE = numRegIdTribDCE;
    }

    public String getNombreDCE() {
        return nombreDCE;
    }

    public void setNombreDCE(String nombreDCE) {
        this.nombreDCE = nombreDCE;
    }

    public String getCalleDCE() {
        return CalleDCE;
    }

    public void setCalleDCE(String CalleDCE) {
        this.CalleDCE = CalleDCE;
    }

    public String getNumExtDCE() {
        return numExtDCE;
    }

    public void setNumExtDCE(String numExtDCE) {
        this.numExtDCE = numExtDCE;
    }

    public String getNumIntDCE() {
        return numIntDCE;
    }

    public void setNumIntDCE(String numIntDCE) {
        this.numIntDCE = numIntDCE;
    }

    public String getColoniaDCE() {
        return coloniaDCE;
    }

    public void setColoniaDCE(String coloniaDCE) {
        this.coloniaDCE = coloniaDCE;
    }

    public String getLocalidadDCE() {
        return localidadDCE;
    }

    public void setLocalidadDCE(String localidadDCE) {
        this.localidadDCE = localidadDCE;
    }

    public String getReferenciaDCE() {
        return ReferenciaDCE;
    }

    public void setReferenciaDCE(String ReferenciaDCE) {
        this.ReferenciaDCE = ReferenciaDCE;
    }

    public String getMunicipioDCE() {
        return municipioDCE;
    }

    public void setMunicipioDCE(String municipioDCE) {
        this.municipioDCE = municipioDCE;
    }

    public String getEstadoDCE() {
        return estadoDCE;
    }

    public void setEstadoDCE(String estadoDCE) {
        this.estadoDCE = estadoDCE;
    }

    public String getPaisDCE() {
        return paisDCE;
    }

    public void setPaisDCE(String paisDCE) {
        this.paisDCE = paisDCE;
    }

    public String getCpDCE() {
        return cpDCE;
    }

    public void setCpDCE(String cpDCE) {
        this.cpDCE = cpDCE;
    }

    public String getNoIdentificacionCE() {
        return noIdentificacionCE;
    }

    public void setNoIdentificacionCE(String noIdentificacionCE) {
        this.noIdentificacionCE = noIdentificacionCE;
    }

    public String getFraccionArancelariaCE() {
        return fraccionArancelariaCE;
    }

    public void setFraccionArancelariaCE(String fraccionArancelariaCE) {
        this.fraccionArancelariaCE = fraccionArancelariaCE;
    }

    public String getCantidadAduanaCE() {
        return cantidadAduanaCE;
    }

    public void setCantidadAduanaCE(String cantidadAduanaCE) {
        this.cantidadAduanaCE = cantidadAduanaCE;
    }

    public String getUnidadAdunaCE() {
        return unidadAdunaCE;
    }

    public void setUnidadAdunaCE(String unidadAdunaCE) {
        this.unidadAdunaCE = unidadAdunaCE;
    }

    public String getValorUnitarioAduanaCE() {
        return valorUnitarioAduanaCE;
    }

    public void setValorUnitarioAduanaCE(String valorUnitarioAduanaCE) {
        this.valorUnitarioAduanaCE = valorUnitarioAduanaCE;
    }

    public String getValorDolaresCE() {
        return valorDolaresCE;
    }

    public void setValorDolaresCE(String valorDolaresCE) {
        this.valorDolaresCE = valorDolaresCE;
    }

    public String getMarcaCE() {
        return marcaCE;
    }

    public void setMarcaCE(String marcaCE) {
        this.marcaCE = marcaCE;
    }

    public String getModeloCE() {
        return modeloCE;
    }

    public void setModeloCE(String modeloCE) {
        this.modeloCE = modeloCE;
    }

    public String getSubmodeloCE() {
        return submodeloCE;
    }

    public void setSubmodeloCE(String submodeloCE) {
        this.submodeloCE = submodeloCE;
    }

    public String getNumSerieCE() {
        return numSerieCE;
    }

    public void setNumSerieCE(String numSerieCE) {
        this.numSerieCE = numSerieCE;
    }

    public Boolean getTablaPropietarios() {
        return tablaPropietarios;
    }

    public void setTablaPropietarios(Boolean tablaPropietarios) {
        this.tablaPropietarios = tablaPropietarios;
    }

    public Boolean getTablaDomicilios() {
        return tablaDomicilios;
    }

    public void setTablaDomicilios(Boolean tablaDomicilios) {
        this.tablaDomicilios = tablaDomicilios;
    }

    public Boolean getTablaDestinatarios() {
        return tablaDestinatarios;
    }

    public void setTablaDestinatarios(Boolean tablaDestinatarios) {
        this.tablaDestinatarios = tablaDestinatarios;
    }

    public Boolean getTablaDescripciones() {
        return tablaDescripciones;
    }

    public void setTablaDescripciones(Boolean tablaDescripciones) {
        this.tablaDescripciones = tablaDescripciones;
    }

    public Boolean getTablaMercancias() {
        return tablaMercancias;
    }

    public void setTablaMercancias(Boolean tablaMercancias) {
        this.tablaMercancias = tablaMercancias;
    }

    public ComplementoComExt getCompComExt() {
        return compComExt;
    }

    public void setCompComExt(ComplementoComExt compComExt) {
        this.compComExt = compComExt;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public String getNumRegIdTrib() {
        return numRegIdTrib;
    }

    public void setNumRegIdTrib(String numRegIdTrib) {
        this.numRegIdTrib = numRegIdTrib;
    }

    public BigDecimal getValorUnitarioB() {
        return valorUnitarioB;
    }

    public void setValorUnitarioB(BigDecimal valorUnitarioB) {
        this.valorUnitarioB = valorUnitarioB;
    }

    public StreamedContent getFilePdfVP() {
        return filePdfVP;
    }

    public void setFilePdfVP(StreamedContent filePdfVP) {
        this.filePdfVP = filePdfVP;
    }

    public void verificarSesion() {
        try {
            if (usuarioSeleccionado == null) {
                FacesContext context = FacesContext.getCurrentInstance();
                context.getExternalContext().redirect("index.xhtml");
            } else {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            listarTiposComprobante();
            listarUsos();
            listarMetodosPago();
            listarMonedas();
            listarFormasPago();
            listarTiposRelacion();
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
        serie = serieDao.buscarId(usuarioSeleccionado, serieS);
        nextFolio = serie.getFolioActual() + 1;
    }

    public List<UsoCfdiBean> completeUso(String dato) {
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

    public List<String> completeCliente(String dato) {
        List<String> filteredCliente = new ArrayList<String>();
        for (Cliente c : allClientes) {
            if (c.getRfc().toLowerCase().contains(dato.toLowerCase())) {
                filteredCliente.add(c.getRfc() + " : " + c.getRazonSocial());
            } else if (c.getRazonSocial().toLowerCase().contains(dato.toLowerCase())) {
                filteredCliente.add(c.getRfc() + " : " + c.getRazonSocial());
            }
        }
        return filteredCliente;
    }

    public void compClienSet() {
        clienteString = clienteString.replaceAll(" : ", ":");
        cliente = clienteDao.buscarIdentifica(clienteString.substring(0, clienteString.indexOf(":")), clienteString.substring(clienteString.indexOf(":") + 1), usuarioSeleccionado);
        clienteString = "";
    }

    public List<String> completeProducto(String dato) {
        List<String> filteredProducto = new ArrayList<String>();
        for (Producto p : allProducto) {
            if (p.getDescripcion().toLowerCase().contains(dato.toLowerCase())) {
                filteredProducto.add(p.getCodigoSat() + " : " + p.getDescripcion());
            } else if (p.getCodigoSat().toLowerCase().contains(dato.toLowerCase())) {
                filteredProducto.add(p.getCodigoSat() + " : " + p.getDescripcion());
            }
        }
        return filteredProducto;
    }

    public void compProdSet() {
        productoString = productoString.replaceAll(" : ", ":");
        producto = productoDao.listarProductosUsuario(true, usuarioSeleccionado, productoString.substring(0, productoString.indexOf(":")), productoString.substring(productoString.indexOf(":") + 1));
        productoString = "";
        cantidad = new BigDecimal(0);
        importe = new BigDecimal(0);
        habilitarInputs();
    }

    public void cantidadXvalor() {
        try {
            importe = cantidad.multiply(valorUnitarioB);
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
            descuento = (porcentajeDes.multiply(new BigDecimal(0.01))).multiply(importe).setScale(2, RoundingMode.HALF_UP);
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
            tipoTasaOCRActiv = true;
            importeT = new BigDecimal(0);
            btnAgrRet = false;
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
            tipoTasaOCTActiv = true;
            importeT = new BigDecimal(0);
            btnAgrTra = false;
        }
    }

    public void tasaOCuotaTraList() {
        listTCTras = new HashMap<String, BigDecimal>();
        if (ivaT.equals("iva")) {
            if (tipoFactorT == null) {
                importeT = new BigDecimal(0);
                btnAgrTra = false;
                tipoTasaOCTActiv = true;
            } else if (tipoFactorT.equals("Tasa")) {
                listTCTras.put("0", new BigDecimal("0.000000"));
                listTCTras.put("8", new BigDecimal("0.080000"));
                listTCTras.put("16", new BigDecimal("0.160000"));
                btnAgrTra = false;
                tipoTasaOCTActiv = false;
            } else if (tipoFactorT.equals("Exento")) {
                listTCTras.put("", new BigDecimal("0.000000"));
                tasaOCuotaT = new BigDecimal(0);
                calcularImpuestosTrasladados();
                tipoTasaOCTActiv = true;
            } else {
                importeT = new BigDecimal(0);
                btnAgrTra = false;
                tipoTasaOCTActiv = true;
            }

        } else if (ivaT.equals("ieps")) {
            if (tipoFactorT == null) {
                importeT = new BigDecimal(0);
                btnAgrTra = false;
                tipoTasaOCTActiv = true;
            } else if (tipoFactorT.equals("Tasa")) {

                listTCTras.put("0.06", new BigDecimal("0.060000"));
                listTCTras.put("0.07", new BigDecimal("0.070000"));
                listTCTras.put("0.08", new BigDecimal("0.080000"));
                listTCTras.put("0.09", new BigDecimal("0.090000"));
                listTCTras.put("0.25", new BigDecimal("0.250000"));
                listTCTras.put("0.265", new BigDecimal("0.2650000"));
                listTCTras.put("0.3", new BigDecimal("0.300000"));
                listTCTras.put("0.304", new BigDecimal("0.304000"));
                listTCTras.put("0.5", new BigDecimal("0.500000"));
                listTCTras.put("0.53", new BigDecimal("0.530000"));
                listTCTras.put("1.6", new BigDecimal("1.600000"));
                tipoTasaOCTActiv = false;
                btnAgrTra = false;

            } else if (tipoFactorT.equals("Cuota")) {
                listTCTras.put("0", new BigDecimal("0.000000"));
                listTCTras.put("0.06", new BigDecimal("0.060000"));
                listTCTras.put("0.07", new BigDecimal("0.070000"));
                listTCTras.put("0.08", new BigDecimal("0.080000"));
                listTCTras.put("0.09", new BigDecimal("0.090000"));
                listTCTras.put("0.16", new BigDecimal("0.160000"));
                listTCTras.put("0.25", new BigDecimal("0.250000"));
                listTCTras.put("0.265", new BigDecimal("0.2650000"));
                listTCTras.put("0.3", new BigDecimal("0.300000"));
                listTCTras.put("0.304", new BigDecimal("0.304000"));
                listTCTras.put("0.5", new BigDecimal("0.500000"));
                listTCTras.put("0.53", new BigDecimal("0.530000"));
                tipoTasaOCTActiv = false;
                btnAgrTra = false;
            } else {
                btnAgrTra = false;
                tasaOCuotaT = new BigDecimal(0);
                calcularImpuestosTrasladados();
                tipoTasaOCTActiv = true;

            }
        }
    }

    public void tasaOCuotaRetList() {
        listTCReten = new HashMap<String, BigDecimal>();
        if (ivaR.equals("iva")) {
            if (tipoFactorR == null) {
                importeR = new BigDecimal(0);
                btnAgrRet = false;
                tipoTasaOCRActiv = true;
            } else if (tipoFactorR.equals("Tasa")) {
                listTCReten.put("0", new BigDecimal("0.000000"));
                listTCReten.put("8", new BigDecimal("0.080000"));
                listTCReten.put("16", new BigDecimal("0.160000"));
                tipoTasaOCRActiv = false;
                btnAgrRet = false;
            } else if (tipoFactorR.equals("Exento")) {
                tasaOCuotaR = new BigDecimal(0);
                listTCReten.put("", new BigDecimal("0.000000"));
                calcularImpuestosRetenidos();
                tipoTasaOCRActiv = true;
            } else {
                importeR = new BigDecimal(0);
                btnAgrRet = false;
                tipoTasaOCRActiv = true;
            }

        } else if (ivaR.equals("ieps")) {
            if (tipoFactorR == null) {
                importeR = new BigDecimal(0);
                btnAgrRet = false;
                tipoTasaOCRActiv = true;
            } else if (tipoFactorR.equals("Tasa")) {
                listTCReten.put("0", new BigDecimal("0.000000"));
                listTCReten.put("0.03", new BigDecimal("0.030000"));
                listTCReten.put("0.06", new BigDecimal("0.060000"));
                listTCReten.put("0.07", new BigDecimal("0.070000"));
                listTCReten.put("0.08", new BigDecimal("0.080000"));
                listTCReten.put("0.09", new BigDecimal("0.090000"));
                listTCReten.put("0.16", new BigDecimal("0.160000"));
                listTCReten.put("0.25", new BigDecimal("0.250000"));
                listTCReten.put("0.265", new BigDecimal("0.2650000"));
                listTCReten.put("0.304", new BigDecimal("0.304000"));
                listTCReten.put("1.6", new BigDecimal("1.600000"));
                tipoTasaOCRActiv = false;
                btnAgrRet = false;

            } else if (tipoFactorR.equals("Exento")) {
                listTCReten.put("", new BigDecimal("0.000000"));
                tasaOCuotaR = new BigDecimal(0);
                calcularImpuestosRetenidos();
                tipoTasaOCRActiv = true;
            } else if (tipoFactorR.equals("Cuota")) {
                listTCReten.put("0", new BigDecimal("0.000000"));
                tipoTasaOCRActiv = false;
            } else {
                importeR = new BigDecimal(0);
                btnAgrRet = false;
                tipoTasaOCRActiv = true;
            }

        } else if (ivaR.equals("isr")) {
            if (tipoFactorR == null) {
                importeR = new BigDecimal(0);
                btnAgrRet = false;
                tipoTasaOCRActiv = true;
            } else if (tipoFactorR.equals("Tasa")) {
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

    public void calcularImpuestosRetenidos() {
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
                impRetenidos im = new impRetenidos();
                im.setBase(baseR.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                im.setImporte(importeR.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                im.setIva(ivaR);
                im.setTasaOCuota(tasaOCuotaR);
                im.setTipoFactor(tipoFactorR);
                im.setMoneda(monedaS);
                im.setTipoCambio(tipoCambio);
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
        calcularTotalGlobal();
    }

    public void eliminarImpRet(impRetenidos ipR) {

        try {
            listRete.remove(ipR);
            if (listRete == null || listRete.size() == 0) {
                impRetPan = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        calcularTotalGlobal();
    }

    public void agregarImpTras() {
        if (!ivaT.equals("") && !tipoFactorT.equals("")) {
            try {
                impTrasladados im = new impTrasladados();
                im.setBase(baseT.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                im.setImporte(importeT.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                im.setIva(ivaT);
                im.setTasaOCuota(tasaOCuotaT);
                im.setTipoFactor(tipoFactorT);
                im.setMoneda(monedaS);
                im.setTipoCambio(tipoCambio);
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
        calcularTotalGlobal();
    }

    public void eliminarImpTra(impTrasladados ipT) {

        try {
            listTras.remove(ipT);
            if (listTras == null || listTras.size() == 0) {
                impTraPan = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        calcularTotalGlobal();
    }

    public void agregarConcepto() {
        Conceptos c = new Conceptos();
        try {
            if (cantidad.intValue() > 0) {
                btnsVE = true;
                Producto pr = new Producto();
                pr.setCodigoSat(producto.getCodigoSat());
                pr.setDescripcion(descripcionProducto);
                pr.setDescripcionCodigoSat(producto.getDescripcionCodigoSat());
                pr.setEmpresa(producto.getEmpresa());
                pr.setFechaCreacion(producto.getFechaCreacion());
                pr.setIdentifica(producto.getIdentifica());
                pr.setIva(producto.getIva());
                pr.setMedida(producto.getMedida());
                pr.setNpedimento(producto.getNpedimento());
                pr.setNumeroIdentificador(producto.getNumeroIdentificador());
                pr.setResponsableCreacion(producto.getResponsableCreacion());
                pr.setValorUnitario(valorUnitarioB.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                pr.setNumeroPredial(producto.getNumeroPredial());
                c.setCantidad(cantidad);
                c.setDescripcion("");
                c.setDescuento(descuento.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                c.setImporte(importe.divide(tipoCambio, 6, RoundingMode.HALF_UP));
                c.setPorcentaje(porcentajeDes);
                c.setProducto(pr);
                if (listRete.size() > 0) {
                    c.setRetenidos(listRete);
                }
                if (listTras.size() > 0) {
                    c.setTrasladados(listTras);
                }
                conceptos.add(c);
                agregarVRetenciones();
                agregarVTrasladados();
            }
            calcularTotalGlobal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            descripcionProducto = "";
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
            valorUnitarioB = new BigDecimal(0);
        }
    }

    public void calcularTotalGlobal() {
        if (conceptos.size() > 0) {
            Double importeC = 0.0;
            Double decuentoC = 0.0;
            Double retenidosC = 0.0;
            Double trasladadosC = 0.0;
            for (Conceptos c : conceptos) {
                importeC += c.getImporte().setScale(2, RoundingMode.HALF_UP).doubleValue();
                if (c.getDescuento() != null) {
                    decuentoC += c.getDescuento().setScale(2, RoundingMode.HALF_UP).doubleValue();
                }
                if (c.getRetenidos() != null && c.getRetenidos().size() > 0) {
                    for (impRetenidos r : c.getRetenidos()) {

                        retenidosC += r.getImporte().setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                }
                if (c.getTrasladados() != null && c.getTrasladados().size() > 0) {
                    for (impTrasladados t : c.getTrasladados()) {
                        trasladadosC += t.getImporte().setScale(2, RoundingMode.HALF_UP).doubleValue();
                    }
                }
            }
            if (listILCR != null && listILCR.size() > 0) {
                for (ImpuestosLocalesC r : listILCR) {
                    retenidosC += r.getImporte().doubleValue();
                }
            }
            if (listILCT != null && listILCT.size() > 0) {
                for (ImpuestosLocalesC t : listILCT) {
                    trasladadosC += t.getImporte().doubleValue();
                }
            }
            totalTrasl = new BigDecimal(trasladadosC);
            totalReten = new BigDecimal(retenidosC);
            subTotal = new BigDecimal(importeC).setScale(2, RoundingMode.HALF_UP);
            descuentoTotal = new BigDecimal(decuentoC).setScale(2, RoundingMode.HALF_UP);
            total = new BigDecimal((((subTotal.doubleValue() + trasladadosC + importeCompImpLocTra.doubleValue()) - retenidosC) - descuentoTotal.doubleValue())).setScale(2, RoundingMode.HALF_UP);
        } else {
            totalTrasl = new BigDecimal(0);
            totalReten = new BigDecimal(0);
            subTotal = new BigDecimal(0);
            descuentoTotal = new BigDecimal(0);
            total = new BigDecimal(0);
        }
        montoLetra();
    }

    public void eliminarConcepto(Conceptos c) {
        conceptos.remove(c);
        if (conceptos.size() == 0) {
            btnsVE = false;
            conceptosTableActv = false;
        }
        calcularTotalGlobal();
    }

    public void habilitarInputs() {
        if (producto != null) {
            descripcionProducto = producto.getMedida().getNombre();
            inputCon = false;
            valorUnitarioB = producto.getValorUnitario();
        } else {
            descripcionProducto = "";
            inputCon = true;
            valorUnitarioB = new BigDecimal(0);
        }
    }

    public void agregarILCR() {
        if ((decripCompImpLocRet != null && !decripCompImpLocRet.equals("")) && (tasaCompImpLocRet > 0) && importeCompImpLocRet.intValue() > 0) {
            ImpuestosLocalesC imR = new ImpuestosLocalesC();
            imR.setDescripcion(decripCompImpLocRet);
            imR.setPorcentaje(tasaCompImpLocRet);
            imR.setImporte(importeCompImpLocRet);
            listILCR.add(imR);
            decripCompImpLocRet = "";
            tasaCompImpLocRet = 0;
            importeCompImpLocRet = new BigDecimal(0);
        }
        if (listILCR.size() > 0) {
            renderILCRTable = true;
        } else {
            renderILCRTable = false;
        }
        calcularTotalGlobal();
        montoLetra();
        renderILCRT = false;
        renderILCRI = false;
        renderILCRB = false;
    }

    public void eliminarILCR(ImpuestosLocalesC imp) {
        listILCR.remove(imp);
        if (listILCR.size() > 0) {
            renderILCRTable = true;
        } else {
            renderILCRTable = false;
        }
        montoLetra();
        calcularTotalGlobal();
    }

    public void agregarILCT() {
        if ((decripCompImpLocTra != null && !decripCompImpLocTra.equals("")) && (tasaCompImpLocTra > 0) && importeCompImpLocTra.intValue() > 0) {
            ImpuestosLocalesC imR = new ImpuestosLocalesC();
            imR.setDescripcion(decripCompImpLocTra);
            imR.setPorcentaje(tasaCompImpLocTra);
            imR.setImporte(importeCompImpLocTra);
            listILCT.add(imR);
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
        calcularTotalGlobal();
        renderILCTT = false;
        renderILCTI = false;
        renderILCTB = false;
    }

    public void eliminarILCT(ImpuestosLocalesC imp) {
        listILCT.remove(imp);
        if (listILCT.size() > 0) {
            renderILCTTable = true;
        } else {
            renderILCTTable = false;
        }
        calcularTotalGlobal();
        montoLetra();
    }

    public void habilitarRenderILCRT() {
        if (decripCompImpLocRet != null && !decripCompImpLocRet.equals("")) {
            renderILCRT = false;
        } else {
            renderILCRT = true;
        }
    }

    public void habilitarRenderILCRI() {
        if (tasaCompImpLocRet != null && tasaCompImpLocRet > 0) {
            renderILCRI = false;
        } else {
            renderILCRI = true;
        }
    }

    public void habilitarRenderILCRB() {
        Double impLCR = importeCompImpLocRet.doubleValue();
        if (impLCR > 0) {
            renderILCRB = false;
        } else {
            renderILCRB = true;
        }

    }

    public void habilitarRenderILCTT() {
        if (decripCompImpLocTra != null && !decripCompImpLocTra.equals("")) {
            renderILCTT = false;
        } else {
            renderILCTT = true;
        }
    }

    public void habilitarRenderILCTI() {
        if (tasaCompImpLocTra != null && tasaCompImpLocTra > 0) {
            renderILCTI = false;
        } else {
            renderILCTI = true;
        }
    }

    public void habilitarRenderILCTB() {
        Double impLCR = importeCompImpLocTra.doubleValue();
        if (impLCR > 0) {
            renderILCTB = false;
        } else {
            renderILCTB = true;
        }

    }

    public void listarTiposRelacion() {
        listaTiposRelacion = tipoRelacionService.findAllTipoRelaciones();
    }

    public void habilitarRelacionado() {
        if (checkRelacionados) {
            strUUIDRel = "block";
        } else {
            strUUIDRel = "none";
        }
    }

    public void habilitarSEP() {
        if (checkComplementoSEP) {
            strComplementoSEP = "block";
        } else {
            strComplementoSEP = "none";
        }
    }

    public void habilitarIpLoc() {
        if (checkComplementoIL) {
            strComplementoIL = "block";
        } else {
            strComplementoIL = "none";
        }
    }

    public void habilitarComExt() {
        if (checkComplementoCE) {
            strComplementoCE = "block";
        } else {
            strComplementoCE = "none";
        }
    }

    public void agregarRelacionado() {
        if (!uuidRela.equals("") && tipoRelacion != null) {
            uuidRelacionados.add(uuidRela);
            uuidRela = "";
            relacionSelect = true;
            relacionTable = true;
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
            CURP = mayus(CURP);
            if (CURP.length() > 17) {
                renderAlu = false;
            }
        } else {
            renderAlu = true;
            renderRfc = true;
            renderAut = true;
            renderNEdu = true;
        }
    }

    public void activarAutRVOE() {
        if (Alumno != null && !Alumno.equals("")) {
            renderAut = false;
            Alumno = mayus(Alumno);
        } else {
            renderRfc = true;
            renderAut = true;
            renderNEdu = true;
        }
    }

    public void activarNEduc() {
        if (autRVOE != null && !autRVOE.equals("")) {
            autRVOE = mayus(autRVOE);
            renderNEdu = false;
        } else {
            renderRfc = true;
            renderNEdu = true;
        }
    }

    public void activarRfc() {
        if (nivelEduc != null && !nivelEduc.equals("")) {
            renderRfc = false;
        }
    }

    public void sepRFC() {
        if (RFCAl != null && !RFCAl.equals("")) {
            RFCAl = mayus(RFCAl);
        }
    }

    public void montoLetra() {
        MontoLetra m = new MontoLetra();
        if (total.doubleValue() >= 0) {
            totalFacturaEnLetras = m.Convertir(total.toString(), true, monedaS);
        }
    }

    public void condicionesP() {
        condicionesPago = condicionesPago.toUpperCase();
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
            for (FormaPago fp : listaFormasPago) {
                if (fp.getIdentifica().equals(formaPagoS)) {
                    formapago = fp;
                }
            }
        } else {
            formapago = new FormaPago();
        }
        formaPagoS = formaPagoS;
    }

    public void metodoP() {
        if (!metodoPagoS.equals("")) {
            for (MetodoPago mp : listaMetodosPago) {
                if (mp.getIdentifica().equals(metodoPagoS)) {
                    metodoPago = mp;
                }
            }
        } else {
            metodoPago = new MetodoPago();
        }
        metodoPagoS = metodoPagoS;
    }

    public void monedaM() {
        monedaS = monedaS;
        if (!monedaS.equals("MXN")) {
            for (Moneda m : listaMonedas) {
                if (m.getIdentifica().equals(monedaS)) {
                    moneda = m;
                }
            }
            tipoCambioBool = false;
        } else {
            tipoCambioBool = true;
            tipoCambio = new BigDecimal(1);
        }

    }

    public void usoCfdiBeanM(UsoCfdiBean us) {
        usoCfdiBean = us;
    }

    public void observacionesSet() {
        observaciones = mayus(observaciones);
    }

    public void agregarPropietario() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (numRegIdTribPCE != null && !numRegIdTribPCE.equals("")) {
            if (recidenciaFiscalPCE != null && !recidenciaFiscalPCE.equals("")) {
                PropietarioCompComExt p = new PropietarioCompComExt();
                p.setNumRegIdTrib(numRegIdTribPCE);
                p.setRecidenciaFiscal(recidenciaFiscalPCE);
                propietario.add(p);
                numRegIdTribPCE = "";
                recidenciaFiscalPCE = "";
                tablaPropietarios = true;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) Se debe de colocar RecidenciaFiscal"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) Se debe de colocar NumRegIdTrib"));
        }
    }

    public void eliminarPropietario(PropietarioCompComExt p) {
        propietario.remove(p);
        if (propietario.size() == 0) {
            tablaPropietarios = false;
        }
    }

    public void agregarDomicilioDest() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (CalleDCE != null && !CalleDCE.equals("")) {
            if (estadoDCE != null && !estadoDCE.equals("")) {
                if (paisDCE != null && !paisDCE.equals("")) {
                    if (cpDCE != null && !cpDCE.equals("")) {
                        DomicilioCompComExt d = new DomicilioCompComExt();
                        d.setCalle(CalleDCE);
                        d.setEstado(estadoDCE);
                        d.setPais(paisDCE);
                        d.setCp(cpDCE);
                        d.setColonia(coloniaDCE);
                        d.setLocalidad(localidadDCE);
                        d.setMunicipio(municipioDCE);
                        d.setNumExt(numExtDCE);
                        d.setNumInt(numIntDCE);
                        d.setReferencia(ReferenciaDCE);
                        domicilioDestin.add(d);
                        tablaDomicilios = true;
                        CalleDCE = "";
                        estadoDCE = "";
                        paisDCE = "";
                        cpDCE = "";
                        coloniaDCE = "";
                        localidadDCE = "";
                        municipioDCE = "";
                        numExtDCE = "";
                        numIntDCE = "";
                        ReferenciaDCE = "";
                    } else {
                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Destinatario- Codigo postal es obligatorio"));
                    }
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Destinatario- pais es obligatorio"));
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Destinatario- Estado es obligatorio"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Destinatario- Calle es obligatorio"));
        }
    }

    public void eliminarDomicilioDest(DomicilioCompComExt d) {
        domicilioDestin.remove(d);
        if (domicilioDestin.size() == 0) {
            tablaDomicilios = false;
        }
    }

    public void agregarDestinatario() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (numRegIdTribDCE != null && !numRegIdTribDCE.equals("")) {
            if (nombreDCE != null && !nombreDCE.equals("")) {
                DestinatarioCompComExt e = new DestinatarioCompComExt();
                e.setNumRegIdTrib(numRegIdTribDCE);
                e.setNombre(nombreDCE);
                e.setDomicilio(domicilioDestin);
                destinatario.add(e);
                tablaDestinatarios = true;
                numRegIdTribDCE = "";
                nombreDCE = "";
                domicilioDestin = new ArrayList<>();
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Destinatario- Nombre es obligatorio"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Destinatario- NumRegIdTrib es obligatorio"));
        }
    }

    public void eliminarDestinatario(DestinatarioCompComExt d) {
        destinatario.remove(d);
        if (destinatario.size() == 0) {
            tablaDestinatarios = false;
        }
    }

    public void agregarDescEsp() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (marcaCE != null && !marcaCE.equals("")) {
            DescEspecCompComExt d = new DescEspecCompComExt();
            d.setMarca(marcaCE);
            d.setModelo(modeloCE);
            d.setNumeroSerie(numSerieCE);
            d.setSubModelo(submodeloCE);
            descpEsp.add(d);
            marcaCE = "";
            modeloCE = "";
            numSerieCE = "";
            submodeloCE = "";
            tablaDescripciones = true;
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Mercancia- Marca es obligatorio"));
        }
    }

    public void eliminarDescEsp(DescEspecCompComExt d) {
        descpEsp.remove(d);
        if (descpEsp.size() == 0) {
            tablaDescripciones = false;
        }
    }

    public void agregarMercancia() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (noIdentificacionCE != null && !noIdentificacionCE.equals("")) {
            if (valorDolaresCE != null && !valorDolaresCE.equals("")) {
                MercanciasCompComExt m = new MercanciasCompComExt();
                m.setNoIdentificacion(noIdentificacionCE);
                m.setFraccionArancelaria(fraccionArancelariaCE);
                m.setCantidadAduana(cantidadAduanaCE);
                m.setUnidadAduana(unidadAdunaCE);
                m.setValorUnitarioAduana(valorUnitarioAduanaCE);
                m.setValorDolares(valorDolaresCE);
                m.setDescEspec(descpEsp);
                mercancia.add(m);
                noIdentificacionCE = "";
                fraccionArancelariaCE = "";
                cantidadAduanaCE = "";
                unidadAdunaCE = "";
                valorUnitarioAduanaCE = "";
                valorDolaresCE = "";
                descpEsp = new ArrayList<>();
                tablaMercancias = true;
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Mercancia- ValorDolares es obligatorio"));
            }
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Comercio Exterior) -Mercancia- NoIdentificacion es obligatorio"));
        }
    }

    public void eliminarMercancia(MercanciasCompComExt m) {
        mercancia.remove(m);
        if (mercancia.size() == 0) {
            tablaMercancias = false;
        }
    }

    public String mayus2(String d) {
        return d.toLowerCase();
    }

    public void mayusDatos(String d) {
        if (d.equals("1")) {
            motivoTrasladoCE = motivoTrasladoCE.toUpperCase();
        } else if (d.equals("2")) {
            clavePedimentoCE = clavePedimentoCE.toUpperCase();
        } else if (d.equals("3")) {
            certificadoOrigenCE = certificadoOrigenCE.toUpperCase();
        } else if (d.equals("4")) {
            numCertificadoOrigenCE = numCertificadoOrigenCE.toUpperCase();
        } else if (d.equals("5")) {
            numExportConfiabCE = numExportConfiabCE.toUpperCase();
        } else if (d.equals("6")) {
            incotermCE = incotermCE.toUpperCase();
        } else if (d.equals("7")) {
            subdivisionCE = subdivisionCE.toUpperCase();
        } else if (d.equals("8")) {
            observacionesCE = observacionesCE.toUpperCase();
        } else if (d.equals("9")) {
            tipoCambioUSDCE = tipoCambioUSDCE.toUpperCase();
        } else if (d.equals("10")) {
            totalUSDCE = totalUSDCE.toUpperCase();
        } else if (d.equals("11")) {
            CurpCE = CurpCE.toUpperCase();
        } else if (d.equals("12")) {
            CalleECE = CalleECE.toUpperCase();
        } else if (d.equals("13")) {
            numExtECE = numExtECE.toUpperCase();
        } else if (d.equals("14")) {
            numIntECE = numIntECE.toUpperCase();
        } else if (d.equals("15")) {
            coloniaECE = coloniaECE.toUpperCase();
        } else if (d.equals("16")) {
            localidadECE = localidadECE.toUpperCase();
        } else if (d.equals("17")) {
            ReferenciaECE = ReferenciaECE.toUpperCase();
        } else if (d.equals("18")) {
            municipioECE = municipioECE.toUpperCase();
        } else if (d.equals("19")) {
            estadoECE = estadoECE.toUpperCase();
        } else if (d.equals("20")) {
            paisECE = paisECE.toUpperCase();
        } else if (d.equals("21")) {
            cpECE = cpECE.toUpperCase();
        } else if (d.equals("22")) {
            CalleRCE = CalleRCE.toUpperCase();
        } else if (d.equals("23")) {
            numExtRCE = numExtRCE.toUpperCase();
        } else if (d.equals("24")) {
            numIntRCE = numIntRCE.toUpperCase();
        } else if (d.equals("25")) {
            coloniaRCE = coloniaRCE.toUpperCase();
        } else if (d.equals("26")) {
            localidadRCE = localidadRCE.toUpperCase();
        } else if (d.equals("27")) {
            ReferenciaRCE = ReferenciaRCE.toUpperCase();
        } else if (d.equals("28")) {
            municipioRCE = municipioRCE.toUpperCase();
        } else if (d.equals("29")) {
            estadoRCE = estadoRCE.toUpperCase();
        } else if (d.equals("30")) {
            paisRCE = paisRCE.toUpperCase();
        } else if (d.equals("31")) {
            cpRCE = cpRCE.toUpperCase();
        } else if (d.equals("32")) {
            numRegIdTribECE = numRegIdTribECE.toUpperCase();
        } else if (d.equals("33")) {
            numRegIdTribPCE = numRegIdTribPCE.toUpperCase();
        } else if (d.equals("34")) {
            recidenciaFiscalPCE = recidenciaFiscalPCE.toUpperCase();
        } else if (d.equals("35")) {
            numRegIdTribDCE = numRegIdTribDCE.toUpperCase();
        } else if (d.equals("36")) {
            nombreDCE = nombreDCE.toUpperCase();
        } else if (d.equals("37")) {
            CalleDCE = CalleDCE.toUpperCase();
        } else if (d.equals("38")) {
            numExtDCE = numExtDCE.toUpperCase();
        } else if (d.equals("39")) {
            numIntDCE = numIntDCE.toUpperCase();
        } else if (d.equals("40")) {
            coloniaDCE = coloniaDCE.toUpperCase();
        } else if (d.equals("41")) {
            localidadDCE = localidadDCE.toUpperCase();
        } else if (d.equals("42")) {
            ReferenciaDCE = ReferenciaDCE.toUpperCase();
        } else if (d.equals("43")) {
            municipioDCE = municipioDCE.toUpperCase();
        } else if (d.equals("44")) {
            estadoDCE = estadoDCE.toUpperCase();
        } else if (d.equals("45")) {
            paisDCE = paisDCE.toUpperCase();
        } else if (d.equals("46")) {
            cpDCE = cpDCE.toUpperCase();
        } else if (d.equals("47")) {
            noIdentificacionCE = noIdentificacionCE.toUpperCase();
        } else if (d.equals("48")) {
            fraccionArancelariaCE = fraccionArancelariaCE.toUpperCase();
        } else if (d.equals("49")) {
            cantidadAduanaCE = cantidadAduanaCE.toUpperCase();
        } else if (d.equals("50")) {
            unidadAdunaCE = unidadAdunaCE.toUpperCase();
        } else if (d.equals("51")) {
            valorUnitarioAduanaCE = valorUnitarioAduanaCE.toUpperCase();
        } else if (d.equals("52")) {
            valorDolaresCE = valorDolaresCE.toUpperCase();
        } else if (d.equals("53")) {
            marcaCE = marcaCE.toUpperCase();
        } else if (d.equals("54")) {
            modeloCE = modeloCE.toUpperCase();
        } else if (d.equals("55")) {
            submodeloCE = submodeloCE.toUpperCase();
        } else if (d.equals("56")) {
            numSerieCE = numSerieCE.toUpperCase();
        }
    }

    public void tipoCambioSet() {
        tipoCambio = tipoCambio;
        calcularTotalGlobal();
    }

    public void descripcionProductoSet() {
        descripcionProducto = descripcionProducto.toUpperCase();
    }

    @SuppressWarnings("deprecation")
    public void validarCamposCFDI(Boolean vistaP) {
        FacesContext context = FacesContext.getCurrentInstance();
        CreacionArchivo creacionArchivo = new CreacionArchivo();
        String[] error;
        ComplementoSEP sep = new ComplementoSEP();
        try {
            if (tipoComp != null && !tipoComp.equals("")) {
                if (serie != null) {
                    if (cliente != null) {
                        if (usoCfdiBean == null) {
                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Receptor) Elegir Uso de CFDI"));
                        } else {
                            if (!formaPagoS.equals("")) {
                                if (metodoPago != null && !metodoPago.getIdentifica().equals("")) {
                                    if (tipoCambio != null && tipoCambio.doubleValue() > 0) {
                                        if (conceptos.size() > 0) {
                                            Boolean bandera = false;
                                            Boolean complemtoSEP = false;
                                            if (CURP != null && !CURP.equals("")) {
                                                if (CURP.length() == 18) {
                                                    if (Alumno != null && !Alumno.equals("")) {
                                                        if (autRVOE != null && !autRVOE.equals("")) {
                                                            if (nivelEduc != null && !nivelEduc.equals("")) {
                                                                Boolean rf = true;
                                                                if (RFCAl != null && !RFCAl.equals("")) {
                                                                    if (RFCAl.length() < 12) {
                                                                        rf = false;
                                                                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Complemento SEP) RFC no cumle con la especificacion"));
                                                                    }
                                                                } else {
                                                                    RFCAl = "";
                                                                }
                                                                if (rf) {
                                                                    bandera = true;
                                                                    complemtoSEP = true;
                                                                    sep.setAlumno(Alumno);
                                                                    sep.setAutoRVOE(autRVOE);
                                                                    sep.setCurp(CURP);
                                                                    sep.setNivelEducativo(nivelEduc);
                                                                    sep.setRfc(RFCAl);
                                                                }
                                                            } else {
                                                                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Complemento SEP) Selecionar Nivel Educativo"));
                                                            }
                                                        } else {
                                                            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Complemento SEP) Agregar autRVOE"));
                                                        }

                                                    } else {
                                                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Complemento SEP) Agregar Alumno"));
                                                    }
                                                } else {
                                                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "(Complemento SEP) CURP no cumple con la especificacion"));
                                                }

                                            } else {
                                                sep = null;
                                                bandera = true;
                                            }

                                            if (bandera) {
                                                if (uuidRelacionados.size() > 0) {
                                                    factura.setRelacionado(true);
                                                    factura.setTipoRelacion(tipoRelacion);
                                                } else {
                                                    factura.setRelacionado(false);
                                                    factura.setTipoRelacion(null);
                                                }
                                                factura.setSucursal(usuarioSeleccionado.getSucursal());
                                                factura.setCliente(cliente);
                                                factura.setTipoComprobante(tcc.getIdentifica());
                                                factura.setMetodoPago(metodoPago);
                                                //factura.setEstado(""); despues de timbrado
                                                factura.setEstatus(null);
                                                factura.setMoneda(moneda);
                                                factura.setUsoCfdi(usosDao.buscarXIdentifica(usoCfdiBean.getIdentifica()));
                                                factura.setSubtotal(subTotal.setScale(2, RoundingMode.HALF_UP));
                                                factura.setTotal(total.setScale(2, RoundingMode.HALF_UP));
                                                LocalDate hoy = LocalDate.now();
                                                LocalTime ahora = LocalTime.now();
                                                LocalDateTime localDateTime = LocalDateTime.of(hoy, ahora);
                                                ZoneId zoneCDMX = ZoneId.of("America/Mexico_City");
                                                ZonedDateTime cdmxZonedDateTime = localDateTime.atZone(zoneCDMX);
                                                ZoneId zoneForanea = ZoneId.of(zonahorariaDao.buscarCP(usuarioSeleccionado.getCp()));
                                                ZonedDateTime nyDateTime = cdmxZonedDateTime.withZoneSameInstant(zoneForanea);
                                                String nd = nyDateTime.toString();
                                                nd = nd.substring(0, nd.indexOf("."));
                                                nd = nd.replaceAll("T", " ");
                                                Date d = null;
                                                try {
                                                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                                    d = formatter.parse(nd);
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                                factura.setFechaCreacion(d);
                                                factura.setResponsableCreacion(usuarioSeleccionado.getNombre());
                                                factura.setLugarExpedicion(usuarioSeleccionado.getCp());
                                                factura.setCondicionPago(condicionesPago);
                                                if (moneda.getIdentifica().equals("MXN")) {
                                                    factura.setTipoCambio(tipoCambio);
                                                } else {
                                                    factura.setTipoCambio(new BigDecimal(0));
                                                }
                                                factura.setFolio(serie.getNombre());
                                                factura.setConsecutivo(nextFolio);
                                                factura.setSerie(nextFolio);
                                                factura.setFormaPago(formaPagoS);
                                                factura.setConfirmacion("");
                                                factura.setObservaciones(observaciones);
                                                Double descuT = 0.0;
                                                for (Conceptos con : conceptos) {
                                                    if (con.getDescuento().doubleValue() > 0) {
                                                        descuT += con.getDescuento().setScale(2, RoundingMode.HALF_UP).doubleValue();
                                                    }
                                                }
                                                factura.setDescuento(new BigDecimal(descuT).setScale(2, RoundingMode.HALF_UP));
                                                CrearXML xml = new CrearXML();
                                                String datoXML = xml.crear(factura, serie, usuarioSeleccionado, uuidRelacionados, cliente, usoCfdiBean, numRegIdTrib, conceptos, sep, listILCT, listILCR);
                                                factura.setXml(datoXML);
                                                System.out.println("XML antes del timbrado \n" + datoXML +"\n");
                                                if (vistaP) {
                                                    try {
                                                        filePdfVP = creacionArchivo.descargarPDFVP2(factura, factura.getXml(), usuarioSeleccionado.getNombre(), factura.getObservaciones());
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                } else {
                                                    xmlTimbrado = timbrarFactura(datoXML);
                                                    if (xmlTimbrado.contains("errores")) {
                                                        errorTimbrado = xmlTimbrado;
                                                        errorBol = true;
                                                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Error!", "La factura no fue creada por que contiene errores"));
                                                    } else {
                                                        errorBol = false;
                                                        errorTimbrado = "";
                                                        Serie actualizarConsecutivo = new Serie();
                                                        actualizarConsecutivo.setIdentifica(factura.getSerie());
                                                        actualizarConsecutivo = serieDao.findSerieByIdentifica(actualizarConsecutivo);
                                                        actualizarConsecutivo.setFolioActual(factura.getConsecutivo());
                                                        serieDao.updateSerie(actualizarConsecutivo);
                                                        if (factura.getMetodoPago() == null || factura.getMetodoPago().getIdentifica() == null || factura.getMetodoPago().getIdentifica().equals("")) {
                                                            MetodoPago n = new MetodoPago();
                                                            n.setIdentifica("PUE");
                                                            n.setDescripcion("Pago en una sola exhibición");
                                                            factura.setMetodoPago(n);
                                                        }
                                                        //Almacenar UUID FMRS
                                                        String uuid = xmlTimbrado.substring(xmlTimbrado.lastIndexOf("UUID") + 6, xmlTimbrado.lastIndexOf("UUID") + 42);
                                                        factura.setUuid(uuid);
                                                        factura.setXmlTimbrado(xmlTimbrado);
                                                        factura.setEstado("FACTURA CREADA EXITOSAMENTE");
                                                        facturaService.insertFactura(factura);
                                                        enviarEmailACliente(factura, usuarioSeleccionado.getNombre());
                                                        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "La factura fue creada de manera exitosa"));
                                                    }
                                                }
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public String timbrarFactura(String xml) {
        String resultado = "";
        Session sess = HibernateUtil.getSessionFactory().openSession();
        String userID = "";
        try {
            Query QueryCert = sess.createQuery("from Certs as C WHERE C.rfc = :RFC order by C.fin desc").setString("RFC", usuarioSeleccionado.getNombre());
            List<Certs> Certificados = (List<Certs>) QueryCert.list();
            if (Certificados != null) {
                userID = String.valueOf(Certificados.get(0).getIdUsuario());
            }
        } catch (Exception e) {
            System.out.println("-errores- No se pudo encontrar certificado");
            resultado = "-errores- No se pudo encontrar certificado consultar con Soporte WFactra ";
            e.printStackTrace();
        } finally {

            sess.clear();
            sess.close();
        }
        userID="1205";
        if (userID != null && !userID.trim().equals("")) {

            String URL = "http://pac.wfactura.com/exp32/superpac?us=" + userID;/* pruebas Amazon */
//            String URL = "http://pac.wfactura.com/exp32_Produccion/superpac?us=" + userID;/* produccion Amazon */

            System.out.println("URL de timbrado " + URL);
            try {
                String queryEncode = URLEncoder.encode(
                        xml.replace("xmlns:tfd=\"http://www.sat.gob.mx/TimbreFiscalDigital\"", ""), "ISO-8859-1");
                try {

                    URLConnection uc = new URL(URL).openConnection();
                    uc.setDoOutput(true);
                    uc.setDoInput(true);
                    uc.setAllowUserInteraction(false);
                    DataOutputStream dos = new DataOutputStream(uc.getOutputStream());
                    dos.writeBytes("&cfdi=" + queryEncode);
                    dos.close();
                    DataInputStream dis = new DataInputStream(uc.getInputStream());
                    String nextline;
                    while ((nextline = dis.readLine()) != null) {
                        resultado = resultado + nextline;
                    }

                    dis.close();

//                    System.err.println(resultado);
                    System.out.println("Respuesta del timbrado");
                    System.out.println(resultado);
                } catch (Exception exep) {
                    exep.printStackTrace();
                }

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public void enviarEmailACliente(Factura factura, String Usuario) {
//        System.out.println("entro a Enviar Mail al usuario  : " + Usuario + "  uuid: " + factura.getUuid() + "  " + factura.getCliente().getRfc());
        FacesContext context = FacesContext.getCurrentInstance();
        EnvioEmail envioEmail = new EnvioEmail();
        Conversion conversion = new Conversion();
        CreacionArchivo creacionArchivo = new CreacionArchivo();
        File pdfFile = creacionArchivo.crearArchivoPdfParaEnviarEmail(factura);

        try {
            envioEmail.sendMailFactura(
                    factura.getCliente().getEmail(),
                    factura.getCliente().getRfc(),
                    factura.getCliente().getRfc(),//factura.getSucursal().getEmpresa().getRfc(),
                    factura.getCliente().getRazonSocial(),
                    creacionArchivo.getFolio(creacionArchivo.crearArchivoXmlParaEnviarEmail(factura)),
                    Usuario,
                    factura.getTipoComprobante(),
                    conversion.formatearFecha(new Date()),
                    conversion.convertirBigDecimalAformatoMoneda(factura.getTotal().doubleValue()),
                    "xxxxxxx", //resolucion
                    factura.getFolio(),
                    creacionArchivo.crearArchivoXmlParaEnviarEmail(factura),
                    pdfFile);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exito!", "El correo fue enviado al destinatario"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
