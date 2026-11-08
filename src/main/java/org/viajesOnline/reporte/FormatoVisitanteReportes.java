package org.viajesOnline.reporte;

public interface FormatoVisitanteReportes {

    public void visitar(ServicioMasReservado s);
    public void visitar(RegistroDeCupos r);
    public void visitar(RegistroDeOperadores o);
    public String obtenerReporte();
}
