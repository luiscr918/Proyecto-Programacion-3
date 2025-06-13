package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    //Validar si existe un estudiante por cedula
    Optional<Usuario> findByCedula(String cedula);
    //Validar si existe un estudiante por correo
    Optional<Usuario> findByEmail(String email);
}
