/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.utileria;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;
import org.apache.fop.apps.Driver;

public class utilFiles {

    public void generaXML(String content, File xml) {
        try {
            FileOutputStream archivoXml = new FileOutputStream(xml);
            OutputStreamWriter fileWriter = new OutputStreamWriter(archivoXml, "UTF-8");
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Error al crear XML " + ex.getMessage());
        }
    }

    public void generaPdf(File xml, File pdf, File xslt, String qr, String logo) throws FileNotFoundException, IOException {
        System.out.println("Genera PDF Pagos!!");
        Driver driver = new Driver();
        driver.setRenderer(Driver.RENDER_PDF);
        OutputStream out = new java.io.FileOutputStream(pdf);

        try {
            System.out.println("entro a generaPDF");
            driver.setOutputStream(out);
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslt));
            transformer.setParameter("img", qr);
            transformer.setParameter("imgLogo", logo);
            Source src = new StreamSource(xml);
            Result res = new SAXResult(driver.getContentHandler());
            transformer.transform(src, res);
            System.out.println("Termina PDF");
        } catch (Exception e) {
            System.out.println("ERROR GENERATE PDF  ");
            e.printStackTrace();
        } finally {
            System.out.println("Sale PDF");
            out.close();
        }
    }

    public String getCambio() throws IOException {
        URLConnection uc = null;
        try {
            //uc = new URL("http://pac.wfactura.com:8080/descargasCFDI_Produccion/getCambio?").openConnection();
            uc = new URL("http://10.10.10.103/descargasCFDI_Produccion/getCambio?").openConnection();
        } catch (Exception ex) {
            System.out.println("No se pudo obtener una conexion al server");
        }
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setAllowUserInteraction(false);
        DataOutputStream dos = new DataOutputStream(uc.getOutputStream());
        dos.writeBytes("fecha=today");
        dos.close();

        DataInputStream dis = null;
        String resp = "";
        try {
            dis = new DataInputStream(uc.getInputStream());
            String nextline;
            while ((nextline = dis.readLine()) != null) {
                String value = new String(nextline.getBytes("UTF-8"));
                System.out.println(value);
                resp += nextline;
            }
        } catch (java.net.SocketTimeoutException ext) {
            System.out.println("No se recibio respuesta");
            resp = "Not Found";
        }
        dis.close();
        System.out.println("Cambio Obtenido " + resp);
        return resp;
    }

    public static void main(String[] args) throws IOException {
        utilFiles u = new utilFiles();
        File xmlFile = new File("C:\\home\\Wf\\xslt\\pago.xml");
        File pdfFile = new File("C:\\home\\Wf\\xslt\\test.pdf");
        File xsltFile = new File("C:\\home\\Wf\\xslt\\pagos.xslt");
        String logo = "C:\\Users\\David\\Downloads\\logo.jpg";
        u.generaPdf(xmlFile, pdfFile, xsltFile, "test", logo);
    }
}

