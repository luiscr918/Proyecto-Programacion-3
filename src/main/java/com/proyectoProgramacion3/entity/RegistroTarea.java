package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class RegistroTarea {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //relacion de registro con estudiante
    @ManyToOne
    @JoinColumn(name = "estudiante_id")
    private Estudiante estudiante;
    //relacion de registro con tarea
    @ManyToOne
    @JoinColumn(name = "tarea_id")
    private Tarea tarea;


    private String estado;

    private Double nota;

    private String comentario;

    @Lob
    @Column(name = "archivo_tarea", columnDefinition = "MEDIUMBLOB")
    private byte[] archivoTarea;

}
