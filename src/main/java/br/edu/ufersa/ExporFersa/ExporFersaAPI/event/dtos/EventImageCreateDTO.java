package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record EventImageCreateDTO(
    @NotBlank(message = "A imagem deve conter um título.")
    @Size (min = 3, max = 100, message = "O título deve conter entre 3 a 100 caracteres.")
    String imageTitle,

    @NotNull (message = "Você dever enviar um arquivo para ser salvo.")
    MultipartFile image
) {}
