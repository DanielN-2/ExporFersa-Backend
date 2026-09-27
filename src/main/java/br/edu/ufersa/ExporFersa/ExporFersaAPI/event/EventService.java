package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.AuthRole;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.DataConflictException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.EntityNotFoundException;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;


@Service 
class EventService {

    private final EventRepository repository;

    EventService(EventRepository repository) {
        this.repository = repository;
    }

    void ValidateCreateEvent(Event event) {
        if (repository.existsByNameIgnoreCase(event.getName())) {
            throw new DataConflictException("Já existe um evento com o nome " + event.getName() + " cadastrado.");
        };
    }

    void ValidateUpdateEvent(Event oldEvent, Event newEvent) {
        if (repository.existsByNameIgnoreCase(newEvent.getName())){
            throw new DataConflictException("Já existe um evento com o nome " + newEvent.getName() + " cadastrado.");
        }

        if (oldEvent.getOperatingDates().startDate().isAfter(LocalDate.now())) {
            throw new InvalidOperationException("Não é possível mudar o evento depois da data de abertura."); 
        }
    }

    void ValidateDeleteEvent(Event event) {
        if (event.getOperatingDates().startDate().isAfter(LocalDate.now())) {
            throw new InvalidOperationException("Não é possível deletar o evento depois de iniciado."); 
        }
    }

    Event ValidateEvent(Optional<Event> event) {
        if (event.isEmpty()) {
            throw new EntityNotFoundException(
                "Não foi possível encontrar o evento."
            );
        }

        return event.get();
    }

    void ValidateAcessEvent(Event event, Auth auth) {
        if (auth == null && event.getCategory() != EventCategory.EXTENSAO) {
            throw new AccessDeniedException(null);
        }
    }

    void ValidateAcessListOfEvents(List<Event> events, Auth auth) {
        if (auth == null) {
            events.removeIf(e -> e.getCategory() != EventCategory.EXTENSAO);
            return;
        }

        AuthRole role = auth.getRole();
        for (var event : events) {
            if (event.getCategory() != EventCategory.EXTENSAO && role == AuthRole.GUEST) {
                events.remove(event);
            }
        }
    }

    void ValidateCategories(List<EventCategory> categories, Auth auth) {
        if (auth == null || auth.getRole() != AuthRole.GUEST) {
            return;
        }

        if (categories.contains(EventCategory.ENSINO) || categories.contains(EventCategory.PESQUISA)) {
            throw new AccessDeniedException(
                "Para acessar eventos de ensino ou pesquisa é necessário estar associado à universiade"
            );
        }
    }

}
