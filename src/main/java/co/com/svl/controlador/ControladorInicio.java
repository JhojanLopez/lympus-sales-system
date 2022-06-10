
package co.com.svl.controlador;

import co.com.svl.modelo.*;
import co.com.svl.servicio.*;
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

@Slf4j
@Controller
public class ControladorInicio {

    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping("/")
    public String inicio(Model model, @AuthenticationPrincipal User user) { //@AuthenticationPrincipal User user){// @AuthenticationPrincipal User user CON ESTO PODEMOS CAPTURAR EL USUSARIO QUE HIZO LOGIN

        log.info("estoy en el controlador del index");
        model.addAttribute("usuarios", user);
        return "index";
    }
 
    @GetMapping("/configuracion")
    public String configuracion(@AuthenticationPrincipal User user, Model model) {

        var usuario = obtenerDatosUsuario(user.getAuthorities().toString(), user.getUsername());
        model.addAttribute("usuario", usuario);
        return "configuracion";
    }

    @PostMapping("/modificar")
    public String modificar(@AuthenticationPrincipal User user, Administrador administrador, Empleado empleado) {//@Valid Persona persona, Errors errores){
//        if(errores.hasErrors()){
//            return "modificar";
////        }

        actualizarDatos(user.getAuthorities().toString(), administrador, empleado);
        return "redirect:/configuracion";
    }

    public void actualizarDatos(String rol, Administrador administrador, Empleado empleado) {

        if (rol.equals("[ROLE_ADMIN]")) {
            administradorService.guardar(administrador);

        } else {
            empleadoService.guardar(empleado);
        }

    }

    public Object obtenerDatosUsuario(String rol, String correo) {//obtengo todos los datos del usuario logeado

        if (rol.equals("[ROLE_ADMIN]")) {
            return administradorService.encontrarAdministradorPorCorreo(correo);

        } else {
            return empleadoService.encontrarEmpleadoPorCorreo(correo);
        }

    }

//    
//    @GetMapping("/inventario")
//    public String inventario(@AuthenticationPrincipal User user, Model model) {
//        return "inventario";
//    }
//    
//    
//    @GetMapping("/reportes")
//    public String reportes(@AuthenticationPrincipal User user, Model model) {
//        return "reportes";
//    }
//    
//    
//    @GetMapping("/editar/{idPersona}")
//    public String editar(Persona persona, Model model){
//        persona = personaService.encontrarPersona(persona);
//        model.addAttribute("persona", persona);
//        return "modificar";
//    }
//    
//    @GetMapping("/eliminar")
//    public String eliminar(Persona persona){
//        personaService.eliminar(persona);
//        return "redirect:/";
//    }
}
