/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.modelo.Producto;
import co.com.svl.servicio.ProductoService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JHOJAN L
 */
@Slf4j
@RestController
@RequestMapping()
public class ControladorRestInventario {

    @Autowired
    private ProductoService productoService;

    @GetMapping(path = "/productos")
    public List<Producto> obtenerProductos() {//se convierte automaticaente en JSON    
        return productoService.listarProductos();
    }

//    @PostMapping(value = "/editarProductoInventario")
//    public ResponseEntity<Producto>(@RequestBody Producto producto) {//se convierte automaticaente en JSON    
//        productoService.guardar(producto);
//        return new ResponseEntity<>(producto,HttpStatus.OK);
//    }

}
