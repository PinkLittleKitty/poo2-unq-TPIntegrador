package org.viajesOnline.reservas.estados;

import org.viajesOnline.items.Item;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.DetalleReserva;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public class SolicitudPendiente extends EstadoReserva {
    @Override
    public void siguiente(Reserva r) {
        Confirmada confirmada = new Confirmada();
        r.setEstadoActual(confirmada);
        confirmada.decrementarCupo(r.getSolicitud());
    }

    @Override
    public void agregarServicio(Item servicio, DetalleReserva detalleReserva) {
        detalleReserva.agregarServicio(servicio);
    }

    @Override
    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {
        observador.reservaPendiente(reserva);
    }
}
