package com.example.voll.vollmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.voll.vollmed.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    
}
