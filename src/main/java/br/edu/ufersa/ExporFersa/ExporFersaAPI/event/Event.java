package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OperatingDate;
import jakarta.persistence.*;

import java.util.List;


@Entity 
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private EventCategory category;

    @Column(nullable = false)
    private OperatingDate operatingDate;

    @Column(nullable = false)
    private OpeningHours openingHours;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    List<String> imagesURLs;


    protected Event(){};
    public Event(Long id) {
        this.id = id;
    }

    public Long getId() { return this.id; }
    public String getName() { return this.name; }
    public EventCategory getCategory() { return this.category; }
    public OperatingDate getOperatingDates() { return this.operatingDate; }
    public OpeningHours getOpeningHours() { return this.openingHours; }
    public List<String> getImagesURLs() { return this.imagesURLs; }

    
}
