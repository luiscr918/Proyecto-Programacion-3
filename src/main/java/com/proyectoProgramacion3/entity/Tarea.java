package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_tarea;

    private double nota;
    private String tareaEntregada;
    private String pauta;
    private String tipoTarea;
    private String comentario;


    //Relacion con materia
    @ManyToOne
    @JoinColumn( name = "materia_idMateria")
    private  Materia materia;

    //Relacion con estudiante
    @ManyToOne
    @JoinColumn( name = "estudiante_idEstudiante")
    private  Estudiante estudiante;


    public Long getId_tarea() {
        return id_tarea;
    }

    public void setId_tarea(Long id_tarea) {
        this.id_tarea = id_tarea;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getTareaEntregada() {
        return tareaEntregada;
    }

    public void setTareaEntregada(String tareaEntregada) {
        this.tareaEntregada = tareaEntregada;
    }

    public String getPauta() {
        return pauta;
    }

    public void setPauta(String pauta) {
        this.pauta = pauta;
    }

    public String getTipoTarea() {
        return tipoTarea;
    }

    public void setTipoTarea(String tipoTarea) {
        this.tipoTarea = tipoTarea;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}
