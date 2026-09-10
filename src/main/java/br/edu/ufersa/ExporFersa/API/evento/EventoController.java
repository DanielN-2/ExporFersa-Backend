package br.edu.ufersa.ExporFersa.API.evento;

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

import br.edu.ufersa.ExporFersa.API.evento.dtos.EventCreateDTO;
import br.edu.ufersa.ExporFersa.API.evento.dtos.EventDeleteDTO;
import br.edu.ufersa.ExporFersa.API.evento.dtos.EventResponseDTO;
import br.edu.ufersa.ExporFersa.API.evento.dtos.EventoUpdateDTO;

@RestController()
@RequestMapping("/api/v1/evento")
public class EventoController {

    @PostMapping
    public ResponseEntity<EventResponseDTO> CreateEvent(EventCreateDTO evento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{categoriaEvento}")
    public ResponseEntity<List<EventResponseDTO>> ListEvents(@PathVariable String categoriaEvento) {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{idEvento}")
    public ResponseEntity<EventResponseDTO> GetSingleEvent(@PathVariable UUID idEvento) {
        return ResponseEntity.ok(null);
    } 

    @PutMapping("/{idEvento}")
    public ResponseEntity<EventResponseDTO> UpdateEvent(@PathVariable UUID idEvento, EventoUpdateDTO eventoNovo) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{idEvento}")
    public ResponseEntity<Void> DeleteEvent(@PathVariable UUID idEvento, EventDeleteDTO eventoDel) {
        return ResponseEntity.ok(null);
    }
    
}
