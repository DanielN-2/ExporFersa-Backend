package br.edu.ufersa.ExporFersa.ExporFersaAPI.project.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.ProjectCategory;

public enum ProjectCategoryDTO {
    CIENCIAS_AGRARIAS,
    ENGENHARIA,
    CIENCIAS_HUMANAS,
    CIENCIAS_EXATAS_E_DA_TERRA,
    CIENCIAS_BIOLOGICAS,
    CIENCIAS_SOCIAIS_E_APLICADAS,
    CIENCIAS_DA_SAUDE;
    public ProjectCategory toDomain() {
        return switch (this) {
            case CIENCIAS_AGRARIAS -> ProjectCategory.CIENCIAS_AGRARIAS;
            case ENGENHARIA -> ProjectCategory.ENGENHARIA;
            case CIENCIAS_HUMANAS -> ProjectCategory.CIENCIAS_HUMANAS;
            case CIENCIAS_EXATAS_E_DA_TERRA -> ProjectCategory.CIENCIAS_EXATAS_E_DA_TERRA;
            case CIENCIAS_BIOLOGICAS -> ProjectCategory.CIENCIAS_BIOLOGICAS;
            case CIENCIAS_SOCIAIS_E_APLICADAS -> ProjectCategory.CIENCIAS_SOCIAIS_E_APLICADAS;
            case CIENCIAS_DA_SAUDE -> ProjectCategory.CIENCIAS_DA_SAUDE;
        };
    }

    public static ProjectCategoryDTO fromDomain(ProjectCategory category) {
        return switch (category) {
            case CIENCIAS_AGRARIAS -> CIENCIAS_AGRARIAS;
            case ENGENHARIA -> ENGENHARIA;
            case CIENCIAS_HUMANAS -> CIENCIAS_HUMANAS;
            case CIENCIAS_EXATAS_E_DA_TERRA -> CIENCIAS_EXATAS_E_DA_TERRA;
            case CIENCIAS_BIOLOGICAS -> CIENCIAS_BIOLOGICAS;
            case CIENCIAS_SOCIAIS_E_APLICADAS -> CIENCIAS_SOCIAIS_E_APLICADAS;
            case CIENCIAS_DA_SAUDE -> CIENCIAS_DA_SAUDE;
        };
    }
}