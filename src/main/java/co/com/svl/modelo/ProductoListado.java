
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.modelo;

import java.util.List;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
public class ProductoListado extends Producto {
    //producto el cual estara en una lista de la venta, la cual tendra una cantidad a vender y subtotal
    //heredando todos los atributos de un producto

    private long gananciaProducto;
    private double cantidadVenta;
    private long subTotal;

    public ProductoListado() {
        super();
    }

    public ProductoListado(double cantidadVenta, Long codigo, String nombre,
            long precio, long costo) {

        super(codigo, nombre, precio , costo);
        this.cantidadVenta = cantidadVenta;

    }

    public double getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(double cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public long getSubTotal() {
        this.subTotal = (long) (this.getPrecio() * this.getCantidadVenta());
        return subTotal;
    }

    public void setSubTotal(long subTotal) {
        this.subTotal = subTotal;
    }

    public long getGananciaProducto() {
        this.gananciaProducto = (long) (this.cantidadVenta*(this.getPrecio() - this.getCosto()));
        return gananciaProducto;
    }

    public void setGananciaProducto(long gananciaVenta) {
        this.gananciaProducto = gananciaVenta;
    }

    @Override
    public String toString() {
        return "ProductoListado{" + " Codigo= " + this.getCodigo() + ", nombre= " + this.getNombre()
                + ", costo= " + this.getCosto()+ ", precio= " + this.getPrecio() + ", cantidadVenta=" + cantidadVenta +
                ", ganancia="+ this.getGananciaProducto() +", subTotal="+ this.getSubTotal() + '}';
    }

}
