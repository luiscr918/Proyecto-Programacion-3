package com.proyectoProgramacion3.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String mostrarlogin(){
        return "pages/login";
    }

    @GetMapping("/postLogin")
    public String redirigirPorRol(Authentication authentication) {
        User usuario = (User) authentication.getPrincipal();

        String role = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("");

        if (role.equals("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else if (role.equals("ROLE_ESTUDIANTE")) {
            return "redirect:/estudiantes";
        } else if (role.equals("ROLE_DOCENTE")) {
            return "redirect:/docentes";
        }

        return "redirect:/login?error";
    }

}
