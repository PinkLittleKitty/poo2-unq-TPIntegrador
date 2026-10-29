package org.viajesOnline.reservas.estados;

public class EnCurso extends EstadoReserva {
    @Override
    public EstadoReserva siguiente() {
        return new Finalizada();
    }
}
