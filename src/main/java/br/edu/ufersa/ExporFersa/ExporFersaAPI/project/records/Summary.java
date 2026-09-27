package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records;

import jakarta.persistence.Embeddable;

@Embeddable
public record Summary(String value) {
    public Summary {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O resumo é obrigatório!");
        }
        if (value.length() > 1000) {
            throw new IllegalArgumentException(
                    "O resumo não pode possuir mais de 1000 caracteres!"
            );
        }
    }
}