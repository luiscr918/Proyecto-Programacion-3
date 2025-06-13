package com.proyectoProgramacion3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String mostrarIndex(){
        return "index";
    }
}
