package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OperatingDate;


@Entity 
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory category;

    @Embedded 
    @AttributeOverride(name = "value", column = @Column(name = "operating_date"))
    private OperatingDate operatingDate;

    @Embedded 
    @AttributeOverride(name = "value", column = @Column(name = "opening_hours"))
    private OpeningHours openingHours;

    @ElementCollection
    @CollectionTable(name = "tb_event_images", joinColumns = @JoinColumn(name="event_id"))
    @Column(name = "images_urls")
    private List<String> imagesURLs = new ArrayList<String>();


    protected Event(){}

    public Event(String name, EventCategory category, OperatingDate dates, OpeningHours hours) {
        // código duplicado: Aqui, EventUpdateDTO e em EventCreateDTO
        if (name == null || category == null || dates == null || hours == null) {
            throw new IllegalArgumentException("Nenhum campo pode ser nulo.");
        }

        if (name.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }

        this.name = name;
        this.category = category;
        this.operatingDate = dates;
        this.openingHours = hours;
    }

    public UUID getId() { return this.id; }
    public String getName() { return this.name; }
    public EventCategory getCategory() { return this.category; }
    public OperatingDate getOperatingDates() { return this.operatingDate; }
    public OpeningHours getOpeningHours() { return this.openingHours; }
    public List<String> getImagesURLs() { return this.imagesURLs; }
     
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Event event = (Event) obj;
        return (event.id == this.id);
    }
}
