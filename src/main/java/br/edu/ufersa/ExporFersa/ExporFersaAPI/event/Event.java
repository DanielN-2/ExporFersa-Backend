package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records.OperatingDate;


@Entity 
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

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

    public UUID getId() { return this.id; }
    public String getName() { return this.name; }
    public EventCategory getCategory() { return this.category; }
    public OperatingDate getOperatingDates() { return this.operatingDate; }
    public OpeningHours getOpeningHours() { return this.openingHours; }
    public List<String> getImagesURLs() { return this.imagesURLs; }

    
}
