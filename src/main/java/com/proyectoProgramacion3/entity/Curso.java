package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    private String nombre;
    private String nivel;
    private String paralelo;
    private String anioLectivo;

}
