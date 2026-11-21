package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.auth.Auth;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.*;


@Validated 
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventApplicationService appService;

    public EventController(EventApplicationService appService) {
        this.appService = appService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDTO> CreateEvent(@RequestBody @Valid EventCreateDTO event) {
        EventResponseDTO response = appService.CreateEvent(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{eventId}/image")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDTO> AddEventImage(
        @PathVariable Long eventId,
        @ModelAttribute @Valid EventImageCreateDTO dto
    ) {
        EventResponseDTO response = appService.AddEventImage(eventId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{eventId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDTO> UpdateEvent(
        @PathVariable Long eventId, 
        @RequestBody @Valid EventUpdateDTO newEvent
    ) {
        EventResponseDTO response = appService.UpdateEvent(eventId, newEvent);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{eventId}/{imageId}/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDTO> UpdateEventImage(
        @PathVariable Long eventId,
        @PathVariable UUID imageId,
        @RequestBody @Valid EventImageUpdateDTO dto
    ) {
        EventResponseDTO response = appService.UpdateEventImage(eventId, imageId, dto);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> GetSingleEvent(
        @PathVariable Long eventId,
        @AuthenticationPrincipal Auth auth
    ) {
        EventResponseDTO response = appService.GetSingleEvent(eventId, auth);
        return ResponseEntity.ok(response);
    } 

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> ListEvents(
        @RequestParam(name = "category", required = false) List<String> eventCategories,
        @AuthenticationPrincipal Auth auth
    ) {
        List<EventResponseDTO> response = appService.GetEventsByCategories(eventCategories, auth);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/name")
    public ResponseEntity<List<EventResponseDTO>> ListEventsByName(
        @RequestParam String name,
        @AuthenticationPrincipal Auth auth
    ) {
        List<EventResponseDTO> response = appService.GetEventsByName(name, auth);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{eventId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> DeleteEvent(@PathVariable Long eventId) {
        appService.DeleteEvent(eventId);
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{eventId}/{imageId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EventResponseDTO> DeleteEventImage(
        @PathVariable Long eventId,
        @PathVariable UUID imageId
    ) {
        EventResponseDTO response = appService.DeleteEventImage(eventId, imageId);
        return ResponseEntity.ok(response);
    }
    
}
