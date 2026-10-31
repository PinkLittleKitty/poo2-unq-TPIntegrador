package org.viajesOnline.reservas.estados;

import org.viajesOnline.reservas.ContextoReserva;

public class EnCheckin implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new EnCurso());
    }

    @Override
    public void cancelar(ContextoReserva r) {
        r.getSolicitud().reiniciarCupo();
        r.reembolsar();
        EstadoReserva.super.cancelar(r);
    }
}
