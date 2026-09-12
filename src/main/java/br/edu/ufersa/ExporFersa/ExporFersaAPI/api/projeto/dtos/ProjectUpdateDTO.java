package br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos;

import java.util.List;

public record ProjectUpdateDTO(
        String nomeProjeto,
        List<String> autores,
        String videoURL,
        String resumo,
        String descricao
) {
    public ProjectUpdateDTO {
        if (nomeProjeto == null || nomeProjeto.isBlank()) {
            throw new IllegalArgumentException("O nome do projeto é obrigatório!");
        }

        if (autores == null || autores.isEmpty()) {
            throw new IllegalArgumentException("O projeto deve possuir pelo menos um autor!");
        }

        if (autores.stream().anyMatch(autor -> autor == null || autor.isBlank())) {
            throw new IllegalArgumentException("O nome dos autores não pode ser vazio!");
        }

        if (videoURL == null || videoURL.isBlank()) {
            throw new IllegalArgumentException("A URL do vídeo é obrigatória!");
        }

        if (resumo == null || resumo.isBlank()) {
            throw new IllegalArgumentException("O resumo é obrigatório!");
        }

        if (descricao == null || descricao.isBlank()) {
            throw new IllegalArgumentException("A descrição é obrigatória!");
        }
    }
}
