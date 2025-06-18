package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.entity.Usuario;
import com.proyectoProgramacion3.service.DocenteServices;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class IndexController {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private EstudianteServicio estudianteServicio;
    @Autowired
    private DocenteServices docenteServices;

    @GetMapping("/index")
    public String mostrarIndex() {
        return "index";
    }


    //Insertar Estudiantes usuario
    @GetMapping("/formulario-estudiante-usuario")
    public String formularioEstudianteUsuario(Model model){
        model.addAttribute("estudiante",new Estudiante());
        return "pages/EstudiantePag/registroEstudiantesUsuario";
    }

    //guardar estudiantes como usuario
    @PostMapping("/guardar-estudiante-usuario")
    public String guardarEstudianteUsuario(@Valid @ModelAttribute Estudiante estudiante,
                                           BindingResult bindingResult,
                                           RedirectAttributes redirectAttributes,
                                           Model model) {
        Optional<Usuario> existentePorCedula = usuarioService.obtenerPorCedulaExacta(estudiante.getCedula());
        if (existentePorCedula.isPresent() && !existentePorCedula.get().getId().equals(estudiante.getId())) {
            bindingResult.rejectValue("cedula", "error.cedula", "Ya existe un usuario con esta cédula");
        }

        Optional<Usuario> existentePorCorreo = usuarioService.obtenerPorEmailExacto(estudiante.getEmail());
        if (existentePorCorreo.isPresent() && !existentePorCorreo.get().getId().equals(estudiante.getId())) {
            bindingResult.rejectValue("email", "error.email", "Ya existe un usuario con este email");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/registroEstudiante";
        }
        // Validar si el curso está vacío o tiene ID nulo
        if (estudiante.getCurso() != null && estudiante.getCurso().getId() == null) {
            estudiante.setCurso(null);
        }
        estudiante.setRol("ESTUDIANTE");
        estudianteServicio.guardarEstudiante(estudiante);
        redirectAttributes.addFlashAttribute("mensajeExito", true);
        return "redirect:/index"; // Redirigir para que no se vuelva a enviar el formulario al recargar
    }


    //Propio de docente

    //RegistroDocentePropcio
    @GetMapping("/formularioDocentePropio")
    public String mostrarFormularioDocentePropio(Model model) {
        model.addAttribute("docente", new Docente());
        return "pages/docenteRegistroPropio";
    }

    //Guardar Docente datos
    @PostMapping("/guardarDocentePropio")
    public String guardarDocentePropio(@Valid @ModelAttribute("docente") Docente docente,
                                       BindingResult result,
                                       Model model,
                                       RedirectAttributes redirectAttributes) {

        Optional<Usuario> existentePorCedula = usuarioService.obtenerPorCedulaExacta(docente.getCedula());
        if (existentePorCedula.isPresent() && !existentePorCedula.get().getId().equals(docente.getId())) {
            result.rejectValue("cedula", "error.cedula", "Ya existe un usuario con esta cédula");
        }

        Optional<Usuario> existentePorCorreo = usuarioService.obtenerPorEmailExacto(docente.getEmail());
        if (existentePorCorreo.isPresent() && !existentePorCorreo.get().getId().equals(docente.getId())) {
            result.rejectValue("email", "error.email", "Ya existe un usuario con este email");
        }

        if (result.hasErrors()) {
            return "pages/docenteRegistroPropio";
        }

        docente.setRol("DOCENTE");
        docenteServices.guardarDocente(docente);

        redirectAttributes.addFlashAttribute("registroExitoso", true);
        return "redirect:/index";
    }




}
