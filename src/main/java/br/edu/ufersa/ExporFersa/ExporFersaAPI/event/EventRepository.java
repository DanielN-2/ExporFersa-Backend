package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface  EventRepository extends JpaRepository<Event, Long> {

    List<Event> findByCategoryIn(List<EventCategory> categories, Sort sort);

    List<Event> findByNameContainingIgnoreCase(String name, Sort sort);

    boolean existsByNameIgnoreCase(String name);

    @Query("""
        SELECT e FROM Event e
        WHERE e.operatingDate.startDate > CURRENT_DATE
    """)
    List<Event> findUpcomingEvents(Sort sort);

    @Query("""
        SELECT e FROM Event e
        WHERE e.operatingDate.startDate <= CURRENT_DATE
        AND e.operatingDate.endDate >= CURRENT_DATE
    """)
    List<Event> findOngoingEvents(Sort sort);

    @Query("""
        SELECT e FROM Event e
        WHERE e.operatingDate.endDate < CURRENT_DATE
    """)
    List<Event> findFinishedEvents(Sort sort);
}

