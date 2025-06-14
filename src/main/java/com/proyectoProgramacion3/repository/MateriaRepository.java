package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository  extends JpaRepository<Materia,Long> {
    //devolver lista de materias que no estan  asignadas a un curso
    List<Materia> findByCursoIsNull();
}
