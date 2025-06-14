package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.entity.Usuario;
import com.proyectoProgramacion3.service.EstudianteServicio;
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
public class EstudianteController {
    //Instancio mi servicio
    @Autowired
    private EstudianteServicio estudianteServicio;
@Autowired
private UsuarioService usuarioService;
    //Mostrar todos los estudiantes
    @GetMapping("/estudiantes")
    public String mostrarEstudiantes(@RequestParam(name = "buscarEstudiante", required = false,defaultValue = "")
                                     String buscarEstudiante, Model model){
        List<Estudiante> estudiantes = estudianteServicio.buscarPorCedula(buscarEstudiante);
        model.addAttribute("estudiantes",estudiantes);
        model.addAttribute("buscarEstudiante",buscarEstudiante);
        return "pages/listaEstudiantes";
    }
    //Insertar Estudiantes
    @GetMapping("/formulario-estudiante")
    public String formularioEstudiante(Model model){
        model.addAttribute("estudiante",new Estudiante());
        return "pages/registroEstudiante";
    }
    @PostMapping("/guardar-estudiante")
    public String guardarEstudiante(@Valid @ModelAttribute Estudiante estudiante,
                                    BindingResult bindingResult, Model model) {
        Optional<Usuario> existentePorCedula = usuarioService.obtenerPorCedulaExacta(estudiante.getCedula());
        if (existentePorCedula.isPresent() && !existentePorCedula.get().getId().equals(estudiante.getId())) {
            bindingResult.rejectValue("cedula", "error.cedula", "Ya existe un docente con esta cédula");
        }

        Optional<Usuario> existentePorCorreo = usuarioService.obtenerPorEmailExacto(estudiante.getEmail());
        if (existentePorCorreo.isPresent() && !existentePorCorreo.get().getId().equals(estudiante.getId())) {
            bindingResult.rejectValue("email", "error.email", "Ya existe un docente con este email");
        }
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/registroEstudiante";
        }
        // Validar si el curso está vacío o tiene ID nulo
        if (estudiante.getCurso() != null && estudiante.getCurso().getId() == null) {
            estudiante.setCurso(null);
        }
            estudianteServicio.guardarEstudiante(estudiante);
            return "redirect:/estudiantes";
    }
    //Actualizar Estudiante
    @GetMapping("/editar-estudiante/{id}")
    public String actualizarEstudiante(@PathVariable long id, Model model){
        Optional<Estudiante> estudiante=estudianteServicio.buscarEstudianteId(id);
        model.addAttribute("estudiante",estudiante);
        return "pages/registroEstudiante";
    }
    //eliminar estudiante
    @GetMapping("/eliminar-estudiante/{id}")
    public String eliminarEstudiante(@PathVariable long id){
        estudianteServicio.eliminarEstudiante(id);
        return "redirect:/estudiantes";
    }

}
