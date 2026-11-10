package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class EnCurso implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new Finalizada());
    }

    @Override
    public void notificar(GestorDeDocumentos gestor, ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaEnCurso(reserva);
    }

    @Override
    public void cancelar(ContextoReserva r) {
        r.reembolsar();
        EstadoReserva.super.cancelar(r);
    }
}
