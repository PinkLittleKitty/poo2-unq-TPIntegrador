package org.viajesOnline.reporte;

import org.viajesOnline.ServicioIndividual;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Periodo;

import java.util.List;

public class GeneradorDeReportes {
    private final List<ServicioIndividual> servicios;
    private final List<ContextoReserva> reservas;

    public GeneradorDeReportes(List<ServicioIndividual> servicios, List<ContextoReserva> reservas) {
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
        return reporte.exportar(formato);
    }

}