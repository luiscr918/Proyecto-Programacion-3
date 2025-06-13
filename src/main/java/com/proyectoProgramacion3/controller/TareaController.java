package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.MateriaServices;
import com.proyectoProgramacion3.service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class TareaController {

    @Autowired
    private TareaService tareaService;

    @Autowired
    private MateriaServices materiaServices;

    @Autowired
    private EstudianteServicio estudianteServicio;

    // Mostrar todas las tareas
    @GetMapping("/tareas")
    public String listarTareas(Model model) {
        List<Tarea> tareas = tareaService.mostrarTarea();
        model.addAttribute("tareas", tareas);
        return "pages/vistaDocente";
    }

    // Mostrar formulario para nueva tarea
    @GetMapping("/formularioTarea")
    public String mostrarFormularioTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("materias", materiaServices.mostrarMaterias()) ;
        model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
        return "pages/formularioTarea";
    }

    // Guardar tarea
    @PostMapping("/guardarTarea")
    public String guardarTarea(@ModelAttribute Tarea tarea) {
        tareaService.guardarTarea(tarea);
        return "redirect:/tareas";
    }

    // Editar tarea existente
    @GetMapping("/editarTarea/{id}")
    public String editarTarea(@PathVariable Long id, Model model) {
        List<Materia> materias = materiaServices.mostrarMaterias();
        model.addAttribute("materias", materias);
        model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
        Optional<Tarea> tarea = tareaService.bucarTareaPorId(id);
        model.addAttribute("tarea", tarea.orElse(new Tarea()));
        return "pages/formularioTarea";
    }

    // Eliminar tarea
    @GetMapping("/eliminarTarea/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }
}
