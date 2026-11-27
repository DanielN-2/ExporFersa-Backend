package br.edu.ufersa.ExporFersa.ExporFersaAPI.project;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.event.EventCategory;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProjectSpecification {

    public static Specification<Project> withFilters(
            ProjectStatus status,
            ProjectCategory category,
            Long eventId,
            EventCategory eventCategory
    ) {
        return (root, query, cb) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (status != null) {
                predicates.add(
                        cb.equal(root.get("status"), status)
                );
            }

            if (category != null) {
                predicates.add(
                        cb.equal(root.get("category"), category)
                );
            }

            if (eventId != null) {
                predicates.add(
                        cb.equal(root.get("event").get("id"), eventId)
                );
            }

            if (eventCategory != null) {
                predicates.add(
                        cb.equal(root.get("event").get("category"), eventCategory)
                );
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}