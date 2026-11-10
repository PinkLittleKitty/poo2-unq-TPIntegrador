package org.viajesOnline.reporte;

import org.viajesOnline.Catalogo;
import org.viajesOnline.ServicioIndividual;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class RegistroDeOperadores {

    private List<ServicioIndividual> catalogo;

    public RegistroDeOperadores(List<ServicioIndividual> catalogo){

        this.catalogo = catalogo;
    }

    public void accept(FormatoVisitanteReportes v){v.visitar(this);}

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

    /* public Map<String, Double> ordenarDatos() {
        Map<String, Double> porcentajes = this.calcularDatos();

        return porcentajes.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }*/

    public boolean hayRiesgoDeConcentracion(double porcentajeMaximo) {
        return this.calcularDatos().values().stream().anyMatch(porcentaje -> porcentaje >= porcentajeMaximo);
    }
}

