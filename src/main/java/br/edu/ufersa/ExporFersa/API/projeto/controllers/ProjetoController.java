package br.edu.ufersa.ExporFersa.API.projeto.controllers;

import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjectCreateDTO;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjetectPatchDTO;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjectResponseDTO;
import br.edu.ufersa.ExporFersa.API.projeto.dtos.ProjectUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/projetos")
public class ProjetoController {

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> criarProjeto(@RequestBody ProjectCreateDTO projeto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping()
    public ResponseEntity<List<ProjectResponseDTO>> listar() {
        return null;
    }

    @GetMapping("/{projetoId}")
    public ResponseEntity<ProjectResponseDTO> buscarPorId(@PathVariable Long projetoId) {return null;}

    @PutMapping("{projetoId}")
    public ResponseEntity<ProjectResponseDTO> atualizar(
            @PathVariable Long projetoId,
            @RequestBody ProjectUpdateDTO dto
    ) {return null;}

    @PatchMapping("{projetoId}")
    public ResponseEntity<ProjectResponseDTO> alterarParcial(
            @PathVariable Long projetoId,
            @RequestBody ProjetectPatchDTO dto
            ) {return null;}

    @DeleteMapping("{projetoId}")
    public ResponseEntity<Void> remover(@PathVariable Long projetoId) {return null;}


}
