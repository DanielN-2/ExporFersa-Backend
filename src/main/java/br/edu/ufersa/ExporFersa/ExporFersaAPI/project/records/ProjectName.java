package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records;

import jakarta.persistence.Embeddable;

@Embeddable
public record ProjectName(String value) {
    public ProjectName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O nome do projeto é obrigatório!");
        }
    }
}
