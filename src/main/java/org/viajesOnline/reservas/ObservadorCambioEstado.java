package org.viajesOnline.reservas;

import org.viajesOnline.documento.GestorDeDocumentos;

public abstract class ObservadorCambioEstado {

    public void reservaPendiente(Reserva reserva) { }

    public void reservaConfirmada(Reserva reserva) { }

    public void reservaEnCheckin(Reserva reserva) { }

    public void reservaEnCurso(Reserva reserva) { }

    public void reservaFinalizada(GestorDeDocumentos gestor, Reserva reserva) { }

    public void reservaCancelada(Reserva reserva) { }
}
