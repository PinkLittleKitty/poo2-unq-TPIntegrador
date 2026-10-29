package org.viajesOnline.reservas.estados;

import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;

public class SolicitudPendiente implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        EstadoReserva EstadoConfirmada = new Confirmada();

        r.setEstadoActual(EstadoConfirmada);
        EstadoConfirmada.decrementarCupo();
    }

    @Override
    public void agregarServicio(Catalogo servicio, Solicitud solicitud) {
        solicitud.agregarServicio(servicio);
    }
}
