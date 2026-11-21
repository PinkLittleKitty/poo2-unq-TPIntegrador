package org.viajesOnline.reservas.estados;

import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class Finalizada extends EstadoReserva {
    public Finalizada() { }

    @Override
    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {
        observador.reservaFinalizada(gestor, reserva);
    }
}
