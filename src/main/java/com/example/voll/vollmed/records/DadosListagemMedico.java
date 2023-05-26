package com.example.voll.vollmed.records;

import com.example.voll.vollmed.models.Medico;

public record DadosListagemMedico(
    Long id,
    String nome,
    String email,
    String crm,
    Especialidade especialidade

) {

    public DadosListagemMedico(Medico medico){
        this(medico.getId(),medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
    
}
