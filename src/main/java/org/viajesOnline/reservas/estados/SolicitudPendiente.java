package org.viajesOnline.reservas.estados;

import org.viajesOnline.Catalogo;
import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.reservas.Solicitud;

public class SolicitudPendiente extends SujetoEstadoAbstracto implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {

        EstadoReserva EstadoConfirmada = new Confirmada();

        r.setEstadoActual(new Confirmada());
        EstadoConfirmada.decrementarCupo(r.getSolicitud());
    }

    @Override
    public void agregarServicio(Catalogo servicio, Solicitud solicitud) {
        solicitud.agregarServicio(servicio);
    }

}
