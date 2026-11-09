package org.viajesOnline.traslados;


public class AdaptadorTrasladoVip implements Traslado {

    private TrasladoVip trasladoVip;

    public AdaptadorTrasladoVip(TrasladoVip trasladoVip){

        this.setTrasladoVip(trasladoVip);
    }

    public TrasladoVip trasladoVip(){return trasladoVip();}

    public void setTrasladoVip(TrasladoVip trasladoVip) {this.trasladoVip = trasladoVip;}

    @Override
    public float calcularCosto(ContextoTraslado c){

        return trasladoVip.calcularCosto(c.precioReserva());
    }
}
