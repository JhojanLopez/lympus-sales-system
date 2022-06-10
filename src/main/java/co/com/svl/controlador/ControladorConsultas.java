/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.modelo.Venta;
import co.com.svl.servicio.VentaService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author JHOJAN L
 */
@Slf4j
@Controller
public class ControladorConsultas {

    @Autowired
    private VentaService ventaService;
    
    private List<Venta> listaConsulta = new ArrayList<Venta>();

    @GetMapping("/consultas")
    public String consultas(Model model) {
  
        
        if(!listaConsulta.isEmpty()){
        model.addAttribute("listaConsulta", listaConsulta);
        }
        return "consultas";
    }

    @GetMapping("/consultasPorCodigo")
    public String consultaPorCodigo(Long codigo) {
        log.info("venta a consultar: "+codigo);
        
        var venta = ventaService.encontrarVentaPorCodigo(codigo);
        
        if(venta!=null){
            listaConsulta.clear();
            listaConsulta.add(venta);
        }
        return "redirect:/consultas";
    }
    @GetMapping("/consultasPorFecha")
    public String consultaPorFecha(String fecha) {
        log.info("fecha a consultar: "+fecha);
        
        return "redirect:/consultas";
    }
    
    @GetMapping("/consultasPorVendedor")
    public String consultaPorVendedor(int opcion) {
        log.info("opcion a consultar: "+opcion);
        
        return "redirect:/consultas";
    }
}
