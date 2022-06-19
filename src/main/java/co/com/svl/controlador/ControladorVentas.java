/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.com.svl.controlador;

import co.com.svl.modelo.Administrador;
import co.com.svl.modelo.Empleado;
import co.com.svl.modelo.Producto;
import co.com.svl.modelo.ProductoListado;
import co.com.svl.modelo.ProductoVenta;
import co.com.svl.modelo.ProductoVentaPK;
import co.com.svl.modelo.Venta;
import co.com.svl.servicio.AdministradorService;
import co.com.svl.servicio.EmpleadoService;
import co.com.svl.servicio.ProductoService;
import co.com.svl.servicio.ProductoVentaService;
import co.com.svl.servicio.VentaService;
import co.com.svl.util.FormatoFechaHora;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author JhojanDS
 * @email jhojanlopez327@gmail.com
 */
@Slf4j
@Controller
public class ControladorVentas {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private AdministradorService administradorService;
    @Autowired
    private EmpleadoService empleadoService;
    @Autowired
    private VentaService ventaService;
    @Autowired
    private ProductoVentaService productoVentaService;

    private List<ProductoListado> listaVentaProductos = new ArrayList<ProductoListado>();
    private List<Producto> listaBusqueda = new ArrayList<Producto>();

    @GetMapping("/ventas")
    public String ventas(@AuthenticationPrincipal User user, Model model) {

        var vendedor = obtenerDatosUsuario(user.getAuthorities().toString(), user.getUsername());
        model.addAttribute("vendedor", vendedor);

        var producto = new Producto();//compartimos un producto sin datos para que se almacene el codigo de barras al agregarlo de esta manera
        model.addAttribute("producto", producto);

        if (!listaVentaProductos.isEmpty()) {
            model.addAttribute("listaProductosVenta", listaVentaProductos);
            var total = obtenerTotalVenta();
            model.addAttribute("total", total);
        }

        if (!listaBusqueda.isEmpty()) {
            model.addAttribute("listaBusqueda", listaBusqueda);
        }
        return "ventas";
    }

    @GetMapping("/salirVentas")
    public String salirVentas() {

        listaVentaProductos.clear();
        listaBusqueda.clear();
        return "index";
    }

    @GetMapping("/busqueda")//filtrara los productos por nombre o descripcion 
    public String busqueda(@Param("busqueda") String busqueda, Model model) {

        log.info("la palabra a buscar es: " + busqueda);

        listaBusqueda = productoService.encontrarProductoPorNombreOrDescripcion(busqueda.toLowerCase());

        if (listaBusqueda.isEmpty()) {
            return "redirect:/ventas?advertencia=true";
        }
        log.info("lista de productos encontrados: " + listaBusqueda.toString());
        return "redirect:/ventas";
    }

    @GetMapping("/agregarProductoVenta/{codigo}")//usado para agregar un producto traido de la busqueda
    public String agregarProductoVentas(Producto producto, @AuthenticationPrincipal User user, Model model) {
        log.info("codigo producto elegido: " + producto.getCodigo());

        producto = productoService.encontrarProductoPorCodigo(producto.getCodigo());

        var productoListado = new ProductoListado(1.0, producto.getCodigo(), producto.getNombre(),
                producto.getPrecio(), producto.getCosto(), producto.getUnidadMedida());

        agregarProductoListaVenta(productoListado);

        return "redirect:/ventas";

    }

    @PostMapping("/agregarProductoCodigoBarras")
    public String agregarProductoCodigoBarras(Producto producto) {

        if (producto.getCodigo() != null) {

            producto = productoService.encontrarProductoPorCodigo(producto.getCodigo());

            if (producto != null) {

                var productoListado = new ProductoListado(1.0, producto.getCodigo(), producto.getNombre(),
                        producto.getPrecio(), producto.getCosto(), producto.getUnidadMedida());
                log.info(productoListado.toString());

                agregarProductoListaVenta(productoListado);

            } else {

                return "redirect:/ventas?advertencia1=true";

            }

        }
        return "redirect:/ventas";
    }

    @PostMapping("/editarCantidadProducto")
    public String editarCantidadProducto(Producto producto) {

        if (producto.getCantidad() > 0.0) {

            if ((producto.getCantidad() == (long) producto.getCantidad()
                    && producto.getUnidadMedida() == 1)
                    || (producto.getUnidadMedida() == 2)) {

                agregarCantidadEspecifica(producto);
                return "redirect:/ventas";

            } else {
                return "redirect:/ventas?advertencia3=true";

            }

        } else {

            return "redirect:/ventas?advertencia2=true";

        }

    }

    @GetMapping("/eliminarProductoVenta/{codigo}")
    public String eliminarProductoVentas(Producto producto) {
        log.info("------------------CONTROLADOR ELIMINAR PRODUCTO DE LA VENTA----------------");

        eliminarProductoListaVenta(producto.getCodigo());

        return "redirect:/ventas";
    }

    @PostMapping("/generarVenta")
    public String generarVenta(@AuthenticationPrincipal User user) {

        if (!listaVentaProductos.isEmpty()) {

            if (user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {

                var administrador = administradorService.encontrarAdministradorPorCorreo(user.getUsername());
                var codigo = guardarVenta(administrador, null);
                return "redirect:/ventas?exito=true&codigo=" + codigo;

            } else {

                var empleado = empleadoService.encontrarEmpleadoPorCorreo(user.getUsername());
                var codigo = guardarVenta(null, empleado);
                return "redirect:/ventas?exito=true&codigo=" + codigo;
            }

        } else {

            return "redirect:/ventas?error=true";

        }

    }

    @GetMapping("/imprimirVenta")
    public String imprimirVenta(@AuthenticationPrincipal User user) {
       
        if(!listaVentaProductos.isEmpty()){
            if (user.getAuthorities().toString().equals("[ROLE_ADMIN]")) {

                var administrador = administradorService.encontrarAdministradorPorCorreo(user.getUsername());
                var codigo = guardarVenta(administrador, null);
                return "redirect:/ventaPdf/" + codigo;

            } else {

                var empleado = empleadoService.encontrarEmpleadoPorCorreo(user.getUsername());
                var codigo = guardarVenta(null, empleado);
                return "redirect:/ventas?exito=true&codigo=" + codigo;
            }
        }
        return "redirect:/ventas";
    }

    @GetMapping("/limpiarVenta")
    public String limpiarProductoVentas() {
        listaVentaProductos.clear();
        return "redirect:/ventas";
    }

    @GetMapping("/limpiarBusqueda")
    public String limpiarBusqueda() {
        listaBusqueda.clear();
        return "redirect:/ventas";
    }

    public void agregarProductoListaVenta(ProductoListado producto) {
        //Metodo usado para agregar cantidad a la lista de la venta si esta dicho producto
        //al ingresarlo por cofigo de barras
        if (!contieneProducto(producto)) {
            //si no lo contiene lo agrega, si lo contiene se agrega 1.0 en cantidad
            //automaticamente
            listaVentaProductos.add(producto);

        }
    }

    public boolean contieneProducto(ProductoListado producto) {
        log.info("-------------------------------ANALIZANDO SI CONTIENE EL PRODUCTO-----------------------");
        for (ProductoListado productoI : listaVentaProductos) {

            log.info("Producto iterado con codigo=" + productoI.getCodigo() + " es igual al producto con codigo= " + producto.getCodigo());
            if (productoI.getCodigo().equals(producto.getCodigo())) {
                log.info("si es igual");
                productoI.setCantidadVenta(productoI.getCantidadVenta() + 1.0);

                return true;
            }
        }

        return false;
    }

    public void eliminarProductoListaVenta(Long codigo) {

        for (Producto productoI : listaVentaProductos) {

            if (productoI.getCodigo().equals(codigo)) {
                listaVentaProductos.remove(productoI);
                log.info("listado de venta: " + listaVentaProductos.toString());
                break;
            }
        }

    }

    private long guardarVenta(Administrador administrador, Empleado empleado) {

        var fecha = new FormatoFechaHora();
        var venta = new Venta();

        if (administrador != null) {

            //creando venta si el vendedor es el administrador
            venta.setFecha(fecha.getFecha());
            venta.setHora(fecha.getHora());
            venta.setGananciaVenta(obtenerGananciaVenta());
            venta.setTotalVenta((long) obtenerTotalVenta());
            venta.setCodigoCliente(null);//ya que la salsamentaria no maneja clientes
            venta.setCodigoAdministrador(administrador);
            venta.setCodigoEmpleado(null);
            //ingresa a la bd
            ventaService.guardar(venta);
            log.info(venta.toString());

            //se ingresa los productos en la relacion (*,*)
            ingresarDatosProductoVenta(venta);
            listaVentaProductos.clear();
            return venta.getCodigo();

        } else {

            //creando venta si el vendedor es el empleado
            venta.setFecha(fecha.getFecha());
            venta.setHora(fecha.getHora());
            venta.setGananciaVenta(obtenerGananciaVenta());
            venta.setTotalVenta((long) obtenerTotalVenta());
            venta.setCodigoCliente(null);

            venta.setCodigoAdministrador(empleado.getCodigoAdministrador());
            venta.setCodigoEmpleado(empleado);
            //ingresa a la bd
            ventaService.guardar(venta);
            log.info(venta.toString());

            //se ingresa los productos en la relacion (*,*)
            ingresarDatosProductoVenta(venta);

            listaVentaProductos.clear();
            return venta.getCodigo();

        }
    }

    private void ingresarDatosProductoVenta(Venta venta) {
        var pv = new ProductoVenta();
        var productoListado = new ProductoListado();
        var pvpk = new ProductoVentaPK(venta.getCodigo());

        for (int i = 0; i < listaVentaProductos.size(); i++) {
            productoListado = listaVentaProductos.get(i);

            pvpk.setCodigoProducto(productoListado.getCodigo());
            pv.setProductoVentaPK(pvpk);//guardamos el pk
            pv.setPrecioVenta(productoListado.getPrecio());
            pv.setCostoVenta(productoListado.getCosto());
            pv.setSubtotal((long) productoListado.getSubTotal());
            pv.setGanancia(productoListado.getGananciaProducto());
            pv.setCantidadVendida(productoListado.getCantidadVenta());
            productoVentaService.guardar(pv);
        }

    }

    private long obtenerGananciaVenta() {

        long ganancia = 0;
        for (ProductoListado productoI : listaVentaProductos) {
            ganancia = ganancia + productoI.getGananciaProducto();
        }

        return ganancia;
    }

    private double obtenerTotalVenta() {
        double totalVenta = 0;
        for (ProductoListado productoI : listaVentaProductos) {
            totalVenta = totalVenta + productoI.getSubTotal();
        }
        return totalVenta;
    }

    private void agregarCantidadEspecifica(Producto producto) {

        for (ProductoListado productoI : listaVentaProductos) {

            if (productoI.getCodigo().equals(producto.getCodigo())) {//encontramos el producto

                log.info("Unidad de medida: " + productoI.getUnidadMedida());
                if (productoI.getUnidadMedida() == 1) {//si es 1 el producto es por unidad por lo cual 
                    //se debe guardar el valor dado con un una cantidad sin decimales

                    int cantidad = (int) producto.getCantidad();
                    if (cantidad != 0) {
                        productoI.setCantidadVenta((double) cantidad);
                    } else {
                        productoI.setCantidadVenta(1.0);
                    }
                    log.info("cantidad a guardar: " + ((double) cantidad));
                } else {

                    productoI.setCantidadVenta(producto.getCantidad());

                }
                break;
            }
        }

    }

    public Object obtenerDatosUsuario(String rol, String correo) {//obtengo todos los datos del usuario logeado

        if (rol.equals("[ROLE_ADMIN]")) {
            return administradorService.encontrarAdministradorPorCorreo(correo);

        } else {
            return empleadoService.encontrarEmpleadoPorCorreo(correo);
        }

    }
}
