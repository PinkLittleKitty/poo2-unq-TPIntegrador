package org.viajesOnline.reservas.estados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class EnCurso implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new Finalizada());
    }

    @Override
    public void notificar(ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaEnCurso(reserva);
    }
}
