package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Estudiante extends Usuario{
    //relacion de estudiante con registro de tareas
    @OneToMany(mappedBy = "estudiante")
    private List<RegistroTareas> resgistroTareas;
    //relacion de estudiante con curso
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener exactamente 10 dígitos")
    @Column(name = "telefono")
    private String telefono;

}
