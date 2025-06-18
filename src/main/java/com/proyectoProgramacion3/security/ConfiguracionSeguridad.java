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
                        .requestMatchers("/","/index","/login","/guardarDocentePropio","/formularioDocentePropio","/formulario-estudiante-usuario","/guardar-estudiante-usuario",
                                "/css/**", "/js/**" , "/imagenes/**").permitAll()
                        .requestMatchers("/docentes").hasRole("DOCENTE")
                        .requestMatchers("/estudiantes").hasRole("ESTUDIANTE")
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/postLogin", true)
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