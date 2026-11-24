package org.viajesOnline.utils;

import org.viajesOnline.reservas.Cupo;

import java.time.LocalDate;
import java.util.*;

public class CalendarioCupos {
    private final int cupoPorDiaInicial;
    private final Periodo periodoVigencia;
    private final Map<LocalDate, Cupo> cupoPorDia = new HashMap<>();

    public CalendarioCupos(Periodo periodo, int cupoPorDiaInicial) {
        this.cupoPorDiaInicial = cupoPorDiaInicial;
        this.periodoVigencia = periodo;
        for (LocalDate dia : periodo.fechas()) {
            cupoPorDia.put(dia, new Cupo(cupoPorDiaInicial, cupoPorDiaInicial));
        }
    }

    public boolean estaDisponibleEn(Periodo periodo) {
        for (LocalDate dia : periodo.fechas()) {
            Cupo cupo = cupoPorDia.get(dia);

            if (cupo == null || cupo.getCupoDisponible() <= 0) {
                return false;
            }
        }
        return true;
    }

    public void decrementarCupo(Periodo periodo) {
        if (!estaDisponibleEn(periodo)) {
            throw new IllegalStateException("No hay cupo suficiente para el periodo solicitado");
        }

        for (LocalDate dia : periodo.fechas()) {
            cupoPorDia.get(dia).decrementarCupo();
        }
    }

    public void incrementarCupo(Periodo periodo) {
        for (LocalDate dia : periodo.fechas()) {
            Cupo cupo = cupoPorDia.get(dia);
            if (cupo != null) {
                cupo.incrementarCupo();
            }
        }
    }

    public Map<LocalDate, Cupo> getCuposPorDia(Periodo periodo) {
        Map<LocalDate, Cupo> resultado = new HashMap<>();
        for (LocalDate dia : periodo.fechas()) {
            if (this.cupoPorDia.containsKey(dia)) {
                resultado.put(dia, this.cupoPorDia.get(dia));
            }
        }
        return Collections.unmodifiableMap(resultado);
    }

    public Map<LocalDate, Cupo> getCuposPorDia() {
        return Collections.unmodifiableMap(this.cupoPorDia);
    }

    public Cupo getCupoDelDia(LocalDate dia) {
        return cupoPorDia.getOrDefault(dia, new Cupo(0, 0));
    }

    public Periodo getPeriodoVigencia() { return  periodoVigencia; }

    public Cupo getCupoEn(Periodo periodo) {
        for (LocalDate dia : periodo.fechas()) {
            Cupo c = cupoPorDia.get(dia);
            return c;
        }
        return new Cupo(0,0);
    }

    public int getCupoPorDiaInicial() {
        return  cupoPorDiaInicial;
    }
}
