package org.viajesOnline.reservas.estados;

public class EnCheckin extends EstadoReserva {
    @Override
    public EstadoReserva siguiente() {
        return new EnCurso();
    }
}
