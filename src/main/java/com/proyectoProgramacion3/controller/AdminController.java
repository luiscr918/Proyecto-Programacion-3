package com.proyectoProgramacion3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    @GetMapping("/admin")
    public String mostrarVentanaAdmin(){
        return "pages/pageAdmin";
    }
}
