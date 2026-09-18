package br.edu.ufersa.ExporFersa.ExporFersaAPI.event.records;

import java.time.LocalDate;

public record OperatingDate(
    LocalDate startDate,
    LocalDate endDate
) {
    public OperatingDate {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("As datas não podem ser vazias.");
        }

        if (startDate.isBefore(LocalDate.now()) || endDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("As datas não podem estar no passado.");
        }

        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("A data de inicio deve anteceder a data de encerramento.");
        }
    }
}
