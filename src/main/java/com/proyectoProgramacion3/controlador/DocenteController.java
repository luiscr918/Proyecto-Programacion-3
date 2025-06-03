package com.proyectoProgramacion3.controlador;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DocenteController {

    @GetMapping("/dirigirVistaDocente")
    public String mostrarVistaDocente(){
        return "pages/vistaDocente";
    }

}
