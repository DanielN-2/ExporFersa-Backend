package br.edu.ufersa.ExporFersa.API.projeto.controllers;

import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjetoCreate;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjetoResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/eventos/${eventoId}/projetos")
public class ProjetoEventoController {
    @GetMapping()
    public ResponseEntity<List<ProjetoResponse>> listarPorEvento(
            @PathVariable Long eventoId
    ) {
        return null;
    }
    @PostMapping()
    public ResponseEntity<ProjetoResponse> cadastrar(
            @PathVariable Long eventoId,
            @RequestBody ProjetoCreate dto,
            UriComponentsBuilder uriBuilder
    ){
        return null;
    }

}
