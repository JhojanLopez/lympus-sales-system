/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.util;

import co.com.svl.modelo.ProductoListado;
import co.com.svl.modelo.Venta;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
public class prueba {

    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
//        var fechaHora = new Date();
//        System.out.println(fechaHora);
//        System.out.println(sdf.format(fechaHora));
//
        var hora = LocalTime.now().toString();
        var fecha = LocalDate.now();
//        System.out.println(fecha);
//
    var formateador = new FormatoFechaHora();

    var fechaHora = new FormatoFechaHora();
        System.out.println(fechaHora.getHora());
       
        Venta v = new Venta();
        v.setHora(formateador.getHora());
        System.out.println(v);
        
        
    }

}
