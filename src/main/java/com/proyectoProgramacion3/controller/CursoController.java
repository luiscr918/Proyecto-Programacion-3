package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Usuario;
import com.proyectoProgramacion3.service.CursoService;
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

    // Listar cursos
    @GetMapping("/cursos")
    public String listarCursos( Model model) {
        model.addAttribute("cursos", cursoService.mostrarCursos());
        return "pages/vistaDocente";
    }




    // Mostrar formulario de registro
    @GetMapping("/formularioCurso")
    public String formularioCurso(Model model) {
        model.addAttribute("curso", new Curso());
        return "pages/formularioDocente";
    }

    //Guardar Docente datos
    @PostMapping("/guardarCurso")
    public String guardarCurso(Curso curso, Model model) {

        Curso cursoGuardado = cursoService.guardarCurso(curso);
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
