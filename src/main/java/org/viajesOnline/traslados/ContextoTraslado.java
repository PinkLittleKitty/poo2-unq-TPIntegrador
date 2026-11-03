package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Direccion;

public class ContextoTraslado extends ContextoReserva {

    private Direccion direccionDeDestino;
    private int pasajeros;
                                            //String
    public ContextoTraslado(Direccion direccionDeDestino, int pasajeros){
        this.direccionDeDestino = direccionDeDestino;
        this.pasajeros = pasajeros;
    }

    public double costoParaTipoDeTraslado(CostoTraslado t){

        return t.calcularCosto(this);
    }

    public int cantPasajeros(){return this.pasajeros;}

    public Direccion direccionDestino(){return this.direccionDeDestino;}
}
