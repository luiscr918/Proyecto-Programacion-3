package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {
    @Autowired
    private MateriaRepository materiaRepository;
    //mostrar todas las materias
    public List<Materia> mostrarMaterias(){

        return materiaRepository.findAll();
    }


    // Buscar por ID
    public Optional<Materia> buscarMateriaPorId(Long id) {
        return materiaRepository.findById(id);
    }

    // Guardar materia
    public Materia guardarMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    // Eliminar materia por ID
    public void eliminarMateria(Long id) {
        materiaRepository.deleteById(id);
    }
    //Listar materias que no estan asignadas a un curso
    public List<Materia> mostrarMateriasSinCurso(){
        return materiaRepository.findByCursoIsNull();
    }
}

