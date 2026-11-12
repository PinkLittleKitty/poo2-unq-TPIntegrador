package org.viajesOnline.reporte;

import java.util.Objects;

public interface Reporte {

    public void accept(FormatoVisitanteReportes v);

    default String exportar(FormatoVisitanteReportes formato) {
        Objects.requireNonNull(formato, "El formato no puede ser nulo");
        this.accept(formato);
        return formato.obtenerReporte();
    }
}




