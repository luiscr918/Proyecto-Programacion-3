package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.repository.DocenteRepositorio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteServices {

    @Autowired
    private DocenteRepositorio docenteRepositorio;
    @Autowired
    private PasswordEncoder passwordEncoder;

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
        //encripto
        String passwordEncriptado=passwordEncoder.encode(docente.getPassword());
        //añado el encriptado al objeto
        docente.setPassword(passwordEncriptado);
        return docenteRepositorio.save(docente);
    }

    // Eliminar docente por ID
    public void eliminarDocente(Long id) {
        docenteRepositorio.deleteById(id);
    }
    //obtener numero total de docentes
    public int obtenerNumeroDocentes(){
        return docenteRepositorio.findAll().size();
    }
    //Obtener docente con sus materias
    @Transactional
    public Docente ObtenerDocenteConMaterias(Long id){
        Docente docente=docenteRepositorio.findById(id).orElseThrow();
        return docente;
    }

}
