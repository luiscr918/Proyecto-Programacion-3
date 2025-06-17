package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    //Buscar curso por id pero en lista
    List<Curso> findByAnioLectivoContainingIgnoreCase(String anioLectivo);
}
