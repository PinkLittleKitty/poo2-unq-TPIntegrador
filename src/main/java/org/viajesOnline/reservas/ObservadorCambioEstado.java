package org.viajesOnline.reservas;

import org.viajesOnline.documento.GestorDeDocumentos;

public interface ObservadorCambioEstado {

    default void reservaPendiente(ContextoReserva reserva) {
    }

    default void reservaConfirmada(ContextoReserva reserva) {
    }

    default void reservaEnCheckin(ContextoReserva reserva) {
    }

    default void reservaEnCurso(ContextoReserva reserva) {
    }

    default void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) {
    }

    default void reservaCancelada(ContextoReserva reserva) {
    }
}
