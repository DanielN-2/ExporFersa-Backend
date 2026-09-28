package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records;

import br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.exceptions.domainExceptions.InvalidOperationException;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;

@Embeddable 
public record OpeningHours(
    @NotNull(message = "O horário de abertura não pode ser vazio.")
    LocalTime startTime,

    @NotNull(message = "O horário de fechamento não pode ser vazio.")
    LocalTime closeTime
) {
    public OpeningHours {
        startTime = startTime.withSecond(0).withNano(0);
        closeTime = closeTime.withSecond(0).withNano(0);

        if (startTime.isAfter(closeTime)) {
            throw new InvalidOperationException("O horário de abertura deve anteceder o de encerramento.");
        }
    }
}