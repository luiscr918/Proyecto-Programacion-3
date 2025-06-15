package com.proyectoProgramacion3.controller;


import com.proyectoProgramacion3.entity.*;
import com.proyectoProgramacion3.service.DocenteServices;
import com.proyectoProgramacion3.service.UsuarioService;
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
@Autowired
private UsuarioService usuarioService;

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
        Docente docente=new Docente();
        model.addAttribute("docente", docente);
        return "pages/formularioDocente";
    }

    //Guardar Docente datos
    @PostMapping("/guardarDocente")
    public String guardarDocente(@Valid @ModelAttribute("docente") Docente docente,
                                 BindingResult result,
                                 Model model) {
        Optional<Usuario> existentePorCedula = usuarioService.obtenerPorCedulaExacta(docente.getCedula());
        if (existentePorCedula.isPresent() && !existentePorCedula.get().getId().equals(docente.getId())) {
            result.rejectValue("cedula", "error.cedula", "Ya existe un docente con esta cédula");
        }

        Optional<Usuario> existentePorCorreo = usuarioService.obtenerPorEmailExacto(docente.getEmail());
        if (existentePorCorreo.isPresent() && !existentePorCorreo.get().getId().equals(docente.getId())) {
            result.rejectValue("email", "error.email", "Ya existe un docente con este email");
        }
        if (result.hasErrors()) {
            return "pages/registroDocente";
        }
        docente.setRol("DOCENTE");
        docenteServicio.guardarDocente(docente);
        model.addAttribute("docente", docente);
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
    // mostrar tabla que simula inicio de sesion hasta ver como es spring security
    @GetMapping("/inicioDocente")
    public String mostrarInicDocente(Model model){
        model.addAttribute("docentes", docenteServicio.mostrarLibros());
        return "pages/DocentePag/simSesionDocente";
    }
    //Obtener materias por  docente
    @GetMapping("/materiasPorDocente/{id}")
    public String materiasPorDocente(@PathVariable Long id,Model model){
        Docente docente=docenteServicio.ObtenerDocenteConMaterias(id);
        List<Materia> materiasDocente=docente.getMaterias();
        model.addAttribute("materiasDocente", materiasDocente);
        return "pages/DocentePag/listaMateriasDocente";
    }

}
