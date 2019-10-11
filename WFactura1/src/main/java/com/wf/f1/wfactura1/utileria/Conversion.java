/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.utileria;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Conversion {
	
	/**
	 * Metodo para converir una fecha de tipo string a una fecha de tipo date
	 * @param fecha a convertir 
	 * @return la fecha convertida en date
	 */
	public Date convertirStringADate(String fecha){
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd-MM-yyyy");
           Date fechaResultado = null;
           try {

           fechaResultado = formatoDelTexto.parse(fecha);

           } catch (ParseException ex) {

           ex.printStackTrace();

           }
           return fechaResultado;
    }
	
	/**
     * Metodo apra convertir un numero a moneda en pesos
     * @param valor numero a convertir
     * @return el numero convertido en moneda
     */
    public String convertirNumeroAformatoMoneda(String valor){
        NumberFormat formatear = NumberFormat.getCurrencyInstance(new Locale("es","MX"));
        return formatear.format(Double.parseDouble(valor));
    }
	
    /**
     * Metodo para formatear una fecha
     * @param fecha fecha a formatear
     * @return fecha con un formato especifico
     */
    public String formatearFecha(Date fecha){
    	SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:dd");
        String fechaMail = formateador.format(fecha);
        return fechaMail;
    }
    
    public String convertirBigDecimalAformatoMoneda(Double numero){
    	NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "MX"));
    	return formatter.format(numero);
    }
    
    public Calendar fechaSegunSat(Date date){ 
		  Calendar cal = Calendar.getInstance();
		  cal.setTime(date);
		  cal.clear(Calendar.ZONE_OFFSET);
	        cal.clear(Calendar.MILLISECOND);
		  return cal;
		}
    
}
