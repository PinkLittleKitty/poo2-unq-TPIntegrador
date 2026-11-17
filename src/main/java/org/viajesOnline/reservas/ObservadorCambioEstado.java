package org.viajesOnline.reservas;

import org.viajesOnline.documento.GestorDeDocumentos;

public abstract class ObservadorCambioEstado {

    public void reservaPendiente(ContextoReserva reserva) { }

    public void reservaConfirmada(ContextoReserva reserva) { }

    public void reservaEnCheckin(ContextoReserva reserva) { }

    public void reservaEnCurso(ContextoReserva reserva) { }

    public void reservaFinalizada(GestorDeDocumentos gestor, ContextoReserva reserva) { }

    public void reservaCancelada(ContextoReserva reserva) { }
}
