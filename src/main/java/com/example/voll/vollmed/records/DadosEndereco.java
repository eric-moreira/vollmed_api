package com.example.voll.vollmed.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(

@NotBlank
String logradouro, 

String numero, 
String complemento, 

@NotBlank
String bairro,

@NotBlank
String cidade,

@NotBlank
String estado,

@NotBlank
@Pattern(regexp = "\\d{8}")
String cep  ) {}
