package org.viajesOnline.traslados;

import org.viajesOnline.reservas.ContextoReserva;

public class AdaptadorTrasladoVip implements Traslado {

    private ContextoReserva reserva;
    private TrasladoVip trasladoVip;

    public AdaptadorTrasladoVip(TrasladoVip trasladoVip, ContextoReserva reserva){

        this.setReserva(reserva);
        this.setTrasladoVip(trasladoVip);
    }

    public void setTrasladoVip(TrasladoVip trasladoVip) {this.trasladoVip = trasladoVip;}

    public void setReserva(ContextoReserva reserva){this.reserva = reserva;}

    @Override
    public float calcularCosto(){

        return trasladoVip.calcularCosto(reserva.precioFinalReserva());
    }
}
