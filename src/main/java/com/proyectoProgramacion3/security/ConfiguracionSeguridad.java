package com.proyectoProgramacion3.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfiguracionSeguridad {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws
            Exception{
        http
                .authorizeHttpRequests(auth ->auth
                        .requestMatchers("/index","/","/login", "/formularioDocentePropio","/guardarDocentePropio","/guardarUsuario",
                                "/admin","/guardarDocente","/registroDocente","/docentes","editarDocente/{id}","/eliminarDocente/{id}",
                                "/inicioDocente","/materiasPorDocente/{id}",
                                "/css/**", "/js/**" , "/imagenes/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/index", true)
                )
                .logout(logout ->logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();

    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
