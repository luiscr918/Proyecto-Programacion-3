package com.proyectoProgramacion3.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Estudiante {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //cedula
    @NotBlank(message = "La cédula es obligatoria")
    @Column(unique = true)
    @Pattern(regexp = "\\d{7,10}", message = "La cédula debe tener entre 7 y 10 dígitos numéricos")
    private String cedula;
    //correo
    @NotBlank(message = "El email es obligatorio")
    @Column(unique = true)
    @Email(message = "Debe ingresar un correo electrónico válido")
    @Pattern(
            regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
            message = "El formato del correo electrónico no es válido"
    )
    private String correo;
    //nombre Completo
    @NotBlank(message = "El nombre completo es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre completo debe tener entre 3 y 100 caracteres")
    private String nombreCompleto;
    //telefono
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "\\d{10}", message = "El teléfono debe tener exactamente 10 dígitos")
    private String telefono;
    //Direccion
    @NotBlank(message = "La dirección es obligatoria")
    @Size(min = 5, max = 150, message = "La dirección debe tener entre 5 y 150 caracteres")
    private String direccion;
    //Password
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

}
