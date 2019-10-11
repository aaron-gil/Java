/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wf.f1.wfactura1.utileria;

/**
 *
 * @author WF Consulting
 */
public class CnvNumsLets {

    private static CnvNumsLets instance = null;
    private String[] Unidad = {"Cero", "Un", "Dos", "Tres", "Cuatro", "Cinco", "Seis", "Siete", "Ocho", "Nueve", "Diez", "Once", "Doce", "Trece", "Catorce", "Quince", "Dieciseis", "Diecisiete", "Dieciocho", "Diecinueve", "Veinte"};
    private String[] Decena = {"Veinti", "Treinta", "Cuarenta", "Cincuenta", "Sesenta", "Setenta", "Ochenta", "Noventa"};
    private String[] Centena = {"Cien", "Doscientos", "Trescientos", "Cuatrocientos", "Quinientos", "Seiscientos", "Setecientos", "Ochocientos", "Novecientos", "Mil", "Un Millon", "Millones", "Un Billon", "Billones"};
    private long Valororiginal = 0L;

    public static CnvNumsLets getInstance() {
        if (instance == null) {
            instance = new CnvNumsLets();
        }
        return instance;
    }

    private String getUnidad(long Numero) {
        String aux = "";
        for (int p = 0; p <= 20; p++) {
            if (Numero == p) {
                aux = this.Unidad[p] + " ";

                return aux;
            }
        }
        return " ";
    }

    private String getDecena(long Numero) {
        String aux = "";
        long pf = Numero % 10L;
        long pi = Numero / 10L;
        int p = 0;
        boolean sal = false;
        while (((p <= 8 ? 1 : 0) & (!sal ? 1 : 0)) != 0) {
            if (pi == p + 2) {
                aux = this.Decena[p];
                sal = true;
            }
            p++;
        }
        if (pf == 0L) {
            return aux + " ";
        }
        if (((Numero > 20L ? 1 : 0) & (Numero < 30L ? 1 : 0)) != 0) {
            return aux + getUnidad(pf) + " ";
        }
        return aux + " y " + getUnidad(pf) + " ";
    }

    private String getCentena(long Numero) {
        String aux = "";
        String aux2 = "";
        long pf = Numero % 100L;
        long pi = Numero / 100L;
        int p = 0;
        boolean sal = false;
        while (((p <= 10 ? 1 : 0) & (!sal ? 1 : 0)) != 0) {
            if (pi == p + 1) {
                aux = this.Centena[p];
                sal = true;
            }
            p++;
        }
        if (pf == 0L) {
            return aux;
        }
        if (pf < 21L) {
            aux2 = getUnidad(pf);
        } else {
            aux2 = getDecena(pf);
        }
        if (Numero < 200L) {
            return aux + "to " + aux2 + " ";
        }
        return aux + " " + aux2 + " ";
    }

    private String getMil(long Numero) {
        String aux = "";
        String aux2 = "";
        long pf = Numero % 1000L;
        long pi = Numero / 1000L;
        long p = 0L;
        if (Numero == 1000L) {
            return "MIL";
        }
        if (((Numero > 1000L ? 1 : 0) & (Numero < 1999L ? 1 : 0)) != 0) {
            aux = this.Centena[9] + " ";
        } else {
            aux = resolverIntervalo(pi) + this.Centena[9] + " ";
        }
        if (pf != 0L) {
            return aux + resolverIntervalo(pf) + " ";
        }
        return aux;
    }

    private String getMillon(long Numero) {
        String aux = "";
        String aux2 = "";
        long pf = Numero % 1000000L;
        long pi = Numero / 1000000L;
        long p = 0L;
        if (((Numero > 1000000L ? 1 : 0) & (Numero < 1999999L ? 1 : 0)) != 0) {
            aux = this.Centena[10] + " ";
        } else {
            aux = resolverIntervalo(pi) + this.Centena[11] + " ";
        }
        if (pf != 0L) {
            return aux + resolverIntervalo(pf) + " ";
        }
        return aux;
    }

    private String getBillon(long Numero) {
        String aux = "";
        String aux2 = "";
        long pf = Numero % 1000000000L;
        long pi = Numero / 1000000000L;
        long p = 0L;
        if (((Numero > 1000000000L ? 1 : 0) & (Numero < 1999999999L ? 1 : 0)) != 0) {
            aux = this.Centena[12] + " ";
        } else {
            aux = resolverIntervalo(pi) + this.Centena[13] + " ";
        }
        if (pf != 0L) {
            return aux + resolverIntervalo(pf) + " ";
        }
        return aux;
    }

    private String resolverIntervalo(long Numero) {
        if (((Numero >= 0L ? 1 : 0) & (Numero <= 20L ? 1 : 0)) != 0) {
            return getUnidad(Numero);
        }
        if (((Numero >= 21L ? 1 : 0) & (Numero <= 99L ? 1 : 0)) != 0) {
            return getDecena(Numero);
        }
        if (((Numero >= 100L ? 1 : 0) & (Numero <= 999L ? 1 : 0)) != 0) {
            return getCentena(Numero);
        }
        if (((Numero >= 1000L ? 1 : 0) & (Numero <= 999999L ? 1 : 0)) != 0) {
            return getMil(Numero);
        }
        if (((Numero >= 1000000L ? 1 : 0) & (Numero <= 999999999L ? 1 : 0)) != 0) {
            return getMillon(Numero);
        }
        if (((Numero >= 1000000000L ? 1 : 0) & (Numero <= 2000000000L ? 1 : 0)) != 0) {
            return getBillon(Numero);
        }
        return "<<El numero esta fuera del rango>>";
    }

    public String toLetras(long Numero) {
        this.Valororiginal = Numero;
        if (Numero >= 0L) {
            return resolverIntervalo(Numero);
        }
        return " Menos " + resolverIntervalo(Numero * -1L);
    }

    public boolean pausa(long p) {
        return true;
    }

    public static void main(String[] Args) {
        CnvNumsLets nl = new CnvNumsLets();
        int i = 0;
        while ((i <= 2000000000 & nl.pausa(5L))) {
            System.out.println(" " + i + "= " + nl.toLetras(i++));
        }
    }
}
