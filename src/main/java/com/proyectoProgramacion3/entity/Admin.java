package com.proyectoProgramacion3.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Admin extends Usuario{
@OneToMany(mappedBy = "admin")
    private List<Curso> cursos;
}
