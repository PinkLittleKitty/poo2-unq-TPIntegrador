package org.viajesOnline.reservas.estados;

import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;

public class Confirmada implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new Confirmada());
    }

    @Override
    public void decrementarCupo(Catalogo servicio, Solicitud solicitud) {
        solicitud.decrementarCupo();
    }
}
