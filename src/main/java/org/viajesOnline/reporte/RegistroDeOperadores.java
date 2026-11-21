package org.viajesOnline.reporte;

import org.viajesOnline.items.Item;

import java.util.Map;
import java.util.stream.Collectors;

public class RegistroDeOperadores implements Reporte {

    private Catalogo catalogo;

    public RegistroDeOperadores(){
        this.catalogo = Catalogo.getInstance();
    }

    @Override
    public void accept(FormatoVisitanteReportes v) { v.visitar(this); }

    public Map<String, Double> calcularDatos() {
        double total = catalogo.getItems().size();

        return catalogo.getItems().stream()
                .collect(Collectors.groupingBy(
                        Item::proveedor,
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

