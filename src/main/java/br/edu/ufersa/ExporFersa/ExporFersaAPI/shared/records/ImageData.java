package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Embeddable 
public record ImageData(
    @Column(unique = true, nullable = false)
    @NotNull(message = "O id não pode ser nulo.")
    UUID id,

    @Column(unique = true, nullable = false)
    @NotBlank(message = "O endereço da imagem não pode estar vazio.")
    String imageURL,

    @Column(nullable = false, length = 100) 
    @NotBlank(message = "A imagem deve conter um título.")
    @Size(min = 3, max = 100, message = "O título deve conter entre 3 a 100 caracteres.")
    String title
) {

    public ImageData(String imageURL, String title) {
        this(UUID.randomUUID(), imageURL, title);
    }

}
