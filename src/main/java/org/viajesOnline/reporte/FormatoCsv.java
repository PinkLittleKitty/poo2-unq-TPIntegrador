package org.viajesOnline.reporte;

import java.util.List;

class FormatoCsv implements FormatoVisitanteReportes {

    private String reporteCsv;

    public void visitar(ServicioMasReservado s){
        List<List<String>> filas = FormatoUtils.serviciosOrdenados(s).stream()
                .map(e -> List.of(e.getKey().getNombre(), String.valueOf(e.getValue()))).toList();
        this.reporteCsv = FormatoUtils.construirCsv(List.of("Servicio", "Reservas"), filas);
    }

    public void visitar(RegistroDeCupos r){
        List<List<String>> filas = FormatoUtils.cuposOrdenados(r).stream()
                .map(e -> List.of(
                        e.getKey().name(),
                        String.valueOf(e.getValue().getCupoInicial()),
                        String.valueOf(e.getValue().getCupoReservado()),
                        String.valueOf(e.getValue().getCupoDisponible())
                )).toList();
        this.reporteCsv = FormatoUtils.construirCsv(List.of("Categoria", "Totales", "Reservados", "Disponibles"), filas);
    }

    public void visitar(RegistroDeOperadores o){
        List<List<String>> filas = FormatoUtils.operadoresOrdenados(o).stream()
                .map(e -> List.of(e.getKey(), FormatoUtils.formatearPorcentaje(e.getValue()))).toList();
        this.reporteCsv = FormatoUtils.construirCsv(List.of("Operador", "Porcentaje"), filas);
    }

    @Override
    public String obtenerReporte(){
        return reporteCsv;
    }
}
