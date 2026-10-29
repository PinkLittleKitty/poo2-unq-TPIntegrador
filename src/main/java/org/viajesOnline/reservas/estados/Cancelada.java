package org.viajesOnline.reservas.estados;

public class Cancelada extends EstadoReserva {
    @Override
    public EstadoReserva siguiente() {
        return super.siguiente();
    }

    @Override
    public EstadoReserva cancelar() {
        return super.cancelar();
    }
}
