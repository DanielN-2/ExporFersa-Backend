package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final AuthApplicationService authApplicationService;

    public AuthController(AuthenticationManager authenticationManager,
                          TokenService tokenService,
                          AuthApplicationService authApplicationService
    ) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.authApplicationService = authApplicationService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> criarUsuario(@RequestBody @Valid UserCreateDTO usuario) {
        UserResponseDTO response = authApplicationService.register(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> logarUsuario(@RequestBody @Valid UserLoginDTO loginDto) {
        var authToken = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var authentication = authenticationManager.authenticate(authToken);
        String token = tokenService.generateToken((Auth) authentication.getPrincipal());
        return ResponseEntity.ok(new TokenResponseDTO(token));
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(authApplicationService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(authApplicationService.searchById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizarUsuario(@PathVariable UUID id, @RequestBody @Valid UserUpdateDTO usuarioAtualizado) {
        return ResponseEntity.ok(authApplicationService.updateUser(id, usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID id) {
        authApplicationService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/password")
    ResponseEntity<Void> alterarSenha(@PathVariable UUID id, @RequestBody @Valid UserPasswordUpdateDTO dto) {
        authApplicationService.updatePassword(id, dto);
        return ResponseEntity.noContent().build();
    }

}
