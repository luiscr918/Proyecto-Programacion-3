package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Curso {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //relacion de curso con admin
    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Admin admin;
    //relacion de curso con matera
    @OneToMany(mappedBy = "curso",fetch = FetchType.LAZY)
    private List<Materia> materias;
    //relacion de curso con estudiante
    @OneToMany(mappedBy = "curso",fetch = FetchType.LAZY)
    private List<Estudiante> estudiantes;

    private String nombre;
    private String nivel;
    private String paralelo;
    private String anioLectivo;

}
