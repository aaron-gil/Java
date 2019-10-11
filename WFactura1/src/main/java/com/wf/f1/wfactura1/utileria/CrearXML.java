/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.utileria;

import com.wf.f1.wfactura1.beanext.ComplementoSEP;
import com.wf.f1.wfactura1.beanext.Conceptos;
import com.wf.f1.wfactura1.beanext.ImpuestosLocalesC;
import com.wf.f1.wfactura1.beanext.impRetenidos;
import com.wf.f1.wfactura1.beanext.impTrasladados;
import com.wf.f1.wfactura1.converterbeans.UsoCfdiBean;
import com.wf.f1.wfactura1.entities.Certs;
import com.wf.f1.wfactura1.entities.HibernateUtil;
import com.wf.f1.wfactura1.model.Cliente;
import com.wf.f1.wfactura1.model.Factura;
import com.wf.f1.wfactura1.model.Serie;
import com.wf.f1.wfactura1.model.Usuario;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.hibernate.Query;
import org.hibernate.Session;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CrearXML {

    @SuppressWarnings({"unchecked", "rawtypes"})
    public String crear(Factura factura, Serie serie, Usuario usuario, List<String> uuidRelacionados, Cliente cliente, UsoCfdiBean usoCfdiBean, String numRegIdTrib, List<Conceptos> conceptosL,
            ComplementoSEP sep, List<ImpuestosLocalesC> ilT, List<ImpuestosLocalesC> ilR) {
        String xml = null;
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Session sess = HibernateUtil.getSessionFactory().openSession();
            String noCertificado = "";
            String tipoImpuesto = "";
            try {
                System.out.println("Buscar  noCertificado de  " + usuario.getNombre().trim());
                Query QueryCert = sess.createQuery("from Certs as C WHERE C.rfc = :RFC and C.estatusCer = 'A' order by C.fin desc").setString("RFC", usuario.getNombre().trim());
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
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("cfdi:Comprobante");
            //Integer folio = Integer.parseInt(factura.getFolio());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd'T'hh:mm:ss");
            doc.appendChild(rootElement);
            rootElement.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
            rootElement.setAttribute("xmlns:cfdi", "http://www.sat.gob.mx/cfd/3");
            rootElement.setAttribute("Version", "3.3");
            rootElement.setAttribute("Serie", serie.getNombre());
            rootElement.setAttribute("Folio", factura.getFolio());
            rootElement.setAttribute("Fecha", formatter.format(factura.getFechaCreacion().toString()));
            rootElement.setAttribute("Sello", "");
            rootElement.setAttribute("FormaPago", factura.getFormaPago());
            rootElement.setAttribute("NoCertificado", noCertificado);
            rootElement.setAttribute("Certificado", "");
            rootElement.setAttribute("CondicionesDePago", factura.getCondicionPago());
            rootElement.setAttribute("SubTotal", factura.getSubtotal().toString());
            if (factura.getDescuento().doubleValue() > 0) {
                rootElement.setAttribute("Descuento", factura.getDescuento().toString());
            }
            rootElement.setAttribute("Moneda", factura.getMoneda().getIdentifica());
            if (factura.getTipoCambio().doubleValue() > 0 && !factura.getMoneda().getIdentifica().equals("MXN")) {
                rootElement.setAttribute("TipoCambio", factura.getTipoCambio().toString());
            }
            rootElement.setAttribute("Total", factura.getTotal().toString());
            rootElement.setAttribute("TipoDeComprobante", factura.getTipoComprobante());
            rootElement.setAttribute("MetodoPago", factura.getMetodoPago().getIdentifica());
            rootElement.setAttribute("LugarExpedicion", factura.getLugarExpedicion());
            if (!factura.getConfirmacion().equals("")) {
                rootElement.setAttribute("Confirmacion", factura.getConfirmacion());
            }

            if (factura.isRelacionado()) {
                Element Urelacionados = doc.createElement("cfdi:CfdiRelacionados");
                Urelacionados.setAttribute("TipoRelacion", factura.getTipoRelacion().getIdentifica());
                rootElement.appendChild(Urelacionados);
                for (String ruuid : uuidRelacionados) {
                    Element Urelacionado = doc.createElement("cfdi:CfdiRelacionado");
                    Urelacionado.setAttribute("UUID", ruuid);
                    Urelacionados.appendChild(Urelacionado);
                }
            }

            Element emisor = doc.createElement("cfdi:Emisor");
            rootElement.appendChild(emisor);
            emisor.setAttribute("Nombre", usuario.getNombreCompleto());
            emisor.setAttribute("Rfc", usuario.getNombre());
            emisor.setAttribute("RegimenFiscal", usuario.getRegimenFiscal());

            Element receptor = doc.createElement("cfdi:Receptor");
            rootElement.appendChild(receptor);
            receptor.setAttribute("Nombre", cliente.getRazonSocial());
            receptor.setAttribute("Rfc", cliente.getRfc());
            receptor.setAttribute("UsoCFDI", usoCfdiBean.getIdentifica());
            if (!numRegIdTrib.equals("")) {
                receptor.setAttribute("NumRegIdTrib", numRegIdTrib);
            }

            Element conceptos = doc.createElement("cfdi:Conceptos");
            rootElement.appendChild(conceptos);
            Double totalIT = 0.0;
            Double totalIR = 0.0;
            Double descuentoT = 0.0;
            Boolean imps = false;
            for (Conceptos c : conceptosL) {
                Element concepto = doc.createElement("cfdi:Concepto");
                conceptos.appendChild(concepto);
                concepto.setAttribute("Cantidad", c.getCantidad().toString());
                concepto.setAttribute("ClaveProdServ", c.getProducto().getCodigoSat());//base de datos tabla catprod columna chargeCode
                concepto.setAttribute("ClaveUnidad", c.getProducto().getMedida().getIdentifica());
                concepto.setAttribute("Descripcion", c.getProducto().getDescripcion());
                if (c.getDescuento() != null && c.getDescuento().doubleValue() > 0) {
                    concepto.setAttribute("Descuento", c.getDescuento().toString());
                }
                concepto.setAttribute("Importe", c.getImporte().toString());
                concepto.setAttribute("ValorUnitario", c.getProducto().getValorUnitario().toString());

                if (sep != null) {
                    Element sepR = doc.createElement("cfdi:ComplementoConcepto");
                    concepto.appendChild(sepR);
                    Element inEduc = doc.createElement("iedu:instEducativas");
                    inEduc.setAttribute("version", "1.0");
                    inEduc.setAttribute("CURP", sep.getCurp());
                    inEduc.setAttribute("autRVOE", sep.getAutoRVOE());
                    inEduc.setAttribute("nivelEducativo", sep.getNivelEducativo());
                    inEduc.setAttribute("nombreAlumno", sep.getAlumno());
                    if (!sep.getRfc().equals("")) {
                        inEduc.setAttribute("rfcPago", sep.getRfc());
                    }
                    sepR.appendChild(inEduc);
                }
                if ((c.getTrasladados() != null && c.getTrasladados().size() > 0) || (c.getRetenidos() != null && c.getRetenidos().size() > 0)) {
                    imps = true;
                    Element impuestoC = doc.createElement("cfdi:Impuestos");
                    concepto.appendChild(impuestoC);
                    if (c.getTrasladados() != null && c.getTrasladados().size() > 0) {
                        Element trasladosI = doc.createElement("cfdi:Traslados");
                        impuestoC.appendChild(trasladosI);
                        for (impTrasladados it : c.getTrasladados()) {
                            Element trasladoT = doc.createElement("cfdi:Traslado");
                            trasladosI.appendChild(trasladoT);
                            trasladoT.setAttribute("Base", it.getBase().toString());
                            trasladoT.setAttribute("Importe", it.getImporte().toString());
                            totalIT += it.getImporte().doubleValue();
                            if (it.getIva().equals("isr")) {
                                trasladoT.setAttribute("Impuesto", "001");
                            } else if (it.getIva().equals("iva")) {
                                trasladoT.setAttribute("Impuesto", "002");
                            } else if (it.getIva().equals("ieps")) {
                                trasladoT.setAttribute("Impuesto", "003");
                            }
                            trasladoT.setAttribute("TasaOCuota", it.getTasaOCuota().toString());
                            trasladoT.setAttribute("TipoFactor", it.getTipoFactor());
                        }
                    }
                    if (c.getRetenidos() != null && c.getRetenidos().size() > 0) {
                        Element RetenidosI = doc.createElement("cfdi:Retenciones");
                        impuestoC.appendChild(RetenidosI);
                        for (impRetenidos it : c.getRetenidos()) {
                            Element RetenidosT = doc.createElement("cfdi:Retencion");
                            RetenidosI.appendChild(RetenidosT);
                            RetenidosT.setAttribute("Base", it.getBase().toString());
                            RetenidosT.setAttribute("Importe", it.getImporte().toString());
                            totalIR += it.getImporte().doubleValue();
                            if (it.getIva().equals("isr")) {
                                RetenidosT.setAttribute("Impuesto", "001");
                            } else if (it.getIva().equals("iva")) {
                                RetenidosT.setAttribute("Impuesto", "002");
                            } else if (it.getIva().equals("ieps")) {
                                RetenidosT.setAttribute("Impuesto", "003");
                            }
                            RetenidosT.setAttribute("TasaOCuota", it.getTasaOCuota().toString());
                            RetenidosT.setAttribute("TipoFactor", it.getTipoFactor());
                        }
                    }
                }
            }

            if (imps) {
                Element impuestos = doc.createElement("cfdi:Impuestos");
                rootElement.appendChild(impuestos);
                if (totalIT > 0) {
                    impuestos.setAttribute("TotalImpuestosTrasladados", totalIT.toString());
                    Element imTraslado = doc.createElement("cfdi:Traslados");
                    impuestos.appendChild(imTraslado);
                    for (Conceptos c : conceptosL) {
                        if (c.getTrasladados() != null && c.getTrasladados().size() > 0) {
                            for (impTrasladados it : c.getTrasladados()) {
                                Element traslado = doc.createElement("cfdi:Traslado");
                                imTraslado.appendChild(traslado);
                                traslado.setAttribute("Importe", it.getImporte().toString());
                                if (it.getIva().equals("isr")) {
                                    traslado.setAttribute("Impuesto", "001");
                                } else if (it.getIva().equals("iva")) {
                                    traslado.setAttribute("Impuesto", "002");
                                } else if (it.getIva().equals("ieps")) {
                                    traslado.setAttribute("Impuesto", "003");
                                }
                                traslado.setAttribute("TipoFactor", it.getTipoFactor());
                                traslado.setAttribute("TasaOCuota", it.getTasaOCuota().toString());
                            }
                        }
                    }

                }

                if (totalIR > 0) {
                    impuestos.setAttribute("TotalImpuestosRetenidos", totalIR.toString());
                    Element imRetenciones = doc.createElement("cfdi:Retenciones");
                    impuestos.appendChild(imRetenciones);
                    for (Conceptos c : conceptosL) {
                        if (c.getRetenidos() != null && c.getRetenidos().size() > 0) {
                            for (impRetenidos ir : c.getRetenidos()) {
                                Element traslado = doc.createElement("cfdi:Traslado");
                                imRetenciones.appendChild(traslado);
                                traslado.setAttribute("Importe", ir.getImporte().toString());
                                if (ir.getIva().equals("isr")) {
                                    traslado.setAttribute("Impuesto", "001");
                                } else if (ir.getIva().equals("iva")) {
                                    traslado.setAttribute("Impuesto", "002");
                                } else if (ir.getIva().equals("ieps")) {
                                    traslado.setAttribute("Impuesto", "003");
                                }
                            }
                        }
                    }

                }
            }

            if (sep != null) {
                rootElement.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/iedu http://www.sat.gob.mx/sitio_internet/cfd/iedu/iedu.xsd");
            } else if ((ilT != null && ilT.size() > 0  ) || (ilR != null && ilR.size() > 0)) {
                rootElement.setAttribute("xmlns:implocal", "http://www.sat.gob.mx/implocal");
                rootElement.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd http://www.sat.gob.mx/implocal http://www.sat.gob.mx/sitio_internet/cfd/implocal/implocal.xsd");
                Element ComplementoRoot = doc.createElement("cfdi:Complemento");
                rootElement.appendChild(ComplementoRoot);
                Element ImpuestosLocales = doc.createElement("implocal:ImpuestosLocales");
                ImpuestosLocales.setAttribute("version", "1.0");
                ComplementoRoot.appendChild(ImpuestosLocales);
                Double tt = 0.0;
                Double tr = 0.0;
                if (ilR != null && ilR.size() > 0) {
                    for (ImpuestosLocalesC t : ilT) {
                        Element imRet = doc.createElement("implocal:RetencionesLocales");
                        imRet.setAttribute("ImpLocRetenido", t.getDescripcion());
                        imRet.setAttribute("TasadeRetencion", String.valueOf(t.getPorcentaje() * .01));
                        imRet.setAttribute("Importe", t.getImporte().toString());
                        ImpuestosLocales.appendChild(imRet);
                        tr += t.getImporte().doubleValue();
                    }
                    ImpuestosLocales.setAttribute("TotaldeRetenciones", String.valueOf(tr));
                }
                if (ilT != null && ilT.size() > 0) {
                    for (ImpuestosLocalesC r : ilR) {
                        Element imTra = doc.createElement("implocal:TrasladosLocales");
                        imTra.setAttribute("ImpLocTrasladado", r.getDescripcion());
                        imTra.setAttribute("TasadeTraslado", String.valueOf(r.getPorcentaje() * .01));
                        imTra.setAttribute("Importe", String.valueOf(r.getImporte()));
                        ImpuestosLocales.appendChild(imTra);
                        tt += r.getImporte().doubleValue();
                    }
                    ImpuestosLocales.setAttribute("TotaldeTraslados", String.valueOf(tt));
                }
            } else {
                rootElement.setAttribute("xsi:schemaLocation", "http://www.sat.gob.mx/cfd/3 http://www.sat.gob.mx/sitio_internet/cfd/3/cfdv33.xsd");
            }

            try {
                DOMSource domSource = new DOMSource(doc);
                Transformer transformer = TransformerFactory.newInstance().newTransformer();
                StringWriter sw = new StringWriter();
                StreamResult sr = new StreamResult(sw);
                transformer.transform(domSource, sr);
                xml = sw.toString();

            } catch (Exception e) {
                xml = null;
                e.printStackTrace();
            }

        } catch (Exception e) {
            xml = null;
            e.printStackTrace();
        }

        return xml;
    }
}
