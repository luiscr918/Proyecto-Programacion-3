package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Estudiante {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
@Column (unique = true)
@Size(min = 10)
    private String cedula;

    @Column (unique = true)
    private String correo;

    private String nombreCompleto;

    private String telefono;
    private String direccion;
    private String password;

}
