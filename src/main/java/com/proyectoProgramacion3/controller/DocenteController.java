package com.proyectoProgramacion3.controller;


import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.service.DocenteServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DocenteController {

    @Autowired
    private DocenteServices docenteServicio;

    // Listar docentes
    @GetMapping("/docentes")
    public String listarDocentes(@RequestParam(name = "buscarDocente", required = false, defaultValue = "")
                                 String buscarDocente,Model model) {
        List<Docente> docentes = docenteServicio.buscarDocentePorCedula(buscarDocente);
        model.addAttribute("buscarDocente", buscarDocente);
        model.addAttribute("docentes", docentes);
        return "pages/vistaDocente";
    }


    @GetMapping("/registroDocente")
    public String mostrarFormulario(Model model) {
        model.addAttribute("docente", new Docente());
        return "pages/registroDocente";
    }

    // Mostrar formulario de registro
    @GetMapping("/formularioDocente")
    public String formularioDocente(Model model) {
        model.addAttribute("docente", new Docente());
        return "pages/formularioDocente";
    }

    //Guardar Docente datos
    @PostMapping("/guardarDocente")
    public String guardarDocente(@Valid @ModelAttribute("docente") Docente docente,
                                 BindingResult result,
                                 Model model) {
        if (result.hasErrors()) {
            return "pages/registroDocente";
        }

        Docente docenteGuardado = docenteServicio.guardarDocente(docente);
        model.addAttribute("docente", docenteGuardado);
        return "redirect:/docentes";
    }






    //actualizar
    @GetMapping("editarDocente/{id}")
    public String actualizarDocente(@PathVariable Long id, Model model){
        Optional<Docente> optionalDocente = docenteServicio.buscarDocentePorId(id);
        if (optionalDocente.isPresent()) {
            model.addAttribute("docente", optionalDocente.get());
            return "pages/registroDocente";
        } else {
            return "redirect:/docentes";
        }
    }

    // Eliminar
    @GetMapping("/eliminarDocente/{id}")
    public String eliminarDocente(@PathVariable Long id) {
        docenteServicio.eliminarDocente(id);
        return "redirect:/docentes";
    }

}
