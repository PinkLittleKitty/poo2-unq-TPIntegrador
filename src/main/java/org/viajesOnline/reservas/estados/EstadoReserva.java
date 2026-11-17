package org.viajesOnline.reservas.estados;

import org.viajesOnline.catalogo.Catalogo;
import org.viajesOnline.documento.GestorDeDocumentos;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.ObservadorCambioEstado;
import org.viajesOnline.reservas.Solicitud;

public abstract class EstadoReserva {

    public void siguiente(ContextoReserva r) {
        throw new UnsupportedOperationException("No existe un estado siguiente.");
    }

    public void cancelar(ContextoReserva r) {
        r.setEstadoActual(new Cancelada());
    }

    public void agregarServicio(Catalogo servicio, Solicitud solicitud) {
        throw new UnsupportedOperationException("No se pueden agregar servicios en este estado de la reserva.");
    }

    public void decrementarCupo(Solicitud solicitud) {
        throw new UnsupportedOperationException("No se puede modificar el cupo en este estado de la reserva.");
    }

    public void notificar(GestorDeDocumentos gestor, ContextoReserva reserva, ObservadorCambioEstado observador) {}

    public double tasasReembolsables(ContextoReserva r) {
        return 0.0;
    }
}
