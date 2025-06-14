package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.MateriaServices;
import com.proyectoProgramacion3.service.TareaService;
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
    private TareaService tareaService;

    @Autowired
    private MateriaServices materiaServices;

    //Mostrar todos los estudiantes y filtro doble
    @GetMapping("/estudiantes")
    public String listarTareasPorEstudianteYMateria(
            @RequestParam(name = "estudianteId", required = false) Long estudianteId,
            @RequestParam(name = "materiaId", required = false) Long materiaId,
            @RequestParam(name = "buscarEstudiante", required = false) String buscarEstudiante,
            Model model) {

        // Aplica el filtro por cédula si se proporciona
        List<Estudiante> estudiantes = estudianteServicio.buscarPorCedula(buscarEstudiante);
        List<Tarea> tareasFiltradas = tareaService.buscarPorEstudianteYMateria(estudianteId, materiaId);

        model.addAttribute("estudiantes", estudiantes);
        model.addAttribute("tareas", tareasFiltradas);
        model.addAttribute("estudianteId", estudianteId);
        model.addAttribute("materiaId", materiaId);
        model.addAttribute("buscarEstudiante", buscarEstudiante);

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

        Optional<Estudiante> existentePorCedula = estudianteServicio.obtenerPorCedulaExacta(estudiante.getCedula());
        if (existentePorCedula.isPresent() && !existentePorCedula.get().getId().equals(estudiante.getId())) {
            bindingResult.rejectValue("cedula", "error.cedula", "Ya existe un estudiante con esta cédula");
        }

        Optional<Estudiante> existentePorCorreo = estudianteServicio.obtenerPorCorreoExacto(estudiante.getCorreo());
        if (existentePorCorreo.isPresent() && !existentePorCorreo.get().getId().equals(estudiante.getId())) {
            bindingResult.rejectValue("correo", "error.correo", "Ya existe un estudiante con este correo");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/registroEstudiante";
        } else {
            estudianteServicio.guardarEstudiante(estudiante);
            return "redirect:/estudiantes";
        }
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



    //***********ASOCIADO CON ESTUDIANTE PARA LA ENTREGA DE TAREAS

    @GetMapping("/formularioEstudianteEntrega/{id}")
    public String mostrarFormularioEditarEntrega(@PathVariable Long id, Model model) {
        Optional<Tarea> tarea = tareaService.bucarTareaPorId(id);
        model.addAttribute("tarea", tarea.orElse(new Tarea()));
        model.addAttribute("materias", materiaServices.mostrarMaterias());
        model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
        return "pages/formularioEstudianteTarea";
    }

    @PostMapping("/guardarTareaEstudiante")
    public String guardarTareaEstudiante(@Valid @ModelAttribute Tarea tarea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("materias", materiaServices.mostrarMaterias());
            model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
            return "pages/formularioEstudianteTarea";
        }
        tarea.setEstadoTarea("Entregado");
        tareaService.guardarTarea(tarea);
        return "redirect:/estudiantes";
    }





}
