/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.dao.VentaDao;
import co.com.svl.modelo.Administrador;
import co.com.svl.modelo.Empleado;
import co.com.svl.modelo.Venta;
import java.util.Date;
import java.util.List;
import javax.management.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaDao ventaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Venta> listarVentas() {
        return (List<Venta>) ventaDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Venta venta) {
        ventaDao.save(venta);
    }

    @Override
    @Transactional
    public void eliminar(Venta venta) {
        ventaDao.delete(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public Venta encontrarVentaPorCodigo(Long codigo) {
        return ventaDao.findById(codigo).orElse(null);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> encontrarVentaPorFecha(Date fecha) {
        return (List<Venta>) ventaDao.findByFecha(fecha);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Venta> encontrarVentaPorAdministrador(Administrador administrador) {
        return (List<Venta>) ventaDao.findByAdministrador(administrador);
    }
    @Override
    public List<Venta> encontrarVentaPorEmpleado(Empleado empleado) {
        return (List<Venta>) ventaDao.findByEmpleado(empleado);

    }

    @Override
    public List<Venta> encontrarVentaPorRangoFecha(Date fechaDesde, Date fechaHasta) {
        return (List<Venta>) ventaDao.findByRangoFecha(fechaDesde, fechaHasta);
    }

}
