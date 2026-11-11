package org.viajesOnline.reporte;


import org.viajesOnline.reservas.Cupo;

import java.util.List;

class FormatoTextoPlano implements FormatoVisitanteReportes {

    private String reporteTextoPlano;

    @Override
    public void visitar(ServicioMasReservado s) {
        List<String> lineas = FormatoUtils.serviciosOrdenados(s).stream()
                .map(e -> "- " + e.getKey().getNombre() + ": " + e.getValue() + " reservas").toList();
        this.reporteTextoPlano = FormatoUtils.construirTextoPlano("Servicios más reservados", lineas);
    }

    @Override
    public void visitar(RegistroDeCupos r) {
        List<String> lineas = FormatoUtils.cuposOrdenados(r).stream()
                .map(e -> {
                    Cupo cupo = e.getValue();
                    return "Categoría: " + e.getKey().name()
                            + " | Totales: " + cupo.getCupoInicial()
                            + " | Reservados: " + cupo.getCupoReservado()
                            + " | Disponibles: " + cupo.getCupoDisponible();
                }).toList();
    }

    @Override
    public void visitar(RegistroDeOperadores o) {
        List<String> lineas = FormatoUtils.operadoresOrdenados(o).stream()
                .map(e -> "- " + e.getKey() + ": " + FormatoUtils.formatearPorcentaje(e.getValue()) + "%").toList();
        this.reporteTextoPlano = FormatoUtils.construirTextoPlano("Registro de operadores", lineas);
    }

    @Override
    public String obtenerReporte() {
        return reporteTextoPlano;
    }
}
