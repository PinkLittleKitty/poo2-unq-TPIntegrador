package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class EnCurso extends EstadoReserva {
    @Override
    public void siguiente(Reserva r) {
        r.setEstadoActual(new Finalizada());
    }

    @Override
    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {
        observador.reservaEnCurso(reserva);
    }

    @Override
    public void cancelar(Reserva r) {
        r.reembolsar();
        super.cancelar(r);
    }
}
