package br.edu.ufersa.ExporFersa.ExporFersaAPI.event;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.ImageData;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OpeningHours;
import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records.OperatingDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity 
@Table(name = "tb_event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EventCategory category;

    @Embedded 
    private OperatingDate operatingDate;

    @Embedded 
    private OpeningHours openingHours;

    @ElementCollection
    @CollectionTable(name = "tb_event_images", joinColumns = @JoinColumn(name="event_id"))
    @Size(max = 10, message = "Um evento só pode ter no máximo 5 imagens.") 
    private List<ImageData> images = new ArrayList<ImageData>();


    protected Event(){}

    public Event(String name, EventCategory category, OperatingDate dates, OpeningHours hours) {
        this.name = name;
        this.category = category;
        this.operatingDate = dates;
        this.openingHours = hours;
    }
    public Event(Long id) {
        this.id = id;
    }
    private Event(
        Long id,
        String name,
        EventCategory category,
        OperatingDate dates,
        OpeningHours hours,
        List<ImageData> images
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.operatingDate = dates;
        this.openingHours = hours;
        this.images= images;
    }

    public Long getId() { return this.id; }
    public String getName() { return this.name; }
    public EventCategory getCategory() { return this.category; }
    public OperatingDate getOperatingDates() { return this.operatingDate; }
    public OpeningHours getOpeningHours() { return this.openingHours; }
    public List<ImageData> getImages() { return this.images; }

    public void addImageData(ImageData imageData) { 
        images.add(imageData);
    }

    public Event getCopy() {
        return new Event(id, name, category, operatingDate, openingHours, images);
    }
     
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Event event = (Event) obj;
        return (id != null) && (event.id == this.id);
    }

}
