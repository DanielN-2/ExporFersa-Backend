package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

interface  EventRepository extends JpaRepository<Event, Long> {
    
    List<Event> findByCategory(EventCategory category);
    List<Event> findByCategoryIn(List<EventCategory> categories);
    List<Event> findByNameContainingIgnoreCase(String name);
    boolean existsByNameIgnoreCase(String name);

    @Query("""
        Select e from Event e
        Where e.operatingDate.startDate > CURRENT_DATE
    """)
    List<Event> findUpcomingEvents();

    @Query("""
        Select e from Event e
        Where e.operatingDate.startDate <= CURRENT_DATE
        AND e.operatingDate.endDate >= CURRENT_DATE
    """)
    List<Event> findOngoingEvents();

    @Query("""
        Select e from Event e
        Where e.operatingDate.endDate < CURRENT_DATE
    """)
    List<Event> findFinishedEvents();

}
