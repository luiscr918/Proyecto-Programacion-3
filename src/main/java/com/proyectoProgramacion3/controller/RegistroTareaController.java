package com.proyectoProgramacion3.controller;

import com.proyectoProgramacion3.entity.*;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.RegistroTareaService;
import com.proyectoProgramacion3.service.TareaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/registroTarea")
public class RegistroTareaController {
    @Autowired
    private RegistroTareaService registroTareaService;
    @Autowired
    private TareaService tareaService;
    @Autowired
    private EstudianteServicio estudianteServicio;
    //mostrar todos los registros por tarea
    @GetMapping("/registrosTareas/{idTarea}")
    public String mostrarRegistrosPorTarea(@PathVariable Long idTarea, Model model){
        Tarea tarea=tareaService.ObtenerTareaConRegistros(idTarea);
        List<RegistroTarea> registroTareas=tarea.getRegistroTareas();
        model.addAttribute("registroTareas",registroTareas);
        return "pages/DocentePag/listaRegistroTareas";
    }
    // Mostrar formulario para nueva tarea
    @GetMapping("/formularioRegistroTarea/{estudianteId}/{tareaId}")
    public String mostrarFormularioTarea(@PathVariable Long estudianteId,@PathVariable Long tareaId, Model model) {
        Optional<Estudiante> estudianteOptional=estudianteServicio.buscarEstudianteId(estudianteId);
        Optional<Tarea> tareaOptional=tareaService.bucarTareaPorId(tareaId);
        Estudiante estudiante=estudianteOptional.get();
        Tarea tarea=tareaOptional.get();
        RegistroTarea registroTarea = new RegistroTarea();
        registroTarea.setEstudiante(estudiante);
        registroTarea.setTarea(tarea);
        model.addAttribute("registroTarea",registroTarea);
        model.addAttribute("tarea",tarea );
        return "pages/EstudiantePag/formRegistroTarea";
    }
//guardar Entrega ESTUDIANTE
    @PostMapping("/guardarRegistroTarea")
    public String guardarRegistroTarea(RegistroTarea registroTarea, Model model,
                                       @RequestParam("archivo") MultipartFile archivo) throws IOException {

        if (registroTarea.getEstado()==null || registroTarea.getEstado().isEmpty()) {
            registroTarea.setEstado("ENTREGADO");
        }else{
            registroTarea.setEstado("CALIFICADO");
        }
        if (!archivo.isEmpty()) {
            registroTarea.setArchivoTarea(archivo.getBytes()); // guarda el contenido binario
        }
        registroTareaService.guardarRegistroTarea(registroTarea);
        model.addAttribute("estudiantes", estudianteServicio.mostrarEstudiantes());
        return "redirect:/estudiante/inicioEstudiante";
    }

    //guardar Entrega DOCENTE
    @PostMapping("/guardarRegistroTareaDocente")
    public String guardarRegistroTareadocente(RegistroTarea registroTarea, Model model)  {

        if (registroTarea.getEstado()==null || registroTarea.getEstado().isEmpty()) {
            registroTarea.setEstado("ENTREGADO");
        }else{
            registroTarea.setEstado("CALIFICADO");
        }
        registroTareaService.guardarRegistroTarea(registroTarea);
        return "redirect:/docente/inicioDocente";
    }
    // Editar registro existente estudiante
    @GetMapping("/editarRegistro/{id}")
    public String editarRegistro(@PathVariable Long id, Model model) {
        Optional<RegistroTarea> registroTareaOp = registroTareaService.bucarRegistroTareaPorId(id);
        RegistroTarea registroTarea= registroTareaOp.get();
        Estudiante estudiante=registroTarea.getEstudiante();
        Tarea tarea=registroTarea.getTarea();
        registroTarea.setEstudiante(estudiante);
        registroTarea.setTarea(tarea);
        model.addAttribute("tarea",tarea);
        model.addAttribute("registroTarea",registroTarea);
        return "pages/EstudiantePag/formRegistroTarea";
    }
    // Editar registro existente DOCENTE
    @GetMapping("/editarRegistroDocente/{id}")
    public String editarRegistroDocente(@PathVariable Long id, Model model) {
        Optional<RegistroTarea> registroTareaOp = registroTareaService.bucarRegistroTareaPorId(id);
        RegistroTarea registroTarea= registroTareaOp.get();
        Estudiante estudiante=registroTarea.getEstudiante();
        Tarea tarea=registroTarea.getTarea();
        registroTarea.setEstudiante(estudiante);
        registroTarea.setTarea(tarea);
        model.addAttribute("tarea",tarea);
        model.addAttribute("registroTarea",registroTarea);
        return "pages/DocentePag/paginaCalificarEntregable";
    }




    //mostrar todos los registros por estudiante
    @GetMapping("/registrosTareasEstudiante/{idEstudiante}")
    public String mostrarRegistrosPorEstudiante(@PathVariable Long idEstudiante, Model model){
        Estudiante estudiante=estudianteServicio.ObtenerEstudianteConRegistro(idEstudiante);
        List<RegistroTarea> registroTareas=estudiante.getResgistroTareas();
        model.addAttribute("registroTareas",registroTareas);
        return "pages/EstudiantePag/entregasEstudiante";
    }
    @GetMapping("/verArchivo/{id}")
    public void verArchivo(@PathVariable Long id, HttpServletResponse response) throws IOException {
        RegistroTarea registro = registroTareaService.bucarRegistroTareaPorId(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        response.setContentType("application/pdf");
        response.getOutputStream().write(registro.getArchivoTarea());
        response.getOutputStream().flush();
    }

}
