package org.viajesOnline.reporte;

import java.util.List;

class FormatoHtml implements FormatoVisitanteReportes {

    private String reporteHtml;

    @Override
    public void visitar(ServicioMasReservado s){
        List<List<String>> filas = FormatoUtils.serviciosOrdenados(s).stream()
                .map(e -> List.of(e.getKey().getNombre(), String.valueOf(e.getValue()))).toList();
        this.reporteHtml = FormatoUtils.construirTablaHtml(
                "Servicios mas reservados",
                List.of("Servicio", "Reservas"), filas);
    }

    @Override
    public void visitar(RegistroDeCupos r){
        List<List<String>> filas = FormatoUtils.cuposOrdenados(r).stream()
                .map(e -> List.of(
                        e.getKey().name(),
                        String.valueOf(e.getValue().getCupoInicial()),
                        String.valueOf(e.getValue().getCupoReservado()),
                        String.valueOf(e.getValue().getCupoDisponible())
                )).toList();
        this.reporteHtml = FormatoUtils.construirTablaHtml(
                "Registro de cupos",
                List.of("Categoria", "Totales", "Reservados", "Disponibles"), filas);
    }

    @Override
    public void visitar(RegistroDeOperadores o){
        List<List<String>> filas = FormatoUtils.operadoresOrdenados(o).stream()
                .map(e -> List.of(e.getKey(), FormatoUtils.formatearPorcentaje(e.getValue()) + "%")).toList();
        this.reporteHtml = FormatoUtils.construirTablaHtml(
                "Registro de operadores",
                List.of("Operador", "Porcentaje"), filas);
    }

    @Override
    public String obtenerReporte(){
        return reporteHtml;
    }
}

