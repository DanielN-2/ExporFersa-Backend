package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

import jakarta.validation.constraints.NotNull;

public record ProjectStatusPatchDTO(
        @NotNull(message = "O status é obrigatório!")
        StatusDTO status
) {}
