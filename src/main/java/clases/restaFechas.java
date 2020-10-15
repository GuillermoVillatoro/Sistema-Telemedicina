/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marvin Moran
 */
public class restaFechas implements Serializable {

    public int restaFechas(Date fechaFinal,Date fechaInicial) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String strFechaInicial = dateFormat.format(fechaInicial);
         String strFechaFinal = dateFormat.format(fechaFinal);
       
       
        int dias = 0;
        try {
            fechaInicial = dateFormat.parse(strFechaInicial);
            fechaFinal = dateFormat.parse(strFechaFinal);
            dias = (int) ((fechaFinal.getTime() - fechaInicial.getTime()) / 86400000);
        } catch (ParseException ex) {
            Logger.getLogger(restaFechas.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dias;

    }

}
