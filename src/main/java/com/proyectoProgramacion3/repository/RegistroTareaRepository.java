package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.RegistroTarea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroTareaRepository extends JpaRepository<RegistroTarea,Long> {
}
