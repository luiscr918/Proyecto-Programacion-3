package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Materia {

    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //relacion de materia  con curso
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    //relacion de materia con docente
    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Docente docente;
    //relacion de materia con tarea
    @OneToMany(mappedBy = "materia",fetch = FetchType.LAZY)
    private List<Tarea> tareas;

    private String nombre;
}
