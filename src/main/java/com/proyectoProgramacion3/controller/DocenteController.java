package com.proyectoProgramacion3.controller;


import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.services.DocenteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocenteController {

    @Autowired
    private DocenteServices docenteServicio;

    @GetMapping("/registroDocente")
    public String mostrarFormulario(Model model) {
        model.addAttribute("docente", new Docente());
        return "pages/registroDocente";
    }

    // Mostrar formulario de registro de docente
    @GetMapping("/formularioDocente")
    public String formularioDocente(Model model) {
        model.addAttribute("docente", new Docente());
        return "pages/formularioDocente";
    }

    @PostMapping("/guardarDocente")
    public String guardarDocente(@Valid @ModelAttribute("docente") Docente docente,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "pages/registroDocente";
        }

        Docente docenteGuardado = docenteServicio.guardarDocente(docente);
        model.addAttribute("docente", docenteGuardado);
        return "pages/vistaDocente"; // vista con datos guardados
    }
}



