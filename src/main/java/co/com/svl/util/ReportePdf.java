/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.util;

import co.com.svl.modelo.Reporte;
import co.com.svl.modelo.Venta;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.html.HtmlWriter;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Juan Turriago
 */
@Slf4j
public class ReportePdf {

    private Venta venta;
    private Reporte reporte;

    public ReportePdf(Venta venta, Reporte reporte) {
        super();
        this.venta = venta;
        this.reporte = reporte;
    }

    public void exportar(HttpServletResponse response) throws IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        Font fuenteTitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuenteTitulo.setColor(Color.RED);
        fuenteTitulo.setSize(18);

        var tituloReporte = new Paragraph("Reporte Salsamentaria L&M"
                + "\n\n", fuenteTitulo);
        tituloReporte.setAlignment(Paragraph.ALIGN_CENTER);

        var informacionReporte = new Paragraph();
        var informacionIzquierda = new Paragraph();
        var informacionCentro = new Paragraph();
        var informacionTotal = new Paragraph();
        var informacionFinal = new Paragraph();

        PdfPTable tabla = new PdfPTable(4);

        documento.open();

        datosReporte(informacionReporte);

        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{6f, 2.3f, 1.7f, 2.1f});
        tabla.setWidthPercentage(110);

        cabeceraTabla(tabla);
        datosTabla(tabla, informacionCentro, informacionIzquierda, informacionTotal, informacionFinal);

        Image img = Image.getInstance("src/main/resources/static/img/logo.png");
        img.scaleAbsolute(120, 100);
        img.setAlignment(Paragraph.ALIGN_CENTER);

        documento.add(tituloReporte);
        documento.add(img);
        documento.add(informacionReporte);
        documento.add(informacionCentro);
        documento.add(informacionIzquierda);
        documento.add(tabla);
        documento.add(informacionTotal);
        documento.add(informacionFinal);
        documento.close();

    }

    private void cabeceraTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setPadding(4);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.black);

        celda.setPhrase(new Phrase("Item", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Cantidad", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Valor", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("Subtotal", fuente));
        tabla.addCell(celda);

        log.info("agregando headers: tam tabla= " + tabla.getRow(0));

    }

    private void datosTabla(PdfPTable tabla, Paragraph informacionCentro, Paragraph informacionIzquierda, Paragraph informacionTotal, Paragraph informacionFinal) {

        var pv = venta.getProductoVentaList();
//        informacionCentro.add(venta.getCodigoAdministrador().getNombreNegocio());
//        informacionCentro.setAlignment(Paragraph.ALIGN_CENTER);
//
//        informacionCentro.add("\n ");
//        informacionCentro.setAlignment(Paragraph.ALIGN_CENTER);
//
//        informacionCentro.add("\n NIT: " + venta.getCodigoAdministrador().getNitNegocio());
//        informacionCentro.setAlignment(Paragraph.ALIGN_CENTER);
//
//        informacionCentro.add("\n Dirección: " + venta.getCodigoAdministrador().getDireccion());
//        informacionCentro.setAlignment(Paragraph.ALIGN_CENTER);
//
//        informacionCentro.add("\n Teléfono: " + venta.getCodigoAdministrador().getTelefono());
//        informacionCentro.setAlignment(Paragraph.ALIGN_CENTER);
//
//        informacionIzquierda.add("\n Número de factura: " + venta.getCodigo());
//        informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

        informacionIzquierda.add("\n Número de factura: " + venta.getCodigo());
        informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

        informacionIzquierda.add("\n Fecha:  " + venta.getFecha() + " / " + venta.getHora());
        informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

        if (venta.getCodigoEmpleado() == null) {

            log.info("vendedor: " + venta.getCodigoAdministrador().getNombre());

            informacionIzquierda.add("\n Vendedor: " + venta.getCodigoAdministrador().getNombre());
            informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

            informacionIzquierda.add("\n ");
            informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

        } else {
            log.info("vendedor: " + venta.getCodigoEmpleado().getNombre());

            informacionIzquierda.add("\n Vendedor: " + venta.getCodigoEmpleado().getNombre());
            informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

            informacionIzquierda.add("\n ");
            informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);
        }
        informacionIzquierda.add("\n Ganancia: " + venta.getGananciaVenta());
        informacionIzquierda.setAlignment(Paragraph.ALIGN_LEFT);

        for (int i = 0; i < pv.size(); i++) {
            tabla.addCell("" + pv.get(i).getProducto().getNombre());
            tabla.addCell("" + pv.get(i).getCantidadVendida());
            tabla.addCell("$" + pv.get(i).getPrecioVenta());
            tabla.addCell("$" + pv.get(i).getSubtotal());
        }

        informacionTotal.add("\n Total final: $" + venta.getTotalVenta());
        informacionTotal.setAlignment(Paragraph.ALIGN_LEFT);

        informacionFinal.add("\n ");
        informacionFinal.setAlignment(Paragraph.ALIGN_CENTER);

        informacionFinal.add("\n ¡Muchas gracias por su compra!");
        informacionFinal.setAlignment(Paragraph.ALIGN_CENTER);

        informacionFinal.add("\n Sistema de venta Lympus");
        informacionFinal.setAlignment(Paragraph.ALIGN_CENTER);

        informacionFinal.add("\n Universidad del Valle");
        informacionFinal.setAlignment(Paragraph.ALIGN_CENTER);
    }

    private void datosReporte(Paragraph informacionReporte) {
        informacionReporte.add("\n\n"
                + " Fecha de generacion: " + reporte.getFechaGeneracion().toString()
                + "\nFecha de inicio: " + reporte.getDesde() + ",   Fecha final: " + reporte.getHasta() + ""
                + "\nCantidad de ventas: " + (reporte.getReporteVentaList().size())
                + "\n Precio de venta total: " + reporte.getPrecioTotalVentas()
                + "\n Costo total: " + reporte.getCostoTotalVentas()
                + "\n Ganancia total: " + reporte.getGananciaTotalVentas() + "\n\n\n\n"
        );

        informacionReporte.setAlignment(Paragraph.ALIGN_JUSTIFIED);

    }

}
