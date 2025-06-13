package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {
    @Autowired
    private EstudianteRepository estudianteRepository; //instancio mi repositorio

    //Listar los estudiantes
    public List<Estudiante> mostrarEdudiantes(){
        return estudianteRepository.findAll();
    }
    //Mostrar por cedula
    public List<Estudiante> buscarPorCedula(String buscarEstudiante){
        if (buscarEstudiante==null||buscarEstudiante.isEmpty()){
            return estudianteRepository.findAll();
        }else{
            return estudianteRepository.findByCedulaContainingIgnoreCase(buscarEstudiante);
        }
    }
    //Buscar por id
    public Optional<Estudiante> buscarEstudianteId(Long id){
        return estudianteRepository.findById(id);
    }
    //Guardar Libro
    public Estudiante guardarEstudiante(Estudiante estudiante){
        return estudianteRepository.save(estudiante);
    }
    //Eliminar Libro
    public void eliminarEstudiante(Long id){
         estudianteRepository.deleteById(id);
    }

//VALIDAR SI EXISTE POR CEDULA
    public Optional<Estudiante> obtenerPorCedulaExacta(String cedula){
        return estudianteRepository.findByCedula(cedula);
    }
//VALIDAR SI EXISTE PRO CORREO
    public Optional<Estudiante> obtenerPorCorreoExacto(String correo){
        return estudianteRepository.findByCorreo(correo);
    }

}
