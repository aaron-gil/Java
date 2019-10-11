/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.utileria;

import com.google.wf.ProcesadorQR;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.wf.f1.wfactura1.entities.Certs;
import com.wf.f1.wfactura1.entities.HibernateUtil;
import com.wf.f1.wfactura1.model.DetalleFactura;
import com.wf.f1.wfactura1.model.Factura;
import com.wf.f1.wfactura1.model.Impuesto;
import com.wf.f1.wfactura1.model.ImpuestoRetenido;
import com.wf.f1.wfactura1.model.ImpuestoRetenidoHijoTotal;
import com.wf.f1.wfactura1.model.ImpuestoTrasladado;
import com.wf.f1.wfactura1.model.ImpuestoTrasladadoHijoTotal;
import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.hibernate.Session;
import mx.gob.sat.cfd.x3.ComprobanteDocument;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.CfdiRelacionados;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.CfdiRelacionados.CfdiRelacionado;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Emisor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Receptor;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.CuentaPredial;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.Impuestos;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.Impuestos.Retenciones;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.Impuestos.Retenciones.Retencion;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.Impuestos.Traslados;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.Impuestos.Traslados.Traslado;
import mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Conceptos.Concepto.InformacionAduanera;
import org.apache.avalon.framework.logger.ConsoleLogger;
import org.apache.commons.io.FileUtils;
import org.apache.fop.apps.Driver;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlOptions;
import org.hibernate.Query;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.apache.avalon.framework.logger.Logger;
import org.apache.fop.messaging.MessageHandler;
/**
 *
 * @author WF Consulting
 */
public class CreacionArchivo {
    
    public String generarXML(Factura factura, Serie serie, List<DetalleFactura> listaDetalleFactura, Usuario user) {
        Session sess = HibernateUtil.getSessionFactory().openSession();
        ComprobanteDocument comDoc = ComprobanteDocument.Factory.newInstance();
        Comprobante comprobante = comDoc.addNewComprobante();
        Conversion conversion = new Conversion();
        String noCertificado = "";
        String tipoImpuesto="";
        try {
            System.out.println("Buscar  noCertificado de  " + user.getNombre().trim());
            Query QueryCert = sess.createQuery("from mx.entities.Certs as C WHERE C.rfc = :RFC and C.estatusCer = 'A' order by C.fin desc").setString("RFC", user.getNombre().trim());
            List<Certs> Certificados = (List<Certs>) QueryCert.list();
            if (Certificados != null && Certificados.size() > 0) {
                noCertificado = Certificados.get(0).getNoCert();
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar certificado");
            e.printStackTrace();
        } finally {

            sess.clear();
            sess.close();
        }

        System.out.println(" 1. NoCertificado " + noCertificado);
        //NameSpaces
        HashMap suggestedPrefixes = new HashMap();
        suggestedPrefixes.put("http://www.sat.gob.mx/cfd/3", "cfdi");
        XmlOptions opcionesXml = new XmlOptions();
        opcionesXml.setSaveSuggestedPrefixes(suggestedPrefixes);
        opcionesXml.setSavePrettyPrint();
        opcionesXml.setSavePrettyPrintIndent(10);
        opcionesXml.setSaveInner();
        opcionesXml.setSaveNamespacesFirst();
        opcionesXml.setCharacterEncoding("UTF-8");

        comprobante.setLugarExpedicion(factura.getLugarExpedicion());
        System.out.println("2. Lugar de Expedición " + factura.getLugarExpedicion());
        comprobante.setTipoDeComprobante(factura.getTipoComprobante());
        System.out.println("3. Tipo de comprobante " + factura.getTipoComprobante());
        comprobante.setTotal(factura.getTotal());
        System.out.println("4. Total " + factura.getTotal());
        comprobante.setTipoCambio(factura.getTipoCambio());
        System.out.println("5. Tipo de cambio " + factura.getTipoCambio());
        comprobante.setMoneda(factura.getMoneda().getIdentifica());
        System.out.println("6. Moneda " + factura.getMoneda().getIdentifica());
        comprobante.setSubTotal(factura.getSubtotal());
        System.out.println("7. Subtotal " + factura.getSubtotal());
        if (factura.getCondicionPago() != null && !factura.getCondicionPago().equals("")) {
            System.out.println("8. Condiciones de Pago " + factura.getCondicionPago());
            comprobante.setCondicionesDePago(factura.getCondicionPago());
        }
        comprobante.setNoCertificado(noCertificado.trim());
        comprobante.setSello("");
        System.out.println("FormaPago Selected " + factura.getFormaPago());
        System.out.println("9. Forma de Pago" + factura.getCliente().getFormaPago().getIdentifica());
        comprobante.setFormaPago(factura.getFormaPago());

        comprobante.setFecha(conversion.fechaSegunSat(factura.getFechaCreacion()));

        try {
            comprobante.setFolio(serie.getFolioActual().toString());
        } catch (Exception e) {
        }
        try {
            comprobante.setSerie(serie.getNombre());
        } catch (Exception e) {
        }

        comprobante.setVersion("3.3");
        if (factura.getMetodoPago().getIdentifica() != null && !factura.getMetodoPago().getIdentifica().equals("")) {
            comprobante.setMetodoPago(factura.getMetodoPago().getIdentifica());
        }
        if (factura.isRelacionado()) {
            if (factura.getTipoRelacion().getIdentifica() != null && factura.getUuid() != null) {
                if (!"".equals(factura.getUuid()) && (!"".equals(factura.getTipoRelacion().getIdentifica()))) {
                    System.out.println("------>Relacionados!<------");
                    CfdiRelacionados cfdiRelacionados = comprobante.addNewCfdiRelacionados();
                    cfdiRelacionados.setTipoRelacion(factura.getTipoRelacion().getIdentifica());
                    CfdiRelacionado cfdiRelacionado = cfdiRelacionados.addNewCfdiRelacionado();
                    cfdiRelacionado.setUUID(factura.getUuid());
                }
            }
        } else {
            System.out.println("Error de Relación");
        }
        Emisor emisor = comprobante.addNewEmisor();
        emisor.setNombre(user.getNombreCompleto().toUpperCase().trim());
        emisor.setRfc(user.getNombre());
        emisor.setRegimenFiscal(user.getRegimenFiscal());

        Receptor receptor = comprobante.addNewReceptor();
        receptor.setNombre(factura.getCliente().getRazonSocial());
        receptor.setRfc(factura.getCliente().getRfc().toUpperCase().trim());
        receptor.setUsoCFDI(factura.getUsoCfdi().getIdentifica());

        Conceptos conceptos = comprobante.addNewConceptos();

        BigDecimal importeTrasladoIva = new BigDecimal(0);
        BigDecimal importeTrasladoIeps = new BigDecimal(0);
        BigDecimal importeRetencionIva = new BigDecimal(0);
        BigDecimal importeRetencionIeps = new BigDecimal(0);
        BigDecimal importeRetencionIsr = new BigDecimal(0);
        BigDecimal tasaTrasladoIva = new BigDecimal(0);
        BigDecimal tasaTrasladoIeps = new BigDecimal(0);

        BigDecimal descuentoTotal = new BigDecimal(0);
        ArrayList<ImpuestoRetenidoHijoTotal> ListaImpuestosRetenidosTotales = new ArrayList<>();
        ArrayList<ImpuestoTrasladadoHijoTotal> ListaImpuestosTrasladadosTotales = new ArrayList<>();
        for (DetalleFactura detalle : listaDetalleFactura) {

            Concepto concepto = conceptos.addNewConcepto();
            concepto.setImporte(detalle.getImporte());
            concepto.setValorUnitario(detalle.getProducto().getValorUnitario());
            if (detalle.getDesccripcionCliente() == null || detalle.getDesccripcionCliente().equals("")) {
                concepto.setDescripcion(detalle.getProducto().getDescripcion());
            } else {
                concepto.setDescripcion(detalle.getDesccripcionCliente());
            }
            concepto.setClaveProdServ(detalle.getProducto().getCodigoSat());
            if (detalle.getDescuento() != null && detalle.getDescuento().compareTo(BigDecimal.ZERO) > 0) {
                concepto.setDescuento(detalle.getDescuento());
                descuentoTotal = descuentoTotal.add(detalle.getDescuento());
            }
            concepto.setClaveUnidad(detalle.getProducto().getMedida().getIdentifica());
            if (detalle.getProducto().getMedida().getNombre() != null) {
                if (detalle.getProducto().getMedida().getNombre().length() > 20) {
                    concepto.setUnidad(detalle.getProducto().getMedida().getNombre().substring(0, 20));
                } else {
                    concepto.setUnidad(detalle.getProducto().getMedida().getNombre());
                }
            }

            concepto.setCantidad(detalle.getCantidadReal());

            if (detalle.getProducto().getNumeroIdentificador() != null) {
                if (!"".equals(detalle.getProducto().getNumeroIdentificador())) {
                    concepto.setNoIdentificacion(detalle.getProducto().getNumeroIdentificador());
                }
            }

            if (!"".equals(detalle.getPedimento())) {
                InformacionAduanera informacionA = concepto.addNewInformacionAduanera();
                informacionA.setNumeroPedimento(detalle.getPedimento());
            }

            if (detalle.getProducto().getNumeroPredial() != null && !"".equals(detalle.getProducto().getNumeroPredial())) {
                CuentaPredial cuentaPredial = concepto.addNewCuentaPredial();
                cuentaPredial.setNumero(detalle.getProducto().getNumeroPredial());
                System.out.println("Numero de cuenta predial -> " + detalle.getProducto().getNumeroPredial());
            }

            if (!"P".equals(factura.getTipoComprobante())) {

                if ((detalle.getListaImpuestosTrasladados() != null && !detalle.getListaImpuestosTrasladados().isEmpty() && detalle.getListaImpuestosTrasladados().size() > 0) || (detalle.getListaImpuestosRetenidos() != null && detalle.getListaImpuestosRetenidos().size() > 0 && !detalle.getListaImpuestosRetenidos().isEmpty())) {
                    System.out.println("Concepto con impuestos");
                    Impuestos impuestosC = concepto.addNewImpuestos();

                    if (detalle.getListaImpuestosTrasladados() != null && !detalle.getListaImpuestosTrasladados().isEmpty() && detalle.getListaImpuestosTrasladados().size() > 0) {
                        ArrayList<ImpuestoTrasladado> listTrasladados = detalle.getListaImpuestosTrasladados();
                        Traslados traslados = impuestosC.addNewTraslados();
                        for (int a = 0; a < listTrasladados.size(); a++) {
                            System.out.println("Tamaño de traslados " + listTrasladados.size());
                            Traslado traslado = traslados.addNewTraslado();
                            if (listTrasladados.get(a).getTipoFactor().equals("Exento")) {
                                System.out.println("Es exento");
                                tipoImpuesto="Exento";
                                traslado.setBase(listTrasladados.get(a).getBase());
                                traslado.setImpuesto(listTrasladados.get(a).getImpuesto());
                                traslado.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                            } else {
                                System.out.println("No es exento");
                                ImpuestoTrasladadoHijoTotal t1 = new ImpuestoTrasladadoHijoTotal();
                                String primaryKey = "";

                                primaryKey = listTrasladados.get(a).getImpuesto() + listTrasladados.get(a).getTipoFactor() + listTrasladados.get(a).getTasaOCuota().toString();
                                System.out.println("Primary key " + primaryKey);
                                if (ListaImpuestosTrasladadosTotales != null && !ListaImpuestosTrasladadosTotales.isEmpty()) {
                                    System.out.println("Lista no vacia ");
                                    int indice = isPrimary(ListaImpuestosTrasladadosTotales, primaryKey);
                                    System.out.println("Indice " + indice);
                                    if (indice != -1) {

                                        BigDecimal total = ListaImpuestosTrasladadosTotales.get(indice).getImporte();
                                        total = total.add(listTrasladados.get(a).getImporte()).setScale(2, RoundingMode.HALF_UP);
                                        ListaImpuestosTrasladadosTotales.get(indice).setImporte(total);

                                    } else {

                                        t1.setLlavePrimaria(primaryKey);
                                        t1.setImpuesto(listTrasladados.get(a).getImpuesto());
                                        t1.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                                        t1.setTasaOCuota(listTrasladados.get(a).getTasaOCuota());
                                        t1.setImporte(listTrasladados.get(a).getImporte().setScale(2, RoundingMode.HALF_UP));
                                        ListaImpuestosTrasladadosTotales.add(t1);
                                    }
                                } else {
                                    System.out.println("Lista vacia ");
                                    t1.setLlavePrimaria(primaryKey);
                                    t1.setImpuesto(listTrasladados.get(a).getImpuesto());
                                    t1.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                                    t1.setTasaOCuota(listTrasladados.get(a).getTasaOCuota());
                                    t1.setImporte(listTrasladados.get(a).getImporte().setScale(2, RoundingMode.HALF_UP));
                                    ListaImpuestosTrasladadosTotales.add(t1);
                                }
                                traslado.setBase(listTrasladados.get(a).getBase());
                                traslado.setImpuesto(listTrasladados.get(a).getImpuesto());
                                traslado.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                                traslado.setTasaOCuota(listTrasladados.get(a).getTasaOCuota());
                                traslado.setImporte(listTrasladados.get(a).getImporte().setScale(2, RoundingMode.HALF_UP));

                                if (listTrasladados.get(a).getImpuesto().equals("002")) {
                                    System.out.println("Traslado IVA");
                                    importeTrasladoIva = importeTrasladoIva.add(listTrasladados.get(a).getImporte()).setScale(2, RoundingMode.HALF_UP);
                                }
                                if (listTrasladados.get(a).getImpuesto().equals("003")) {
                                    System.out.println("Traslado IEPS");
                                    importeTrasladoIeps = importeTrasladoIeps.add(listTrasladados.get(a).getImporte()).setScale(2, RoundingMode.HALF_UP);
                                }

                            }
                        }
                    }

                    if (detalle.getListaImpuestosRetenidos() != null && detalle.getListaImpuestosRetenidos().size() > 0 && !detalle.getListaImpuestosRetenidos().isEmpty()) {
                        ArrayList<ImpuestoRetenido> ListaRetenidos = detalle.getListaImpuestosRetenidos();
                        Retenciones retenciones = impuestosC.addNewRetenciones();
                        for (int b = 0; b < ListaRetenidos.size(); b++) {
                            Retencion retencion = retenciones.addNewRetencion();
                            retencion.setBase(ListaRetenidos.get(b).getBase());
                            retencion.setImpuesto(ListaRetenidos.get(b).getImpuesto());
                            retencion.setTipoFactor(ListaRetenidos.get(b).getTipoFactor());
                            retencion.setTasaOCuota(ListaRetenidos.get(b).getTasaOCuota());
                            retencion.setImporte(ListaRetenidos.get(b).getImporte());

                            if (ListaRetenidos.get(b).getImpuesto().equals("001")) {
                                System.out.println("Retencion ISR");
                                importeRetencionIsr = importeRetencionIsr.add(ListaRetenidos.get(b).getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }
                            if (ListaRetenidos.get(b).getImpuesto().equals("002")) {
                                System.out.println("Retencion IVA");
                                importeRetencionIva = importeRetencionIva.add(ListaRetenidos.get(b).getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }
                            if (ListaRetenidos.get(b).getImpuesto().equals("003")) {
                                System.out.println("Retencion IEPS");
                                importeRetencionIeps = importeRetencionIeps.add(ListaRetenidos.get(b).getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }
                        }

                    }
                }
                Boolean Rete = true;
            }

        }

        if (descuentoTotal.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Descuento Total" + descuentoTotal);

            comprobante.setDescuento(descuentoTotal.setScale(2, RoundingMode.HALF_UP));
        } else {
            System.out.println("El comprobante no lleva descuento");
        }

        if (!tipoImpuesto.equals("Exento")&&((factura.getTotalImpuestoTrasladados() != null && factura.getTotalImpuestoTrasladados().compareTo(BigDecimal.ZERO) >= 0) || (factura.getTotalImpuestoRetenidos() != null && factura.getTotalImpuestoRetenidos().compareTo(BigDecimal.ZERO) > 0))) {
            mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos impuestosTotales = comprobante.addNewImpuestos();
            System.out.println("nodo trasladados");
            if (factura.getTotalImpuestoTrasladados().compareTo(BigDecimal.ZERO) >= 0) {
                impuestosTotales.setTotalImpuestosTrasladados(factura.getTotalImpuestoTrasladados().setScale(2, RoundingMode.HALF_UP));
                mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados traslados = impuestosTotales.addNewTraslados();
                System.out.println("nodo trasladado hijo");
                if (ListaImpuestosTrasladadosTotales != null && !ListaImpuestosTrasladadosTotales.isEmpty() && ListaImpuestosTrasladadosTotales.size() > 0) {
                    System.out.println("agregando uno por uno");
                    for (int c = 0; c < ListaImpuestosTrasladadosTotales.size(); c++) {
                        System.out.println("datos  "+ListaImpuestosTrasladadosTotales.get(c).getTipoFactor());
                        mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados.Traslado traslado = traslados.addNewTraslado();
                        traslado.setTipoFactor(ListaImpuestosTrasladadosTotales.get(c).getTipoFactor());
                        traslado.setImpuesto(ListaImpuestosTrasladadosTotales.get(c).getImpuesto());
                        traslado.setTasaOCuota(ListaImpuestosTrasladadosTotales.get(c).getTasaOCuota());
                        System.out.println("total de  impuestos trasladados antes de la conversion    " + ListaImpuestosTrasladadosTotales.get(c).getImporte());
                        traslado.setImporte(ListaImpuestosTrasladadosTotales.get(c).getImporte().setScale(2, RoundingMode.HALF_UP));
                  }
                }

            }

            if (factura.getTotalImpuestoRetenidos().compareTo(BigDecimal.ZERO) > 0) {
                impuestosTotales.setTotalImpuestosRetenidos(factura.getTotalImpuestoRetenidos());
                mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones retenciones = impuestosTotales.addNewRetenciones();
                if (importeRetencionIva.compareTo(BigDecimal.ZERO) > 0) {
                    mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones.Retencion retencionIvaTotal = retenciones.addNewRetencion();
                    retencionIvaTotal.setImporte(importeRetencionIva);
                    retencionIvaTotal.setImpuesto("002");
                }

                if (importeRetencionIsr.compareTo(BigDecimal.ZERO) > 0) {
                    mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones.Retencion retencionIsrTotal = retenciones.addNewRetencion();
                    retencionIsrTotal.setImporte(importeRetencionIsr);
                    retencionIsrTotal.setImpuesto("001");
                }

                if (importeRetencionIeps.compareTo(BigDecimal.ZERO) > 0) {
                    mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones.Retencion retencionIepsTotal = retenciones.addNewRetencion();
                    retencionIepsTotal.setImporte(importeRetencionIeps);
                    retencionIepsTotal.setImpuesto("003");
                }

            }

        }

        XmlCursor cursor = comDoc.newCursor();
        if (cursor.toFirstChild()) {
            cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation"), "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
        }

        String xmlFormateado = comDoc.xmlText(opcionesXml);
        System.out.println("Imprimiendo XML en generar XML");
        System.out.println(xmlFormateado);
        return xmlFormateado;

    }
    
    public String crearXML(Factura factura, Serie serie, List<DetalleFactura> listaDetalleFactura, List<Impuesto> listaImpuestosPorDetalle, Usuario user){
        String xml="";
        
        Session sess = HibernateUtil.getSessionFactory().openSession();
        ComprobanteDocument comDoc = ComprobanteDocument.Factory.newInstance();
        Comprobante comprobante = comDoc.addNewComprobante();
        Conversion conversion = new Conversion();
        String noCertificado = "";
        String tipoImpuesto="";
        
        try {
            System.out.println("Buscar  noCertificado de  " + user.getNombre().trim());
            Query QueryCert = sess.createQuery("from mx.entities.Certs as C WHERE C.rfc = :RFC and C.estatusCer = 'A' order by C.fin desc").setString("RFC", user.getNombre().trim());
            List<Certs> Certificados = (List<Certs>) QueryCert.list();
            if (Certificados != null && Certificados.size() > 0) {
                noCertificado = Certificados.get(0).getNoCert();
            }
        } catch (Exception e) {
            System.out.println("No se pudo encontrar certificado");
            e.printStackTrace();
        } finally {

            sess.clear();
            sess.close();
        }
        
        System.out.println(" 1. NoCertificado " + noCertificado);
        //NameSpaces
        HashMap suggestedPrefixes = new HashMap();
        suggestedPrefixes.put("http://www.sat.gob.mx/cfd/3", "cfdi");
        XmlOptions opcionesXml = new XmlOptions();
        opcionesXml.setSaveSuggestedPrefixes(suggestedPrefixes);
        opcionesXml.setSavePrettyPrint();
        opcionesXml.setSavePrettyPrintIndent(10);
        opcionesXml.setSaveInner();
        opcionesXml.setSaveNamespacesFirst();
        opcionesXml.setCharacterEncoding("UTF-8");

        comprobante.setLugarExpedicion(factura.getLugarExpedicion());
        System.out.println("2. Lugar de Expedición " + factura.getLugarExpedicion());
        comprobante.setTipoDeComprobante(factura.getTipoComprobante());
        System.out.println("3. Tipo de comprobante " + factura.getTipoComprobante());
        comprobante.setTotal(factura.getTotal());
        System.out.println("4. Total " + factura.getTotal());
        comprobante.setTipoCambio(factura.getTipoCambio());
        System.out.println("5. Tipo de cambio " + factura.getTipoCambio());
        comprobante.setMoneda(factura.getMoneda().getIdentifica());
        System.out.println("6. Moneda " + factura.getMoneda().getIdentifica());
        comprobante.setSubTotal(factura.getSubtotal());
        System.out.println("7. Subtotal " + factura.getSubtotal());
        if (factura.getCondicionPago() != null && !factura.getCondicionPago().equals("")) {
            System.out.println("8. Condiciones de Pago " + factura.getCondicionPago());
            comprobante.setCondicionesDePago(factura.getCondicionPago());
        }
        comprobante.setNoCertificado(noCertificado.trim());
        comprobante.setSello("");
        System.out.println("FormaPago Selected " + factura.getFormaPago());
        System.out.println("9. Forma de Pago" + factura.getCliente().getFormaPago().getIdentifica());
        comprobante.setFormaPago(factura.getFormaPago());

        comprobante.setFecha(conversion.fechaSegunSat(factura.getFechaCreacion()));

        try {
            comprobante.setFolio(serie.getFolioActual().toString());
        } catch (Exception e) {
        }
        try {
            comprobante.setSerie(serie.getNombre());
        } catch (Exception e) {
        }

        comprobante.setVersion("3.3");
        if (factura.getMetodoPago().getIdentifica() != null && !factura.getMetodoPago().getIdentifica().equals("")) {
            comprobante.setMetodoPago(factura.getMetodoPago().getIdentifica());
        }
        
        if (factura.isRelacionado()) {
            if (factura.getTipoRelacion().getIdentifica() != null && factura.getUuid() != null) {
                if (!"".equals(factura.getUuid()) && (!"".equals(factura.getTipoRelacion().getIdentifica()))) {
                    System.out.println("------>Relacionados!<------");
                    CfdiRelacionados cfdiRelacionados = comprobante.addNewCfdiRelacionados();
                    cfdiRelacionados.setTipoRelacion(factura.getTipoRelacion().getIdentifica());
                    CfdiRelacionado cfdiRelacionado = cfdiRelacionados.addNewCfdiRelacionado();
                    cfdiRelacionado.setUUID(factura.getUuid());
                }
            }
        } else {
            System.out.println("Error de Relación");
        }
        
        Emisor emisor = comprobante.addNewEmisor();
        emisor.setNombre(user.getNombreCompleto().toUpperCase().trim());
        emisor.setRfc(user.getNombre());
        emisor.setRegimenFiscal(user.getRegimenFiscal());

        Receptor receptor = comprobante.addNewReceptor();
        receptor.setNombre(factura.getCliente().getRazonSocial());
        receptor.setRfc(factura.getCliente().getRfc().toUpperCase().trim());
        receptor.setUsoCFDI(factura.getUsoCfdi().getIdentifica());

        Conceptos conceptos = comprobante.addNewConceptos();

        BigDecimal importeTrasladoIva = new BigDecimal(0);
        BigDecimal importeTrasladoIeps = new BigDecimal(0);
        BigDecimal importeRetencionIva = new BigDecimal(0);
        BigDecimal importeRetencionIeps = new BigDecimal(0);
        BigDecimal importeRetencionIsr = new BigDecimal(0);
        BigDecimal tasaTrasladoIva = new BigDecimal(0);
        BigDecimal tasaTrasladoIeps = new BigDecimal(0);

        BigDecimal descuentoTotal = new BigDecimal(0);
        ArrayList<ImpuestoRetenidoHijoTotal> ListaImpuestosRetenidosTotales = new ArrayList<>();
        ArrayList<ImpuestoTrasladadoHijoTotal> ListaImpuestosTrasladadosTotales = new ArrayList<>();
        for (DetalleFactura detalle : listaDetalleFactura) {

            Concepto concepto = conceptos.addNewConcepto();
            concepto.setImporte(detalle.getImporte());
            concepto.setValorUnitario(detalle.getProducto().getValorUnitario());
            if (detalle.getDesccripcionCliente() == null || detalle.getDesccripcionCliente().equals("")) {
                concepto.setDescripcion(detalle.getProducto().getDescripcion());
            } else {
                concepto.setDescripcion(detalle.getDesccripcionCliente());
            }
            concepto.setClaveProdServ(detalle.getProducto().getCodigoSat());
            if (detalle.getDescuento() != null && detalle.getDescuento().compareTo(BigDecimal.ZERO) > 0) {
                concepto.setDescuento(detalle.getDescuento());
                descuentoTotal = descuentoTotal.add(detalle.getDescuento());
            }
            concepto.setClaveUnidad(detalle.getProducto().getMedida().getIdentifica());
            if (detalle.getProducto().getMedida().getNombre() != null) {
                if (detalle.getProducto().getMedida().getNombre().length() > 20) {
                    concepto.setUnidad(detalle.getProducto().getMedida().getNombre().substring(0, 20));
                } else {
                    concepto.setUnidad(detalle.getProducto().getMedida().getNombre());
                }
            }

            concepto.setCantidad(detalle.getCantidadReal());

            if (detalle.getProducto().getNumeroIdentificador() != null) {
                if (!"".equals(detalle.getProducto().getNumeroIdentificador())) {
                    concepto.setNoIdentificacion(detalle.getProducto().getNumeroIdentificador());
                }
            }

            if (!"".equals(detalle.getPedimento())) {
                InformacionAduanera informacionA = concepto.addNewInformacionAduanera();
                informacionA.setNumeroPedimento(detalle.getPedimento());
            }
            
            if (detalle.getProducto().getNumeroPredial() != null && !"".equals(detalle.getProducto().getNumeroPredial())) {
//                if (!"".equals(detalle.getProducto().getNumeroIdentificador())) {
                CuentaPredial cuentaPredial = concepto.addNewCuentaPredial();
                cuentaPredial.setNumero(detalle.getProducto().getNumeroPredial());
                System.out.println("Numero de cuenta predial -> " + detalle.getProducto().getNumeroPredial());
//                }
            }

            if (!"P".equals(factura.getTipoComprobante())) {

                if ((detalle.getListaImpuestosTrasladados() != null && !detalle.getListaImpuestosTrasladados().isEmpty() && detalle.getListaImpuestosTrasladados().size() > 0) || (detalle.getListaImpuestosRetenidos() != null && detalle.getListaImpuestosRetenidos().size() > 0 && !detalle.getListaImpuestosRetenidos().isEmpty())) {
                    System.out.println("Concepto con impuestos");
                    Impuestos impuestosC = concepto.addNewImpuestos();

                    if (detalle.getListaImpuestosTrasladados() != null && !detalle.getListaImpuestosTrasladados().isEmpty() && detalle.getListaImpuestosTrasladados().size() > 0) {
                        ArrayList<ImpuestoTrasladado> listTrasladados = detalle.getListaImpuestosTrasladados();
                        Traslados traslados = impuestosC.addNewTraslados();
                        for (int a = 0; a < listTrasladados.size(); a++) {
                            System.out.println("Tamaño de traslados " + listTrasladados.size());
                            Traslado traslado = traslados.addNewTraslado();
                            if (listTrasladados.get(a).getTipoFactor().equals("Exento")) {
                                System.out.println("Es exento");
                                tipoImpuesto="Exento";
                                traslado.setBase(listTrasladados.get(a).getBase());
                                traslado.setImpuesto(listTrasladados.get(a).getImpuesto());
                                traslado.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                            } else {
                                System.out.println("No es exento");
                                ImpuestoTrasladadoHijoTotal t1 = new ImpuestoTrasladadoHijoTotal();
                                String primaryKey = "";

                                primaryKey = listTrasladados.get(a).getImpuesto() + listTrasladados.get(a).getTipoFactor() + listTrasladados.get(a).getTasaOCuota().toString();
                                System.out.println("Primary key " + primaryKey);
                                if (ListaImpuestosTrasladadosTotales != null && !ListaImpuestosTrasladadosTotales.isEmpty()) {
                                    System.out.println("Lista no vacia ");
                                    int indice = isPrimary(ListaImpuestosTrasladadosTotales, primaryKey);
                                    System.out.println("Indice " + indice);
                                    if (indice != -1) {

                                        BigDecimal total = ListaImpuestosTrasladadosTotales.get(indice).getImporte();
                                        total = total.add(listTrasladados.get(a).getImporte()).setScale(2, RoundingMode.HALF_UP);
                                        ListaImpuestosTrasladadosTotales.get(indice).setImporte(total);

                                    } else {

                                        t1.setLlavePrimaria(primaryKey);
                                        t1.setImpuesto(listTrasladados.get(a).getImpuesto());
                                        t1.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                                        t1.setTasaOCuota(listTrasladados.get(a).getTasaOCuota());
                                        t1.setImporte(listTrasladados.get(a).getImporte().setScale(2, RoundingMode.HALF_UP));
                                        ListaImpuestosTrasladadosTotales.add(t1);
                                    }
                                } else {
                                    System.out.println("Lista vacia ");
                                    t1.setLlavePrimaria(primaryKey);
                                    t1.setImpuesto(listTrasladados.get(a).getImpuesto());
                                    t1.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                                    t1.setTasaOCuota(listTrasladados.get(a).getTasaOCuota());
                                    t1.setImporte(listTrasladados.get(a).getImporte().setScale(2, RoundingMode.HALF_UP));
                                    ListaImpuestosTrasladadosTotales.add(t1);
                                }
                                traslado.setBase(listTrasladados.get(a).getBase());
                                traslado.setImpuesto(listTrasladados.get(a).getImpuesto());
                                traslado.setTipoFactor(listTrasladados.get(a).getTipoFactor());
                                traslado.setTasaOCuota(listTrasladados.get(a).getTasaOCuota());
                                traslado.setImporte(listTrasladados.get(a).getImporte().setScale(2, RoundingMode.HALF_UP));

                                if (listTrasladados.get(a).getImpuesto().equals("002")) {
                                    System.out.println("Traslado IVA");
                                    importeTrasladoIva = importeTrasladoIva.add(listTrasladados.get(a).getImporte()).setScale(2, RoundingMode.HALF_UP);
                                }
                                if (listTrasladados.get(a).getImpuesto().equals("003")) {
                                    System.out.println("Traslado IEPS");
                                    importeTrasladoIeps = importeTrasladoIeps.add(listTrasladados.get(a).getImporte()).setScale(2, RoundingMode.HALF_UP);
                                }

                            }
                        }
                    }

                    if (detalle.getListaImpuestosRetenidos() != null && detalle.getListaImpuestosRetenidos().size() > 0 && !detalle.getListaImpuestosRetenidos().isEmpty()) {
                        ArrayList<ImpuestoRetenido> ListaRetenidos = detalle.getListaImpuestosRetenidos();
                        Retenciones retenciones = impuestosC.addNewRetenciones();
                        for (int b = 0; b < ListaRetenidos.size(); b++) {
                            Retencion retencion = retenciones.addNewRetencion();
                            retencion.setBase(ListaRetenidos.get(b).getBase());
                            retencion.setImpuesto(ListaRetenidos.get(b).getImpuesto());
                            retencion.setTipoFactor(ListaRetenidos.get(b).getTipoFactor());
                            retencion.setTasaOCuota(ListaRetenidos.get(b).getTasaOCuota());
                            retencion.setImporte(ListaRetenidos.get(b).getImporte());

                            if (ListaRetenidos.get(b).getImpuesto().equals("001")) {
                                System.out.println("Retencion ISR");
                                importeRetencionIsr = importeRetencionIsr.add(ListaRetenidos.get(b).getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }
                            if (ListaRetenidos.get(b).getImpuesto().equals("002")) {
                                System.out.println("Retencion IVA");
                                importeRetencionIva = importeRetencionIva.add(ListaRetenidos.get(b).getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }
                            if (ListaRetenidos.get(b).getImpuesto().equals("003")) {
                                System.out.println("Retencion IEPS");
                                importeRetencionIeps = importeRetencionIeps.add(ListaRetenidos.get(b).getImporte()).setScale(2, RoundingMode.HALF_UP);
                            }
                        }

                    }
                }
                Boolean Rete = true;
                }

        }
        
        if (descuentoTotal.compareTo(BigDecimal.ZERO) > 0) {
            System.out.println("Descuento Total" + descuentoTotal);

            comprobante.setDescuento(descuentoTotal.setScale(2, RoundingMode.HALF_UP));
        } else {
            System.out.println("El comprobante no lleva descuento");
        }

        if (!tipoImpuesto.equals("Exento")&&((factura.getTotalImpuestoTrasladados() != null && factura.getTotalImpuestoTrasladados().compareTo(BigDecimal.ZERO) >= 0) || (factura.getTotalImpuestoRetenidos() != null && factura.getTotalImpuestoRetenidos().compareTo(BigDecimal.ZERO) > 0))) {
            mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos impuestosTotales = comprobante.addNewImpuestos();
            System.out.println("nodo trasladados");
            if (factura.getTotalImpuestoTrasladados().compareTo(BigDecimal.ZERO) >= 0) {
                impuestosTotales.setTotalImpuestosTrasladados(factura.getTotalImpuestoTrasladados().setScale(2, RoundingMode.HALF_UP));
                mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados traslados = impuestosTotales.addNewTraslados();
                System.out.println("nodo trasladado hijo");
                if (ListaImpuestosTrasladadosTotales != null && !ListaImpuestosTrasladadosTotales.isEmpty() && ListaImpuestosTrasladadosTotales.size() > 0) {
                    System.out.println("agregando uno por uno");
                    for (int c = 0; c < ListaImpuestosTrasladadosTotales.size(); c++) {
                        System.out.println("datos  "+ListaImpuestosTrasladadosTotales.get(c).getTipoFactor());
                        mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Traslados.Traslado traslado = traslados.addNewTraslado();
                        traslado.setTipoFactor(ListaImpuestosTrasladadosTotales.get(c).getTipoFactor());
                        traslado.setImpuesto(ListaImpuestosTrasladadosTotales.get(c).getImpuesto());
                        traslado.setTasaOCuota(ListaImpuestosTrasladadosTotales.get(c).getTasaOCuota());
                        System.out.println("total de  impuestos trasladados antes de la conversion    " + ListaImpuestosTrasladadosTotales.get(c).getImporte());
                        traslado.setImporte(ListaImpuestosTrasladadosTotales.get(c).getImporte().setScale(2, RoundingMode.HALF_UP));
//                        traslado.setImporte(ListaImpuestosTrasladadosTotales.get(c).getImporte());
//                        System.out.println("total de  impuestos trasladados despues de la conversion    " + ListaImpuestosTrasladadosTotales.get(c).getImporte().setScale(2, RoundingMode.HALF_UP));
                    }
                }

            }

            if (factura.getTotalImpuestoRetenidos().compareTo(BigDecimal.ZERO) > 0) {
                impuestosTotales.setTotalImpuestosRetenidos(factura.getTotalImpuestoRetenidos());
                mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones retenciones = impuestosTotales.addNewRetenciones();
                if (importeRetencionIva.compareTo(BigDecimal.ZERO) > 0) {
                    mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones.Retencion retencionIvaTotal = retenciones.addNewRetencion();
                    retencionIvaTotal.setImporte(importeRetencionIva);
                    retencionIvaTotal.setImpuesto("002");
                }

                if (importeRetencionIsr.compareTo(BigDecimal.ZERO) > 0) {
                    mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones.Retencion retencionIsrTotal = retenciones.addNewRetencion();
                    retencionIsrTotal.setImporte(importeRetencionIsr);
                    retencionIsrTotal.setImpuesto("001");
                }

                if (importeRetencionIeps.compareTo(BigDecimal.ZERO) > 0) {
                    mx.gob.sat.cfd.x3.ComprobanteDocument.Comprobante.Impuestos.Retenciones.Retencion retencionIepsTotal = retenciones.addNewRetencion();
                    retencionIepsTotal.setImporte(importeRetencionIeps);
                    retencionIepsTotal.setImpuesto("003");
                }

            }

        }

        XmlCursor cursor = comDoc.newCursor();
        if (cursor.toFirstChild()) {
            cursor.setAttributeText(new QName("http://www.w3.org/2001/XMLSchema-instance", "schemaLocation"), "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
        }

        String xmlFormateado = comDoc.xmlText(opcionesXml);
        System.out.println("Imprimiendo XML en generar XML");
        System.out.println(xmlFormateado);
        return xmlFormateado;
    }
    
        public void crearXML(String content, File xml) {
        try {

            FileOutputStream archivoXml = new FileOutputStream(xml);
            OutputStreamWriter fileWriter = new OutputStreamWriter(archivoXml, "UTF-8");
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error al crear XML " + ex.getMessage());
        }
    }

    /**
     * Metodo para generar el archivo pdf
     *
     * @param xml parametro que define el archivo xml
     * @param pdf parametro que define el archivo pdf
     * @param xslt parametro que define el archivo xslt
     * @param pathlogo parametro que define el logo en la que aparecera en el
     * pdf
     * @param montoLetra parametro que define el monto de la factura en letras
     * @param qrInfo parametro que define el QR de la factura
     * @throws FileNotFoundException
     * @throws IOException
     */
    /*public void generarPdf(File xml, File pdf, File xslt, String pathlogo, String montoLetra, String qrInfo,String cufe) throws FileNotFoundException, IOException {
        Driver driver = new Driver();
        Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_INFO);
        driver.setLogger(logger);
        MessageHandler.setScreenLogger(logger);
        driver.setRenderer(Driver.RENDER_PDF);
        OutputStream out = new java.io.FileOutputStream(pdf);

        try {
            driver.setOutputStream(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslt));
            //transformer.setParameter("imgLogo", pathlogo);
            transformer.setParameter("montoLetra", montoLetra);
            transformer.setParameter("cadenaQr", qrInfo);
            transformer.setParameter("cufe", cufe);
            Source src = new StreamSource(xml);
            Result res = new SAXResult(driver.getContentHandler());
            transformer.transform(src, res);
        } catch (Exception e) {
            System.out.println("ERROR GENERATE PDF  ");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }*/
    public void abrirCarpetaTxt() {
        String path_home = System.getProperty("user.home") + "\\Downloads";
        try {
            Process p = Runtime.getRuntime().exec("explorer.exe " + path_home);
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String aux = br.readLine();

            while (aux != null) {
                aux = br.readLine();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StreamedContent descargarArchivoXML(Factura factura) {

        StreamedContent file;
        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\factura.xml");
        System.out.println("Imprimiendo ruta de la factura " + xmlFile.getAbsolutePath());
        crearXML(factura.getXmlTimbrado(), xmlFile);

        InputStream stream = null;
        try {
            stream = new FileInputStream(xmlFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        file = new DefaultStreamedContent(stream, "application/xml", factura.getFolio() + ".xml");
        return file;
    }

    public StreamedContent descargarArchivoPDF(Factura factura) {

        //String path_home = System.getProperty("user.home")+"\\Downloads\\factura.xslt";
        StreamedContent file;

        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\factura.xml");
        File pdfFile = new File(temp + "\\factura.pdf");
        String pathLogo = "/home/Wf/logos/";
        //File xlstFile = new File(path_home);
        File xlstFile = new File("/home/Wf/xslt/factura.xslt");

        String leyenda = "";
        if (factura.getObservaciones() != null && !factura.getObservaciones().equals("")) {
            leyenda = factura.getObservaciones();
            System.out.println("Leyenda en la factura" + leyenda);
        }
        String rfcEmisor = getRFCEmisor(xmlFile);

        if (rfcEmisor != null && !rfcEmisor.trim().equals("") && rfcEmisor.trim().equals("ISE180124TL4")) {
            xlstFile = new File("/home/Wf/xslt/infiniti2.xslt");
        }

        crearXML(factura.getXmlTimbrado(), xmlFile);

        String montoLetra = "";
        String total = getTotal(xmlFile);
        BigDecimal numero = new BigDecimal(total).setScale(2);

        System.out.println("Imprimiendo el total " + numero.toString());
        try {
            montoLetra = CnvNumsLets.getInstance().toLetras(numero.longValue());
            int ttt = numero.intValue();
            float tt = numero.floatValue();
            Float t = Float.valueOf((tt - ttt) * 100.0F);
            if ((factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("PESOS")) || (factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("MXN")) || (factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("MXN"))) {
                montoLetra = montoLetra.toUpperCase() + " " + factura.getMoneda().getIdentifica() + " " + total.substring(total.length() - 2) + "/100 M.N.";
            } else {
                montoLetra = montoLetra.toUpperCase() + " " + factura.getMoneda().getIdentifica() + " " + Math.round(t.floatValue()) + "/100 ";
            }

        } catch (Exception e) {
        }

        try {
            generarPdf(xmlFile, pdfFile, xlstFile, montoLetra, leyenda);
        } catch (FileNotFoundException e1) {

            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        InputStream stream = null;
        try {
            stream = new FileInputStream(pdfFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        file = new DefaultStreamedContent(stream, "application/pdf", factura.getFolio() + ".pdf");
        return file;
    }

    public StreamedContent descargarArchivoPDFPago(Factura factura) {

        //String path_home = System.getProperty("user.home")+"\\Downloads\\factura.xslt";
        StreamedContent file;

        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\factura" + factura.getUuid() + ".xml");
        File pdfFile = new File(temp + "\\factura" + factura.getUuid() + ".pdf");

        //File xlstFile = new File(path_home);
        File xlstFile = new File("/home/Wf/xslt/pagos.xslt");
        crearXML(factura.getXmlTimbrado(), xmlFile);

        String rfcEmisor = getRFCEmisor(xmlFile);
        if (rfcEmisor != null && !rfcEmisor.trim().equals("") && rfcEmisor.trim().equals("ISE180124TL4")) {
            xlstFile = new File("/home/Wf/xslt/infiniti2-pagos.xslt");
        }

        String pathLogo = getPath(xmlFile);
        try {
            String qr = getQrData3(xmlFile);
            utilFiles u = new utilFiles();
            u.generaPdf(xmlFile, pdfFile, xlstFile, qr, pathLogo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        InputStream stream = null;
        try {
            stream = new FileInputStream(pdfFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        file = new DefaultStreamedContent(stream, "application/pdf", factura.getFolio() + ".pdf");
        try {
            FileUtils.forceDelete(pdfFile);
        } catch (Exception e) {
            System.out.println("No fue posible eliminar PDF Pagos " + e.getMessage());
        }
        try {
            FileUtils.forceDelete(xmlFile);
        } catch (Exception e) {
            System.out.println("No fue posible eliminar XML Pagos " + e.getMessage());
        }
        return file;
    }

    public void generarPdf(File xml, File pdf, File xslt, String montoLetra, String Leyenda) throws FileNotFoundException, IOException {

        Driver driver = new Driver();
        Logger logger = new ConsoleLogger(ConsoleLogger.LEVEL_INFO);
        driver.setLogger(logger);
        MessageHandler.setScreenLogger(logger);
        driver.setRenderer(Driver.RENDER_PDF);
        OutputStream out = new java.io.FileOutputStream(pdf);
        String imagenQr = getQrData3(xml);
        String pathLogo = getPath(xml);
        if (Leyenda == null) {
            Leyenda = "        ";
        }
        System.out.println("XML " + xml);
        System.out.println("Qr " + imagenQr);
        System.out.println("Path " + pathLogo);
        System.out.println("XSLT" + xslt);

        try {
            driver.setOutputStream(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslt));
            transformer.setParameter("imgLogo1", pathLogo);
            transformer.setParameter("observacion", Leyenda);
            transformer.setParameter("montoLetra", montoLetra);
            transformer.setParameter("cadenaQr", imagenQr);
            Source src = new StreamSource(xml);
            Result res = new SAXResult(driver.getContentHandler());
            transformer.transform(src, res);
        } catch (Exception e) {
            System.out.println("ERROR GENERATE PDF  ");
            e.printStackTrace();
        } finally {
            out.close();
        }
    }

    public File crearArchivoXmlParaEnviarEmail(Factura factura) {
        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\" + factura.getFolio() + ".xml");
        crearXML(factura.getXmlTimbrado(), xmlFile);
        return xmlFile;
    }

    public File crearArchivoPdfParaEnviarEmail(Factura factura) {
        //String path_home = System.getProperty("user.home")+"\\Downloads\\factura.xslt";
        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\" + factura.getFolio() + ".xml");
        File pdfFile = new File(temp + "\\" + factura.getFolio() + ".pdf");
        //File xsltFile= new File(path_home);
        File xsltFile = new File("/home/Wf/xslt/factura.xslt");
        String leyenda = "";
        if (factura.getObservaciones() != null && !factura.getObservaciones().equals("")) {
            leyenda = factura.getObservaciones();
        }

        String rfcEmisor = getRFCEmisor(xmlFile);

        if (rfcEmisor != null && !rfcEmisor.trim().equals("") && rfcEmisor.trim().equals("ISE180124TL4")) {
            xsltFile = new File("/home/Wf/xslt/infiniti2.xslt");
        }

        String montoLetra = "";
        try {
            montoLetra = CnvNumsLets.getInstance().toLetras(factura.getTotal().longValue());
            int ttt = factura.getTotal().intValue();
            float tt = factura.getTotal().floatValue();
            Float t = Float.valueOf((tt - ttt) * 100.0F);
            if ((factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("PESOS")) || (factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("MXN")) || (factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("MXN"))) {
//                montoLetra = montoLetra.toUpperCase() + " " + factura.getMoneda().getIdentifica() + " " + Math.round(t.floatValue()) + "/100 M.N.";
                montoLetra = montoLetra.toUpperCase() + " " + "PESOS" + " " + Math.round(t.floatValue()) + "/100 M.N.";
            } else {
                montoLetra = montoLetra.toUpperCase() + " " + factura.getMoneda().getIdentifica() + " " + Math.round(t.floatValue()) + "/100 ";
            }

        } catch (Exception e) {
        }

        crearXML(factura.getXmlTimbrado(), xmlFile);
        try {
            generarPdf(xmlFile, pdfFile, xsltFile, montoLetra, leyenda);
        } catch (FileNotFoundException e) {
            // 
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //crearPDF();
        return pdfFile;
    }

    public File crearArchivoPdfPagoParaEnviarEmail(Factura factura) {

        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\factura.xml");
        File pdfFile = new File(temp + "\\factura.pdf");
        String pathLogo = getPath(xmlFile);
        //File xlstFile = new File(path_home);
        File xlstFile = new File("/home/Wf/xslt/pagos.xslt");
        crearXML(factura.getXmlTimbrado(), xmlFile);

        String rfcEmisor = getRFCEmisor(xmlFile);
        if (rfcEmisor != null && !rfcEmisor.trim().equals("") && rfcEmisor.trim().equals("ISE180124TL4")) {
            xlstFile = new File("/home/Wf/xslt/infiniti2-pagos.xslt");
        }

        try {
            String qr = getQrData3(xmlFile);
            utilFiles u = new utilFiles();
            u.generaPdf(xmlFile, pdfFile, xlstFile, qr, pathLogo);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return pdfFile;
    }

    public static String getQrData3(File xmlFile) {
        System.out.println("Entro a generar QR");

        String imagenQR = "";
        try {
            org.w3c.dom.Document xmlDocument = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(xmlFile);
            byte[] bytesCodigoQR = null;

            StringBuffer testoACodificarEnQR = new StringBuffer("");
            testoACodificarEnQR.append("https://verificacfdi.facturaelectronica.sat.gob.mx/default.aspx?");
            if (xmlDocument != null) {
                String uuid = "";
                if (xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Complemento").item(0) != null) {
                    if ((xmlDocument.getDocumentElement().getElementsByTagName("tfd:TimbreFiscalDigital").item(0).getAttributes().getNamedItem("UUID") != null) && (!xmlDocument.getDocumentElement().getElementsByTagName("tfd:TimbreFiscalDigital").item(0).getAttributes().getNamedItem("UUID").getTextContent().equals(""))) {
                        uuid = xmlDocument.getDocumentElement().getElementsByTagName("tfd:TimbreFiscalDigital").item(0).getAttributes().getNamedItem("UUID").getTextContent();
                    } else {
                        uuid = "SIN_UUID_TIMBREFISCALDIGITAL";
                    }
                }
                testoACodificarEnQR.append("&id=");
                testoACodificarEnQR.append(uuid);
                testoACodificarEnQR.append("&re=");

                String rfcEmisor = "";
                if ((xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc") != null) && (!xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc").getTextContent().equals(""))) {
                    rfcEmisor = xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc").getTextContent();
                } else {
                    rfcEmisor = "SIN_RFC_EMISOR";
                }
                testoACodificarEnQR.append(rfcEmisor);
                testoACodificarEnQR.append("&rr=");
                String rfcReceptor = "";
                if ((xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Receptor").item(0).getAttributes().getNamedItem("Rfc") != null) && (!xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Receptor").item(0).getAttributes().getNamedItem("Rfc").getTextContent().equals(""))) {
                    rfcReceptor = xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Receptor").item(0).getAttributes().getNamedItem("Rfc").getTextContent();
                } else {
                    rfcReceptor = "SIN_RFC_RECEPTOR";
                }
                testoACodificarEnQR.append(rfcReceptor);
                testoACodificarEnQR.append("&tt=");
                String total = "";
                if ((xmlDocument.getDocumentElement().getAttribute("Total") != null) && (!xmlDocument.getDocumentElement().getAttribute("Total").equals(""))) {
                    total = xmlDocument.getDocumentElement().getAttribute("Total");
                } else {
                    total = "0.00";
                }

                String sello = "";

                if ((xmlDocument.getDocumentElement().getAttribute("Sello") != null) && (!xmlDocument.getDocumentElement().getAttribute("Sello").toString().equals(""))) /*  90 */ {

                    sello = xmlDocument.getDocumentElement().getAttribute("Sello").toString();

                } else {

                    sello = "SIN_SELLO";

                }

                DecimalFormat decimalFormat = new DecimalFormat("0.00");
                Double totalDecimal = Double.parseDouble(total);
                String totalFormato = decimalFormat.format(totalDecimal);
                testoACodificarEnQR.append(totalFormato);

                testoACodificarEnQR.append("&fe=");
                testoACodificarEnQR.append(sello.substring(sello.length() - 8));

                System.out.println("Qr " + testoACodificarEnQR.toString());
                try {
                    bytesCodigoQR = ProcesadorQR.codificaTextoACodigoQR(testoACodificarEnQR.toString());
                    String RepositorioDocumentos = "/home/Wf/codigoQr/";
                    String rutaQr = RepositorioDocumentos + uuid + ".jpeg";
                    OutputStream outCode = new FileOutputStream(rutaQr);
                    imagenQR = rutaQr;
                    outCode.write(bytesCodigoQR);
                    outCode.close();
                    System.out.println("ruta imagen QR ya creado "+ imagenQR);
                } catch (ChecksumException ex) {
                    System.out.println("2 " + ex);
                    ex.printStackTrace();
                } catch (FormatException ex) {
                    System.out.println("3 " + ex);
                    ex.printStackTrace();
                } catch (IOException ex) {
                    System.out.println("4 " + ex);
                    ex.printStackTrace();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        } catch (Exception e) {
            System.out.println("Error al generar cadena QR " + e.getMessage());
            e.printStackTrace();
        }
        return imagenQR;
    }

    public static String getPath(File xmlFile) {
        System.out.println("Entro a obtener el path del Logo");
        String pathLogo = "/home/Wf/logos/";
        try {
            org.w3c.dom.Document xmlDocument = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(xmlFile);

            if (xmlDocument != null) {

                String rfcEmisor = "";
                if ((xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc") != null) && (!xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc").getTextContent().equals(""))) {
                    rfcEmisor = xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc").getTextContent();
                } else {
                    rfcEmisor = "SIN_RFC_EMISOR";
                }

                pathLogo = pathLogo + rfcEmisor + ".jpg";

            }
        } catch (Exception e) {
            System.out.println("Error al generar path " + e.getMessage());
            e.printStackTrace();
        }
        return pathLogo;
    }

    public static String getTotal(File xmlFile) {
        System.out.println("Entro a obtener el path del Logo");
        String total = "";
        try {
            org.w3c.dom.Document xmlDocument = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(xmlFile);

            if (xmlDocument != null) {

                if ((xmlDocument.getDocumentElement().getAttribute("Total") != null) && (!xmlDocument.getDocumentElement().getAttribute("Total").equals(""))) {
                    total = xmlDocument.getDocumentElement().getAttribute("Total");
                } else {
                    total = "0.00";
                }

            }
        } catch (Exception e) {
            System.out.println("Error al generar path " + e.getMessage());
            e.printStackTrace();
        }
        return total;
    }

    public static String getFolio(File xmlFile) {
        System.out.println("Entro a obtener el path del Logo");
        String folio = "";
        try {
            org.w3c.dom.Document xmlDocument = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(xmlFile);

            if (xmlDocument != null) {

                if ((xmlDocument.getDocumentElement().getAttribute("Folio") != null) && (!xmlDocument.getDocumentElement().getAttribute("Folio").equals(""))) {
                    folio = xmlDocument.getDocumentElement().getAttribute("Folio");
                } else {
                    folio = "";
                }

            }
        } catch (Exception e) {
            System.out.println("Error al generar path " + e.getMessage());
            e.printStackTrace();
        }
        return folio;
    }

    public static String getRFCEmisor(File xmlFile) {
        System.out.println("Entro a obtener el path del Logo");
        String Rfc = "";
        try {
            org.w3c.dom.Document xmlDocument = null;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            xmlDocument = builder.parse(xmlFile);

            if (xmlDocument != null) {

                if ((xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc") != null) && (!xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc").getTextContent().equals(""))) {
                    Rfc = xmlDocument.getDocumentElement().getElementsByTagName("cfdi:Emisor").item(0).getAttributes().getNamedItem("Rfc").getTextContent();
                }

            }
        } catch (Exception e) {
            System.out.println("Error al generar path " + e.getMessage());
            e.printStackTrace();
        }
        return Rfc;
    }

    public static int isPrimary(ArrayList<ImpuestoTrasladadoHijoTotal> Lista, String llave) {
        int loContiene = -1;
        System.out.println("PrimaryKey " + llave);

        try {
            for (int i = 0; i < Lista.size(); i++) {
                System.out.println("Llave primaria en i  " + i + "   " + Lista.get(i).getLlavePrimaria());
                if (Lista.get(i).getLlavePrimaria().equals(llave)) {
                    return i;
                }
            }
        } catch (Exception e) {
        }

        return loContiene;
    }
    
    
    public StreamedContent descargarPDFVP(Factura factura, String xmlS,String rfc, String obs) {

        //String path_home = System.getProperty("user.home")+"\\Downloads\\factura.xslt";
        StreamedContent file;

        String temp = System.getProperty("java.io.tmpdir");
        File xmlFile = new File(temp + "\\facturaTem"+rfc+".xml");
        File pdfFile = new File(temp + "\\facturaTem"+rfc+".pdf");
        System.out.println("archivos pdf xml "+xmlFile+"\n"+pdfFile);
        String pathLogo = "/home/Wf/logos/";
        //File xlstFile = new File(path_home);
        File xlstFile = new File("/home/Wf/xslt/factura.xslt");

        String leyenda = "";
        if (obs != null && !obs.equals("")) {
            leyenda = obs;
            System.out.println("Leyenda en la factura " + leyenda);
        }
        //String rfcEmisor = getRFCEmisor(xmlFile);

        if (rfc != null && !rfc.trim().equals("") && rfc.trim().equals("ISE180124TL4")) {
            xlstFile = new File("/home/Wf/xslt/infiniti2.xslt");
        }

        crearXML(xmlS, xmlFile);

        String montoLetra = "";
        String total = getTotal(xmlFile);
        BigDecimal numero = new BigDecimal(total).setScale(2);

        System.out.println("Imprimiendo el total " + numero.toString());
        try {
            montoLetra = CnvNumsLets.getInstance().toLetras(numero.longValue());
            int ttt = numero.intValue();
            float tt = numero.floatValue();
            Float t = Float.valueOf((tt - ttt) * 100.0F);
            if ((factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("PESOS")) || (factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("MXN")) || (factura.getMoneda().getIdentifica().toUpperCase().equalsIgnoreCase("MXN"))) {
                montoLetra = montoLetra.toUpperCase() + " " + factura.getMoneda().getIdentifica() + " " + total.substring(total.length() - 2) + "/100 M.N.";
            } else {
                montoLetra = montoLetra.toUpperCase() + " " + factura.getMoneda().getIdentifica() + " " + Math.round(t.floatValue()) + "/100 ";
            }

        } catch (Exception e) {
        }

        try {
            generarPdf(xmlFile, pdfFile, xlstFile, montoLetra, leyenda);
        } catch (FileNotFoundException e1) {

            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        InputStream stream = null;
        try {
            stream = new FileInputStream(pdfFile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        file = new DefaultStreamedContent(stream, "application/pdf", factura.getFolio() + ".pdf");
        return file;
    }
    
}
