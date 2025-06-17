package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.entity.Usuario;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    //Insertar Estudiantes admin
    @GetMapping("/formulario-estudiante")
    public String formularioEstudiante(Model model){
        model.addAttribute("estudiante",new Estudiante());
        return "pages/registroEstudiante";
    }
    //guardar estudiantes como admin
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
        estudiante.setRol("ESTUDIANTE");
        estudianteServicio.guardarEstudiante(estudiante);
        return "redirect:/estudiantes";
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
        estudiante.setRol("ESTUDIANTE");
        estudianteServicio.guardarEstudiante(estudiante);
        redirectAttributes.addFlashAttribute("mensajeExito", true);
        return "redirect:/index"; // Redirigir para que no se vuelva a enviar el formulario al recargar
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
    //simulador de inicio sesion estudiante
    @GetMapping("/inicioEstudiante")
    public String simuladorIcEstudiante(Model model) {
        model.addAttribute("estudiantes",estudianteServicio.mostrarEstudiantes());
        return "pages/EstudiantePag/simSesionEstudiante";
    }

    // Obtener materias por estudiante
    @GetMapping("/materiasPorEstudiante/{id}")
    public String materiasPorEstudiante(@PathVariable Long id, Model model) {
        Estudiante estudiante = estudianteServicio.ObtenerEstudianteConMaterias(id);

        // Validar si el estudiante tiene curso asignado
        if (estudiante.getCurso() == null) {
            return "index"; // Redirige al index
        }
        List<Materia> materiasEstudiante = estudiante.getCurso().getMaterias();
        model.addAttribute("materiasEstudiante", materiasEstudiante);
        model.addAttribute("estudiante",estudiante);
        return "pages/EstudiantePag/listaMateriasEstudiante";
    }

}
