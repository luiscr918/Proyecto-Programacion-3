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
            Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/index", "/login", "/guardarDocentePropio", "/formulario-estudiante-usuario",
                                "/guardar-estudiante-usuario", "/formularioDocentePropio", "/guardarDocentePropio",
                                "/css/**", "/js/**", "/imagenes/**").permitAll()

                        .requestMatchers("/estudiante/inicioEstudiante", "/estudiante/materiasPorEstudiante/*",
                                "/registroTarea/formularioRegistroTarea/*/*", "/registroTarea/guardarRegistroTarea",
                                "/registroTarea/editarRegistro/*", "/tarea/tareasPorMateriaEstudiante/*/*","/registroTarea/registrosTareasEstudiante/*"
                        ).hasRole("ESTUDIANTE")

                        .requestMatchers("/registroTarea/**", "/tarea/**").hasRole("DOCENTE")
                        .requestMatchers("/admin/**", "/estudiante/**", "/curso/**").hasRole("ADMIN")
                        //las que comparten roles
                        .requestMatchers("/materia/**", "/docente/**").hasAnyRole("DOCENTE", "ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/postLogin", true)
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}