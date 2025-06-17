package com.proyectoProgramacion3.service;

import com.proyectoProgramacion3.entity.RegistroTarea;
import com.proyectoProgramacion3.entity.Tarea;
import com.proyectoProgramacion3.repository.RegistroTareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroTareaService {
    @Autowired
    private RegistroTareaRepository registroTareaRepository;

    //Mostrar todos los registro de tareas
    public List<RegistroTarea> mostrarRegistroTareas(){
        return  registroTareaRepository.findAll();
    }

    //Buscar por id
    public Optional<RegistroTarea> bucarRegistroTareaPorId(Long id){
        return registroTareaRepository.findById(id);
    }

    //Guardar
    public RegistroTarea  guardarRegistroTarea(RegistroTarea registroTarea){
        return registroTareaRepository.save(registroTarea);
    }

    //Eliminar
    public void eliminarRegistroTarea(Long id){
        registroTareaRepository.deleteById(id);
    }


}
