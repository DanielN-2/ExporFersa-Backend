package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventUpdateDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    default Event toEntity(EventCreateDTO dto) {
        if (dto == null) {
            return null;
        }

        return new Event(dto.name(), dto.category(), dto.dates(), dto.hours());
    }

    EventResponseDTO toResponseDTO(Event entity);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(EventUpdateDTO dto, @MappingTarget Event entity);
    
}
