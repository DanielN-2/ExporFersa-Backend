package br.edu.ufersa.ExporFersa.ExporFersaAPI.shared.records;

import java.time.LocalTime;

import jakarta.persistence.Embeddable;

@Embeddable 
public record OpeningHours(
    LocalTime startTime,
    LocalTime closeTime
) {
    public OpeningHours {
        if (startTime == null || closeTime == null) {
            throw new IllegalArgumentException("Os horários não podem ser vazios.");
        }

        startTime = startTime.withSecond(0).withNano(0);
        closeTime = closeTime.withSecond(0).withNano(0);

        if (startTime.isAfter(closeTime)) {
            throw new IllegalArgumentException("O horário de abertura deve anteceder o de encerramento.");
        }
    }
}