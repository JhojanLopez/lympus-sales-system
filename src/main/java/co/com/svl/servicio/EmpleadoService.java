/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.modelo.Empleado;
import java.util.List;

/**
 *
 * @author JhojanDS
 */
public interface EmpleadoService {

    public List<Empleado> listarEmpleados();

    public void guardar(Empleado empleado);

    public void eliminar(Empleado empleado);

    public Empleado encontrarEmpleado(Empleado empleado);

    public Empleado encontrarEmpleadoPorCorreo(String correo);

}
