package org.viajesOnline.reporte;

import org.viajesOnline.items.Categoria;
import org.viajesOnline.items.Item;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.util.Map;
import java.util.stream.Collectors;

public class RegistroDeCupos implements Reporte {

    private Periodo periodo;
    private Catalogo catalogo = Catalogo.getInstance();

    public RegistroDeCupos(Periodo periodo) {
        this.periodo = periodo;
    }

    @Override
    public void accept(FormatoVisitanteReportes v) { v.visitar(this); }

    public Map<Categoria, Cupo> calcularDatos() {
        return catalogo.getItems().stream()
                .filter(s -> s.getPeriodoVigencia().seSuperponeCon(periodo))
                .collect(Collectors.groupingBy(
                        Item::categoria,
                        Collectors.reducing(new Cupo(0, 0), Item::getCupo, Cupo::sumarCupos)));
    }
}
