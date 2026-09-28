package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {

    default Event toEntity(EventCreateDTO dto) {
        return new Event(dto.name(), dto.category(), dto.dates(), dto.hours());
    }
    
    @Mapping(target = "dates", source = "operatingDates")
    @Mapping(target = "hours", source = "openingHours")
    EventResponseDTO toResponseDTO(Event entity);
    
    List<EventResponseDTO> toListResponseDTOs(List<Event> entities);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "imagesURLs", ignore = true)
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
