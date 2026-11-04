package org.viajesOnline.reservas;

import org.viajesOnline.reservas.estados.EstadoReserva;

public interface ObservadorCambioEstado {
    public void cambioDeEstado(ContextoReserva reserva, EstadoReserva anterior, EstadoReserva nuevo);
}
