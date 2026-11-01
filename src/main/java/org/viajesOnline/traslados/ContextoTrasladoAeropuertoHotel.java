package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;

public class ContextoTrasladoAeropuertoHotel extends ContextoReserva {

    private Direccion direccionDeDestino;
    private int pasajeros;
                                            //String
    public ContextoTrasladoAeropuertoHotel(Direccion direccionDeDestino, int pasajeros){

        this.direccionDeDestino = direccionDeDestino;
        this.pasajeros = pasajeros;
    }

    public double costoParaTipoDeTraslado(CostoTraslado t){

        return t.calcularCosto(this);
    }

    public int cantPasajeros(){return this.pasajeros;}

    public String direccionDestino(){return this.direccionDeDestino;}
}
