package com.example.voll.vollmed.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.voll.vollmed.models.Paciente;
import com.example.voll.vollmed.records.DadosCadastroPaciente;
import com.example.voll.vollmed.records.DadosListagemPaciente;
import com.example.voll.vollmed.records.DadosUpdatePaciente;
import com.example.voll.vollmed.repository.PacienteRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;
    
    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        pacienteRepository.save(new Paciente(dados));
    }

    @GetMapping("/listar")
    public Page<DadosListagemPaciente> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable)  {
        return pacienteRepository.findAll(pageable).map(DadosListagemPaciente::new);

    }

    @PutMapping("/update")
    @Transactional
    public void updatePaciente(@RequestBody @Valid DadosUpdatePaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.id()).orElseThrow();
        paciente.update(dados);
        pacienteRepository.save(paciente);

    }

    @DeleteMapping("/delete")
    @Transactional
    public void deletePaciente(@RequestBody @Valid DadosUpdatePaciente dados) {
        Paciente paciente = pacienteRepository.findById(dados.id()).orElseThrow();
        pacienteRepository.delete(paciente);
    }



}
