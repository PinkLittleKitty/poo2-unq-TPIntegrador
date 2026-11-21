package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class EnCheckin extends EstadoReserva {
    @Override
    public void siguiente(Reserva r) {
        r.setEstadoActual(new EnCurso());
    }

    @Override
    public double tasasReembolsables(Reserva r) {
        return r.getCostoTasas();
    }

    @Override
    public void cancelar(Reserva r) {
        r.getSolicitud().incrementarCupo();
        r.reembolsar();
        super.cancelar(r);
    }

    @Override
    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {
        observador.reservaEnCheckin(reserva);
    }
}
