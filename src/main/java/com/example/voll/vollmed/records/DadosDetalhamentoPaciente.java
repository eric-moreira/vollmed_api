package com.example.voll.vollmed.records;

import com.example.voll.vollmed.models.Endereco;
import com.example.voll.vollmed.models.Paciente;

public record DadosDetalhamentoPaciente (
    Long id, 
    String nome, 
    String cpf, 
    String email, 
    String telefone, 
    Endereco endereco) {

        public DadosDetalhamentoPaciente(Paciente paciente){
            this(paciente.getId(), paciente.getNome(), paciente.getCpf(), paciente.getEmail(), 
            paciente.getTelefone(), paciente.getEndereco());
        }


}