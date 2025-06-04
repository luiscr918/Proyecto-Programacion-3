package com.proyectoProgramacion3.services;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.repository.DocenteRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServices {

    @Autowired
    private DocenteRepositorio docenteRepositorio;

    // Guardar docente
    public Docente guardarDocente(Docente docente) {
        return docenteRepositorio.save(docente);
    }

    // Eliminar docente por ID
    public void eliminarDocente(Long id) {
        docenteRepositorio.deleteById(id);
    }

}
