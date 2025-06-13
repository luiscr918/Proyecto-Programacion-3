package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Debe seleccionar el nombre de la materia")
    private String nombreMateria;


    //Conexion con curso
    @ManyToOne
    @JoinColumn( name = "curso_idCurso")
    private  Curso curso;

    //Conexion con docente
    @ManyToOne
    @JoinColumn( name = "docente_idDocente")
    private  Docente docente;


    //Relacion con tarea
    @OneToMany(mappedBy = "materia")
    private List<Tarea> tareas;


    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public Long getIdMateria() {
        return id;
    }

    public void setIdMateria(Long idMateria) {
        this.id = idMateria;
    }
}
