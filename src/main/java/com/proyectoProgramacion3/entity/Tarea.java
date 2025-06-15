package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Tarea {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //relacion de tarea con registro de tareas
    @OneToMany(mappedBy = "tarea",fetch = FetchType.LAZY)
    private List<RegistroTarea> registroTareas;
    //relacion de tarea con materia
    @ManyToOne
    @JoinColumn(name = "materia_id")
    private Materia materia;

    private String titulo;
    private String descripcion;
}
