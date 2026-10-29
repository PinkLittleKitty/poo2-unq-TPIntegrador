package org.viajesOnline.reservas.estados;

import org.viajesOnline.Catalogo;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;

public interface EstadoReserva {
    public abstract void siguiente(ContextoReserva r);

    public default void cancelar(ContextoReserva r) {
        r.setEstadoActual(new Cancelada());
    }

    public default void agregarServicio(Catalogo servicio, Solicitud solicitud) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public default decrementarCupo() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
