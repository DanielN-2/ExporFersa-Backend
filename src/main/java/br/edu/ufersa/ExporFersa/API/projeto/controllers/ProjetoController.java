package br.edu.ufersa.ExporFersa.API.projeto.controllers;

import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjetoPatch;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjetoResponse;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjetoUpdate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoController {
    @GetMapping()
    public ResponseEntity<List<ProjetoResponse>> listar() {
        return null;
    }
    @GetMapping("/${projetoId}")
    public ResponseEntity<ProjetoResponse> buscarPorId(
            @PathVariable Long projetoId
    ) {
        return null;
    }
    @PutMapping("${projetoId}")
    public ResponseEntity<ProjetoResponse> atualizar(
            @PathVariable Long projetoId,
            @RequestBody ProjetoUpdate dto
    ) {
        return null;
    }
    @PatchMapping("${projetoId")
    public ResponseEntity<ProjetoResponse> alterarParcial(
            @PathVariable Long projetoId,
            @RequestBody ProjetoPatch dto
            ) {
        return null;
    }
    @DeleteMapping("${projetoId}")
    public ResponseEntity<Void> remover(
            @PathVariable Long projetoId
    ) {
        return null;
    }
}
