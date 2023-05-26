package com.example.voll.vollmed.records;

import jakarta.validation.constraints.NotNull;

public record DadosUpdateMedico(
    @NotNull
    Long id,
    String nome,
    DadosEndereco endereco,
    String telefone
) {

    public DadosUpdateMedico(long l, String string, String string2, DadosEndereco dadosEndereco) {
        this(l, string, dadosEndereco, string2);
    }
}
