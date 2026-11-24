package org.viajesOnline.reporte;

import org.viajesOnline.items.Item;
import org.viajesOnline.utils.Periodo;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ServicioMasReservado implements Reporte {

    private Periodo periodo;
    private GestorReservas reservas = GestorReservas.getInstance();

    public ServicioMasReservado(Periodo periodo) {
        this.periodo = periodo;
//        this.reservas = reservas;
    }

    @Override
    public void accept(FormatoVisitanteReportes v){ v.visitar(this); }

    public Map<Item, Long> calcularDatos() {
        return reservas.getReservas().stream()
                .filter(r -> r.getFechaCompra() != null && periodo.incluye(r.getFechaCompra())) //r.fueCompradaEn(periodo)
                .flatMap(r -> r.getDetalle().getServicios().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())); //TODO: estamos incluyendo el precio promedio?
    }
}


