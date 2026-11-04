package org.viajesOnline.reservas.estados;

import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.reservas.ContextoReserva;

public class EnCheckin extends SujetoEstadoAbstracto implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new EnCurso());
    }

    @Override
    public void cancelar(ContextoReserva r) {
        r.getSolicitud().incrementarCupo();
        r.reembolsar();
        EstadoReserva.super.cancelar(r);
    }

}
