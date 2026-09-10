package br.edu.ufersa.ExporFersa.API.projeto.controllers;

import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjectResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos/{eventoId}/projetos")
public class ProjetoEventoController {
    @GetMapping()
    public ResponseEntity<List<ProjectResponseDTO>> listarPorEvento(
            @PathVariable Long eventoId
    ) {
        return null;
    }
    @PostMapping()
    public ResponseEntity<ProjectResponseDTO> cadastrar(
            @PathVariable Long eventoId,
            @RequestBody ProjectCreateDTO dto,
            UriComponentsBuilder uriBuilder
    ){
        return null;
    }

}
