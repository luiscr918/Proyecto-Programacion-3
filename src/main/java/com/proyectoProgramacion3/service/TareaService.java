package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    //Mostrar todos los productos
    public List<Tarea> mostrarTarea(){
        return  tareaRepository.findAll();
    }

    //Buscar por id
    public Optional<Tarea> bucarTareaPorId(Long id){
        return tareaRepository.findById(id);
    }

    //Guardar
    public Tarea  guardarTarea(Tarea tarea){
        return tareaRepository.save(tarea);
    }

    //Eliminar
    public void eliminarTarea(Long id){
        tareaRepository.deleteById(id);
    }


    //*****Busqueda doble asociado con estudiante
    public List<Tarea> buscarPorEstudianteYMateria(Long estudianteId, Long materiaId) {
        return tareaRepository.findByEstudianteIdAndMateriaId(estudianteId, materiaId);
    }


    //*****Asociado con docente - materia
    public List<Tarea> obtenerTareasPorMateriaId(Long materiaId) {
        return tareaRepository.findByMateriaId(materiaId);
    }


}
