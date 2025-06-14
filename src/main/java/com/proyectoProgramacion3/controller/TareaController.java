package com.proyectoProgramacion3.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.service.DocenteServices;
import com.proyectoProgramacion3.service.EstudianteServicio;
import com.proyectoProgramacion3.service.MateriaServices;
import com.proyectoProgramacion3.service.TareaService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @Autowired
    private DocenteServices docenteServices;

    // Mostrar todas las tareas
    @GetMapping("/tareas")
    public String listarTareas(@RequestParam(name = "buscarDocente", required = false, defaultValue = "")
                               String buscarDocente, Model model) {
        {
            List<Tarea> tareas = tareaService.mostrarTarea();
            model.addAttribute("tareas", tareas);
            List<Docente> docentes = docenteServices.buscarDocentePorCedula(buscarDocente);
            model.addAttribute("buscarDocente", buscarDocente);
            model.addAttribute("docentes", docentes);
            return "pages/vistaDocente";
        }
    }

    // Mostrar formulario para nueva tarea
    @GetMapping("/formularioTarea")
    public String mostrarFormularioTarea(Model model) {
        model.addAttribute("tarea", new Tarea());
        model.addAttribute("materias", materiaServices.mostrarMaterias());
        model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
        return "pages/formularioTarea";
    }



    @PostMapping("/guardarTarea")
    public String guardarTarea(@Valid @ModelAttribute Tarea tarea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("materias", materiaServices.mostrarMaterias());
            model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
            return "pages/formularioTarea";
        }

        tareaService.guardarTarea(tarea);
        return "redirect:/tareas";
    }


    @PostMapping("/guardarTareaCalificado")
    public String guardarTareaCalificadi(@Valid @ModelAttribute Tarea tarea, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("materias", materiaServices.mostrarMaterias());
            model.addAttribute("estudiantes", estudianteServicio.mostrarEdudiantes());
            return "pages/formularioCalificarTarea";
        }

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
        return "pages/formularioCalificarTarea";
    }

    // Eliminar tarea
    @GetMapping("/eliminarTarea/{id}")
    public String eliminarTarea(@PathVariable Long id) {
        tareaService.eliminarTarea(id);
        return "redirect:/tareas";
    }



    //**********ASOCIADO CON DOCENTE

    @GetMapping("/buscarTareasPorMateria")
    public String buscarTareasPorMateria(@RequestParam("idMateria") Long idMateria, Model model) {
        List<Tarea> tareas = tareaService.obtenerTareasPorMateriaId(idMateria);
        List<Docente> docentes = docenteServices.mostrarLibros();
        model.addAttribute("docentes", docentes);
        model.addAttribute("tareas", tareas);
        return "pages/vistaDocente";
    }






    //EN PDF POR DOCENTE
    @GetMapping("/tareas/pdf")
    public void exportarPDF(HttpServletResponse response) throws IOException, DocumentException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=tareas.pdf");

        List<Tarea> tareas = tareaService.mostrarTarea();

        Document documento = new Document();
        PdfWriter.getInstance(documento, response.getOutputStream());
        documento.open();

        documento.add(new Paragraph("Listado de Tareas"));
        documento.add(new Paragraph(" "));

        for (Tarea tarea : tareas) {
            documento.add(new Paragraph("ID: " + tarea.getId_tarea()));
            documento.add(new Paragraph("Descripción: " + tarea.getComentario()));
            documento.add(new Paragraph("Estado: " + tarea.getEstadoTarea()));
            documento.add(new Paragraph("Nota: " + (tarea.getNota())));
            documento.add(new Paragraph("Tipo: " + tarea.getTipoTarea()));
            documento.add(new Paragraph("Estudiante: " + (tarea.getEstudiante().getNombreCompleto())));
            documento.add(new Paragraph(" "));
        }

        documento.close();
    }
}
