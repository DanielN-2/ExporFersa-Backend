package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventUpdateDTO;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    Event toEntity(EventCreateDTO dto);

    EventResponseDTO toResponseDTO(Event entity);
    
    List<EventResponseDTO> toListResponseDTOs(List<Event> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(EventUpdateDTO dto, @MappingTarget Event entity);
    
}
