package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OperatingDate;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.project.entity.Project;

public record EventResponseDTO(
    UUID id,
    String name,
    EventCategory category,
    OperatingDate dates,
    OpeningHours hours,
    Optional<List<String>> imagesURLs,
    Optional<List<Project>> projects
) {

}
