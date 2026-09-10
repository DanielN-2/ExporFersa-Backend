package br.edu.ufersa.ExporFersa.API.usuario;

import br.edu.ufersa.ExporFersa.API.usuario.dtos.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuario")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@RequestBody UserCreateDTO usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logarUsuario(@RequestBody UserLoginDTO loginDto) {
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody UserUpdateDTO usuarioAtualizado) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/senha")
    ResponseEntity<Void> alterarSenha(@PathVariable UUID id, @RequestBody UserPasswordUpdateDTO dto) {
        return ResponseEntity.noContent().build();
    }
}
