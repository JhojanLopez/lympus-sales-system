/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.svl.dao;

import co.com.svl.modelo.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author JhojanDS
 */
public interface ProductoDao extends JpaRepository<Producto, Long>{
    
    @Query("SELECT p FROM Producto p WHERE p.nombre = :nombre")
    public Producto findProductoByNombre(@Param("nombre") String nombre);
    
//    @Query("SELECT p FROM Producto p WHERE p.codigo = :codigo")
//    public Producto findProductoByCodigo(@Param("codigo") Long codigo);
//    
}
