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
    }

    @Override
    public void accept(FormatoVisitanteReportes v){ v.visitar(this); }

    public Map<Item, EstadisticaServicio> calcularDatos() {
        return reservas.getReservas().stream()
                .filter(r -> r.getFechaCompra() != null && periodo.incluye(r.getFechaCompra())) //r.fueCompradaEn(periodo)
                .flatMap(r -> r.getDetalle().getServicios().stream())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.collectingAndThen(
                        Collectors.toList(),
                        items -> new EstadisticaServicio(items.size(),
                                items.stream().mapToDouble(Item::precioFinal).average().orElse(0.0))
                )));
    }
}


