/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.modelo.Reporte;

/**
 *
 * @author JHOJAN L
 */
public interface ReporteService {
    
    
    public void guardar(Reporte reporte);

    public void eliminar(Reporte reprte);

    public Reporte encontrarPorCodigo(Long codigo);

}
