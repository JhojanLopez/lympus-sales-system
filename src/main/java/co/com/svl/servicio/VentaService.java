/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.modelo.Administrador;
import co.com.svl.modelo.Empleado;
import co.com.svl.modelo.Venta;
import java.util.Date;
import java.util.List;

/**
 *
 * @author JhojanDS
 */
public interface VentaService {

    public List<Venta> listarVentas();

    public void guardar(Venta venta);

    public void eliminar(Venta venta);

    public Venta encontrarVentaPorCodigo(Long codigo);

    public List<Venta> encontrarVentaPorFecha(Date fecha);

    public List<Venta> encontrarVentaPorAdministrador(Administrador administrador);

    public List<Venta> encontrarVentaPorEmpleado(Empleado empleado);

    public List<Venta> encontrarVentaPorRangoFecha(Date fechaDesde ,Date fechaHasta);

}
