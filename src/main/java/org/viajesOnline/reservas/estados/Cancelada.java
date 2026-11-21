package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class Cancelada extends EstadoReserva {
    public Cancelada() { }

    @Override
    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {
        observador.reservaCancelada(reserva);
    }
}
