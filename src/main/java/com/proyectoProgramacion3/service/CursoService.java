package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Curso;
import com.proyectoProgramacion3.entity.Docente;
import com.proyectoProgramacion3.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;


    //mostrar los cursos
    public List<Curso> mostrarCursos(){

        return cursoRepository.findAll();
    }


    // Buscar por ID
    public Optional<Curso> buscarCursoPorId(Long id) {
        return cursoRepository.findById(id);
    }

    // Guardar curso
    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    // Eliminar curso por ID
    public void eliminarCurso(Long id) {
        cursoRepository.deleteById(id);
    }
    //obtener numero de cursos totales:
    public int obtenerNumeroCursos(){
        return cursoRepository.findAll().size();
    }
    //buscar curso por anio lectivo
    public List<Curso> buscarCursoAnioLectivo(String buscarCurso){
        if (buscarCurso==null||buscarCurso.isEmpty()){
            return cursoRepository.findAll();
        }else{
            return cursoRepository.findByAnioLectivoContainingIgnoreCase(buscarCurso);
        }
    }

}
