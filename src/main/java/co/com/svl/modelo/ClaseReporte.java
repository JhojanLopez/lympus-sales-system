/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.modelo;

import java.util.List;

/**
 *
 * @author JHOJAN L
 */
public class ClaseReporte {
    private List<Venta> ventas;
    private long precioVentas;
    private long costoVentas;
    private long ganancia;

    public ClaseReporte() {
    }

    public ClaseReporte(List<Venta> ventas, long precioVenta, long costoVenta, long ganancia) {
        this.ventas = ventas;
        this.precioVentas = precioVenta;
        this.costoVentas = costoVenta;
        this.ganancia = ganancia;
    }

    public List<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(List<Venta> ventas) {
        this.ventas = ventas;
    }

    public long getPrecioVenta() {
        return precioVentas;
    }

    public void setPrecioVenta(long precioVenta) {
        this.precioVentas = precioVenta;
    }

    public long getCostoVenta() {
        return costoVentas;
    }

    public void setCostoVenta(long costoVenta) {
        this.costoVentas = costoVenta;
    }

    public long getGanancia() {
        return ganancia;
    }

    public void setGanancia(long ganancia) {
        this.ganancia = ganancia;
    }

    @Override
    public String toString() {
        return "Reporte{" + "ventas=" + ventas + ", precioVentas=" + precioVentas + ", costoVentas=" + costoVentas + ", ganancia=" + ganancia + '}';
    }
    
    
    
}
