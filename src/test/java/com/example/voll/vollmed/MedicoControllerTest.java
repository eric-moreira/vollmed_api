package com.example.voll.vollmed;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import com.example.voll.vollmed.controller.MedicoController;
import com.example.voll.vollmed.models.Medico;
import com.example.voll.vollmed.records.DadosCadastroMedico;
import com.example.voll.vollmed.records.DadosEndereco;
import com.example.voll.vollmed.records.DadosUpdateMedico;
import com.example.voll.vollmed.repository.MedicoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MedicoController.class)
class MedicoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    void listarMedicos_deveRetornarListaDeMedicos() throws Exception {
        Medico medico1 = new Medico(
                new DadosCadastroMedico("João", "joao@teste.com", "123456", "123456", "CARDIOLOGIA",
                        new DadosEndereco("Rua A", "123", "Apto 1", "Bairro A", "Cidade A", "Estado A", "12345678")));
        Medico medico2 = new Medico(
                new DadosCadastroMedico("Maria", "maria@teste.com", "654321", "987654321", "PEDIATRIA",
                        new DadosEndereco("Rua B", "456", "Apto 2", "Bairro B", "Cidade B", "Estado B", "87654321")));
        List<Medico> medicos = Arrays.asList(medico1, medico2);
        Page<Medico> page = new PageImpl<>(medicos);

        when(medicoRepository.findAll(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/medicos/listar"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(2)))
                .andExpect(jsonPath("$.content[0].nome", is("João")))
                .andExpect(jsonPath("$.content[0].email", is("joao@teste.com")))
                .andExpect(jsonPath("$.content[0].crm", is("123456")))
                .andExpect(jsonPath("$.content[0].especialidade", is("CARDIOLOGIA")))
                .andExpect(jsonPath("$.content[1].nome", is("Maria")))
                .andExpect(jsonPath("$.content[1].email", is("maria@teste.com")))
                .andExpect(jsonPath("$.content[1].crm", is("987654321")))
                .andExpect(jsonPath("$.content[1].especialidade", is("PEDIATRIA")));
    }

    @Test
    void cadastrarMedico_deveCadastrarNovoMedico() throws Exception {
        DadosCadastroMedico dados = new DadosCadastroMedico("João", "joao@teste.com", "1123456789", "12345",
                "CARDIOLOGIA",
                new DadosEndereco("Rua A", "123", "Apto 1", "Bairro A", "Cidade A",
                        "Estado A", "12345678"));
        Medico medico = new Medico(dados);

        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);

        ObjectMapper objectMapper = new ObjectMapper();
        String dadosJson = objectMapper.writeValueAsString(dados);

        mockMvc.perform(post("/api/medicos/cadastro")
                .contentType(MediaType.APPLICATION_JSON)
                .content(dadosJson))
                .andExpect(status().isCreated());

        verify(medicoRepository, times(1)).save(any(Medico.class));
    }

    @Test
    void updateMedico_deveAtualizarMedicoExistente() throws Exception {
        DadosUpdateMedico dados = new DadosUpdateMedico(1L, "João", "123456789",
                new DadosEndereco("Rua A", "123", "Apto 1", "Bairro A", "Cidade A", "Estado A", "12345678"));
        Medico medico = new Medico(
                new DadosCadastroMedico("João", "joao@teste.com", "123456", "123456789", "CARDIOLOGIA",
                        new DadosEndereco("Rua A", "123", "Apto 1", "Bairro A", "Cidade A", "Estado A", "12345678")));
    
        when(medicoRepository.findById(anyLong())).thenReturn(Optional.of(medico));
        when(medicoRepository.save(any(Medico.class))).thenReturn(medico);
    
        mockMvc.perform(put("/api/medicos/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(dados)))
                .andExpect(status().isOk());
    
        verify(medicoRepository, times(1)).findById(dados.id());
        verify(medicoRepository, times(1)).save(any(Medico.class));
    
    }
    
}