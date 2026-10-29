package org.viajesOnline.reservas.estados;

public class Finalizada extends EstadoReserva {
    @Override
    public EstadoReserva siguiente() {
        throw new ;
    }

    @Override
    public EstadoReserva cancelar() {
        return super.cancelar();
    }
}
