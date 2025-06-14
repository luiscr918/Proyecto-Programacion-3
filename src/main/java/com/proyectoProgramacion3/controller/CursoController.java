package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.*;
import com.proyectoProgramacion3.service.CursoService;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.MateriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CursoController {
    @Autowired
    private CursoService cursoService;
    @Autowired
    private EstudianteServicio estudianteServicio;
    @Autowired
    private MateriaService materiaService;

    // Listar cursos
    @GetMapping("/cursos")
    public String listarCursos( Model model) {
        model.addAttribute("cursos", cursoService.mostrarCursos());
        return "pages/vistaDocente";
    }

    // Mostrar formulario de registro
    @GetMapping("/formularioCurso")
    public String formularioCurso(Model model) {
        List<Estudiante> estudiantesSinCurso= estudianteServicio.mostrarEstudiantesSinCurso();
        List<Materia> materiasSinCurso=materiaService.mostrarMateriasSinCurso();
        model.addAttribute("materiasSinCurso",materiasSinCurso);
        model.addAttribute("estudiantesSinCurso",estudiantesSinCurso);
        model.addAttribute("curso", new Curso());
        return "pages/cursoPage/registroCurso";
    }

    //Guardar Curso datos
    @PostMapping("/guardarCurso")
    public String guardarCurso(Curso curso,
                               @RequestParam(value = "idsEstudiantes", required = false) List<Long> idsEstudiantes,
                               @RequestParam(value = "idsMaterias", required = false) List<Long> idsMaterias,
                               Model model) {
        Curso cursoGuardado = cursoService.guardarCurso(curso);
        // Asocia estudiantes
        if (idsEstudiantes != null) {
            for (Long idEstudiante : idsEstudiantes) {
                Optional<Estudiante> estudianteOptional = estudianteServicio.buscarEstudianteId(idEstudiante);
                Estudiante est=estudianteOptional.get();
                est.setCurso(cursoGuardado);
                estudianteServicio.guardarEstudiante(est);
            }
        }

        // Asocia materias
        if (idsMaterias != null) {
            for (Long idMateria : idsMaterias) {
                Optional<Materia> materiaOptional = materiaService.buscarMateriaPorId(idMateria);
                Materia materia=materiaOptional.get();
                materia.setCurso(cursoGuardado);
                materiaService.guardarMateria(materia);
            }
        }
        model.addAttribute("curso", cursoGuardado);
        return "redirect:/docentes";
    }

    //actualizar
    @GetMapping("editarCurso/{id}")
    public String actualizarCurso(@PathVariable Long id, Model model){
        Optional<Curso> optionalCurso= cursoService.buscarCursoPorId(id);
        if (optionalCurso.isPresent()) {
            model.addAttribute("curso", optionalCurso.get());
            return "pages/registroDocente";
        } else {
            return "redirect:/docentes";
        }
    }

    // Eliminar
    @GetMapping("/eliminarCurso/{id}")
    public String eliminarCurso(@PathVariable Long id) {
        cursoService.eliminarCurso(id);
        return "redirect:/docentes";
    }
}
