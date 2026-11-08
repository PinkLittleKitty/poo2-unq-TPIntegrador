package org.viajesOnline.reservas.estados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class Finalizada implements EstadoReserva {
    public Finalizada() { }

    @Override
    public void notificar(ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaFinalizada(reserva);
    }
}
