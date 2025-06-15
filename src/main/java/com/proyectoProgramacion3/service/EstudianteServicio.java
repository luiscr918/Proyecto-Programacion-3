package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.entity.Estudiante;
import com.proyectoProgramacion3.repository.EstudianteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServicio {
    @Autowired
    private EstudianteRepository estudianteRepository; //instancio mi repositorio

    //Listar los estudiantes
    public List<Estudiante> mostrarEstudiantes(){
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
    //Listar estudiantes sin curso
    public List<Estudiante> mostrarEstudiantesSinCurso(){
        return estudianteRepository.findByCursoIsNull();
    }
    //Obtener numero total de estudiantes
    public int obtenerNumeroEstudiantes(){
        return estudianteRepository.findAll().size();
    }
    //Obtener estudiante con sus materias
    @Transactional
    public Estudiante ObtenerEstudianteConMaterias(Long id){
        Estudiante estudiante=estudianteRepository.findById(id).orElseThrow();
        return estudiante;
    }
    //Obtener estudiante con sus registro de entrega
    @Transactional
    public Estudiante ObtenerEstudianteConRegistro(Long id){
        Estudiante estudiante=estudianteRepository.findById(id).orElseThrow();
        return estudiante;
    }



}
