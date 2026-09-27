package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.ProjectStatus;

public enum StatusDTO {
    PENDING,
    APPROVED,
    REJECTED;

    public ProjectStatus toDomain() {
        return switch (this) {
            case PENDING -> ProjectStatus.PENDING;
            case APPROVED -> ProjectStatus.APPROVED;
            case REJECTED -> ProjectStatus.REJECTED;
        };
    }

    public static StatusDTO fromDomain(ProjectStatus status) {
        return switch (status) {
            case PENDING -> PENDING;
            case APPROVED -> APPROVED;
            case REJECTED -> REJECTED;
        };
    }
}