/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
public class FormatoFechaHora {

    private SimpleDateFormat formatoHora;
    private SimpleDateFormat formatoFecha1;
    private SimpleDateFormat formatoFecha2;
    private Date fechaHora;

    public FormatoFechaHora() {
        this.fechaHora = new Date();
        this.formatoHora = new SimpleDateFormat("HH:mm:ss");
        this.formatoFecha1 = new SimpleDateFormat("yyyy/MM/dd");
        this.formatoFecha2 = new SimpleDateFormat("dd/MM/yyyy");

    }

    public Date getHora() {

        var hora = formatoHora.format(fechaHora);
        System.out.println(hora);
        try {
            return formatoHora.parse(hora);
        } catch (ParseException ex) {
            Logger.getLogger(FormatoFechaHora.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public Date getFecha() {
        return fechaHora;
    }
    
    

}
