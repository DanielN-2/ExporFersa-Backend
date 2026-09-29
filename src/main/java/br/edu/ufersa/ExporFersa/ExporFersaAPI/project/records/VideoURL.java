package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.records;

import jakarta.persistence.Embeddable;

@Embeddable
public record VideoURL(String value) {
    public VideoURL {
        if (value == null || !value.matches("^https?://.+")) {
            throw new IllegalArgumentException("A URL do Video deve ser um link válido!");
        }
    }
}