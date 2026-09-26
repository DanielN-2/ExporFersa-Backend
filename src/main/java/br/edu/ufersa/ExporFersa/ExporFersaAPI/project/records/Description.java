package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records;

import jakarta.persistence.Embeddable;

@Embeddable
public record Description(String value) {
    public Description {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória!");
        }
        if (value.length() > 5000) {
            throw new IllegalArgumentException(
                    "A descrição não pode possuir mais de 5000 caracteres!"
            );
        }
    }
}