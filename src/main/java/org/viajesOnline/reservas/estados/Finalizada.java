package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class Finalizada implements EstadoReserva {
    public Finalizada() { }

    @Override
    public void notificar(GestorDeDocumentos gestor, ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaFinalizada(gestor, reserva);
    }
}
