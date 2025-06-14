package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.service.CursoService;
import com.proyectoProgramacion3.service.DocenteServices;
import com.proyectoProgramacion3.service.EstudianteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    @Autowired
    private EstudianteServicio estudianteServicio;

    @Autowired
    private DocenteServices docenteServices;

    @Autowired

    private CursoService cursoService;


    @GetMapping("/admin")
    public String mostrarVentanaAdmin(@RequestParam(name = "buscarCurso", required = false,defaultValue = "")
                                          String buscarCurso, Model model){
        model.addAttribute("totalEstudiantes",estudianteServicio.obtenerNumeroEstudiantes());
        model.addAttribute("totalDocentes",docenteServices.obtenerNumeroDocentes());
        model.addAttribute("totalCursos",cursoService.obtenerNumeroCursos());
        //cursos para listar en el homeAdmin
        List<Curso> cursos = cursoService.buscarCursoAnioLectivo(buscarCurso);
        model.addAttribute("cursos", cursos);
        model.addAttribute("buscarCurso",buscarCurso);
        return "pages/AdminPag/homeAdmin";
    }
}
