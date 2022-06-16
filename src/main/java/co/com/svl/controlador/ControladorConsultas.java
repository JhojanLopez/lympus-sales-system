/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.modelo.Administrador;
import co.com.svl.modelo.Empleado;
import co.com.svl.modelo.Venta;
import co.com.svl.servicio.VentaService;
import co.com.svl.util.FormatoFechaHora;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author JHOJAN L
 */
@Slf4j
@Controller
public class ControladorConsultas {

    @Autowired
    private VentaService ventaService;

    private List<Venta> listaConsulta = new ArrayList<Venta>();

    @GetMapping("/consultas")
    public String consultas(Model model) {

        if (!listaConsulta.isEmpty()) {
            model.addAttribute("listaConsulta", listaConsulta);
        }
        return "consultas";
    }

    @GetMapping("/salirConsultas")
    public String salirConsultas() {
        listaConsulta.clear();
        return "index";
    }

    @GetMapping("/consultasPorCodigo")
    public String consultaPorCodigo(double dato) {
        log.info("venta a consultar: " + dato);

        var codigo = (long) dato;//si llega a poner un valor con x.0 que lo castee a entero
        var venta = ventaService.encontrarVentaPorCodigo(codigo);

        if (venta != null) {

            listaConsulta.clear();
            listaConsulta.add(venta);
            return "redirect:/consultas";
        } else {
            listaConsulta.clear();
            return "redirect:/consultas?advertencia=true";

        }

    }

    @GetMapping("/consultasPorFecha")
    public String consultaPorFecha(String fecha) throws ParseException {
        log.info("fecha a consultar: " + fecha);

        var formatoFecha = new FormatoFechaHora();//creamos un obj de la clase FormatoFechaHora
        var fechaFormateada = formatoFecha.getFormatoFecha1().parse(fecha);//usamos de la clase el simpleFormat 1 y parseamos la fecha a date

        var listaEncontrada = ventaService.encontrarVentaPorFecha(fechaFormateada);
        log.info("comparacion =" + (listaEncontrada != null)
                + " lista: " + listaEncontrada.toString());

        if (!listaEncontrada.isEmpty()) {

            listaConsulta.clear();
            listaConsulta.addAll(listaEncontrada);

            return "redirect:/consultas";

        } else {
            listaConsulta.clear();
            return "redirect:/consultas?advertencia=true";

        }

    }

    @GetMapping("/consultasPorVendedor")
    public String consultaPorVendedor(int opcion) {
        log.info("opcion a consultar: " + opcion);

        if (opcion == 1) {//1 filtra por administrador
            var administrador = new Administrador((short) 1);
            var listaEncontrada = ventaService.encontrarVentaPorAdministrador(administrador);

            log.info("venta encontradas: " + listaEncontrada.toString());

            if (!listaEncontrada.isEmpty()) {

                listaConsulta.clear();
                listaConsulta.addAll(listaEncontrada);
                return "redirect:/consultas";

            } else {
                listaConsulta.clear();
                return "redirect:/consultas?advertencia=true";
            }

        } else if (opcion == 2) {//2 filtra por empleado

            var empleado = new Empleado((short) 1);
            var listaEncontrada = ventaService.encontrarVentaPorEmpleado(empleado);
            log.info("cantidad de ventas: " + listaEncontrada.size() + " venta encontrada: " + listaEncontrada.toString());

            if (!listaEncontrada.isEmpty()) {

                listaConsulta.clear();
                listaConsulta.addAll(listaEncontrada);
                return "redirect:/consultas";

            } else {
                listaConsulta.clear();
                return "redirect:/consultas?advertencia=true";
            }

        }

        return "redirect:/consultas";
    }

    @GetMapping("/limpiarConsultas")
    public String limpiarConsultas() {

        listaConsulta.clear();
        return "redirect:/consultas";
    }

}
