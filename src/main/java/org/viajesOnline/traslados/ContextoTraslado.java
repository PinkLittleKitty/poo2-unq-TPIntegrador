package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;

public class ContextoTraslado {

    private Traslado tipoTraslado;
    private ContextoReserva reserva;
    private int cantPasajeros;

    public ContextoTraslado(Traslado tipoTraslado,int cantPasajeros,ContextoReserva reserva){

        this.setReserva(reserva);
        this.setTraslado(tipoTraslado);
        this.setCantPasajeros(cantPasajeros);
    }

    public void setCantPasajeros(int cantPasajeros) {this.cantPasajeros = cantPasajeros;}

    public void setReserva(ContextoReserva reserva){this.reserva = reserva;}

    public void setTraslado(Traslado tipoTraslado){this.tipoTraslado = tipoTraslado;}

    public double costoParaTipoDeTraslado(){

        return tipoTraslado.calcularCosto(this);
    }

    public float precioReserva(){return reserva.precioFinalReserva();}

    public int cantPasajeros(){return this.cantPasajeros;}

}
