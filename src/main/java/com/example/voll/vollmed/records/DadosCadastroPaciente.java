package com.example.voll.vollmed.records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DadosCadastroPaciente(

@NotBlank
String nome,

@NotBlank @Email 
String email, 

@NotBlank @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2")
String cpf, 

@NotNull
DadosEndereco endereco, 

@NotBlank @Pattern(regexp = "\\d{10,11}")
String telefone ) {
}
