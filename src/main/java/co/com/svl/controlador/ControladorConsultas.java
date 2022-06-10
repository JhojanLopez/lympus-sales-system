/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.servicio.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author JHOJAN L
 */
@Slf4j
@Controller
public class ControladorConsultas {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/consultas")
    public String consultas() {
        return "consultas";
    }

}
