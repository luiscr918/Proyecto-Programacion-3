package com.proyectoProgramacion3.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.service.MateriaServices;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class MateriaController {

    @Autowired
    private MateriaServices materiaServices;

    @GetMapping("/buscarMateriaPorId")
    public String buscarMateriaPorId(@RequestParam(name = "idMateria", required = false) Long idMateria,
                                     Model model) {
        if (idMateria != null) {
            Optional<Materia> materiaOpt = materiaServices.buscarMateriaPorId(idMateria);
            if (materiaOpt.isPresent()) {
                model.addAttribute("materiaEncontrada", materiaOpt.get());
            } else {
                model.addAttribute("mensajeNoEncontrado", "No se encontró una materia con el ID: " + idMateria);
            }
        }
        return "pages/vistaDocente";
    }





}

