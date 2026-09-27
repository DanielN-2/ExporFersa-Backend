package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records;

import jakarta.persistence.Embeddable;

@Embeddable
public record Author(String name) {
    public Author {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O Autor não pode ser vazio!");
        }
    }
}