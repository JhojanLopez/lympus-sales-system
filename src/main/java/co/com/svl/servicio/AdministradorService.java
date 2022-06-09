/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package co.com.svl.servicio;

import co.com.svl.modelo.Administrador;
import java.util.List;

/**
 *
 * @author JhojanDS
 */
public interface AdministradorService{
    
    public List<Administrador> listarAdministradores();
    
    public void guardar(Administrador administrador);
    
    public void eliminar(Administrador administrador);
    
    public Administrador encontrarAdministrador(Administrador administrador);
    
    public Administrador encontrarAdministradorPorCorreo(String correo);
    
}
