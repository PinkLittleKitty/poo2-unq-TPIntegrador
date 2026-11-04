package org.viajesOnline.reservas.estados;

import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;

public interface EstadoReserva {

    public default void siguiente(ContextoReserva r) {
        throw new UnsupportedOperationException("No existe un estado siguiente.");
    }

    public default void cancelar(ContextoReserva r) {
        r.setEstadoActual(new Cancelada());
    }

    public default void agregarServicio(Catalogo servicio, Solicitud solicitud) {
        throw new UnsupportedOperationException("No se pueden agregar servicios en este estado de la reserva.");
    }

    public default void decrementarCupo(Solicitud solicitud) {
        throw new UnsupportedOperationException("No se puede modificar el cupo en este estado de la reserva.");
    }

}

