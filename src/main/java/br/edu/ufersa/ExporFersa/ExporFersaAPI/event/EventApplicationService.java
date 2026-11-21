package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventImageCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventImageUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventUpdateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.ImageData;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.services.FileService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service 
class EventApplicationService {
    
    private final EventRepository repository;
    private final EventService service;
    private final FileService fileService;
    private final EventMapper mapper;

    EventApplicationService(EventRepository repository, EventService service, FileService fileService, EventMapper mapper) {
        this.repository = repository;
        this.service = service;
        this.fileService = fileService;
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
    EventResponseDTO AddEventImage(Long eventId, EventImageCreateDTO dto) {
        Event event = service.ValidateEvent(repository.findById(eventId));

        ImageData imageData = fileService.saveImage("eventos", dto.imageTitle(), dto.image());
        event.addImageData(imageData);

        repository.save(event);
        return mapper.toResponseDTO(event);
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
    EventResponseDTO UpdateEventImage(Long eventId, UUID imageId, EventImageUpdateDTO dto) {
        Event event = service.ValidateEvent(repository.findById(eventId));
        int index = service.ValidateEventImage(event, imageId);

        mapper.updateEventImageDataFromDTO(dto, event.getImages().get(index));

        repository.save(event);
        return mapper.toResponseDTO(event);
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

    @Transactional
    EventResponseDTO DeleteEventImage(Long id, UUID imageId) {
        Event event = service.ValidateEvent(repository.findById(id));
        int imageIndex = service.ValidateEventImage(event, imageId);

        String imageURL = event.getImages().get(imageIndex).imageURL();
        fileService.deleteImage(imageURL);

        event.getImages().remove(imageIndex);

        repository.save(event);
        return mapper.toResponseDTO(event);
    }

}
