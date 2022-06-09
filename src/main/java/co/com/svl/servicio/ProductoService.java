/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.modelo.Producto;
import java.util.List;

/**
 *
 * @author JhojanDS
 */
public interface ProductoService{
    
    public List<Producto> listarProductos();
    
    public void guardar(Producto producto);
    
    public void eliminar(Producto producto);
    
    public Producto encontrarProductoPorCodigo(Long codigo);
    
    public Producto encontrarProductoPorNombre(String nombre);
    
}
