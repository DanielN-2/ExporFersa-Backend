package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EventImageUpdateDTO(
    @NotBlank(message = "A imagem deve conter um título.")
    @Size (min = 3, max = 100, message = "O título deve conter entre 3 a 100 caracteres.")
    String imageTitle
) {}
