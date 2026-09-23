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
    public EventResponseDTO {
        if (id == null) {
            throw new IllegalArgumentException("O campo id não pode ser nulo.");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("O campo nome não pode estar vazio.");
        }

        if (category == null) {
            throw new IllegalArgumentException("O campo categoria do evento não pode ser nulo.");
        }

        if (dates == null) {
            throw new IllegalArgumentException("O campo dias de operação não pode ser nulo.");
        }

        if (hours == null) {
            throw new IllegalArgumentException("O campo horario de funcionamento não pode ser nulo.");
        }

        if (imagesURLs == null) {
            throw new IllegalArgumentException("A lista de imagens não pode ser nula.");
        }
    }
}
