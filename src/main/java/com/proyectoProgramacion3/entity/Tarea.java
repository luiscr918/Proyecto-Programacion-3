package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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

    private String nombre;
    private String descripcion;
    private String tipo;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private Date fechaEntrega;

}
