package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.service.EstudianteServicio;
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
                                    BindingResult bindingResult,Model model){
        if (estudianteServicio.existePorCedula(estudiante.getCedula())){
             bindingResult.rejectValue("cedula","error.cedula","Ya existe un estudiante con esta cedula");
        }
        if (estudianteServicio.existePorCorreo(estudiante.getCorreo())){
            bindingResult.rejectValue("correo","error.correo","Ya existe un estudiante con este correo");
        }
        if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "pages/registroEstudiante";
        }else{
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

}
