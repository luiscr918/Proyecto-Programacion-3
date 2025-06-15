package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.Materia;
import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.repository.TareaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TareaService {
    @Autowired
    private TareaRepository tareaRepository;

    //Mostrar todos las tareas
    public List<Tarea> mostrarTareas(){
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
    //Obtener tarea con sus registros
    @Transactional
    public Tarea ObtenerTareaConRegistros(Long id){
        Tarea tarea=tareaRepository.findById(id).orElseThrow();
        return tarea;
    }

}
