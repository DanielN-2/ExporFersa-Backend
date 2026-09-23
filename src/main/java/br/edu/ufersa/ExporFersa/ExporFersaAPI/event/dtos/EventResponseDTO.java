package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import java.util.List;
import java.util.UUID;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OperatingDate;

public record EventResponseDTO(
    UUID id,
    String name,
    EventCategory category,
    OperatingDate dates,
    OpeningHours hours,
    List<String> imagesURLs
) {

}
