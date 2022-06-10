/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.dao.ProductoDao;
import co.com.svl.modelo.Empleado;
import co.com.svl.modelo.Producto;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> listarProductos() {
        return (List<Producto>) productoDao.findAll();
    }

    @Override
    @Transactional
    public void guardar(Producto producto) {
        productoDao.save(producto);

    }

    @Override
    @Transactional  
    public void eliminar(Producto producto) {
        productoDao.delete(producto);
    }

    @Override
    @Transactional
    public Producto encontrarProductoPorCodigo(Long codigo) {
        return productoDao.findById(codigo).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> encontrarProductoPorNombreOrDescripcion(String busqueda) {
        return productoDao.findProductoByNombreOrByDescripcion(busqueda);
    }

}
