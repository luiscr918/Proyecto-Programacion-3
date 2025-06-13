package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_curso;

    @NotBlank(message = "Debe seleccionar un nombre de curso")
    @Column(length = 45)
    private String nombreCurso;

    @NotBlank(message = "Debe seleccionar un nivel")
    private String nivel;

    @NotBlank(message = "Debe seleccionar un paralelo")
    private String paralelo;

    @NotBlank(message = "Debe escribir el año lectivo")
    private String anioLectivoCurso;


    //Relacion con materia
    @OneToMany(mappedBy = "curso")
    private List<Materia> materias;

    //Relacion con admin
    @ManyToOne
    @JoinColumn( name = "admin_idAdmin")
    private  Admin admin;




















    public Long getId_curso() {
        return id_curso;
    }

    public void setId_curso(Long id_curso) {
        this.id_curso = id_curso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getParalelo() {
        return paralelo;
    }

    public void setParalelo(String paralelo) {
        this.paralelo = paralelo;
    }

    public String getAnioLectivoCurso() {
        return anioLectivoCurso;
    }

    public void setAnioLectivoCurso(String anioLectivoCurso) {
        this.anioLectivoCurso = anioLectivoCurso;
    }
}
