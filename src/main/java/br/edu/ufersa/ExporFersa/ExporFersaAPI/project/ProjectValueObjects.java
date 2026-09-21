package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import jakarta.persistence.Embeddable;

@Embeddable
record ProjectName(String value) {
    ProjectName {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("O nome do projeto é obrigatório!");
        }
    }
}

@Embeddable
record Summary(String value) {
    Summary {
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

@Embeddable
record Description(String value) {
    Description {
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

@Embeddable
record VideoURL(String value) {
    VideoURL {
        if (value == null || !value.matches("^https?://.+")) {
            throw new IllegalArgumentException("A URL do Video deve ser um link válido!");
        }
    }
}

@Embeddable
record Author(String name) {
    Author {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O Autor não pode ser vazio!");
        }
    }
}