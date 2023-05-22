package com.example.voll.vollmed.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroMedico(
    
    @NotBlank
    String nome,

    @NotBlank
    @Email
    String email, 
    
    @NotBlank @Pattern(regexp = "\\d{10,11}")
    String telefone,

    @NotBlank
    @Pattern(regexp = "\\d{4,6}")
    String crm,

    @NotNull
    Especialidade especialidade, 
    
    @NotNull
    DadosEndereco endereco) {}
