package co.com.svl.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@Configuration
@EnableWebSecurity //habilitamos la seguridad a partir de esta anotacion
public class SecurityConfig extends WebSecurityConfigurerAdapter { //extiende de WebSecurityConfigurerAdapter para poder personalizar los usuarios que pueden acceder a la aplicacion

    @Autowired
    private UserDetailsService userDetailsService; //inyectamos la interface UserDetailsService, que la implementamos en usuarioservice

    public BCryptPasswordEncoder passwordEncoder() { //definimos un bean para que spring pueda usar esta encriptacion. por lo tanto estara en el contenedor de spring al implementarlo como un bean
        return new BCryptPasswordEncoder();
    }

    @Autowired //inyectamos este metodo lo que realizara de manera automatica del obj build de la clase AuthenticationManagerBuilder(ya esta definido en la fabrica de spring)
    public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
        build.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());  //establecemos el usuario al poner build.usuario.tipodeencriptacion        
    }

    @Override//este concepto se le conoce como autorizacion: aqui se le restringe dependidendo de la configuracion accesos a funcionalidades del sistema o paginas de la misma
    protected void configure(HttpSecurity http) throws Exception { //este meetodo permite restringir algunas funcionalidades del sistema
        http.authorizeRequests()
                .antMatchers("/consultas", "/reportes")
                .hasRole("ADMIN")
                .antMatchers("/", "/configuracion", "/ventas", "/inventario")
                .hasAnyRole("USER", "ADMIN")
                .and()
                .formLogin() //con esto indicamos que login vamos a usar e indicamos la vista con su path la cual sera el login que haremos
                .loginPage("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/errores/403");
    }
}
