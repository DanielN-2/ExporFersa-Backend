package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;
import java.util.stream.Collectors;

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
        return new Event(dto.name(), dto.category(), dto.dates(), dto.hours());
    }

    EventResponseDTO toResponseDTO(Event entity);
    
    List<EventResponseDTO> toListResponseDTOs(List<Event> entities);

    @Mapping(target = "id", ignore = true)
    void updateEntityFromDTO(EventUpdateDTO dto, @MappingTarget Event entity);
    
    default List<EventCategory> toCategories(List<String> categories) {
        List<EventCategory> mapped = categories.stream().map(cat -> {
            try {
                return EventCategory.valueOf(cat.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }).filter(cat -> cat != null).collect(Collectors.toList());

        return mapped;
    }

}
