package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface  EventRepository extends JpaRepository<Event, Long> {
    
    List<Event> findByCategory(EventCategory category);
    List<Event> findByCategoryIn(List<EventCategory> categories);
    List<Event> findByNameContainingIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}
