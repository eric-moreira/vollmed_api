package com.example.voll.vollmed.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import com.example.voll.vollmed.models.Medico;
import com.example.voll.vollmed.records.DadosCadastroMedico;
import com.example.voll.vollmed.records.DadosDetalhamentoMedico;
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
    @Transactional
    public ResponseEntity<DadosDetalhamentoMedico> cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        medicoRepository.save(medico);
        
        var uri = uriBuilder.path("/api/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public Page<DadosListagemMedico> listarMedicos(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable)  {
        return medicoRepository.findAll(pageable).map(DadosListagemMedico::new);
    }

    @PutMapping("/update")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public DadosDetalhamentoMedico updateMedico(@RequestBody @Valid DadosUpdateMedico dados) {
        Medico medico = medicoRepository.findById(dados.id()).orElseThrow();
        medico.update(dados);
        medicoRepository.save(medico);
        return new DadosDetalhamentoMedico(medico);
    }

    @DeleteMapping("/delete/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMedico(@PathVariable Long id) {
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico);

    }
}
