package br.edu.ufersa.ExporFersa.API.usuario.dtos;

public record UserPasswordUpdateDTO(
        String senhaAntiga,
        String senhaNova
) {
    public UserPasswordUpdateDTO {
        if (senhaAntiga == null || senhaAntiga.isBlank()) {
            throw new IllegalArgumentException("Nao informou a senha antiga.");
        }
        if (senhaNova == null || !senhaNova.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[^a-zA-Z0-9]).{8,}$")) {
            throw new IllegalArgumentException("A nova senha deve ter no mínimo 8 caracteres, incluindo uma letra maiúscula, uma minúscula, um número e um símbolo.");
        }
        if (senhaNova.equals(senhaAntiga)) {
            throw new IllegalArgumentException("A senha nova nao pode ser igual a antiga!");
        }
    }
}