package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface  EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCategory(EventCategory category);
    List<Event> findByCategories(List<EventCategory> categories);
    List<Event> findByNameContainingIgnoreCase(String name);

    boolean existsByNameIgnoreCase(String name);

}
