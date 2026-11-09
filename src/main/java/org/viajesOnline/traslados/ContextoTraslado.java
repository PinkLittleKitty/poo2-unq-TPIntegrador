package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Direccion;

public class ContextoTraslado {

    private ContextoReserva reserva;
    private Traslado tipoTraslado;

    public ContextoTraslado(Traslado tipoTraslado, int cantPasajeros){

        this.setTraslado(tipoTraslado);
        this.setReserva(reserva);
    }

    public void setTraslado(Traslado tipoTraslado){this.tipoTraslado = tipoTraslado;}

    public void setReserva(ContextoReserva reserva){this.reserva = reserva;}

    public double costoParaTipoDeTraslado(){

        //la cantidad de pasajeros y la direccion salen de la reserva
        return tipoTraslado.calcularCosto(reserva.precioFinalReserva());
    }

}
