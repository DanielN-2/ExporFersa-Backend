package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OperatingDate;

public record EventUpdateDTO(
    String name,
    EventCategory category,
    OperatingDate dates,
    OpeningHours hours
) {
    public EventUpdateDTO {
        if (name == null || category == null || dates == null || hours == null) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo.");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
    }
}