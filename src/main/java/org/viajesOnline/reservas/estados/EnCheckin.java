package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class EnCheckin implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new EnCurso());
    }

    @Override
    public double tasasReembolsables(ContextoReserva r) {
        return r.getCostoTasas();
    }

    @Override
    public void cancelar(ContextoReserva r) {
        r.getSolicitud().incrementarCupo();
        r.reembolsar();
        EstadoReserva.super.cancelar(r);
    }

    @Override
    public void notificar(GestorDeDocumentos gestor, ContextoReserva reserva, ObservadorCambioEstado observador) {
        observador.reservaEnCheckin(reserva);
    }
}
