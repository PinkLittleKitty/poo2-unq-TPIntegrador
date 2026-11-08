package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class Cancelada implements EstadoReserva {
    public Cancelada() { }

    @Override
    public void notificar(GestorDeDocumentos gestor, ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaCancelada(reserva);
    }
}
