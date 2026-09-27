package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
class EventInternalApiImpl implements EventInternalApi {
    private final EventRepository repository;
    EventInternalApiImpl(EventRepository repository) {
        this.repository = repository;
    }
    @Override
    public Event findById(Long eventId){
        return repository.findById(eventId).orElseThrow(() -> new EntityNotFoundException("Evento não encontrado!"));
    }
}
