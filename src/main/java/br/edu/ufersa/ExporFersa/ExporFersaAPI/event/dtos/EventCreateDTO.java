package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OperatingDate;

public record EventCreateDTO(
    @NotBlank(message = "O nome do evento não pode ser vazio.") 
    @Size(min = 3, max = 255, message = "O nome do evento deve ser possuir de 3 a 255 caracteres.") 
    String name,

    @NotNull(message = "A categoria do envento não pode ser nula.") 
    EventCategory category,

    @NotNull(message = "O campo da data de realização do evento não pode ser nulo.") 
    OperatingDate dates, 
    
    @NotNull(message = "O campo de horário de funcionamento não pode ser nulo.") 
    OpeningHours hours
) {}
