package br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos.ProjectResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos.ProjectUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.projeto.dtos.ProjetectPatchDTO;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projects")
public class ProjetoController {

    @PostMapping()
    public ResponseEntity<ProjectResponseDTO> criarProjeto(@RequestBody ProjectCreateDTO projeto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponseDTO>> listar() {
        return null;
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> buscarPorId(@PathVariable Long projectId) {return null;}

    @PutMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> atualizar(
            @PathVariable Long projectId,
            @RequestBody ProjectUpdateDTO dto
    ) {return null;}

    @PatchMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> alterarParcial(
            @PathVariable Long projectId,
            @RequestBody ProjetectPatchDTO dto
            ) {return null;}

    @DeleteMapping("/{projectId}")
    public ResponseEntity<Void> remover(@PathVariable Long projectId) {return null;}


}
