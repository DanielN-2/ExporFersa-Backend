package br.edu.ufersa.ExporFersa.API.usuario;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.edu.ufersa.ExporFersa.API.usuario.dtos.UserCreate;
import br.edu.ufersa.ExporFersa.API.usuario.dtos.UserLogin;
import br.edu.ufersa.ExporFersa.API.usuario.dtos.UserResponse;
import br.edu.ufersa.ExporFersa.API.usuario.dtos.UserUpdate;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<UserResponse> criarUsuario(@RequestBody UserCreate usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<String> logarUsuario(@RequestBody UserLogin loginDto) {
        return ResponseEntity.ok(null);
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> listarUsuarios() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> atualizarUsuario(@PathVariable UUID id, @RequestBody UserUpdate usuarioAtualizado) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        return ResponseEntity.noContent().build();
    }
}
