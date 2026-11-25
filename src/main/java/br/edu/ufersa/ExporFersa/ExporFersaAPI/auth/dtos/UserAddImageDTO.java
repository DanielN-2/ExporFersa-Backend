package br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.dtos;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

public record UserAddImageDTO(
    @NotNull(message = "Você dever enviar um arquivo para ser salvo.")
    MultipartFile image
) {}
