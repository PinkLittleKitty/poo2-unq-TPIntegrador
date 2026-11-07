package org.viajesOnline.reservas.estados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class Cancelada implements EstadoReserva {
    public Cancelada() { }

    @Override
    public void notificar(ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaCancelada(reserva);
    }
}
