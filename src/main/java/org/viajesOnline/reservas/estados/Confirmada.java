package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.DetalleReserva;

public class Confirmada extends EstadoReserva {
    @Override
    public void siguiente(Reserva r) {
        r.setEstadoActual(new EnCheckin());
    }

    @Override
    public void cancelar(Reserva r) {
        r.setEstadoActual(new Cancelada());
        r.decrementarCupo();
    }
    @Override
    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {
        observador.reservaConfirmada(reserva);
    }
}
