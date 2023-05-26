package com.example.voll.vollmed.records;

import jakarta.validation.constraints.NotNull;

public record DadosUpdatePaciente(
    @NotNull
    Long id,
    String nome, 
    DadosEndereco endereco, 
    String telefone ) {

}
