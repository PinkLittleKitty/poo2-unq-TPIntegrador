package org.viajesOnline.traslados;

public class AdaptadorCostoTrasladoStandard implements CostoTraslado {

    private PlataformaTransporte plataforma; //costo estandar

    @Override
    public double calcularCosto(ContextoTrasladoAeropuertoHotel c) {

        //interpreto que plataforma la conoce desde siempre
        return plataforma.estimarTraslado(c.cantPasajeros(), c.direccionDestino());
    }

}
