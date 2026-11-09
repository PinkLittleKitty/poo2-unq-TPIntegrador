package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Direccion;

public class ContextoTraslado {

    private ContextoReserva reserva;
    private Traslado tipoTraslado;

    public ContextoTraslado(ContextoReserva reserva, Traslado tipoTraslado){

        this.setTraslado(tipoTraslado);
        this.setReserva(reserva);
    }

    public void setTraslado(Traslado tipoTraslado){this.tipoTraslado = tipoTraslado;}

    public void setReserva(ContextoReserva reserva){this.reserva = reserva;}

    public double costoParaTipoDeTraslado(){

        //la cantidad de pasajeros y la direccion salen de la reserva
        return tipoTraslado.calcularCosto(this);
    }

    //public int cantPasajeros(){return this.pasajeros;}

    //public Direccion direccionDestino(){return this.direccionDeDestino;}

}
