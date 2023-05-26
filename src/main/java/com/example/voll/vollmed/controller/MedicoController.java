package com.example.voll.vollmed.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;

import com.example.voll.vollmed.models.Medico;
import com.example.voll.vollmed.records.DadosCadastroMedico;
import com.example.voll.vollmed.records.DadosListagemMedico;
import com.example.voll.vollmed.records.DadosUpdateMedico;
import com.example.voll.vollmed.repository.MedicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping("/cadastro")
    @ResponseStatus(HttpStatus.CREATED)
    @Transactional
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        medicoRepository.save(new Medico(dados));
    }

    @GetMapping("/listar")
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable)  {
        return medicoRepository.findAll(pageable).map(DadosListagemMedico::new);
    }

    @PutMapping("/update")
    @Transactional
    public void updateMedico(@RequestBody @Valid DadosUpdateMedico dados) {
        Medico medico = medicoRepository.findById(dados.id()).orElseThrow();
        medico.update(dados);
        medicoRepository.save(medico);

    }

    @DeleteMapping("/delete")
    @Transactional
    public void deleteMedico(@RequestBody @Valid DadosUpdateMedico dados) {
        Medico medico = medicoRepository.findById(dados.id()).orElseThrow();
        medicoRepository.delete(medico);

    }
}
