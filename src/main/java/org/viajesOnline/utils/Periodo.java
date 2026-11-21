package org.viajesOnline.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public record Periodo(LocalDate fechaInicio, LocalDate fechaFin) {
    public Periodo {
        Objects.requireNonNull(fechaInicio, "La fecha de inicio no puede ser nula.");
        Objects.requireNonNull(fechaFin, "La fecha de fin no puede ser nula.");

        if (fechaFin.isBefore(fechaInicio)) {
            throw new IllegalArgumentException("La fecha de fin no puede ser previa a la de inicio.");
        }
    }

    public List<LocalDate> fechas() {
        return fechaInicio.datesUntil(fechaFin.plusDays(1)).toList();
    }

    public boolean incluye(LocalDate fecha) {
        return !fecha.isBefore(fechaInicio) && !fecha.isAfter(fechaFin);
    }

    public boolean seSuperponeCon(Periodo otroPeriodo) {
        return !this.fechaInicio.isAfter(otroPeriodo.fechaFin) && !this.fechaFin.isBefore(otroPeriodo.fechaInicio);
    }
}
