package com.example.voll.vollmed.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.voll.vollmed.domain.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long>{
    
}
