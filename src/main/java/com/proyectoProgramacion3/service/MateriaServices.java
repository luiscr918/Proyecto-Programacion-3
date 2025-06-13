package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaServices {
    @Autowired
    private MateriaRepository materiaRepository;

    // Mostrar todas las materias
    public List<Materia> mostrarMaterias() {
        return materiaRepository.findAll();
    }

    // Buscar materia por ID
    public Optional<Materia> buscarMateriaPorId(Long id) {
        return materiaRepository.findById(id);
    }

    // Guardar o actualizar una materia
    public Materia guardarMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    // Eliminar materia por ID
    public void eliminarMateria(Long id) {
        materiaRepository.deleteById(id);
    }
}

