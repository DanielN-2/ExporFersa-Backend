package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.dtos.EventUpdateDTO;


@Validated 
@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    @PostMapping
    public ResponseEntity<EventResponseDTO> CreateEvent(@RequestBody @Valid EventCreateDTO event) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{eventCategory}")
    public ResponseEntity<List<EventResponseDTO>> ListEvents(@PathVariable String eventCategory) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> GetSingleEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(null);
    } 

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> UpdateEvent(
        @PathVariable Long eventId,
        @RequestBody @Valid EventUpdateDTO newEvent
    ) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> DeleteEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(null);
    }
    
}
