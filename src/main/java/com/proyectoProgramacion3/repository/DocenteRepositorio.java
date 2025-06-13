package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DocenteRepositorio extends JpaRepository<Docente,Long> {

    List<Docente> findByCedulaContainingIgnoreCase(String cedula);
    //Validar si existe un estudiante por cedula
    Optional<Docente> findByCedula(String cedula);
    //Validar si existe un estudiante por correo
    Optional<Docente> findByEmail(String correo);
}

