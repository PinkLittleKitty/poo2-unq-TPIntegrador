package org.viajesOnline.reporte;

import org.viajesOnline.items.ServicioIndividual;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.utils.Periodo;

import java.util.List;
import java.util.Objects;

public class GeneradorDeReportes {
    private final List<Reserva> reservas;

    public GeneradorDeReportes(List<Reserva> reservas) {
        this.reservas = List.copyOf(reservas);
    }

    public ServicioMasReservado serviciosMasReservados(Periodo periodo) {
        return new ServicioMasReservado(periodo, reservas);
    }

    public RegistroDeCupos registroDeCupos(Periodo periodo) {
        return new RegistroDeCupos(periodo);
    }

    public RegistroDeOperadores registroDeOperadores() {
        return new RegistroDeOperadores();
    }

    public String exportar(Reporte reporte, FormatoVisitanteReportes formato) {
        Objects.requireNonNull(formato, "El formato no puede ser nulo");
        reporte.accept(formato);
        return formato.obtenerReporte();
    }

}