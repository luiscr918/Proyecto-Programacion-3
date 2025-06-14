package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    //Buscar por Cedula
    List<Estudiante> findByCedulaContainingIgnoreCase(String cedula);
    //metodo para filtrar en una lista mis estudiantes que no tengan asignado un curso
    List<Estudiante> findByCursoIsNull();
}
