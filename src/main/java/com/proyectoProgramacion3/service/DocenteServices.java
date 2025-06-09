package com.proyectoProgramacion3.service;

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

    //mostrar los docentes
    public List<Docente> mostrarLibros(){

        return docenteRepositorio.findAll();
    }


    // Mostrar todos los docentes o buscar por cédula
    public List<Docente> buscarDocentePorCedula(String cedula) {
        if (cedula == null || cedula.isEmpty()) {
            return docenteRepositorio.findAll();
        } else {
            return docenteRepositorio.findByCedulaContainingIgnoreCase(cedula);
        }
    }

    // Buscar por ID
    public Optional<Docente> buscarDocentePorId(Long id) {
        return docenteRepositorio.findById(id);
    }

    // Guardar docente
    public Docente guardarDocente(Docente docente) {
        return docenteRepositorio.save(docente);
    }

    // Eliminar docente por ID
    public void eliminarDocente(Long id) {
        docenteRepositorio.deleteById(id);
    }
    //Validar si existe docente por cedula
    public boolean existePorCedula(String cedula){
        return docenteRepositorio.existsByCedula(cedula);
    }
    //Validar si existe docente por correo
    public boolean existePorEmail(String email){
        return docenteRepositorio.existsByEmail(email);
    }
}
