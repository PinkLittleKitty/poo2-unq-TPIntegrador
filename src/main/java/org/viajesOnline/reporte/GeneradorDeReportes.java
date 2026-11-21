package org.viajesOnline.reporte;

import org.viajesOnline.catalogo.ServicioIndividual;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.utils.Periodo;

import java.util.List;
import java.util.Objects;

public class GeneradorDeReportes {
    private final List<ServicioIndividual> servicios;
    private final List<Reserva> reservas;

    public GeneradorDeReportes(List<ServicioIndividual> servicios, List<Reserva> reservas) {
        this.servicios = List.copyOf(servicios);
        this.reservas = List.copyOf(reservas);
    }

    public ServicioMasReservado serviciosMasReservados(Periodo periodo) {
        return new ServicioMasReservado(periodo, reservas);
    }

    public RegistroDeCupos registroDeCupos(Periodo periodo) {
        return new RegistroDeCupos(periodo, servicios);
    }

    public RegistroDeOperadores registroDeOperadores() {
        return new RegistroDeOperadores(servicios);
    }

    public String exportar(Reporte reporte, FormatoVisitanteReportes formato) {
        Objects.requireNonNull(formato, "El formato no puede ser nulo");
        reporte.accept(formato);
        return formato.obtenerReporte();
    }

}