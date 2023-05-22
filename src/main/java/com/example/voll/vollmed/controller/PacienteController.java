package com.example.voll.vollmed.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.voll.vollmed.records.DadosCadastroPaciente;

@Controller
@RequestMapping("/api/pacientes")
public class PacienteController {
    
    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarPaciente(@RequestBody DadosCadastroPaciente dados) {
        System.out.println(dados);
    }
}
