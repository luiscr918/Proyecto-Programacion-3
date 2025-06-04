package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.service.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class EstudianteController {
    //Instancio mi servicio
    @Autowired
    private EstudianteServicio estudianteServicio;

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
    public String guardarEstudiante(Estudiante estudiante){
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
    //eliminar LIBRO
    @GetMapping("eliminar-libro/{id}")
    public String eliminarEstudiante(@PathVariable long id){
        estudianteServicio.eliminarEstudiante(id);
        return "redirect/estudiantes";
    }

}
