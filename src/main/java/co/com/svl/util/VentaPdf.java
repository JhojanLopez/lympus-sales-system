/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.util;

import co.com.svl.modelo.Venta;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Juan Turriago
 */
@Slf4j
public class VentaPdf {

    private Venta venta;

    public VentaPdf(Venta venta) {
        super();
        this.venta = venta;
    }

    public void exportar(HttpServletResponse response) throws IOException {
        Document documento = new Document(PageSize.A4);
        PdfWriter.getInstance(documento, response.getOutputStream());

        documento.open();

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fuente.setColor(Color.BLUE);
        fuente.setSize(18);

        Paragraph titulo = new Paragraph("Lista de ventas", fuente);
        titulo.setAlignment(Paragraph.ALIGN_CENTER);
        documento.add(titulo);
        PdfPTable tabla = new PdfPTable(5);

        tabla.setWidthPercentage(100);
        tabla.setSpacingBefore(15);
        tabla.setWidths(new float[]{1f, 2.3f, 2.3f, 6f, 2.9f});
        tabla.setWidthPercentage(110);

        cabeceraTabla(tabla);
        datosTabla(tabla);

//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        tabla.addCell("hola");
//        
        log.info("--------------------------------------------PDF---------------------------------------------");
        log.info("tamano de la tabla: " + tabla.size() + " pdf");

        documento.add(tabla);
        documento.close();

    }

    private void cabeceraTabla(PdfPTable tabla) {

        PdfPCell celda = new PdfPCell();

        celda.setBackgroundColor(Color.BLUE);
        celda.setPadding(5);
        celda.setBorderColorBottom(Color.GREEN);

        Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
        fuente.setColor(Color.black);

        celda.setPhrase(new Phrase("Codigo", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("fecha", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("hora", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("ganancia", fuente));
        tabla.addCell(celda);

        celda.setPhrase(new Phrase("total", fuente));
        tabla.addCell(celda);

        log.info("agregando headers: tam tabla= " + tabla.getRow(0));

    }

    private void datosTabla(PdfPTable tabla) {

        var pv = venta.getProductoVentaList();
        //cod 19 cod 1
//        pv.get(0).

        log.info("\n------------------------obtenemos datos negocio--------------------------");
        log.info("\n nombre negocio: "+ venta.getCodigoAdministrador().getNombreNegocio()+
                "\n nit: "+ venta.getCodigoAdministrador().getNitNegocio()+
                "\n direccion: "+ venta.getCodigoAdministrador().getDireccion()+
                "\n telefono: "+ venta.getCodigoAdministrador().getTelefono());
        
        log.info("\n------------------------obtenemos vendedor--------------------------");
        if (venta.getCodigoEmpleado() == null) {
            log.info("vendedor: " + venta.getCodigoAdministrador().getNombre());
        } else {
            log.info("vendedor: " + venta.getCodigoEmpleado().getNombre());
        }

        log.info("\n------------------------obtenemos datos de productos--------------------------");
        log.info("\n Codigo" + pv.get(0).getProducto().getCodigo()
                + "\n nombre=" + pv.get(0).getProducto().getNombre()
                + "\n precio venta=" + pv.get(0).getPrecioVenta()//por unidad
                + "\n costo venta=" + pv.get(0).getCostoVenta()//por unidad
                + "\n cantidad=" + pv.get(0).getCantidadVendida()//cantidad
                + "\n subtotal=" + pv.get(0).getSubtotal()
                + "\n ganancia=" + pv.get(0).getGanancia());

        tabla.addCell("" + venta.getCodigo());
        tabla.addCell("" + venta.getFecha());
        tabla.addCell("" + venta.getHora());
        tabla.addCell("" + venta.getGananciaVenta());
        tabla.addCell("" + venta.getTotalVenta());
//        int prueba = 8;
//        
//        for (int i = 0; i < prueba; i++) {
//
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//            tabla.addCell("Hola" + i);
//        }
/*
venta 19 relacion *,* 
    
    pv---lista
    pv.get(i).getProducto().getNombre();
    pv.get(i).getCantidadVendida();
    venta.getGanancia();



         */
//        for (Venta venta : listaVenta) {
//            tabla.addCell("" + venta.getCodigo());
//            tabla.addCell("" + venta.getFecha());
//            tabla.addCell("" + venta.getHora());
////            tabla.addCell(""+venta.getProductoVentaList());
//            tabla.addCell("" + venta.getGananciaVenta());
//            tabla.addCell("" + venta.getTotalVenta());
//
//            System.out.println(venta);
//            System.out.println("Hola");
//
//        }
    }

}
