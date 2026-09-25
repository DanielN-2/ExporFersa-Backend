package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OperatingDate;

public record EventUpdateDTO(
    String name,
    EventCategory category,
    OperatingDate dates,
    OpeningHours hours
) {}
