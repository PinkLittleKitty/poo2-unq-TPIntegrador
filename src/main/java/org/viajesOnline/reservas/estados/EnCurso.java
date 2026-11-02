package org.viajesOnline.reservas.estados;

import org.viajesOnline.notificacionReservas.SujetoEstadoAbstracto;
import org.viajesOnline.reservas.ContextoReserva;

public class EnCurso extends SujetoEstadoAbstracto implements EstadoReserva {
    @Override
    public void siguiente(ContextoReserva r) {
        r.setEstadoActual(new Finalizada());
    }

}
