package br.edu.ufersa.ExporFersa.API.projeto.dtos;

import java.util.List;

public record ProjetoPatch(
        String nomeProjeto,
        List<String> autores,
        String videoURL,
        String resumo,
        String descricao
) {
}
