package org.viajesOnline.reporte;

import org.viajesOnline.catalogo.Categoria;
import org.viajesOnline.catalogo.ServicioIndividual;
import org.viajesOnline.reservas.Cupo;
import org.viajesOnline.utils.Periodo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RegistroDeCupos implements Reporte {

    private Periodo periodo;
    private List<ServicioIndividual> catalogo;

    public RegistroDeCupos(Periodo periodo, List<ServicioIndividual> catalogo) {
        this.periodo = periodo;
        this.catalogo = catalogo;
    }

    @Override
    public void accept(FormatoVisitanteReportes v) { v.visitar(this); }

    public Map<Categoria, Cupo> calcularDatos() {
        return catalogo.stream()
                .filter(s -> s.getPeriodoVigencia().seSuperponeCon(periodo))
                .collect(Collectors.groupingBy(
                        ServicioIndividual::categoria,
                        Collectors.reducing(new Cupo(0, 0), ServicioIndividual::getCupo, Cupo::sumar)));
    }
}
