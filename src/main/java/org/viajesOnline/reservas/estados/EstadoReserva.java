package org.viajesOnline.reservas.estados;

import org.viajesOnline.items.Item;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.DetalleReserva;
import org.viajesOnline.reservas.Reserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;

public abstract class EstadoReserva {

    public void siguiente(Reserva r) {
        throw new UnsupportedOperationException("No existe un estado siguiente.");
    }

    public void cancelar(Reserva r) {
        r.setEstadoActual(new Cancelada());
    }

    public void agregarServicio(Item servicio, DetalleReserva detalleReserva) {
        throw new UnsupportedOperationException("No se pueden agregar servicios en este estado de la reserva.");
    }

    // TODO: QUIEN LLAMA A DECREMENTAR CUPO EN NINGUN ESTADO LO VEO
    public void decrementarCupo(DetalleReserva detalleReserva) {
        throw new UnsupportedOperationException("No se puede modificar el cupo en este estado de la reserva.");
    }

    public void notificar(GestorDeDocumentos gestor, Reserva reserva, ObservadorCambioEstado observador) {}

    public double tasasReembolsables(Reserva r) {
        return 0.0;
    }
}
