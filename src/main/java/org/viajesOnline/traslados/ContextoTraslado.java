package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;
import org.viajesOnline.utils.Direccion;

public class ContextoTraslado {

    private Traslado tipoTraslado;

    public ContextoTraslado(Traslado tipoTraslado, int cantPasajeros){

        this.setTraslado(tipoTraslado);
    }

    public void setTraslado(Traslado tipoTraslado){this.tipoTraslado = tipoTraslado;}

    public double costoParaTipoDeTraslado(){

        //la cantidad de pasajeros y la direccion salen de la reserva
        return tipoTraslado.calcularCosto();
    }

}
