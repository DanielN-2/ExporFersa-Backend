package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;

import java.time.LocalDate;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Future;

@Embeddable 
public record OperatingDate(
    @NotNull(message = "A data de abertura não pode ser nula.")
    @Future(message = "A data de abertura deve ser de um dia futuro.")
    LocalDate startDate,

    @NotNull(message = "A data de encerramento não pode ser nula.")
    @Future(message = "A data de encerramento deve ser de um dia futuro.")
    LocalDate endDate
) {
    public OperatingDate {
        if (startDate.isAfter(endDate)) {
            throw new InvalidOperationException("A data de inicio deve anteceder a data de encerramento.");
        }
    }

    public boolean inBetween(LocalDate date) {
       return (startDate.isAfter(date) && endDate.isBefore(date));
    }
}
