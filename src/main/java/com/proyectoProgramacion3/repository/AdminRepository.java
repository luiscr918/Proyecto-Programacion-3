package com.proyectoProgramacion3.repository;

import com.proyectoProgramacion3.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin,Long> {
}
