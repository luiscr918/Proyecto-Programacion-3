package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.*;
import com.proyectoProgramacion3.service.DocenteServices;
import com.proyectoProgramacion3.service.MateriaService;
import com.proyectoProgramacion3.service.TareaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Controller
public class TareaController {
@Autowired
private TareaService tareaService;
@Autowired
private MateriaService materiaService;
@Autowired
private DocenteServices docenteServices;

    // Mostrar todas las tareas por materia
    @GetMapping("/tareasPorMateria/{materiaId}")
    public String listarTareasPorMateria(@PathVariable Long materiaId, Model model) {
            Materia materia=materiaService.ObtenerMateriaConTareas(materiaId);
            List<Tarea> tareasCurso=materia.getTareas();
            model.addAttribute("tareasCurso", tareasCurso);
            model.addAttribute("materia", materia);
            return "pages/DocentePag/listaTareasMateria";
    }

    // Mostrar formulario para nueva tarea
    @GetMapping("/formularioTarea/{docenteId}")
    public String mostrarFormularioTarea(@PathVariable Long docenteId, Model model) {
        Docente docente =docenteServices.ObtenerDocenteConMaterias(docenteId);
        List<Materia> materiasDocente=docente.getMaterias();
        model.addAttribute("materiasDocente",materiasDocente);
        model.addAttribute("tarea", new Tarea());
        return "pages/DocentePag/asignacionTareas";
    }

    @PostMapping("/guardarTarea")
    public String guardarTarea(@Valid @ModelAttribute Tarea tarea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "pages/DocentePag/asignacionTareas"; //mencionar error a abne
        }
        tareaService.guardarTarea(tarea);
        model.addAttribute("docentes", docenteServices.mostrarLibros());
        return "pages/DocentePag/simSesionDocente";
    }

    // Editar tarea existente
    @GetMapping("/editarTarea/{id}")
    public String editarTarea(@PathVariable Long id, Model model) {
        Optional<Tarea> tarea = tareaService.bucarTareaPorId(id);
        Tarea tareaObIdDocente= tarea.get();
        Docente docente=tareaObIdDocente.getMateria().getDocente();
        List<Materia> materiasDocente=docente.getMaterias();
        model.addAttribute("tarea",tarea);
        model.addAttribute("materiasDocente",materiasDocente);
        return "pages/DocentePag/asignacionTareas";
    }

    // Eliminar tarea
    @GetMapping("/eliminarTarea/{id}")
    public String eliminarTarea(@PathVariable Long id,Model model) {
        tareaService.eliminarTarea(id);

        model.addAttribute("docentes", docenteServices.mostrarLibros());
        return "pages/DocentePag/simSesionDocente";
    }
}
