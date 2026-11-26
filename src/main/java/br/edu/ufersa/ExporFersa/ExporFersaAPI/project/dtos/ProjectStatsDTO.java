package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

public record ProjectStatsDTO(
        long approved,
        long pending,
        long rejected
) {}