package org.viajesOnline.reporte;

import org.viajesOnline.EmpresaTurismo;
import org.viajesOnline.items.Item;
import org.viajesOnline.items.ServicioIndividual;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroDeOperadores implements Reporte {

    @Override
    public void accept(FormatoVisitanteReportes v) { v.visitar(this); }

    public Map<String, Double> calcularDatos() {
        double total = EmpresaTurismo.getItems().size();

        return EmpresaTurismo.getItems().stream()
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

