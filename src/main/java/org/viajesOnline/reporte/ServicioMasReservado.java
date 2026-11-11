package org.viajesOnline.reporte;

import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Periodo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class ServicioMasReservado implements Reporte {

    private Periodo periodo;
    private List<ContextoReserva> reservas;

    public ServicioMasReservado(Periodo periodo, List<ContextoReserva> reservas) {
        this.periodo = periodo;
        this.reservas = reservas;
    }

    @Override
    public void accept(FormatoVisitanteReportes v){ v.visitar(this); }

    public Map<Catalogo, Long> calcularDatos() {
        return reservas.stream()
                .filter(r -> r.getFechaCompra() != null && periodo.incluye(r.getFechaCompra())) //r.fueCompradaEn(periodo)
                .flatMap(r -> r.getSolicitud().getServicios().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}


