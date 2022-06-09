package co.com.svl.controlador;

import co.com.svl.modelo.*;
import co.com.svl.servicio.*;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class ControladorInventario {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/inventario")
    public String inventario(Model model) {

        var productoEditar = new Producto();
        model.addAttribute("productoEditar", productoEditar);
        return "inventario";
    }

    @PostMapping("/editarProducto")
    public String editarProducto(Producto productoEditado) {

        //Se debe validar que el costo no sea mayor al precio porque podria dar algun error
        log.info("producto a editar desde el inventario" + productoEditado.toString());

        var admin = new Administrador();//establecemos el administrador en este caso es el cod 1 ya que la aplicacion solo cuenta con este administrador
        admin.setCodigo((short) 1);
        productoEditado.setCodigoAdministrador(admin);
        
        productoService.guardar(productoEditado);
        return "redirect:/inventario";
    }
}
