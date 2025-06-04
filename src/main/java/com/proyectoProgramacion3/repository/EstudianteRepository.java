package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    //Buscar por Cedula
    List<Estudiante> finByCedulaContainingIgnoreCase(String cedula);

    //Buscar por Email
   // List<Estudiante> findByEmailContiningIgnoreCase(String correo);
}
