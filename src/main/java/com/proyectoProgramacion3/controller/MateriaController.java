package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.service.DocenteServices;
import com.proyectoProgramacion3.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MateriaController {
    @Autowired
    private MateriaService materiaService;
    @Autowired
    private DocenteServices docenteServices;
    // Listar materias
    @GetMapping("/materias")
    public String listarMaterias( Model model) {
        model.addAttribute("materias", materiaService.mostrarMaterias());
        return "pages/Materia/listaMateria";
    }

    // Mostrar formulario de registro
    @GetMapping("/formularioMateria")
    public String formularioMateria(Model model) {
        List<Docente> docentes=docenteServices.mostrarLibros();
        model.addAttribute("docentes",docentes);
        model.addAttribute("materia", new Materia());
        return "pages/Materia/registroMateria";
    }

    //Guardar Materia datos
    @PostMapping("/guardarMateria")
    public String guardarMateria(Materia materia, Model model) {

        // Validar si el curso está vacío o tiene ID nulo
        if (materia.getCurso() != null && materia.getCurso().getId() == null) {
            materia.setCurso(null);
        }

        materiaService.guardarMateria(materia);
        return "redirect:/materias";
    }

    //actualizar
    @GetMapping("/editarMateria/{id}")
    public String actualizarMateria(@PathVariable Long id, Model model){
        Optional<Materia> optionalMateria= materiaService.buscarMateriaPorId(id);
        if (optionalMateria.isPresent()) {
            model.addAttribute("materia", optionalMateria.get());
            return "pages/Materia/registroActualizarMateria";
        } else {
            return "redirect:/docentes";
        }
    }

    // Eliminar
    @GetMapping("/eliminarMateria/{id}")
    public String eliminarMateria(@PathVariable Long id) {
        materiaService.eliminarMateria(id);
        return "redirect:/docentes";
    }
}
