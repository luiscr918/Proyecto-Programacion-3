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
                        .map(grantedAuthority -> grantedAuthority.getAuthority())
                        .findFirst()
                        .orElse("");
                if (role.equals("ROLE_ADMIN")) {
                    return "redirect:/admin/home";
                } else if (role.equals("ROLE_ESTUDIANTE")) {
                    return "redirect:/estudiante/inicioEstudiante";
                } else if (role.equals("ROLE_DOCENTE")) {
                    return "redirect:/docente/inicioDocente";
                }

                return "redirect:/login?error";
            }

        }