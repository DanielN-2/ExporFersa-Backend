package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthService authService;

    public UserController(AuthenticationManager authenticationManager,
        TokenService tokenService,
        AuthService authService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@RequestBody UserCreateDTO usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> logarUsuario(@RequestBody UserLoginDTO loginDto) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDto.username(), loginDto.senha());
        var authentication = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((User) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO(token));
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

    @PatchMapping("/{id}/password")
    ResponseEntity<Void> alterarSenha(@PathVariable UUID id, @RequestBody UserPasswordUpdateDTO dto) {
        return ResponseEntity.noContent().build();
    }
}
