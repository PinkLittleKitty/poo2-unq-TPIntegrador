package org.viajesOnline.reporte;

import org.viajesOnline.items.Item;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.utils.Periodo;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServicioMasReservado implements Reporte {

    private Periodo periodo;
    private List<Reserva> reservas;

    public ServicioMasReservado(Periodo periodo, List<Reserva> reservas) {
        this.periodo = periodo;
        this.reservas = reservas;
    }

    @Override
    public void accept(FormatoVisitanteReportes v){ v.visitar(this); }

    public Map<Item, Long> calcularDatos() {
        return reservas.stream()
                .filter(r -> r.getFechaCompra() != null && periodo.incluye(r.getFechaCompra())) //r.fueCompradaEn(periodo)
                .flatMap(r -> r.getSolicitud().getServicios().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}


