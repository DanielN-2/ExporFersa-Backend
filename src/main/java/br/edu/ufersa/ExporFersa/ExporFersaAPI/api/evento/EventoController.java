package br.edu.ufersa.ExporFersa.ExporFersaAPI.api.evento;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.evento.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.evento.dtos.EventDeleteDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.evento.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.api.evento.dtos.EventUpdateDTO;

@RestController()
@RequestMapping("/api/v1/events")
public class EventoController {

    @PostMapping
    public ResponseEntity<EventResponseDTO> CreateEvent(EventCreateDTO evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{eventCategory}")
    public ResponseEntity<List<EventResponseDTO>> ListEvents(@PathVariable String eventCategory) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> GetSingleEvent(@PathVariable UUID eventId) {
        return ResponseEntity.ok(null);
    } 

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> UpdateEvent(@PathVariable UUID eventId, EventUpdateDTO newEvent) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> DeleteEvent(@PathVariable UUID eventId, EventDeleteDTO eventoDel) {
        return ResponseEntity.ok(null);
    }
    
}
