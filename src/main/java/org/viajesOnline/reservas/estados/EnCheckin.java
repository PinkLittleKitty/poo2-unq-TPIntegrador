package org.viajesOnline.reservas.estados;

import org.viajesOnline.reservas.ContextoReserva;

public class EnCheckin implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new EnCurso());
    }
}
