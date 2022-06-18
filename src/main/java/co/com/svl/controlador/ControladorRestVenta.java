/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.modelo.Reporte;
import co.com.svl.modelo.Venta;
import co.com.svl.servicio.VentaService;
import co.com.svl.util.ReportePdf;
import co.com.svl.util.VentaPdf;
import com.lowagie.text.DocumentException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JHOJAN L
 */
@Slf4j
@RestController
@RequestMapping()
public class ControladorRestVenta {

    @Autowired
    private VentaService ventaService;

    @GetMapping(path = "/ventaPdf/{codigo}")
    public void exportarListadoVentasPDF(Venta v, HttpServletResponse response) throws DocumentException, IOException {

        response.setContentType("application/pdf");

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String fechaActual = dateFormatter.format(new Date());

        String cabecera = "content-Disposition";
        String valor = "attachment; filename=Venta_" + fechaActual + ".pdf";

        response.setHeader(cabecera, valor);

        var venta = ventaService.encontrarVentaPorCodigo(v.getCodigo());

        var exporter = new VentaPdf(venta);
        exporter.exportar(response);

    }
}
