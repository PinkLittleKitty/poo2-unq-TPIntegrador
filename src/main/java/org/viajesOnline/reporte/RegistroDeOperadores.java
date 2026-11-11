package org.viajesOnline.reporte;

import org.viajesOnline.Catalogo;
import org.viajesOnline.ServicioIndividual;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class RegistroDeOperadores implements Reporte {

    private List<ServicioIndividual> catalogo;

    public RegistroDeOperadores(List<ServicioIndividual> catalogo){

        this.catalogo = catalogo;
    }

    @Override
    public void accept(FormatoVisitanteReportes v) { v.visitar(this); }

    public Map<String, Double> calcularDatos() {
        double total = catalogo.size();

        return catalogo.stream()
                .collect(Collectors.groupingBy(
                        ServicioIndividual::proveedor,
                        Collectors.collectingAndThen(
                                Collectors.counting(),
                                count -> (count * 100.0) / total
                        )
                ));
    }

    public boolean hayRiesgoDeConcentracion(double porcentajeMaximo) {
        return this.calcularDatos().values().stream().anyMatch(porcentaje -> porcentaje >= porcentajeMaximo);
    }
}

