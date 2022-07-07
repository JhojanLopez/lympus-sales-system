/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.util;

import java.text.DecimalFormat;

/**
 *
 * @author JHOJAN L
 */
public class prueba {

    public static void main(String[] args) {
        double number = 322.452;
        var df = new DecimalFormat("#.00");
        number = Double.parseDouble(df.format(number).replace(",", "."));
        System.out.println(number);
    }

}
