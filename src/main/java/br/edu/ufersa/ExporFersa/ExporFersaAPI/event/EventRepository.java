package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

interface  EventRepository extends JpaRepository<Event, UUID> {
    List<Event> findByCategory(EventCategory category);
    List<Event> findByNameContainingIgnoreCase(String name);
}
