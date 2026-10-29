package org.viajesOnline.reservas.estados;

public class Confirmada extends EstadoReserva {
    @Override
    public EstadoReserva siguiente() {
        return new EnCheckin();
    }
}
