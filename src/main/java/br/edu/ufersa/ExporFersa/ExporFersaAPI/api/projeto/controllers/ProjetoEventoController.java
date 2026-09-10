package br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos.ProjectResponseDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events/{eventId}/projects")
public class ProjetoEventoController {
    @GetMapping()
    public ResponseEntity<List<ProjectResponseDTO>> listarPorEvento(
            @PathVariable Long eventId
    ) {
        return null;
    }
    @PostMapping()
    public ResponseEntity<ProjectResponseDTO> cadastrar(
            @PathVariable Long eventId,
            @RequestBody ProjectCreateDTO dto,
            UriComponentsBuilder uriBuilder
    ){
        return null;
    }

}
