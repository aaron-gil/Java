/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.utileria;

import com.wf.f1.wfactura1.model.Usuario;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.logging.Level;
import java.util.logging.Logger;

public class EnvioEmail {

    /**
     * Metodo para enviar la factura en un correo
     *
     * @param correoReceptor direccion del correo
     * @param nitRecep nit del recpetor
     * @param nitEmi nit del emisor
     * @param nombreR nombre del receptor
     * @param folio numero de la factura que acompaña el prefijo
     * @param nombreE nombre del emisor
     * @param tipo tipo del receptor
     * @param fecha fecha de la factura
     * @param total total de la factura
     * @param resolucion numero de la resolucion del comprobante
     * @param prefijo prefijo de la factura
     * @throws UnsupportedEncodingException
     */
    public void sendMailFactura(String correoReceptor, String nitRecep, String nitEmi, String nombreR, String folio, String nombreE, String tipo, String fecha, String total, String resolucion, String prefijo, File archivoXml, File archivoPdf) throws UnsupportedEncodingException {
        final String username = "factura1@wf.com.mx";
        final String password = "F4ctur41";

        StringBuffer servicio = new StringBuffer();
        servicio.append("<head>\n"
                + "        <meta content=\"text/html; charset=iso-8859-1\" http-equiv=\"Content-Type\">\n"
                + "        <style type=\"text/css\">\n"
                + "            .auto-style1 {\n"
                + "                color: #616D75;\n"
                + "            }\n"
                + "            .auto-style2 {\n"
                + "                color: #ED9402;\n"
                + "            }\n"
                + "            .auto-style3 {\n"
                + "                background-color: #FF6600;\n"
                + "            }\n"
                + "            .auto-style4 {\n"
                + "                font-family: Arial, Helvetica, sans-serif;\n"
                + "                font-size: 10px;\n"
                + "                color: #999999;\n"
                + "            }\n"
                + "        </style>\n"
                + "    </head>");
        servicio.append("<body>\n"
                + "        <center>\n"
                + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                <tbody>\n"
                + "                    <tr>\n"
                + "                        <td style=\"background-position: right top; background-repeat: no-repeat;\" valign=\"top\">\n"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td align=\"right\" height=\"1272\" class=\"auto-style3\">\n"
                + "                                            <table align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"138\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>\n"
                + "                                                            <img  height=\"1272\" src=\"https://1.bp.blogspot.com/-9gNwiycFNPg/WWzOReCHRKI/AAAAAAAAAAw/kFi6kHYjUVwy9WDwE5VK9clB8OYpU__WgCEwYBhgL/s1600/bannerIzquierdo.png\" style=\"display: block;\" width=\"138\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                        <td valign=\"top\" width=\"650\">\n"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td bgcolor=\"#FFFFFF\" width=\"650\">\n"
                + "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td width=\"39\">&nbsp;</td>\n"
                + "                                                        <td width=\"335\">\n"
                + "                                                            <img alt=\"Logo-1\" height=\"94\" src=\"https://4.bp.blogspot.com/-0aTCmnlwElk/WWzM7FUldDI/AAAAAAAAAAk/9zY2tqQVB64wTf_NEXC9zEMxZ3ItXMahgCLcBGAs/s1600/factura1.png\" width=\"240\"></td>\n"
                + "                                                        <td align=\"right\" height=\"129\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px;\" width=\"198\">\n"
                + "                                                           <br>  <a href=\"\" style=\"color: #666;\">\n"
                + "                                                                </a></td>\n"
                + "                                                        <td width=\"78\">&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td width=\"78\"></td>\n"
                + "                                                        <td width=\"494\"><img height=\"5\" src=\"img3.jpg\" width=\"494\"></td>\n"
                + "                                                        <td width=\"78\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td><img  height=\"1\" src=\"img4.gif\" width=\"78\"></td>\n"
                + "                                                        <td style=\"font-size: 33px; font-family: Arial, Helvetica, sans-serif; text-align: left;\" class=\"auto-style1\">\n"
                + "                                                            Recibiste una nueva <span class=\"auto-style2\">Factura</span></td>\n"
                + "                                                        <td><img  height=\"1\" src=\"img5.gif\" width=\"78\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"18\"><img  height=\"1\" src=\"img6.gif\" width=\"494\"></td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td><table><td height=\"25\" style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-weight: bold; font-size: 15px; text-align: left;\">\n"
                + "                                                            Estimado:</td>\n"
                + "                                                        <td>" + nombreR + "&nbsp;</td>\n" + "</table></td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                        <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + "                                                            Te han enviado una nueva Factura con los siguientes datos:</td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"22\"></td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"22\">\n"
                + "                                                            <center>\n"
                + "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"468\">\n"
                + "                                                                    <tbody>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td height=\"1\" style=\"text-align: left;\" width=\"468\" class=\"auto-style4\">\n"
                + "                                                                                Anuncios</td>\n"
                + "                                                                        </tr>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td height=\"22\"><a href=\"#\"><a id=\"ph793\"></a>\n"
                + "                                                                                    <a href=\"http://www.factura1.mx/\" target=\"_blank\">\n"
                + "                                                                                        <img src='http://www.factura1.mx/images/logo.png' border='0' height=\"70\" width=\"200\"/></a></a></td>\n"
                + "                                                                        </tr>\n"
                + "                                                                    </tbody>\n"
                + "                                                                </table>\n"
                + "                                                            </center>\n"
                + "                                                        </td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"22\"></td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                            <center>\n"
                + "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"526\">\n"
                + "                                                    <tbody>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <img alt=\"arriba\" height=\"42\" src=\"http://www.factura1.mx/correo_CFDI/img8.gif\" style=\"display: block;\" width=\"526\"></td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"526\">\n"
                + "                                                                    <tbody>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td background=\"img18.gif\" width=\"16\">\n"
                + "                                                                                <img alt=\"izquierda\" height=\"410\" src=\"http://www.factura1.mx/correo_CFDI/img9.gif\" style=\"display: block;\" width=\"16\"></td>\n"
                + "                                                                            <td valign=\"top\" width=\"494\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"432\">\n"
                + "                                                                                    <tbody>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Emisor</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + nombreE
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                A nombre de</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + nombreR
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Tipo de Comprobante</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + tipo
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Serie/Folio</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + prefijo + "/" + folio
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Fecha de emisión</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left; height: 23px;\">\n"
                + fecha
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Total</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + total
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + //"                                                                                        <tr>\n" +
                //"                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n" +
                //"                                                                                                Validación Fiscal</td>\n" +
                //"                                                                                        </tr>\n" +
                //"                                                                                        <tr>\n" +
                //"                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n" +
                //                                                                                                 validacion+
                //"                                                                                                &nbsp;</td>\n" +
                //"                                                                                        </tr>\n" +
                "                                                                                    </tbody>\n"
                + "                                                                                </table>\n"
                + "                                                                            </td>\n"
                + "                                                                            <td align=\"right\" background=\"img19.gif\" width=\"16\">\n"
                + "                                                                                <img alt=\"derecha\" height=\"410\" src=\"http://www.factura1.mx/correo_CFDI/img10.gif\" style=\"display: block;\" width=\"16\"></td>\n"
                + "                                                                        </tr>\n"
                + "                                                                    </tbody>\n"
                + "                                                                </table>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <img alt=\"abajo\" height=\"42\" src=\"http://www.factura1.mx/correo_CFDI/img11.gif\" style=\"display: block;\" width=\"526\"></td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>&nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td align=\"center\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px;\">\n"
                + "                                                                 <center> <a href=\"http://pac.wf.com.mx/Factura1/\" target=\"_blank\">pac.wf.com.mx/Factura1/</a></center><br/>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                //                + "                                                        <tr>\n"
                //                + "                                                            <td align=\"center\" height=\"48\" valign=\"bottom\">\n"
                //                + "                                                                <center>\n"
                //                + "                                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"430\">\n"
                //                + "                                                                        <tbody>\n"
                //                + "                                                                            <tr>\n"
                //                + "                                                                                <td align=\"left\" width=\"215\"><a href='http://192.168.1.204:8080/servicesCo/getFiles?tipo=xml&nitEmisor=" + nitEmi + "&nitReceptor=" + nitRecep + "&folio=" + folio + "&prefijo=" + prefijo + "&resolucion=" + resolucion + "' target=\"_blank\">\n"
                //                + "                                                                                        <img alt=\"Descarga tu XML\" border=\"0\" src=\"http://www.factura1.mx/correo_CFDI/boton1.jpg\" style=\"display: block;\" height=\"40\" width=\"200\"></a></td>\n"
                //                + "                                                                                <td align=\"right\" width=\"215\"><a href='http://192.168.1.204:8080/servicesCo/getFiles?tipo=pdf&nitEmisor=" + nitEmi + "&nitReceptor=" + nitRecep + "&folio=" + folio + "&prefijo=" + prefijo + "&resolucion=" + resolucion + "' target=\"_blank\">\n"
                //                + "                                                                                        <img alt=\"Descarga tu PDF\" border=\"0\" src=\"http://www.factura1.mx/correo_CFDI/boton2.jpg\" style=\"display: block;\" height=\"40\" width=\"200\"></a></td>\n"
                //                + "                                                                            </tr>\n"
                //                + "                                                                        </tbody>\n"
                //                + "                                                                    </table>\n"
                //                + "                                                                </center>\n"
                //                + "                                                            </td>\n"
                //                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td align=\"center\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td align=\"center\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>&nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <center>\n"
                + "                                                                    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"468\">\n"
                + "                                                                        <tbody>\n"
                + "                                                                            <tr>\n"
                + "                                                                                <td height=\"1\" style=\"color: #999; font-family: Arial, Helvetica, sans-serif; font-size: 10px; text-align: left;\" width=\"468\">\n"
                + "                                                                                    Anuncios</td>\n"
                + "                                                                            </tr>\n"
                + "                                                                            <tr>\n"
                + "                                                                                <td height=\"22\"><a href=\"#\"><a id=\"ph794\"></a>\n"
                + "                                                                                        <a href=\"http://www.factura1.mx/\" target=\"_blank\">\n"
                + "                                                                                            <img src='http://www.factura1.mx/images/logo.png' border='0' height=\"70\" width=\"200\"/></a></a></td>\n"
                + "                                                                            </tr>\n"
                + "                                                                        </tbody>\n"
                + "                                                                    </table>\n"
                + "                                                                </center>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px; line-height: 23px;\">\n"
                + "                                                                <center> Copyright 2015-2017 Factura 1 de C.V. Todos los derechos reservados </center>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                    </tbody>\n"
                + "                                                </table>\n"
                + "                                            </center>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                        <td valign=\"top\">\n"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td align=\"left\" height=\"1272\" class=\"auto-style3\">\n"
                + "                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"138\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>\n"
                + "                                                            <img alt=\"2\" height=\"1272\" src=\"https://1.bp.blogspot.com/-qVMTfVdOSz4/WWzORAP8dWI/AAAAAAAAAAs/qrCDSv5RNRsJ2--w8U-Snvfl8xyFvJh7wCEwYBhgL/s1600/bannerDerecho.png\" style=\"display: block;\" width=\"138\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </tbody>\n"
                + "            </table>\n"
                + "        </center>\n"
                + "\n"
                + "    </body>");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setSubject("Nueva Factura de " + nombreE + ", con  No. de Documento " + prefijo + folio, "UTF-8");
            message.setFrom(new InternetAddress("factura1@wf.com.mx", "Factura1"));
            System.out.println("CORREO DEL RECEPTOR " + correoReceptor.trim());
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(correoReceptor.trim()));

            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(servicio.toString(), "text/html; charset=UTF-8");

            BodyPart adjuntoXml = new MimeBodyPart();
            adjuntoXml.setDataHandler(
                    new DataHandler(new FileDataSource(archivoXml.getPath())));
            adjuntoXml.setFileName(archivoXml.getName());

            BodyPart adjuntoPdf = new MimeBodyPart();
            adjuntoPdf.setDataHandler(
                    new DataHandler(new FileDataSource(archivoPdf.getPath())));
            adjuntoPdf.setFileName(archivoPdf.getName());

            multipart.addBodyPart(messageBodyPart);
            multipart.addBodyPart(adjuntoXml);
            multipart.addBodyPart(adjuntoPdf);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendMailUsuario(Usuario usuario, Usuario usuarioElegido) throws UnsupportedEncodingException {
        final String username = "factura1@wf.com.mx";
        final String password = "F4ctur41";
        Conversion conversion = new Conversion();

        StringBuffer servicio = new StringBuffer();
        servicio.append("<head>\n"
                + "        <meta content=\"text/html; charset=iso-8859-1\" http-equiv=\"Content-Type\">\n"
                + "        <style type=\"text/css\">\n"
                + "            .auto-style1 {\n"
                + "                color: #616D75;\n"
                + "            }\n"
                + "            .auto-style2 {\n"
                + "                color: #ED9402;\n"
                + "            }\n"
                + "            .auto-style3 {\n"
                + "                background-color: #FF6600;\n"
                + "            }\n"
                + "            .auto-style4 {\n"
                + "                font-family: Arial, Helvetica, sans-serif;\n"
                + "                font-size: 10px;\n"
                + "                color: #999999;\n"
                + "            }\n"
                + "        </style>\n"
                + "    </head>");
        servicio.append("<body>\n"
                + "        <center>\n"
                + "            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                <tbody>\n"
                + "                    <tr>\n"
                + "                        <td style=\"background-position: right top; background-repeat: no-repeat;\" valign=\"top\">\n"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td align=\"right\" height=\"1272\" class=\"auto-style3\">\n"
                + "                                            <table align=\"right\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"138\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>\n"
                + "                                                            <img  height=\"1272\" src=\"https://1.bp.blogspot.com/-9gNwiycFNPg/WWzOReCHRKI/AAAAAAAAAAw/kFi6kHYjUVwy9WDwE5VK9clB8OYpU__WgCEwYBhgL/s1600/bannerIzquierdo.png\" style=\"display: block;\" width=\"138\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                        <td valign=\"top\" width=\"650\">\n"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td bgcolor=\"#FFFFFF\" width=\"650\">\n"
                + "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td width=\"39\">&nbsp;</td>\n"
                + "                                                        <td width=\"335\">\n"
                + "                                                            <img alt=\"Logo-1\" height=\"94\" src=\"https://4.bp.blogspot.com/-0aTCmnlwElk/WWzM7FUldDI/AAAAAAAAAAk/9zY2tqQVB64wTf_NEXC9zEMxZ3ItXMahgCLcBGAs/s1600/factura1.png\" width=\"240\"></td>\n"
                + "                                                        <td align=\"right\" height=\"129\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px;\" width=\"198\">\n"
                + "                                                            ¿No puedes ver este mensaje?<br> haz clic <a href=\"\" style=\"color: #666;\">\n"
                + "                                                                aquí</a></td>\n"
                + "                                                        <td width=\"78\">&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"650\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td width=\"78\"></td>\n"
                + "                                                        <td width=\"494\"><img height=\"5\" src=\"img3.jpg\" width=\"494\"></td>\n"
                + "                                                        <td width=\"78\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td><img  height=\"1\" src=\"img4.gif\" width=\"78\"></td>\n"
                + "                                                        <td style=\"font-size: 33px; font-family: Arial, Helvetica, sans-serif; text-align: left;\" class=\"auto-style1\">\n"
                + "                                                            Recibiste un nuevo <span class=\"auto-style2\">Usuario</span></td>\n"
                + "                                                        <td><img  height=\"1\" src=\"img5.gif\" width=\"78\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"18\"><img  height=\"1\" src=\"img6.gif\" width=\"494\"></td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td><table><td height=\"25\" style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-weight: bold; font-size: 15px; text-align: left;\">\n"
                + "                                                            Estimado:</td>\n"
                + "                                                        <td>" + usuario.getNombreCompleto() + "&nbsp;</td>\n" + "</table></td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                        <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + "                                                            Te han enviado un nuevo usuario con los siguientes datos:</td>\n"
                + "                                                        <td>&nbsp;</td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"22\"></td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"22\">\n"
                + "                                                            <center>\n"
                + "                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"468\">\n"
                + "                                                                    <tbody>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td height=\"1\" style=\"text-align: left;\" width=\"468\" class=\"auto-style4\">\n"
                + "                                                                                Anuncios</td>\n"
                + "                                                                        </tr>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td height=\"22\"><a href=\"#\"><a id=\"ph793\"></a>\n"
                + "                                                                                    <a href=\"http://www.factura1.mx/\" target=\"_blank\">\n"
                + "                                                                                        <img src='http://www.factura1.mx/images/logo.png' border='0' height=\"70\" width=\"200\"/></a></a></td>\n"
                + "                                                                        </tr>\n"
                + "                                                                    </tbody>\n"
                + "                                                                </table>\n"
                + "                                                            </center>\n"
                + "                                                        </td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                    <tr>\n"
                + "                                                        <td></td>\n"
                + "                                                        <td height=\"22\"></td>\n"
                + "                                                        <td></td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                            <center>\n"
                + "                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"526\">\n"
                + "                                                    <tbody>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <img alt=\"arriba\" height=\"42\" src=\"http://www.factura1.mx/images/logo.png\" style=\"display: block;\" width=\"526\"></td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"526\">\n"
                + "                                                                    <tbody>\n"
                + "                                                                        <tr>\n"
                + "                                                                            <td background=\"img18.gif\" width=\"16\">\n"
                + "                                                                                <img alt=\"izquierda\" height=\"410\" src=\"http://www.factura1.mx/images/logo.png\" style=\"display: block;\" width=\"16\"></td>\n"
                + "                                                                            <td valign=\"top\" width=\"494\">\n"
                + "                                                                                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"432\">\n"
                + "                                                                                    <tbody>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Emisor</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + usuarioElegido.getNombreCompleto()
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                A nombre de</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + usuario.getNombreCompleto()
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                RFC</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + usuario.getNombre()
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                               Password</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n"
                + usuario.getPassword()
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n"
                + "                                                                                                Fecha de alta</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left; height: 23px;\">\n"
                + conversion.formatearFecha(usuario.getFechaCreacion())
                + "                                                                                                &nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + "                                                                                        <tr>\n"
                + "                                                                                            <td>&nbsp;</td>\n"
                + "                                                                                        </tr>\n"
                + //"                                                                                        <tr>\n" +
                //"                                                                                            <td style=\"font-family: Arial, Helvetica, sans-serif; color: #ff6600; font-size: 15px; text-align: left;\">\n" +
                //"                                                                                                Validación Fiscal</td>\n" +
                //"                                                                                        </tr>\n" +
                //"                                                                                        <tr>\n" +
                //"                                                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px; text-align: left;\">\n" +
                //                                                                                                 validacion+
                //"                                                                                                &nbsp;</td>\n" +
                //"                                                                                        </tr>\n" +
                "                                                                                    </tbody>\n"
                + "                                                                                </table>\n"
                + "                                                                            </td>\n"
                + "                                                                            <td align=\"right\" background=\"img19.gif\" width=\"16\">\n"
                + "                                                                                <img alt=\"derecha\" height=\"410\" src=\"http://www.factura1.mx/correo_CFDI/img10.gif\" style=\"display: block;\" width=\"16\"></td>\n"
                + "                                                                        </tr>\n"
                + "                                                                    </tbody>\n"
                + "                                                                </table>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <img alt=\"abajo\" height=\"42\" src=\"http://www.factura1.mx/correo_CFDI/img11.gif\" style=\"display: block;\" width=\"526\"></td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>&nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td align=\"center\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px;\">\n"
                + "                                                                <center> Tienes acceso a nuestros servicios ingresando al siguiente portal: </center><br/><br/>\n"
                + "                                                                 <center> <a href=\"http://pac.wf.com.mx/Factura1/\" target=\"_blank\">pac.wf.com.mx/Factura1/</a></center><br/>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                //                + "                                                        <tr>\n"
                //                + "                                                            <td align=\"center\" height=\"48\" valign=\"bottom\">\n"
                //                + "                                                                <center>\n"
                //                + "                                                                    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"430\">\n"
                //                + "                                                                        <tbody>\n"
                //                + "                                                                            <tr>\n"
                //                + "                                                                                <td align=\"left\" width=\"215\"><a href='http://192.168.1.204:8080/servicesCo/getFiles?tipo=xml&nitEmisor=" + nitEmi + "&nitReceptor=" + nitRecep + "&folio=" + folio + "&prefijo=" + prefijo + "&resolucion=" + resolucion + "' target=\"_blank\">\n"
                //                + "                                                                                        <img alt=\"Descarga tu XML\" border=\"0\" src=\"http://www.factura1.mx/correo_CFDI/boton1.jpg\" style=\"display: block;\" height=\"40\" width=\"200\"></a></td>\n"
                //                + "                                                                                <td align=\"right\" width=\"215\"><a href='http://192.168.1.204:8080/servicesCo/getFiles?tipo=pdf&nitEmisor=" + nitEmi + "&nitReceptor=" + nitRecep + "&folio=" + folio + "&prefijo=" + prefijo + "&resolucion=" + resolucion + "' target=\"_blank\">\n"
                //                + "                                                                                        <img alt=\"Descarga tu PDF\" border=\"0\" src=\"http://www.factura1.mx/correo_CFDI/boton2.jpg\" style=\"display: block;\" height=\"40\" width=\"200\"></a></td>\n"
                //                + "                                                                            </tr>\n"
                //                + "                                                                        </tbody>\n"
                //                + "                                                                    </table>\n"
                //                + "                                                                </center>\n"
                //                + "                                                            </td>\n"
                //                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td align=\"center\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td align=\"center\" style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 15px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>&nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td>\n"
                + "                                                                <center>\n"
                + "                                                                    <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"468\">\n"
                + "                                                                        <tbody>\n"
                + "                                                                            <tr>\n"
                + "                                                                                <td height=\"1\" style=\"color: #999; font-family: Arial, Helvetica, sans-serif; font-size: 10px; text-align: left;\" width=\"468\">\n"
                + "                                                                                    Anuncios</td>\n"
                + "                                                                            </tr>\n"
                + "                                                                            <tr>\n"
                + "                                                                                <td height=\"22\"><a href=\"#\"><a id=\"ph794\"></a>\n"
                + "                                                                                        <a href=\"http://www.factura1.mx/\" target=\"_blank\">\n"
                + "                                                                                            <img src='http://www.factura1.mx/images/logo.png' border='0' height=\"70\" width=\"200\"/></a></a></td>\n"
                + "                                                                            </tr>\n"
                + "                                                                        </tbody>\n"
                + "                                                                    </table>\n"
                + "                                                                </center>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px; line-height: 23px;\">\n"
                + "                                                                <center> Copyright 2015-2017 Factura 1 de C.V. Todos los derechos reservados </center>\n"
                + "                                                            </td>\n"
                + "                                                        </tr>\n"
                + "                                                        <tr>\n"
                + "                                                            <td style=\"color: #666666; font-family: Arial, Helvetica, sans-serif; font-size: 10px; line-height: 23px;\">\n"
                + "                                                                &nbsp;</td>\n"
                + "                                                        </tr>\n"
                + "                                                    </tbody>\n"
                + "                                                </table>\n"
                + "                                            </center>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                        <td valign=\"top\">\n"
                + "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "                                <tbody>\n"
                + "                                    <tr>\n"
                + "                                        <td align=\"left\" height=\"1272\" class=\"auto-style3\">\n"
                + "                                            <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"138\">\n"
                + "                                                <tbody>\n"
                + "                                                    <tr>\n"
                + "                                                        <td>\n"
                + "                                                            <img alt=\"2\" height=\"1272\" src=\"https://1.bp.blogspot.com/-qVMTfVdOSz4/WWzORAP8dWI/AAAAAAAAAAs/qrCDSv5RNRsJ2--w8U-Snvfl8xyFvJh7wCEwYBhgL/s1600/bannerDerecho.png\" style=\"display: block;\" width=\"138\"></td>\n"
                + "                                                    </tr>\n"
                + "                                                </tbody>\n"
                + "                                            </table>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </tbody>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </tbody>\n"
                + "            </table>\n"
                + "        </center>\n"
                + "\n"
                + "    </body>");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        javax.mail.Session session = javax.mail.Session.getInstance(props,
                new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            MimeMessage message = new MimeMessage(session);
            message.setSubject("Nuevas Credenciales para el usuario " + usuario.getNombreCompleto(), "UTF-8");
            message.setFrom(new InternetAddress("factura1@wf.com.mx", "Factura1"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(usuario.getEmail().trim()));

            MimeMultipart multipart = new MimeMultipart("related");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(servicio.toString(), "text/html; charset=UTF-8");

            multipart.addBodyPart(messageBodyPart);

            message.setContent(multipart);

            Transport.send(message);

            System.out.println("Done");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        try {
            File archivoXml = new File("C:\\Users\\WF Consulting\\Documents\\xslt\\E63.xml");
            File archivoPdf = new File("C:\\Users\\WF Consulting\\Documents\\xslt\\E63.pdf");
            EnvioEmail env = new EnvioEmail();
            env.sendMailFactura("javila@catsa.mx", "nitRecep", "nitEmi", "Construcciones y Acabados Torna S.A. de C.V. ", "E63", "JUAN LUIS JIMENEZ GUZMAN", "I", "2019-10-01T11:58:44", "11500.72", "resolucion", "prefijo", archivoXml, archivoPdf);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(EnvioEmail.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
