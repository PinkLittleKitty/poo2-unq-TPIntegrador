package org.viajesOnline.reservas.estados;

import org.viajesOnline.Catalogo;
import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;

public class Confirmada extends SujetoEstadoAbstracto implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new EnCheckin());
    }

    @Override
    public void decrementarCupo(Solicitud solicitud) {
        solicitud.decrementarCupo();
    }

}
