package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventUpdateDTO;

import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;


@Service 
class EventApplicationService {
    
    private final EventRepository repository;
    private final EventService service;
    private final EventMapper mapper;

    EventApplicationService(EventRepository repository, EventService service, EventMapper mapper) {
        this.repository = repository;
        this.service = service;
        this.mapper = mapper;
    }

    @Transactional  
    EventResponseDTO CreateEvent(EventCreateDTO dto) {
        Event event = mapper.toEntity(dto);

        service.ValidateCreateEvent(event);
        Event createdEvent = repository.save(event);
        
        return mapper.toResponseDTO(createdEvent);
    }

    @Transactional 
    EventResponseDTO UpdateEvent(Long eventId, EventUpdateDTO dto) {
        Event oldEvent = service.ValidateEvent(repository.findById(eventId));
        
        Event newEvent = oldEvent.getCopy();
        mapper.updateEntityFromDTO(dto, newEvent);

        service.ValidateUpdateEvent(oldEvent, newEvent);

        repository.save(newEvent);
        return mapper.toResponseDTO(newEvent);
    }

    @Transactional 
    EventResponseDTO GetSingleEvent(Long id, Auth auth) {
        Event event = service.ValidateEvent(repository.findById(id));
        service.ValidateAcessEvent(event, auth);
        return mapper.toResponseDTO(event);
    }

    @Transactional 
    List<EventResponseDTO> GetEventsByCategories(List<String> categories, Auth auth) {
        List<Event> events;

        if (categories == null) {
            events = repository.findAll();
            service.ValidateAcessListOfEvents(events, auth);
        } else {
            List<EventCategory> categoriesMapped = mapper.toCategories(categories);
            service.ValidateCategories(categoriesMapped, auth);
            events = repository.findByCategoryIn(categoriesMapped);
        }

        return mapper.toListResponseDTOs(events);
    }

    @Transactional 
    List<EventResponseDTO> GetEventsByName(String name, Auth auth) {
        List<Event> events = repository.findByNameContainingIgnoreCase(name);
        service.ValidateAcessListOfEvents(events, auth);
        return mapper.toListResponseDTOs(events);
    }

    @Transactional 
    void DeleteEvent(Long id) {
        Event event = service.ValidateEvent(repository.findById(id));
        service.ValidateDeleteEvent(event);
        repository.delete(event);
    }

}
