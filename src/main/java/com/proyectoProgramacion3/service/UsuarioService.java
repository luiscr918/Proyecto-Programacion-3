package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.entity.Usuario;
import com.proyectoProgramacion3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
@Autowired
private UsuarioRepository usuarioRepository;

    //VALIDAR SI EXISTE POR CEDULA
    public Optional<Usuario> obtenerPorCedulaExacta(String cedula){
        return usuarioRepository.findByCedula(cedula);
    }
    //VALIDAR SI EXISTE PRO CORREO
    public Optional<Usuario> obtenerPorEmailExacto(String email){
        return usuarioRepository.findByEmail(email);
    }
}
