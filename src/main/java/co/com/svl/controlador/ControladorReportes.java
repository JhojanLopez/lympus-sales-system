/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.modelo.Administrador;
import co.com.svl.modelo.Empleado;
import co.com.svl.modelo.ProductoVenta;
import co.com.svl.modelo.Reporte;
import co.com.svl.modelo.Venta;
import co.com.svl.servicio.VentaService;
import co.com.svl.util.FormatoFechaHora;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author JHOJAN L
 */
@Slf4j
@Controller
public class ControladorReportes {

    @Autowired
    private VentaService ventaService;

    private Reporte reporte = new Reporte();

    @GetMapping("/reportes")
    public String reportes(Model model) {

        if (reporte.getVentas() != null) {
            model.addAttribute("reporte", reporte);

        }

        return "reportes";
    }

    @GetMapping("/generarReporte")
    public String generarReporte(@Param("fechaDesde") String fechaDesde,
            @Param("fechaHasta") String fechaHasta) throws ParseException {

        log.info("desde: " + fechaDesde + " hasta: " + fechaHasta);
        var formatoFecha = new FormatoFechaHora();//creamos un obj de la clase FormatoFechaHora
        var fechaDesdeFormateada = formatoFecha.getFormatoFecha1().parse(fechaDesde);//usamos de la clase el simpleFormat 1 y parseamos la fecha a date
        var fechaHastaFormateada = formatoFecha.getFormatoFecha1().parse(fechaHasta);//usamos de la clase el simpleFormat 1 y parseamos la fecha a date

        var listaVentas = ventaService.encontrarVentaPorRangoFecha(fechaDesdeFormateada, fechaHastaFormateada);

        if (!listaVentas.isEmpty()) {
            establecerReporte(listaVentas);
            return "redirect:/reportes";

        } else {
            return "redirect:/reportes?advertencia=true";

        }

    }

    @GetMapping("/exportarReporte")
    public String generarReporte() {
        if(reporte.getVentas()!=null){
        
        return "redirect:/reportePdf/"+reporte;
        
        }else{
        return "redirect:/reportes";
        }
        
    }

    @GetMapping("/limpiarReporte")
    public String limpiar() {
        reporte.setVentas(null);
        return "redirect:/reportes";
    }

    private void establecerReporte(List<Venta> listaVentas) {

        reporte.setVentas(listaVentas);
        establecerCostoPrecio(listaVentas);
        reporte.setGanancia(reporte.getPrecioVenta() - reporte.getCostoVenta());

        log.info("------------------------------------------------------------------------------");
        log.info("Precio del reporte generado= " + reporte.getPrecioVenta());
        log.info("Costo del reporte generado= " + reporte.getCostoVenta());
        log.info("Ganancia del reporte generado= " + reporte.getGanancia());
    }

    private long establecerCostoPrecio(List<Venta> listaVentas) {

        log.info("ventas listadas: " + listaVentas.toString());
        long costo = 0;
        long venta = 0;

        log.info("----   Establecer costo ----");
        for (int i = 0; i < listaVentas.size(); i++) {
            var listaProductos = listaVentas.get(i).getProductoVentaList();

            for (int j = 0; j < listaProductos.size(); j++) {

                costo = costo + (long) (listaProductos.get(j).getCantidadVendida() * listaProductos.get(j).getCostoVenta());
                venta = venta + (long) (listaProductos.get(j).getCantidadVendida() * listaProductos.get(j).getPrecioVenta());

            }
        }

        reporte.setCostoVenta(costo);
        reporte.setPrecioVenta(venta);

        return costo;
    }
}
