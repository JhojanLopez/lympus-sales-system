package co.com.svl.servicio;

import co.com.svl.dao.AdministradorDao;
import co.com.svl.dao.EmpleadoDao;
import co.com.svl.modelo.Administrador;
import co.com.svl.modelo.Empleado;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UsuarioService implements UserDetailsService {

    @Autowired
    private AdministradorDao administradorDao;

    @Autowired
    private EmpleadoDao empleadoDao;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var roles = new ArrayList<GrantedAuthority>();
        Administrador usuarioAdmin = administradorDao.findAdministradorByCorreo(username);//busca en la tabla de administrador si el correo es igual al proporcionado


        if (usuarioAdmin != null) {//si no es nulo, se pasan los datos

            roles.add(new SimpleGrantedAuthority("ROLE_ADMIN")); //si no es nulo significa que es un admin por lo cual se le asigna el rol de admin
            return new User(usuarioAdmin.getCorreo(), usuarioAdmin.getContrasena(), roles);//retorna el usuario admin

        } else {//si es nulo se busca en la tabla empleado

            Empleado usuarioEmpleado = empleadoDao.findEmpleadoByCorreo(username);

            if (usuarioEmpleado != null) {//si no es nulo encuentra el usuario empleado y retorna  el usuario con sus datos

                roles.add(new SimpleGrantedAuthority("ROLE_USER")); 
                return new User(usuarioEmpleado.getCorreo(), usuarioEmpleado.getContrasena(), roles);//retorna el usuario empleado

            }//si es nulo arrojara una excepcion

        }

        throw new UsernameNotFoundException(username);

    }

}
