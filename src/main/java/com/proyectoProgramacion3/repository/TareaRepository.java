package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
    List<Tarea> findByEstudianteIdAndMateriaId(Long estudianteId, Long materiaId);
}
